package org.academiadecodigo.thunderstructs.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final String TOURIST_NUMBER = "Tourist - ";
    private ServerSocket socket;
    private ExecutorService service;
    private final List<ClientConnection> clients;
    private ClientConnection connection;

    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
        clients = Collections.synchronizedList(new LinkedList<>());
        service = Executors.newCachedThreadPool();

    }

    public void start() {
        int connections = 1;

        while (true) {
            waitConnection(connections);
            connections++;

            // System.out.println(connections);
        }
    }

    private void waitConnection(int connections) {
        try {
            Socket clientSocket = socket.accept();

            connection = new ClientConnection(clientSocket, this, TOURIST_NUMBER + connections);
            service.submit(connection);
            clients.add(connection);
            System.out.println("New connection: " + connection.getClientSocket() + "\n " + connection.getName() + " logged in");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //System.out.println(clients);


        } catch (IOException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
        }
    }



}
