package command.student;
import command.Command;
import moblima.cineplex.*;
import moblima.SilverVillage;
import java.util.Scanner;

/**
 * Represents command for Guest and Customer to change the Cineplex branch they are viewing
 */
public class ChangeLocationCommand implements Command{
    private Cineplex cineplex;

    /**
     * Creates a ChangeLocationCommand Object with given Cineplex
     * @param cineplex which is the current Cineplex Guest/Customer is viewing
     */
    public ChangeLocationCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }

    /**
     * Gets input from user to search for Cineplex branch Guest/Customer wants to view
     */
    public void execute(){
        Scanner scanner = new Scanner(System.in);
        SilverVillage.getCineplexList().listCineplexes();
        while (true){
            System.out.println();
            System.out.print("Please enter the cineplex location number: ");

            if(scanner.hasNextInt() == false) {
                System.out.println("Invalid input format for location number. Please try again.");
                scanner.next();
                continue;
            }
            int locationCh = scanner.nextInt();
            scanner.nextLine();
            cineplex = SilverVillage.getCineplexList().getCineplexByIndex(locationCh-1);
            if (cineplex!=null) break;
            System.out.println("Option number out of range. Please try again.");
        }
        this.cineplex = cineplex;
    }

    /**
     * Gets the Cineplex of the ChangeLocationCommand
     * @return Cineplex
     */
    public Cineplex getCineplex(){
        return this.cineplex;
    }
}
