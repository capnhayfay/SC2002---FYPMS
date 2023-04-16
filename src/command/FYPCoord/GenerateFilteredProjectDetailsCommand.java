/**
 * This class represents a src.command to generate filtered details from the src.FYPMS.
 * It implements the Command interface.
 */
package src.command.FYPCoord;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.FYPMS.project.FYPStatus;
import src.command.Command;
import src.exceptions.fypmsExceptions;

import java.util.*;

/**
 * Class that Generated Projects based on a specific Filter
 */
public class GenerateFilteredProjectDetailsCommand implements Command {
    private final int filterType;
    private FYPStatus fypStatus;

    /**
     * Constructor for the GenerateFilteredProjectDetailsCommand class.
     *
     * @param filterType the type of filter to be applied (1: Filter by status, 2:
     *                   Filter by supervisor)
     */
    public GenerateFilteredProjectDetailsCommand(int filterType) {
        this.filterType = filterType;
    }

    /**
     * Executes the src to generate filtered details from the src.FYPMS.
     */
    public void execute() {
        boolean validInput = false;
        int selection;
        Scanner sc = new Scanner(System.in);
        int fypCount = 1;
        ArrayList<FYP> fypList = FYPList.getFypList();

        switch (filterType) {
            // Filter by status
            case 1 -> {
                System.out.println();
                System.out.println("==================================");
                System.out.println("Filter by:");
                System.out.println("(1) Available");
                System.out.println("(2) Reserved");
                System.out.println("(3) Unavailable");
                System.out.println("(4) Allocated");
                System.out.println();
                System.out.println("Enter the number of your choice:");
                System.out.println("==================================");
                while (!validInput) {
                    try {
                        selection = sc.nextInt();
                        if (!(selection < 5 & selection >= 1))
                        {
                            throw new fypmsExceptions.invalidInputException("Invalid input, please try again.");
                        }
                        else{
                            validInput = true;
                            switch (selection) {
                                case 1 -> this.fypStatus = FYPStatus.AVAILABLE;
                                case 2 -> this.fypStatus = FYPStatus.RESERVED;
                                case 3 -> this.fypStatus = FYPStatus.UNAVAILABLE;
                                case 4 -> this.fypStatus = FYPStatus.ALLOCATED;
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.print("Your selection can only be an integer!");
                    } catch (fypmsExceptions.invalidInputException e){
                        System.out.print(e.toString().substring(e.toString().indexOf(":")+2));
                    }
                }
                System.out.println();
                System.out.println("List of " + this.fypStatus.toString().toLowerCase() + " Final Year Projects");
                System.out.println();
                for (FYP fyp : fypList) {
                    if (fyp.getStatus() == this.fypStatus) {
                        System.out.println("============= FYP No. " + fypCount++ + " ==============");
                        fyp.printFYPDetails();
                        System.out.println();
                    }
                }
                System.out.println("===== There are " + (fypCount - 1) + " Final Year Projects "
                        + this.fypStatus.toString().toLowerCase() + "! =====");
            }

            // Filter by supervisor
            case 2 -> {
                int i = 1;
                String supervisorName = "";
                System.out.println();
                System.out.println("==================================");
                System.out.println("Enter Supervisor's name:");
                Map<Integer, String> supervisorMapIdx = new TreeMap<>();
                // Map<String, Integer> supervisorMapIdx= new HashMap<>();
                for (FYP fyp : fypList) {
                    if (!supervisorMapIdx.containsValue(fyp.getSupervisorName())) {
                        supervisorMapIdx.put(i, fyp.getSupervisorName());
                        i++;
                    }
                }
                for (Map.Entry<Integer, String> pair : supervisorMapIdx.entrySet()) {
                    System.out.print("(" + pair.getKey() + ") ");
                    System.out.println(pair.getValue());
                }
                System.out.println();
                System.out.println("Enter the number of your choice:");
                System.out.println("==================================");
                while (!validInput) {
                    try {
                        selection = sc.nextInt();
                        if (!(selection < i & selection >= 1)) {
                            throw new fypmsExceptions.invalidInputException("Invalid input, please try again.");
                        }
                        else{
                            validInput = true;
                            for (Map.Entry<Integer, String> pair : supervisorMapIdx.entrySet()) {
                                if (pair.getKey() == selection) {
                                    supervisorName = pair.getValue();
                                }
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.print("Your selection can only be an integer!");
                    } catch (fypmsExceptions.invalidInputException e) {
                        System.out.print(e.toString().substring(e.toString().indexOf(":")+2));
                    }
                }
                System.out.println();
                System.out.println("List of all Final Year Projects");
                System.out.println();
                for (FYP filteredFyp : fypList) {
                    if (filteredFyp.getSupervisorName().equals(supervisorName)) {
                        System.out.println("============= FYP No. " + fypCount++ + " ==============");
                        filteredFyp.printFYPDetails();
                        System.out.println();
                    }
                }
                System.out.println("===== There are " + (fypCount - 1) + " Final Year Projects! =====");
            }
        }

    }
}
