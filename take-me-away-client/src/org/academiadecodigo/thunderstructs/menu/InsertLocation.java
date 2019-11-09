package org.academiadecodigo.thunderstructs.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class InsertLocation implements MenuOperation {

   private String option;

    @Override
    public void execute(Prompt prompt) {

        String[] options = new String[] {"Interior" , "Coast"};

        MenuInputScanner locationOption = new MenuInputScanner(options);
        locationOption.setMessage(Messages.LocationQuestion);

        int answer = prompt.getUserInput(locationOption);
        option = options[answer - 1];

    }

    public String getOption() {
        return option;
    }
}
