/**
 * ViewSubmittedFYPCommand is a src.command class that retrieves all Final Year Projects assigned to a specific Supervisor and displays their details in a numbered list format.
 * It implements the Command interface.
 */

package src.command.Supervisor;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.command.Command;

import java.util.ArrayList;

/**
 * Command class for a supervisor to view all FYPs they have submitted
 */
public class ViewSubmittedFYPCommand implements Command {
    /**
     * The name of the Supervisor whose Final Year Projects will be displayed
     */
    private final String supervisorName;

    /**
     * Constructs a ViewSubmittedFYPCommand object with the provided Supervisor
     * name.
     *
     * @param supervisorName - The name of the Supervisor whose Final Year Projects will
     *                   be displayed.
     */
    public ViewSubmittedFYPCommand(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    /**
     * Executes the src.command to retrieve and display all Final Year Projects assigned
     * to the Supervisor.
     */
    public void execute() {
        int fypCount = 1;
        ArrayList<FYP> fypList = FYPList.getFypList();
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
