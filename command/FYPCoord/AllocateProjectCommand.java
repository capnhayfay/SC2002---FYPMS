/**
 * The AllocateProjectCommand class represents a command for the FYP Coordinator to allocate a project to a student.
 * It implements the Command interface and contains an execute method to carry out the allocation process.
 * The class takes in a FYP object and a RequestRegister object in its constructor.
 */
package command.FYPCoord;

import FYPMS.project.*;

import java.util.Scanner;

import FYPMS.FYPMS1;
import FYPMS.request.Request;
import FYPMS.request.RequestRegister;
import FYPMS.request.RequestStatus;
import account.student.StudentStatus;
import account.supervisor.SupervisorAccount;
import command.Command;

/**
 * Constructs a new AllocateProjectCommand object with the specified FYP project
 * and RequestRegister object.
 * 
 * @param project         the FYP project to be allocated
 * @param registerRequest the request for the project registration
 */
public class AllocateProjectCommand implements Command {
    private FYP project;
    private RequestRegister registerRequest;
    private SupervisorAccount supervisor;

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
                String StudentName = FYPMS1.getStudentName(registerRequest.getRequesterID());
                String StudentEmail = FYPMS1.getStudentEmail(registerRequest.getRequesterID());
                project.setStatus(FYPStatus.ALLOCATED);
                project.setStudentID(registerRequest.getRequesterID());
                project.setStudentEmail(StudentEmail);
                project.setStudentName(StudentName);
                registerRequest.setStatus(RequestStatus.APPROVED);
                FYPMS1.setStudentStatus(registerRequest.getRequesterID(), StudentStatus.ASSIGNED_PROJECT,
                        registerRequest.getFypID());
                supervisor = FYPMS1.getSupervisorAccount(project.getSupervisorName());
                if (supervisor.getProjList().size() < 2) {
                    supervisor.addProj(project.getTitle());
                    if (supervisor.getProjList().size() == 2) {
                        for (FYP fyp : FYPMS1.getSuperFypList(supervisor.getName())) {
                            if (fyp.getStatus().equals(FYPStatus.AVAILABLE)
                                    || fyp.getStatus().equals(FYPStatus.RESERVED)) {
                                fyp.setStatus(FYPStatus.UNAVAILABLE);
                                for (Object indivRequest : FYPMS1.getRequestList().get(2)) {
                                    Request indivCastedRequest = (Request) indivRequest;
                                    if (indivCastedRequest.getFypID() == fyp.getProjectId()) {
                                        indivCastedRequest.setStatus(RequestStatus.REJECTED);
                                        String setStudentStatus = indivCastedRequest.getRequesterID();
                                        FYPMS1.setStudentStatus(setStudentStatus, StudentStatus.NO_PROJECT,
                                                indivCastedRequest.getFypID());
                                    }
                                }
                            }
                        }
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
                FYPMS1.setStudentStatus(registerRequest.getRequesterID(), StudentStatus.NO_PROJECT, 0);
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
