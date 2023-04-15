/**
 * The AllocateProjectCommand class represents a command for the FYP Coordinator to allocate a project to a student.
 * It implements the Command interface and contains an execute method to carry out the allocation process.
 * The class takes in a FYP object and a RequestRegister object in its constructor.
 */
package command.FYPCoord;

import FYPMS.project.*;

import java.util.Scanner;

import FYPMS.request.Request;
import FYPMS.request.RequestHistory;
import FYPMS.request.RequestRegister;
import FYPMS.request.RequestStatus;
import account.AccountManager;
import account.student.StudentStatus;
import account.supervisor.SupervisorAccount;
import command.Command;

/**
 * Constructs a new AllocateProjectCommand object with the specified FYP project
 * and RequestRegister object.
 *
 */
public class AllocateProjectCommand implements Command {
    private final FYP project;
    private final RequestRegister registerRequest;

    public AllocateProjectCommand(FYP project, RequestRegister registerRequest) {
        this.project = project;
        this.registerRequest = registerRequest;
    }

    /**
     * Executes the command to allocate the FYP project to a student.
     * The method prompts the user to either accept or reject the registration
     * request.
     * It then updates the FYP, student, supervisor, project, and request status
     * accordingly.
     * It also updates the student and supervisor status and project lists.
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        registerRequest.printDetails();
        System.out.println();
        System.out.println("Select option:");
        System.out.println("1. Accept registration request");
        System.out.println("2. Reject registration request");
        System.out.println();
        int requestAction = sc.nextInt();
        System.out.println("=========================================");
        switch (requestAction) {
            case 1:
                String StudentName = AccountManager.getStudentName(registerRequest.getRequesterID());
                String StudentEmail = AccountManager.getStudentEmail(registerRequest.getRequesterID());
                project.setStatus(FYPStatus.ALLOCATED);
                project.setStudentID(registerRequest.getRequesterID());
                project.setStudentEmail(StudentEmail);
                project.setStudentName(StudentName);
                registerRequest.setStatus(RequestStatus.APPROVED);
                AccountManager.setStudentStatus(registerRequest.getRequesterID(), StudentStatus.ASSIGNED_PROJECT,
                        registerRequest.getFypID());
                SupervisorAccount supervisor = AccountManager.getSupervisorAccount(project.getSupervisorName());
                if (supervisor.getProjList().size() < 2) {
                    supervisor.addProj(project.getTitle());
                    if (supervisor.getProjList().size() == 2) {
                        for (FYP fyp : FYPList.getSuperFypList(supervisor.getName())) {
                            if (fyp.getStatus().equals(FYPStatus.AVAILABLE)
                                    || fyp.getStatus().equals(FYPStatus.RESERVED)) {
                                fyp.setStatus(FYPStatus.UNAVAILABLE);
                                for (Object indivRequest : RequestHistory.getRequestList().get(2)) {
                                    Request indivCastedRequest = (Request) indivRequest;
                                    if (indivCastedRequest.getFypID() == fyp.getProjectId()) {
                                        indivCastedRequest.setStatus(RequestStatus.REJECTED);
                                        String setStudentStatus = indivCastedRequest.getRequesterID();
                                        AccountManager.setStudentStatus(setStudentStatus, StudentStatus.NO_PROJECT,
                                                indivCastedRequest.getFypID());
                                    }
                                }
                            }
                        }
                        System.out.println("Error: " + supervisor.getName() + " has reached the project limit.");
                        System.out.println(
                                "Rejecting all pending registration requests for " + supervisor.getName()
                                        + "'s projects.");
                        System.out.println("Setting all of " + supervisor.getName() + "'s projects to unavailable");

                    }

                } else {
                    System.out.println("Error: " + supervisor.getName() + " has reached the project limit.");
                    return;
                }
                System.out.println("Allocated project " + project.getTitle() + " to " + StudentName);
                System.out.println("Press enter to continue...");
                sc.nextLine();
                break;
            case 2:
                registerRequest.setStatus(RequestStatus.REJECTED);
                AccountManager.setStudentStatus(registerRequest.getRequesterID(), StudentStatus.NO_PROJECT, 0);
                System.out.println("Rejected " + registerRequest.getRequesterID() + " for " + project.getTitle());
                System.out.println("Press enter to continue...");
                sc.nextLine();
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

    }

}
