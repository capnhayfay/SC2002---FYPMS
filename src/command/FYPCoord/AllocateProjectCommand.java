/**
 * The AllocateProjectCommand class represents a src.command for the FYP Coordinator to allocate a project to a student.
 * It implements the Command interface and contains an execute method to carry out the allocation process.
 * The class takes in a FYP object and a RequestRegister object in its constructor.
 */
package src.command.FYPCoord;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.FYPMS.project.FYPStatus;
import src.FYPMS.request.Request;
import src.FYPMS.request.RequestHistory;
import src.FYPMS.request.RequestRegister;
import src.FYPMS.request.RequestStatus;
import src.account.AccountManager;
import src.account.student.StudentStatus;
import src.account.supervisor.SupervisorAccount;
import src.command.Command;
import src.exceptions.fypmsExceptions;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Constructs a new AllocateProjectCommand object with the specified FYP project
 * and RequestRegister object.
 */
public class AllocateProjectCommand implements Command {
    private final FYP fyp;
    private final RequestRegister registerRequest;

    /**
     * Allocates a project from an approved request
     *
     * @param fyp             The FYP object of the requested project
     * @param registerRequest The RequestRegister request object with the details of the request
     */
    public AllocateProjectCommand(FYP fyp, RequestRegister registerRequest) {
        this.fyp = fyp;
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
        int requestAction = 0;
        do {
            try {
                System.out.println("Select option:");
                System.out.println("1. Accept registration request");
                System.out.println("2. Reject registration request");
                System.out.println();
                do {
                    try {
                        requestAction = sc.nextInt();
                    } catch (InputMismatchException e) {
                        sc.nextLine();
                        requestAction = -1;
                        System.out.println(new fypmsExceptions.invalidInputException(e.toString()).toString());
                        continue;
                    }
                } while (requestAction == -1);
                if (!(requestAction == 1 || requestAction==2)){
                    throw new fypmsExceptions.invalidInputException("Please enter only 1 or 2");
                }
            } catch (fypmsExceptions.invalidInputException badInput) {
                System.out.println(badInput);
                sc.nextLine();
            }
        }while(!(requestAction == 1 || requestAction == 2));

        System.out.println("=========================================");
        try {
            switch (requestAction) {
                case 1 -> {
                    String StudentName = AccountManager.getStudentName(registerRequest.getRequesterID());
                    String StudentEmail = AccountManager.getStudentEmail(registerRequest.getRequesterID());
                    fyp.setStatus(FYPStatus.ALLOCATED);
                    fyp.setStudentID(registerRequest.getRequesterID());
                    fyp.setStudentEmail(StudentEmail);
                    fyp.setStudentName(StudentName);
                    registerRequest.setStatus(RequestStatus.APPROVED);
                    AccountManager.setStudentStatus(registerRequest.getRequesterID(), StudentStatus.ASSIGNED_PROJECT,
                            registerRequest.getFypID());
                    SupervisorAccount supervisorAccount = AccountManager.getSupervisorAccount(fyp.getSupervisorName());
                    assert supervisorAccount != null;
                    if (supervisorAccount.getProjList().size() < 2) {
                        supervisorAccount.addProj(fyp.getTitle());
                        if (supervisorAccount.getProjList().size() == 2) {
                            for (FYP fyp : FYPList.getSuperFypList(supervisorAccount.getName())) {
                                if (fyp.getStatus().equals(FYPStatus.AVAILABLE)
                                        || fyp.getStatus().equals(FYPStatus.RESERVED)) {
                                    fyp.setStatus(FYPStatus.UNAVAILABLE);
                                    for (Request indivRequest : RequestHistory.getRequestHistory().get(2)) {
                                        if (indivRequest.getFypID() == fyp.getProjectId()) {
                                            indivRequest.setStatus(RequestStatus.REJECTED);
                                            String setStudentStatus = indivRequest.getRequesterID();
                                            AccountManager.setStudentStatus(setStudentStatus, StudentStatus.NO_PROJECT,
                                                    indivRequest.getFypID());
                                        }
                                    }
                                }
                            }
                            System.out.println("Error: " + supervisorAccount.getName() + " has reached the project limit.");
                            System.out.println(
                                    "Rejecting all pending registration requests for " + supervisorAccount.getName()
                                            + "'s projects.");
                            System.out.println("Setting all of " + supervisorAccount.getName() + "'s projects to unavailable");

                        }

                    } else {
                        System.out.println("Error: " + supervisorAccount.getName() + " has reached the project limit.");
                        return;
                    }
                    System.out.println("Allocated project " + fyp.getTitle() + " to " + StudentName);
                    System.out.println("Press enter to continue...");
                    sc.nextLine();
                }
                case 2 -> {
                    registerRequest.setStatus(RequestStatus.REJECTED);
                    AccountManager.setStudentStatus(registerRequest.getRequesterID(), StudentStatus.NO_PROJECT, 0);
                    System.out.println("Rejected " + registerRequest.getRequesterID() + " for " + fyp.getTitle());
                    System.out.println("Press enter to continue...");
                    sc.nextLine();
                }
                default -> {
                    throw new fypmsExceptions.invalidInputException("Invalid choice");
                }
            }
        } catch (fypmsExceptions.invalidInputException e) {
            System.out.println(e.toString().toUpperCase());
        }

    }

}