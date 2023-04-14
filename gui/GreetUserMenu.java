package gui;

/**
 * Displayed at start of program
 */
public class GreetUserMenu implements Menu {
    /**
     * Prints welcome message
     */
    public void display() {
        System.out.println(" _______   ______  __  __ ____  ");
        System.out.println("|  ___\\ \\ / /  _ \\|  \\/  / ___|         ");
        System.out.println("| |_   \\ V /| |_) | |\\/| \\___ \\  ");
        System.out.println("|  _|   | | |  __/| |  | |___) |");
        System.out.println("|_|     |_| |_|   |_|  |_|____/  ");

        System.out.println("Welcome to Final Year Project Management System (FYPMS)");
    }

}
