package org.academiadecodigo.thunderstructs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket ServerSocket;
    private Socket clientSocket;
    private ExecutorService service;
    private LinkedList<String> requests;
    private ClientConnection clientConnection;

    private boolean quit;


    public Server(int port) throws IOException {

        ServerSocket = new ServerSocket(port);
        service = Executors.newCachedThreadPool();
        requests = new LinkedList<>();

    }

    public void start() {
        int connections = 1;
        while (!quit) {
            waitConnection(connections);
            connections++;
        }
    }

    private void serverBound() {
        try {
            clientSocket = ServerSocket.accept();
            if(clientSocket.isClosed()){
                quit = true;
            }
        } catch (IOException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
        }
    }

    private void waitConnection(int connections) {

        serverBound();
        clientConnection = new ClientConnection(clientSocket, this);

        service.submit(clientConnection);

        System.out.println("New connection: " + clientConnection.getClientSocket() + "\n" + "Connection: " + connections);

        addTouristConnections();
    }


    private void addTouristConnections(){
        Map<String, Socket> clientConnections = new HashMap<>();
        clientConnections.put(clientConnection.getName(), clientConnection.getClientSocket());
        System.out.println(clientConnections.entrySet());

        for (String name : clientConnections.keySet()) {
            if(name.equals(clientConnection.getName())){
                clientConnection.send();
            }

        }
    }

    public LinkedList<String> getRequests() {
        return requests;
    }

}

