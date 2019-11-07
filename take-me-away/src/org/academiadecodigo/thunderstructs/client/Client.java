package org.academiadecodigo.thunderstructs.client;

import java.io.IOException;
import java.net.Socket;

public class Client {

    private Socket socket;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }
}
