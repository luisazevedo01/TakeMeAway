package org.academiadecodigo.thunderstructs.server;

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
    private ExecutorService service;
    private  List<ClientConnection> clients;

    public Server(int port) throws IOException {
        ServerSocket = new ServerSocket(port);
        clients = Collections.synchronizedList(new LinkedList<>());
        service = Executors.newCachedThreadPool();
    }

    public void start(){
        int connections = 1;

        while (true) {
            waitConnection(connections);
            connections++;
        }
    }

    private void waitConnection(int connections) {
        try {
            Socket clientSocket = ServerSocket.accept();

            ClientConnection connection = new ClientConnection(clientSocket, this, TOURIST_NAME + connections);
            service.submit(connection);
            System.out.println("New connection: " + connection.getClientSocket() + "\n" + "Tourist: " + connections );

        } catch (IOException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
        }
    }
}
