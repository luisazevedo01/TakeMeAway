package org.academiadecodigo.thunderstructs.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

public class ManagerResponse implements MenuOperation{

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
