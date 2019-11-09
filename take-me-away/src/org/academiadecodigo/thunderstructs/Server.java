package org.academiadecodigo.thunderstructs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final String TOURIST_NAME = "Tourist -";
    private ServerSocket ServerSocket;
    private Socket clientSocket;
    private ExecutorService service;
    private LinkedList<String> requests;

    //private List<ClientConnection> clients;
    private boolean quit;

    public Server(int port) throws IOException {
        ServerSocket = new ServerSocket(port);
        //clients = Collections.synchronizedList(new LinkedList<>());
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
        ClientConnection connection = new ClientConnection(clientSocket, this, TOURIST_NAME + connections);

        service.submit(connection);
        System.out.println("New connection: " + connection.getClientSocket() + "\n" + "Connection: " + connections);

    }

    public LinkedList<String> getRequests() {
        return requests;
    }
}
