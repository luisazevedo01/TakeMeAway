package org.academiadecodigo.thunderstructs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ExecutorService service;
    private UserConnection userConnection;

    private volatile LinkedList<String> requests;
    private LinkedList<Socket> clientConnects;
    private LinkedList<Socket> managerConnections;
    private List<Socket> connections;

    private boolean quit;
    private int clientConnectionNumber;
    private int managerConnectionNumber;


    public Server(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        service = Executors.newCachedThreadPool();
        requests = new LinkedList<>();
        clientConnects = new LinkedList<>();
        managerConnections = new LinkedList<>();
        connections = Collections.synchronizedList(new LinkedList<>());

    }

    public void start() {

        while (!quit) {
            waitConnection();
        }
    }

    private void serverBound() {

        try {
            clientSocket = serverSocket.accept();
            if (clientSocket.isClosed()) {
                quit = true;
            }
        } catch (IOException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
        }
    }

    private void waitConnection() {

        serverBound();
        userConnection = new UserConnection(clientSocket, this);

        service.submit(userConnection);

    }


    public void addConnections() {

        if (userConnection.getStatus().equals("Tourist")) {
            clientConnects.add(userConnection.getUserSocket());
            connections.add(userConnection.getUserSocket());
            System.out.println("New client connection: " + userConnection.getUserSocket() + "\n" + "Connection: " + clientConnectionNumber);
            System.out.println("Client connection: " + clientConnects.get(clientConnectionNumber) + "\n");
            clientConnectionNumber++;
            return;
        }
        managerConnections.add(userConnection.getUserSocket());
        connections.add(userConnection.getUserSocket());
        System.out.println("New manager connection: " + userConnection.getUserSocket() + "\n" + "Connection: " + managerConnectionNumber);
        System.out.println("Manager connections : " + managerConnections.get(managerConnectionNumber) + "\n");
        managerConnectionNumber++;
    }

    public LinkedList<String> getRequests() {
        return requests;
    }

    public LinkedList<Socket> getManagerConnections() {
        return managerConnections;
    }

    public LinkedList<Socket> getClientConnects() {
        return clientConnects;
    }

    public int getManagerConnectionNumber() {
        return managerConnectionNumber;
    }
}

