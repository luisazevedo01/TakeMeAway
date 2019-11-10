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
    private String response;
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
                listenToManager(in);
                broadcastFinalResponse();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader openStreams() throws IOException {
        out = new PrintWriter(userSocket.getOutputStream(), true);
        return new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
    }

    public void listen(BufferedReader in) throws IOException {

        request = in.readLine();

        if (request.split(" ")[0].equals("Client")) {
            status = "Tourist";
            System.out.println(request);
            return;
        }
        status = "Manager";
    }


    public void listenToManager(BufferedReader in) throws IOException {
        response = in.readLine();
        System.out.println(response);
    }

    public void broadcastFinalResponse() {

        for (Socket socket : server.getConnections()) {
            try {

                String clientKey = response.split("-")[0];
                Socket key = server.getClientConnections().get(Integer.parseInt(clientKey));

                if (socket.equals(key)) {
                    out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(response);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveTouristRequest() {

        if (request.split(" ")[0].equals("Client")) {
            server.getRequests().add(request);
        }
    }

    public void sendTouristRequestToManager() {

        int counter = 0;
        while (userSocket.isBound() && counter < 5) {

            if (server.getRequests().isEmpty()) {
                return;
            }

            if (status.equals("Manager")) {

                int req = server.getRequests().size();

                if (req <= 5) {

                    for (int i = 0; i < req; i++) {

                        out.println(server.getRequests().get(i));
                        counter++;
                        if (counter == 4) {
                            return;
                        }

                    }
                    int remain = 5 - req;

                    for (int i = 0; i < remain; i++) {
                        out.println(" ");
                    }
                }

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
