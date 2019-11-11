package org.academiadecodigo.thunderstructs.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

public class Display {


    private Prompt prompt;
    private static String msg;
    private String clientName;
    private String climate;
    private String location;
    private boolean quit;
    private int budget;
    private int status;


    public Display() {
        prompt = new Prompt(System.in, System.out);

    }

    public void welcome() {

        System.out.println(Messages.WELCOME);
        System.out.println(Messages.BAR);
        StringInputScanner name = new StringInputScanner();
        name.setMessage(Messages.INTRODUCTION);
        clientName = prompt.getUserInput(name);

        System.out.println(Messages.WELCOME_MSG + clientName + "!");
    }


    public void startClientMenu() {
        String[] options = new String[]{Messages.INSERT_BUDGET, Messages.INSERT_CLIMATE, Messages.INSERT_LOCATION, Messages.INSERT_TAKEMEAWAY};
        MenuInputScanner menu = new MenuInputScanner(options);
        menu.setMessage(Messages.MENU_INTRO);

        while (!quit) {
            int menuAnswer = prompt.getUserInput(menu);
            menuClientOptions(menuAnswer);
        }
        System.out.println(Messages.WAITING_FOR_RESPONSE);
    }

    public void menuClientOptions(int menuAnswer) {

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
            msg ="A budget of: " + budget + "$, A climate: " + climate + ", In: " + location + Messages.INFORMATION_CONFIRM;
            TakeMeAway takeMeAway = new TakeMeAway();
            takeMeAway.execute(prompt);
            quit = takeMeAway.checked();
        } else {

            System.out.println(Messages.MISSING_INFORMATION);
        }
    }
    public void checkStatus(){
        String[] options = {"Tourist" , "Trip Advisor"};
        MenuInputScanner statusQuestion = new MenuInputScanner(options);
        statusQuestion.setMessage(Messages.STATUS_QUESTION);

        status = prompt.getUserInput(statusQuestion);

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

    public boolean getStatus() {
        if(status == 1){
        return true;
        }
        return false;
    }

    public String getClientName() {
        return clientName;
    }


    public Prompt getPrompt() {
        return prompt;
    }
}
