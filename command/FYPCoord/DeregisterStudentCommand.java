/**

A command that deregisters a student from a project in the FYPMS system.
*/
package command.FYPCoord;

import FYPMS.project.*;
import FYPMS.request.Request;
import FYPMS.request.RequestDeregister;
import FYPMS.request.RequestHistory;
import FYPMS.request.RequestStatus;
import account.AccountManager;
import account.student.StudentStatus;
import account.supervisor.SupervisorAccount;
import command.Command;

import java.util.ArrayList;
import java.util.Scanner;

public class DeregisterStudentCommand implements Command {
    private final FYP fyp;
    private final RequestDeregister requestDeregister;

    /**
     * Constructs a new DeregisterStudentCommand object with the specified project
     * and deregistration request.
     *
     * @param fyp           the project to deregister the student from
     * @param requestDeregister the deregistration request
     */
    public DeregisterStudentCommand(FYP fyp, RequestDeregister requestDeregister) {
        this.fyp = fyp;
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
        System.out.println();
        System.out.println("Select option:");
        System.out.println("1. Accept deregistration request");
        System.out.println("2. Reject deregistration request");
        int requestAction = sc.nextInt();
        System.out.println("=========================================");
        switch (requestAction) {
            case 1:
                String StudentName = AccountManager.getStudentName(requestDeregister.getRequesterID());
                fyp.setStatus(FYPStatus.AVAILABLE);
                fyp.setStudentID("");
                fyp.setStudentEmail(null);
                fyp.setStudentName(null);
                requestDeregister.setStatus(RequestStatus.APPROVED);
                AccountManager.setStudentStatus(requestDeregister.getRequesterID(), StudentStatus.DEREGISTERED_PROJECT,
                        requestDeregister.getFypID());
                for (ArrayList<Request> requestList : RequestHistory.getRequestHistory())
                    for (Request indivRequest : requestList) {
                        if (indivRequest.getRequesterID().equals(requestDeregister.getRequesterID())
                                && indivRequest.getRequestStatus().equals(RequestStatus.PENDING)) {
                                    indivRequest.setStatus(RequestStatus.REJECTED);
                        }
                    }
                SupervisorAccount supervisorAccount = AccountManager.getSupervisorAccount(fyp.getSupervisorName());
                supervisorAccount.getProjList().remove(fyp.getTitle());
                if (supervisorAccount.getProjList().size() < 2) {
                    for (FYP fyp : FYPList.getSuperFypList(supervisorAccount.getName())) {
                        if (fyp.getStatus().equals(FYPStatus.UNAVAILABLE)) {
                            fyp.setStatus(FYPStatus.AVAILABLE);
                        }
                    }
                }

                System.out.println("Deregistered project " + fyp.getTitle() + " from " + StudentName);
                System.out.println("Press enter to continue...");
                sc.nextLine();
                break;
            case 2:
                requestDeregister.setStatus(RequestStatus.REJECTED);
                AccountManager.setStudentStatus(requestDeregister.getRequesterID(), StudentStatus.ASSIGNED_PROJECT, 0);
                System.out.println("Rejected " + requestDeregister.getRequesterID() + " for " + fyp.getTitle());
                System.out.println("Press enter to continue...");
                sc.nextLine();
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

}
