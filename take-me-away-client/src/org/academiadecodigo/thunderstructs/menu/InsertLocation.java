package org.academiadecodigo.thunderstructs.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class InsertLocation implements MenuOperation {

   private String option;

    @Override
    public void execute(Prompt prompt) {

        String[] options = new String[] {Messages.INSERT_INTERIOR , Messages.INSERT_COAST};

        MenuInputScanner locationOption = new MenuInputScanner(options);
        locationOption.setMessage(Messages.LOCATION_QUESTION);

        int answer = prompt.getUserInput(locationOption);
        option = options[answer - 1];

    }

    public String getOption() {
        return option;
    }
}
