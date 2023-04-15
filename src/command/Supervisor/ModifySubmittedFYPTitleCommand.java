/**
 * This class represents a src.command that allows a supervisor to modify the title of a submitted FYP project.
 * The src.command contains a reference to the project to be modified, and a request to change the title of the project.
 */
package src.command.Supervisor;

import src.FYPMS.project.FYP;
import src.FYPMS.request.RequestChangeTitle;
import src.FYPMS.request.RequestStatus;
import src.command.Command;

import java.util.Scanner;

/**
 * Supervisor Command to modify title of a submitted FYP
 */
public class ModifySubmittedFYPTitleCommand implements Command {

    private final FYP fyp;
    private final RequestChangeTitle titleRequest;

    /**
     * Constructor for the ModifySubmittedFYPTitleCommand class.
     *
     * @param fyp      The FYP project to be modified.
     * @param titleRequest The request to change the title of the FYP project.
     */
    public ModifySubmittedFYPTitleCommand(FYP fyp, RequestChangeTitle titleRequest) {
        this.fyp = fyp;
        this.titleRequest = titleRequest;
    }

    /**
     * Executes the src.command to modify the title of a submitted FYP project.
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Select option:");
        System.out.println("1. Accept Title request");
        System.out.println("2. Reject Title request");
        int requestAction = sc.nextInt();
        System.out.println("=========================================");
        switch (requestAction) {
            case 1:
                fyp.setTitle(titleRequest.getNewTitle());
                titleRequest.setStatus(RequestStatus.APPROVED);
                System.out.println("Project title has been change to" + fyp.getTitle());
                break;
            case 2:
                titleRequest.setStatus(RequestStatus.REJECTED);
                System.out.println("Rejected changing of title to" + fyp.getTitle());
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

    }

}
