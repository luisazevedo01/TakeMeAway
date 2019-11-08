package org.academiadecodigo.thunderstructs;

public class ClientLauncher {
    public static void main(String[] args) {
        Client client = new Client(args[0], 8080);
        client.start();
    }
}
