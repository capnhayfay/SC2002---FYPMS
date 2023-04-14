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
    private FYPList supervisorFYPList;

    public AllocateProjectCommand(FYP project, RequestRegister registerRequest) {
        this.project = project;
        this.registerRequest = registerRequest;
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
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
                if (supervisor.getProj_1().equals("0")) {
                    supervisor.setProj_1(project.getTitle());
                } else if (supervisor.getProj_2().equals("0")) {
                    supervisor.setProj_2(project.getTitle());
                }
                System.out.println(supervisor.getProj_1());
                System.out.println(supervisor.getProj_2());
                if (!(supervisor.getProj_1().equals("0")) && !(supervisor.getProj_2().equals("0"))) {
                    System.out.println("Both projects are full, setting all projects to unavailable");
                    supervisorFYPList = FYPMS1.getSuperFypList(supervisor.getName());
                    System.out.println("reached");
                    for (FYP fyp : supervisorFYPList.getFYPs()) {
                        if (fyp.getStatus().equals(FYPStatus.AVAILABLE) || fyp.getStatus().equals(FYPStatus.RESERVED)) {
                            fyp.setStatus(FYPStatus.UNAVAILABLE);
                        }
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
