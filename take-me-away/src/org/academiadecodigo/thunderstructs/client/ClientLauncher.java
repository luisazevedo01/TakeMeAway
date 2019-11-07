package org.academiadecodigo.thunderstructs.client;

import java.io.IOException;

public class ClientLauncher {
    public static void main(String[] args) {

        try {
        Client client = new Client(args[0], Integer.parseInt(args[1]));

        } catch (IOException e){
            e.getMessage();
        }
    }
}
