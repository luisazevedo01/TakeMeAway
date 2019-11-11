package org.academiadecodigo.thunderstructs.connections;

import org.academiadecodigo.thunderstructs.menu.Display;
import org.academiadecodigo.thunderstructs.menu.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client extends User {

    private BufferedReader in;

    private Display display;

    public Client(String hostName, int portNumber) {
        super(hostName, portNumber);
        this.display = new Display();
        start();

    }

    @Override
    public void start() {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new MultiThread());
    }

    private void writeRequest() throws IOException {
        out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Client information : " + display.getMsg());

    }

    @Override
    protected void read() throws IOException {

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        message = in.readLine();

        System.out.println(message);

        in.close();

        System.out.println();
    }

    private class MultiThread implements Runnable {

        @Override
        public void run() {

            try {
                socket = new Socket(hostName, portNumber);

                display.startClientMenu();
                writeRequest();
                read();
                System.out.println(Messages.GOOD_BYE_MESSAGE);
                socket.close();


            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}



