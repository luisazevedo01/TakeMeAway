package org.academiadecodigo.thunderstructs.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.thunderstructs.Messages;

import java.util.HashSet;
import java.util.Set;

public class Display {


    private Prompt prompt;
    private static String msg;
    private String clientName;
    private String climate;
    private String location;
    private boolean quit;
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

        while (!quit) {
            int menuAnswer = prompt.getUserInput(menu);
            menuOptions(menuAnswer);
        }
        System.out.println(Messages.WAITING_FOR_RESPONSE);
    }

    public void menuOptions(int menuAnswer) {

        switch (menuAnswer) {
            case 1:
                budgetChoice();
                break;
            case 2:
                climateChoice();
                break;
            case 3:
                locationChoice();
                break;
            case 4:
                takeMeAway();
                break;
            default:
                System.out.println(Messages.INVALID_OPTION);

        }

    }

    private void takeMeAway() {
        if (finalCheck()) {
            msg = budget + ", " + climate + ", " + location;
            TakeMeAway takeMeAway = new TakeMeAway();
            takeMeAway.execute(prompt);
            quit = takeMeAway.checked();
        } else {

            System.out.println("You are missing some information...");
        }
    }
    private void budgetChoice() {
        InsertBudget insertBudget = new InsertBudget();
        insertBudget.execute(prompt);
        budget = insertBudget.getBudget();
        System.out.println(budget + "$");

    }

    private void climateChoice() {
        InsertClimate insertClimate = new InsertClimate();
        insertClimate.execute(prompt);
        climate = insertClimate.getOption();
        System.out.println("Climate: " + climate);

    }

    private void locationChoice() {
        InsertLocation insertLocation = new InsertLocation();
        insertLocation.execute(prompt);
        location = insertLocation.getOption();
        System.out.println("Location: " + location);

    }
    private boolean finalCheck() {
        return (!((budget == 0) || (climate == null) || (location == null)));
    }

    public static String getMsg() {
        return msg;
    }

}
