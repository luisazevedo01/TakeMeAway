package org.academiadecodigo.thunderstructs;

import org.academiadecodigo.thunderstructs.menu.Display;

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

    public Client(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
        this.display = new Display();
    }


    public void start() {
        setDisplay();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new MultiThread());
        try {
            socket = new Socket(hostName, portNumber);
            while(!socket.isClosed()){
            write();
            read();
            }

        } catch (IOException e) {
            e.getMessage();
        }

    }

    private void read() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while (!socket.isClosed()) {
            in.readLine();
        }

    }

    private void write() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(display.getMsg());
    }

    public void setDisplay(){
        display.welcome();
        display.startMenu();

    }

    private class MultiThread implements Runnable {

        @Override
        public void run() {

        }
    }
}