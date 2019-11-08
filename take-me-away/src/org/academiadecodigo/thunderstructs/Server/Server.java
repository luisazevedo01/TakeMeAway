package org.academiadecodigo.thunderstructs.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final String CLIENT_NAME = "Client -";
    private ServerSocket socket;
    private ExecutorService service;
    private final List<ClientConnection> clients;

    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
        clients = Collections.synchronizedList(new LinkedList<>());
        service = Executors.newCachedThreadPool();
    }

    public void start(){
        int connections = 1 ;

        while (true) {
            waitConnection(connections);
            connections++;


        }
    }

    private void waitConnection(int connections) {
        try {
            Socket clientSocket = socket.accept();

            ClientConnection connection = new ClientConnection(clientSocket, this, CLIENT_NAME + connections);
            service.submit(connection);

        } catch (IOException e) {
            System.err.println("Error establishing connection: " + e.getMessage());
        }
    }


}
