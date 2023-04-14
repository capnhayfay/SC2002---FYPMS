package cli;

/**
 * The EndProgramMenu class implements the Menu interface and provides a way to
 * display a closing message to the user when they exit the program.
 */
public class EndProgramMenu implements Menu {
    /**
     * This method displays a goodbye message to the user on the console, thanking
     * them for using FYPMS and wishing them a nice day.
     */
    public void display() {
        System.out.println();
        System.out.println("Thank you for using FYPMS. Have a nice day!");
        System.out.println();
        System.out.println("=========================================");
    }
}
