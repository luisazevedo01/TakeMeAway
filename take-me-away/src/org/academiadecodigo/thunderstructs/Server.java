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

    private LinkedList<String> requests;
    //private LinkedList<Socket> clientConnects;
    private Map<Integer, Socket> clientConnections;
    private Map<Integer, Socket> managerConnections;
    private List<Socket> connections;

    private boolean quit;
    private int clientConnectionNumber;
    private int managerConnectionNumber;


    public Server(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        service = Executors.newCachedThreadPool();
        requests = new LinkedList<>();
        //clientConnects = new LinkedList<>();
        clientConnections = new HashMap<>();
        managerConnections = new HashMap<>();
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

        if (!(userConnection.getStatus().equals("Manager"))) {
            System.out.println("New client connection: " + userConnection.getUserSocket() + "\n" + "Connection: " + clientConnectionNumber);
            clientConnections.put(clientConnectionNumber, userConnection.getUserSocket());
            System.out.println("Client connections: " + clientConnections.entrySet() + "\n");
            //clientConnects.add(userConnection.getUserSocket());
            connections.add(userConnection.getUserSocket());
            //System.out.println("Client connection: " + clientConnects.size() + "\n");
            clientConnectionNumber++;
            return;
        }
        managerConnectionNumber++;
        System.out.println("New manager connection: " + userConnection.getUserSocket() + "\n" + "Connection: " + managerConnectionNumber);
        managerConnections.put(managerConnectionNumber, userConnection.getUserSocket());
        System.out.println("Manager connections :" + managerConnections.entrySet() + "\n");
    }

    public LinkedList<String> getRequests() {
        return requests;
    }

    public List<Socket> getConnections() {
        return connections;
    }

    public Map<Integer, Socket> getClientConnections() {
        return clientConnections;
    }
}

