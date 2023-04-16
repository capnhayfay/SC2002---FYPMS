/**
 * The CreateProjectCommand class is responsible for creating a new FYP project in the src.FYPMS system.
 * It prompts the supervisor to input the title of the project, and creates a new FYP object with
 * the given title, supervisor's name and email, and an initial status of AVAILABLE. The FYP object
 * is added to the FYP list in the src.FYPMS system.
 */

package src.command.Supervisor;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.FYPMS.project.FYPStatus;
import src.account.supervisor.SupervisorAccount;
import src.command.Command;
import src.exceptions.fypmsExceptions;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Command class for Supervisors to create new Projects
 */
public class CreateProjectCommand implements Command {
    final ArrayList<FYP> fypList = FYPList.getFypList();
    private final SupervisorAccount supervisor;

    /**
     * Constructor for CreateProjectCommand
     * @param supervisor Account of the supervisor creating project
     */
    public CreateProjectCommand(SupervisorAccount supervisor) {
        this.supervisor = supervisor;
    }

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
        String title = "INVALIDTITLEWONTBEREPLACED";
        do {
            try {
                title = sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(new fypmsExceptions.invalidInputException("Invalid Input, please try again.").toString()
                        .substring(e.toString().indexOf(":")+2));
            }
        } while (title.equals("INVALIDTITLEWONTBEREPLACED"));
        FYP fyp;
        if (supervisor.getProjList().size() >= 2) {
            try {
                throw new fypmsExceptions.supervisorMaxProjectsReachedException();

            } catch (fypmsExceptions.supervisorMaxProjectsReachedException e) {
                System.out.println(e.toString().subSequence(e.toString().indexOf(":")+2, e.toString().length()-1));
                System.out.println("Proceeding to make new project unavailable...");
                fyp = new FYP(FYPList.getFypList().size() + 1, supervisor.getName(), supervisor.getEmail(), "", "", "",
                        title,
                        FYPStatus.UNAVAILABLE);
                fypList.add(fyp);
                return;
            }
        } else {
            fyp = new FYP(FYPList.getFypList().size() + 1, supervisor.getName(), supervisor.getEmail(), "", "", "",
                    title,
                    FYPStatus.AVAILABLE);
            fypList.add(fyp);
            System.out.println();
            System.out.println("Project has successfully been created!");
        }
    }
}
