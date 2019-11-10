package org.academiadecodigo.thunderstructs.connections;

import org.academiadecodigo.thunderstructs.menu.Display;
import org.academiadecodigo.thunderstructs.menu.Messages;


public class UserFactory {

    private Display display;
    private String hostName;
    private int portNumber;

    public UserFactory(String hostName, int porNumber) {
        this.display = new Display();
        this.hostName = hostName;
        this.portNumber = porNumber;

    }

    public void setDisplay() {

        display.welcome();
        display.checkStatus();
        confirmUser();
    }

    public User confirmUser() {


        if (display.getStatus() == 1) {
            return new Client(hostName, portNumber);

        } else {

            System.out.println(Messages.ADVISOR_CONFIRMATION);
            return new Manager(hostName, portNumber);

        }
    }
}
