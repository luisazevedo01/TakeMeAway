package org.academiadecodigo.thunderstructs.connections;


import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.thunderstructs.menu.Display;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Manager extends User {
    private Prompt prompt;
    private String[] options;
    private int choosenResquest;
    private Display display;
    private String response;


    public Manager(String hostName, int portNumber) {
        super(hostName, portNumber);
        display = new Display();
        prompt = new Prompt(System.in, System.out);
        options = new String[5];
        start();
    }

    private void sendResponse() throws IOException {
        ManagerResponse managerResponse = new ManagerResponse();
        managerResponse.execute(prompt);
        response = managerResponse.getManagerResponse();
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(response);
    }

    @Override
    public void start() {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new MultiThread());
    }

    public void startManagerMenu() {
        MenuInputScanner requestMenu = new MenuInputScanner(options);
        requestMenu.setMessage("Choose a request to answer:");
        choosenResquest = prompt.getUserInput(requestMenu);

    }

    private void writeRequest() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Manager online!");

    }

    @Override
    protected void read() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        String msg;
        int counter = 0;

        while (counter < 6 && ((msg = in.readLine()) != null)) {

            options[counter] = msg;
            counter++;

            if (counter == 2) {
                startManagerMenu();
                sendResponse();

            }
        }
        in.close();
    }

    private class MultiThread implements Runnable {

        @Override
        public void run() {
            try {
                socket = new Socket(hostName, portNumber);
                while (!socket.isClosed()) {
                    writeRequest();
                    read();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
