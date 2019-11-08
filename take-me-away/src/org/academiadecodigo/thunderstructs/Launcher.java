package org.academiadecodigo.thunderstructs.server;

import java.io.IOException;

public class ServerLauncher {
    private static final int port = 8080;

    public static void main(String[] args) {

        try {
            Server server = new Server( port); //args.length > 0 ? Integer.valueOf(args[0]) : port);
            server.start();

        } catch (IOException e) {
            System.err.println("Error opening server socket: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.err.println("Error port must be a valid number: " + args[0]);
        }
    }
}
