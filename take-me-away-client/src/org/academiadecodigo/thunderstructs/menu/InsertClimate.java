package org.academiadecodigo.thunderstructs.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class InsertClimate implements MenuOperation {

    private String option;

    @Override
    public void execute(Prompt prompt) {
        String[] options = new String[]{"HOT", "COLD"};

        MenuInputScanner menu = new MenuInputScanner(options);
        menu.setMessage(Messages.CLIMATE_OPTIONS);

        int answer = prompt.getUserInput(menu);
        option = options[answer - 1];
    }

    public String getOption() {
        return option;
    }
}
