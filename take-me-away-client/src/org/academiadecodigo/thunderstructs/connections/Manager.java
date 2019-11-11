package org.academiadecodigo.thunderstructs.connections;


import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.thunderstructs.menu.Display;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Manager extends User {
    private Prompt prompt;
    private String[] options;
    private String response;
    private int requestNumber;
    private BufferedReader in;


    public Manager(String hostName, int portNumber) {
        super(hostName, portNumber);
        prompt = new Prompt(System.in, System.out);
        options = new String[10];
        start();
    }

    private void sendResponse() throws IOException {
        ManagerResponse managerResponse = new ManagerResponse();
        managerResponse.execute(prompt);
        response = managerResponse.getManagerResponse();
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(requestNumber);
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
        requestNumber = prompt.getUserInput(requestMenu);

    }

    private void writeRequest() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Manager online!");

    }

    @Override
    protected void read() throws IOException {
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        String msg;
        int counter = 0;

        while (counter < 10 && socket.isBound()) {
            message = in.readLine();
            options[counter] = message;
            counter++;


        }
    }

    private class MultiThread implements Runnable {

        @Override
        public void run() {
            try {
                socket = new Socket(hostName, portNumber);
                writeRequest();
                while (!socket.isClosed()) {
                    read();
                    startManagerMenu();
                    sendResponse();

                }
                in.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();


            }

        }
    }
}
