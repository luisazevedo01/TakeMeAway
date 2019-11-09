package org.academiadecodigo.thunderstructs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ClientConnection implements Runnable {

    private Socket clientSocket;
    private Server server;
    private String request;

    private PrintWriter out;
    private String statusNumber;

    public ClientConnection(Socket clientSocket, Server server) {

        this.clientSocket = clientSocket;
        this.server = server;

    }


    @Override
    public void run() {

        try {
            BufferedReader in = openStreams();

            while (!clientSocket.isClosed()) {

                listen(in);
                saveRequest();
                send();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader openStreams() throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void listen(BufferedReader in) throws IOException {
        statusNumber = in.readLine();
        request = in.readLine();

        if (statusNumber.equals("2")) {
            System.out.println(statusNumber);
            return;
        }
        if (request == null) {
            return;
        }
        System.out.println(statusNumber);
        System.out.println(request);
    }


    public void saveRequest() {
        if (request.split(" ")[2].equals("null")) {
            return;
        }
        server.getRequests().add(request);
    }

    public void send() {
        while (clientSocket.isBound()) {

            if(server.getRequests().isEmpty()){
                return;
            }

            if (statusNumber.equals("2")) {
                out.println(server.getRequests().getLast());
                server.getRequests().removeLast();
           }
        }
    }

    public String getName(){
        String[] parts = request.split(":");
        String namePart = parts[0];
        return namePart;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
