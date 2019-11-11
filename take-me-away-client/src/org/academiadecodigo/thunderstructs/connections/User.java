package org.academiadecodigo.thunderstructs.connections;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class User {

    protected Socket socket;
    protected String hostName;
    protected int portNumber;
    protected PrintWriter out;
    protected String message;

    public User(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;

    }


    public abstract void start();


    protected abstract void read() throws IOException;


}
