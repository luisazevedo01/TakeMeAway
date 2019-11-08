package org.academiadecodigo.thunderstructs.server;

import java.io.IOException;

public class ServerLauncher {
    private static final int port = 8080;

    public static void main(String[] args) {

        try {
            Server server = new Server( args.length > 0 ? Integer.valueOf(args[0]) : port);
            server.start();

        } catch (IOException e) {
            System.err.println("Server socket error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.err.println("Port not valid: " + args[0]);
        }
    }
}
