package command.FYPCoord;

import FYPMS.project.*;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.FYPMS1;
import FYPMS.request.Request;
import FYPMS.request.RequestRegister;
import FYPMS.request.RequestStatus;
import account.student.StudentStatus;
import account.supervisor.SupervisorAccount;
import command.Command;

public class AllocateProjectCommand implements Command {
    private FYP project;
    private RequestRegister registerRequest;
    private SupervisorAccount supervisor;

    public AllocateProjectCommand(FYP project, RequestRegister registerRequest) {
        this.project = project;
        this.registerRequest = registerRequest;
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        registerRequest.printDetails();
        System.out.println();
        System.out.println("Select option:");
        System.out.println("1. Accept registration request");
        System.out.println("2. Reject registration request");
        System.out.println();
        switch (sc.nextInt()) {
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
                        ArrayList<FYP> supervisorFYPList = FYPMS1.getSuperFypList(supervisor.getName());
                        for (FYP fyp : supervisorFYPList) {
                            if (fyp.getStatus().equals(FYPStatus.AVAILABLE)
                                    || fyp.getStatus().equals(FYPStatus.RESERVED)) {
                                fyp.setStatus(FYPStatus.UNAVAILABLE);
                                ArrayList<Object> requests = FYPMS1.getRequestList().get(2);
                                for (Object indivRequest : requests) {
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
