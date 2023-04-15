/**
 * The CreateProjectCommand class is responsible for creating a new FYP project in the FYPMS system.
 * It prompts the supervisor to input the title of the project, and creates a new FYP object with
 * the given title, supervisor's name and email, and an initial status of AVAILABLE. The FYP object
 * is added to the FYP list in the FYPMS system.
*/

package command.Supervisor;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.project.FYPStatus;
import account.supervisor.SupervisorAccount;
import command.Command;

public class CreateProjectCommand implements Command {
    private final SupervisorAccount supervisor;

    public CreateProjectCommand(SupervisorAccount supervisor) {
        this.supervisor = supervisor;
    }

    final ArrayList<FYP> fypList = FYPList.getFypList();

    /**
     * This method prompts the user to input the title of the new FYP project and
     * creates a new FYP object
     * with the given title, supervisor's name and email, and an initial status of
     * AVAILABLE. The FYP object
     * is then added to the FYP list in the FYPMS system.
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input Title of FYP: ");
        String title = sc.nextLine();
        FYP fyp;
        if (supervisor.getProjList().size() >= 2) {
            System.out.println("Warning! You are already in charge of at least 2 projects");
            System.out.println("Proceeding to make new project unavailable...");
            fyp = new FYP(FYPList.getFypList().size() + 1, supervisor.getName(), supervisor.getEmail(), "", "", "",
                    title,
                    FYPStatus.UNAVAILABLE);
        } else {
            fyp = new FYP(FYPList.getFypList().size() + 1, supervisor.getName(), supervisor.getEmail(), "", "", "",
                    title,
                    FYPStatus.AVAILABLE);
            fypList.add(fyp);
        }
    }
}
