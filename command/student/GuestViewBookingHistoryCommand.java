package command.student;

import moblima.SilverVillage;
import command.Command;
import java.util.Scanner;

/**
 * Represents a command for Guest to view their prior booking history
 */
public class GuestViewBookingHistoryCommand implements Command {
    /**
     * Requests for name from Guest to search for and prints booking history of Guest
     */
    public void execute() {
        //should search for specific customers
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter your Name: ");
        String name = scanner.nextLine();
        SilverVillage.getBookingHistory().showUserBookingHistory(name);

    }
}
