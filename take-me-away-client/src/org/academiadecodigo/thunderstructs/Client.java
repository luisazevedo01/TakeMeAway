package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.menu.Display;
import org.academiadecodigo.thunderstructs.menu.ManagerResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    private Socket socket;
    private String hostName;
    private int portNumber;
    private Display display;
    private PrintWriter out;


    public Client(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.display = new Display();

    }


    public void start() {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new MultiThread());

    }

    private void read() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        while (socket.isConnected()) {
            String message = in.readLine();

            if (message == null) {
                return;
            }

            System.out.println(message);

        }
        in.close();
    }

    private void informStatus() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(display.getStatus());

    }

    private void writeRequest() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(display.getClientName() + " : " + display.getMsg());
    }


    public void setDisplay() throws IOException {

        display.welcome();
        display.checkStatus();
        confirmUser();

    }

    public void confirmUser() throws IOException {
        if (display.getStatus() == 1) {
            display.startMenu();

        } else {

            System.out.println(Messages.ADVISOR_CONFIRMATION);
            //display.sendResponse();
            //sendResponseStream();
        }
    }

 /*   private void sendResponseStream() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println(display.getResponse());
    }*/


    private class MultiThread implements Runnable {

        @Override
        public void run() {

            try {
                socket = new Socket(hostName, portNumber);
                while (!socket.isClosed()) {

                    setDisplay();
                    informStatus();
                    writeRequest();
                    read();
                }
                socket.close();

            } catch (IOException e) {
                e.getMessage();
            } finally {

            }

        }
    }

}
