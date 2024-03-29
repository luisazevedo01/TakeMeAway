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
    private String requestNumber;
    private PrintWriter out;
    private BufferedReader in;

    public UserConnection(Socket userSocket, Server server) {

        this.userSocket = userSocket;
        this.server = server;
    }


    @Override
    public void run() {

        try {
            in = openStreams();

            while (!userSocket.isClosed()) {

                listen(in);
                saveTouristRequest();
                server.addConnections();
                interaction();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void interaction() throws IOException {
        sendTouristRequestToManager();
        listenToManager(in);
        broadcastFinalResponse();
        if (!(server.getRequests().getFirst().equals(" "))) {
            interaction();
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
        requestNumber = in.readLine();
        System.out.println(requestNumber);
        response = in.readLine();
        System.err.println(response);
    }

    public void broadcastFinalResponse() {

        try {

            String clientKey = requestNumber;
            Socket targetSocket = server.getClientConnects().get(Integer.parseInt(clientKey) - 1);

            PrintWriter out = new PrintWriter(targetSocket.getOutputStream(), true);
            out.println(response);

            server.getRequests().remove((Integer.parseInt(requestNumber) - 1));
            server.getClientConnects().remove(Integer.parseInt(requestNumber) - 1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveTouristRequest() {

        if (request.split(" ")[0].equals("Client")) {
            server.getRequests().add(request);
        }
    }

    public synchronized void sendTouristRequestToManager() throws IOException {
        int counter = 0;
        int managerConnects = server.getManagerConnectionNumber();
        PrintWriter out = new PrintWriter(server.getManagerConnections().get(managerConnects - 1).getOutputStream(), true);
        while (userSocket.isBound() && counter < 10) {

            if (server.getRequests().getFirst().equals(" ")) {
                return;
            }
            if (status.equals("Manager")) {

                int req = server.getRequests().size();

                if (req <= 10) {

                    for (int i = 0; i < req; i++) {

                        out.println(server.getRequests().get(i));
                        counter++;

                    }
                    int remain = 10 - req;

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
