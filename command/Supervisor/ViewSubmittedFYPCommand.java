/**
 * ViewSubmittedFYPCommand is a command class that retrieves all Final Year Projects assigned to a specific Supervisor and displays their details in a numbered list format.
 * It implements the Command interface.
*/

package command.Supervisor;

import java.util.ArrayList;

import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import command.Command;

public class ViewSubmittedFYPCommand implements Command {
    // The name of the Supervisor whose Final Year Projects will be displayed
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
     * Executes the command to retrieve and display all Final Year Projects assigned
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
