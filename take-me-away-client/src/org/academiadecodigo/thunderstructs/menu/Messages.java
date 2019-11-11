package org.academiadecodigo.thunderstructs.menu;

import java.sql.ResultSet;

public class Messages {


    private static final String RED = "\u001b[31m";
    private static final String YELLOW = "\u001b[33m";
    private static final String CYAN = "\u001b[36;1m";
    private static final String ORANGE = "\u001b[31;1m";
    private static final String GREEN = "\u001b[32;1m";
    private static final String BRIGHT_YELLOW = "\u001b[33;1m";
    private static final String PINK = "\u001b[35;1m";
    private static final String RESET = "\u001b[0m";
    public static final String WELCOME = YELLOW +
            "████████╗ █████╗ ██╗  ██╗███████╗███╗   ███╗███████╗ █████╗ ██╗    ██╗ █████╗ ██╗   ██╗\n" +
            "╚══██╔══╝██╔══██╗██║ ██╔╝██╔════╝████╗ ████║██╔════╝██╔══██╗██║    ██║██╔══██╗╚██╗ ██╔╝\n" +
            "   ██║   ███████║█████╔╝ █████╗  ██╔████╔██║█████╗  ███████║██║ █╗ ██║███████║ ╚████╔╝ \n" +
            "   ██║   ██╔══██║██╔═██╗ ██╔══╝  ██║╚██╔╝██║██╔══╝  ██╔══██║██║███╗██║██╔══██║  ╚██╔╝  \n" +
            "   ██║   ██║  ██║██║  ██╗███████╗██║ ╚═╝ ██║███████╗██║  ██║╚███╔███╔╝██║  ██║   ██║   \n" +
            "   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝     ╚═╝╚══════╝╚═╝  ╚═╝ ╚══╝╚══╝ ╚═╝  ╚═╝   ╚═╝   \n" +
            "                                                                                       " + RESET;
    public static final String BAR = YELLOW + "/////////////////////////////////////////////////////////////////////////////////////" + RESET + "\n";
    public static final String INTRODUCTION = CYAN + "Tell us your name: ";
    public static final String STATUS_QUESTION = CYAN + "Are you logging in as a tourist or a manager?" + GREEN;
    public static final String WELCOME_MSG = CYAN + "Welcome! Let us help you plan your trip ";
    public static final String MENU_INTRO = CYAN + "Put some information to help us choose the best trip for you!!" + GREEN;

    protected static final String INSERT_BUDGET ="Insert budget";
    protected static final String INSERT_CLIMATE = "Insert climate";
    protected static final String INSERT_LOCATION = "Insert location";
    protected static final String INSERT_TAKEMEAWAY = "TakeMeAway";

    public static final String CLIMATE_OPTIONS = CYAN + "What is the climate that you are looking for?" + GREEN;

    protected static final String INSERT_HOT = "HOT";
    protected static final String INSERT_COLD = "COLD";

    public static final String INSERT_THE_BUDGET = CYAN + "How much are you looking to spend?! ";
    public static final String INVALID_OPTION = ORANGE + "That option is invalid" + CYAN;
    public static final String INVALID_BUDGET = ORANGE + "Not a valid budget!" + "\n" + "Insert a budget between 500$ and 5000$" + CYAN;


    public static final String LOCATION_QUESTION = CYAN + "Do like Coast or Interior? " + GREEN;

    protected static final String INSERT_INTERIOR = "Interior";
    protected static final String INSERT_COAST = "Coast";

    public static final String TAKEMEAWAY_OPTIONS = CYAN + "Is this the right information?";
    public static final String INFORMATION_CONFIRM = "?" + GREEN;
    public static final String WAITING_FOR_RESPONSE = BRIGHT_YELLOW + "We are analysing your information... ";
    public static final String ADVISOR_CONFIRMATION = "Logged in as a Trip advisor, please wait for requests...";
    public static final String MISSING_INFORMATION = "You are missing some information...";
    public static final String MANAGER_RESPONSE = "Please advise the best destination: ";
    public static final String GOOD_BYE_MESSAGE = PINK + "We hope that you got the best destiny!";
}
