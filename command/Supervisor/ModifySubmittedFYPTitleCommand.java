/**
 * This class represents a command that allows a supervisor to modify the title of a submitted FYP project.
 * The command contains a reference to the project to be modified, and a request to change the title of the project.
*/
package command.Supervisor;

import command.Command;

import java.util.Scanner;

import FYPMS.project.*;
import FYPMS.request.RequestChangeTitle;
import FYPMS.request.RequestStatus;

public class ModifySubmittedFYPTitleCommand implements Command {

    private final FYP project;
    private final RequestChangeTitle titleRequest;

    /**
     * Constructor for the ModifySubmittedFYPTitleCommand class.
     * 
     * @param project      The FYP project to be modified.
     * @param titleRequest The request to change the title of the FYP project.
     */
    public ModifySubmittedFYPTitleCommand(FYP project, RequestChangeTitle titleRequest) {
        this.project = project;
        this.titleRequest = titleRequest;
    }

    /**
     * Executes the command to modify the title of a submitted FYP project.
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
                project.setTitle(titleRequest.getNewTitle());
                titleRequest.setStatus(RequestStatus.APPROVED);
                System.out.println("Project title has been change to" + project.getTitle());
                break;
            case 2:
                titleRequest.setStatus(RequestStatus.REJECTED);
                System.out.println("Rejected changing of title to" + project.getTitle());
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

    }

}
