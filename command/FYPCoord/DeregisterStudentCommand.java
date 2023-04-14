/**

A command that deregisters a student from a project in the FYPMS system.
*/
package command.FYPCoord;

import FYPMS.FYPMS1;
import FYPMS.project.*;
import FYPMS.request.Request;
import FYPMS.request.RequestDeregister;
import FYPMS.request.RequestStatus;
import account.student.StudentStatus;
import command.Command;

import java.util.ArrayList;
import java.util.Scanner;

public class DeregisterStudentCommand implements Command {
    private FYP project;
    private RequestDeregister requestDeregister;

    /**
     * Constructs a new DeregisterStudentCommand object with the specified project
     * and deregistration request.
     *
     * @param project           the project to deregister the student from
     * @param requestDeregister the deregistration request
     */
    public DeregisterStudentCommand(FYP project, RequestDeregister requestDeregister) {
        this.project = project;
        this.requestDeregister = requestDeregister;
    }

    /**
     * Executes the command to deregister the student from the project.
     * Prompts the user to accept or reject the deregistration request, and updates
     * the project and student status accordingly.
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        requestDeregister.printDetails();
        ;
        System.out.println();
        System.out.println("Select option:");
        System.out.println("1. Accept deregistration request");
        System.out.println("2. Reject deregistration request");
        int requestAction = sc.nextInt();
        System.out.println("=========================================");
        switch (requestAction) {
            case 1:
                String StudentName = FYPMS1.getStudentName(requestDeregister.getRequesterID());
                project.setStatus(FYPStatus.AVAILABLE);
                project.setStudentID("");
                project.setStudentEmail(null);
                project.setStudentName(null);
                requestDeregister.setStatus(RequestStatus.APPROVED);
                FYPMS1.setStudentStatus(requestDeregister.getRequesterID(), StudentStatus.DEREGISTERED_PROJECT,
                        requestDeregister.getFypID());
                for (ArrayList<Object> requests : FYPMS1.getRequestList())
                    for (Object indivRequest : requests) {
                        Request indivCastedRequest = (Request) indivRequest;
                        if (indivCastedRequest.getRequesterID().equals(requestDeregister.getRequesterID())
                                && indivCastedRequest.getRequestStatus().equals(RequestStatus.PENDING)) {
                            indivCastedRequest.setStatus(RequestStatus.REJECTED);
                        }
                    }
                System.out.println("Deregistered project " + project.getTitle() + " from " + StudentName);
                System.out.println("Press enter to continue...");
                sc.nextLine();
                break;
            case 2:
                requestDeregister.setStatus(RequestStatus.REJECTED);
                FYPMS1.setStudentStatus(requestDeregister.getRequesterID(), StudentStatus.ASSIGNED_PROJECT, 0);
                System.out.println("Rejected " + requestDeregister.getRequesterID() + " for " + project.getTitle());
                System.out.println("Press enter to continue...");
                sc.nextLine();
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

}
