package org.academiadecodigo.thunderstructs;

public class ClientLauncher {
    public static void main(String[] args) {
        Client client = new Client(args[0], Integer.valueOf(args[1]));
        client.start();
    }
}
