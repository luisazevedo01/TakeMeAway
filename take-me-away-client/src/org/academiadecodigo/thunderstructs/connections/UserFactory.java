package org.academiadecodigo.thunderstructs.connections;

import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.thunderstructs.menu.Display;
import org.academiadecodigo.thunderstructs.menu.Messages;


public class UserFactory {

    private Display display;
    private String hostName;
    private int portNumber;
    private String pass;

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

    private void confirmManager() {
        StringInputScanner confirmation = new StringInputScanner();
        confirmation.setMessage("Do you have permission? ");
        pass = display.getPrompt().getUserInput(confirmation);
        confirmation.setError("Wrong confirmation word!!");

    }

    public User confirmUser() {

        if (display.getStatus()) {
            return new Client(hostName, portNumber);

        }
        confirmManager();

        if (!(pass.equals("bravo"))) {
            System.err.println("It seems that you don't have permission...");
            confirmManager();
        }


        System.out.println(Messages.ADVISOR_CONFIRMATION);
        return new Manager(hostName, portNumber);
    }
}

