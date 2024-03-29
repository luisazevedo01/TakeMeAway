package org.academiadecodigo.thunderstructs.connections;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.thunderstructs.menu.MenuOperation;
import org.academiadecodigo.thunderstructs.menu.Messages;

public class ManagerResponse implements MenuOperation {

    private String managerResponse;

    @Override
    public void execute(Prompt prompt) {
        StringInputScanner response = new StringInputScanner();
        response.setMessage(Messages.MANAGER_RESPONSE);
        managerResponse = prompt.getUserInput(response);
    }


    public String getManagerResponse(){
        return this.managerResponse;
    }
}
