package org.academiadecodigo.thunderstructs.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientConnection implements Runnable {

    private Socket clientSocket;
    private Server server;
    private String name;
    private String request;
    private PrintWriter out;
    private List<String> requests;


    public ClientConnection(Socket clientSocket, Server server, String name) {

        this.requests = new LinkedList<>();
        this.clientSocket = clientSocket;
        this.server = server;
        this.name = name;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = openStreams();

            while (!clientSocket.isClosed()) {
                listen(in);
                saveRequest();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BufferedReader openStreams() throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream());
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private void listen(BufferedReader in) throws IOException {


        request = in.readLine();
        System.out.println(request);


    }

    public String getName() {
        return name;
    }

    public String getRequest() {
        return request;
    }

    public void send(String message) {
        out.println(message);
    }

    private void saveRequest() {


        requests.add(request);


        // while (!requests.contains(request)) {
        // }
        System.out.println(requests);
    }
}

