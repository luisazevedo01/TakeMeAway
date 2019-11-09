package org.academiadecodigo.thunderstructs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserConnection implements Runnable {

    private Socket userSocket;
    private Server server;
    private String request;
    private String status;

    private PrintWriter out;

    public UserConnection(Socket userSocket, Server server) {

        this.userSocket = userSocket;
        this.server = server;
    }


    @Override
    public void run() {

        try {
            BufferedReader in = openStreams();

            while (!userSocket.isClosed()) {

                listen(in);
                saveTouristRequest();
                server.addConnections();
                sendTouristRequestToManager();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader openStreams() throws IOException {
        out = new PrintWriter(userSocket.getOutputStream(), true);
        return new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
    }

    //listens incoming messages from the tourist and manager. Criar dois métodos diferentes?
    public void listen(BufferedReader in) throws IOException {

        request = in.readLine();

        if (request.split(" ")[0].equals("Client")) {
            status = "Tourist";
            System.out.println(request);
            return;
        }
        status = "Manager";
    }


    public void saveTouristRequest() {

        if (request.split(" ")[0].equals("Client")) {
            server.getRequests().add(request);
        }

       /* for (String s : server.getRequests()) {
            System.out.println(s);
        }*/
    }

    public void sendTouristRequestToManager() {
        //server.getRequests().add(null);
        int counter = 0;
        while (userSocket.isBound()) {

            if (server.getRequests().isEmpty()) {
                return;
            }
            if (status.equals("Manager")) {
                out.println(server.getRequests().get(counter));
                counter++;
            }
        }
    }


    public Socket getUserSocket() {
        return userSocket;
    }

    public String getStatus() {
        return status;
    }
}