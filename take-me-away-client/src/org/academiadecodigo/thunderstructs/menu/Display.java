package org.academiadecodigo.thunderstructs.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.thunderstructs.Messages;

public class Display {

    private InsertBudget insertBudget;
    private InsertClimate insertClimate;
    private InsertLocation insertLocation;

    private Prompt prompt;

    private String clientName;
    private String climate;
    private String location;

    private int budget;


    public Display() {
        prompt = new Prompt(System.in, System.out);

    }

    public void welcome() {

        System.out.println(Messages.WELCOME);
        StringInputScanner name = new StringInputScanner();
        name.setMessage(Messages.INTRODUCTION);
        clientName = prompt.getUserInput(name);

        System.out.println(Messages.WELCOME_MSG + clientName);
    }


    public void startMenu() {
        String[] options = new String[]{"Insert Budget", "Insert Climate", "Insert Location", "TakeMeAway"};
        MenuInputScanner menu = new MenuInputScanner(options);
        menu.setMessage(Messages.MENU_INTRO);

        while (true) {
            int menuAnswer = prompt.getUserInput(menu);
            menuOptions(menuAnswer);
        }
    }

    public void menuOptions(int menuAnswer) {

        switch (menuAnswer) {
            case 1:
                insertBudget = new InsertBudget();
                insertBudget.execute(prompt);
                budget = insertBudget.getBudget();
                System.out.println(budget);
                break;
            case 2:
                insertClimate = new InsertClimate();
                insertClimate.execute(prompt);
                climate = insertClimate.getOption();
                System.out.println(climate);
                break;
            case 3:
                insertLocation = new InsertLocation();
                insertLocation.execute(prompt);
                location = insertLocation.getOption();
                System.out.println(location);
                break;
            case 4:
                if (finalCheck()) {
                    new TakeMeAway().execute(prompt);
                } else {
                    System.out.println("You are missing some information...");
                }
                break;
            default:
                System.out.println(Messages.INVALID_OPTION);

        }

    }

    public boolean finalCheck() {
        return (!((budget == 0) || (climate == null) || (location == null)));
    }

    public int getBudget() {
        return budget;
    }

    public String getClimate() {
        return climate;
    }

    public String getLocation() {
        return location;
    }
}
