package org.academiadecodigo.thunderstructs.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.thunderstructs.Messages;

public class TakeMeAway implements MenuOperation {

    private int answer;

    @Override
    public void execute(Prompt prompt) {
        String[] options = new String[]{"YES", "NO"};
        MenuInputScanner menu = new MenuInputScanner(options);
        menu.setMessage(Messages.TAKEMEAWAY_OPTIONS + "\n" + Display.getMsg());
        answer = prompt.getUserInput(menu);


    }

    public boolean checked() {
        return answer == 1;

    }
}
