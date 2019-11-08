package org.academiadecodigo.thunderstructs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientConnection implements Runnable {

    private Socket clientSocket;
    private Server server;
    private String name;
    private String request;
    private List<String> requests;
    private PrintWriter out;

    public ClientConnection(Socket clientSocket, Server server, String name) {

        this.requests = new LinkedList<>();
        this.clientSocket = clientSocket;
        this.server = server;
        this.name = name;
    }

    @Override
    public void run() {

        try {
            BufferedReader in = openStreams();

            while (!clientSocket.isClosed()){
                listen(in);
                saveRequest();
                send(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader openStreams() throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void listen(BufferedReader in) throws IOException{
        request = in.readLine();
        if(request == null){
            return;
        }
        System.out.println(request);

    }

    public void saveRequest(){
        requests.add(request);
        System.out.println(requests);
    }

    public void send(String message){
        out.println(message);
    }

    public String getName() {

        return name;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }
}
