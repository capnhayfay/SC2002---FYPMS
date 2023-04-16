/**
 * The AllocateProjectCommand class represents a src.command for the FYP Coordinator to allocate a project to a student.
 * It implements the Command interface and contains an execute method to carry out the allocation process.
 * The class takes in a FYP object and a RequestRegister object in its constructor.
 */
package src.command.FYPCoord;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.FYPMS.project.FYPStatus;
import src.FYPMS.request.RequestHistory;
import src.FYPMS.request.RequestRegister;
import src.FYPMS.request.RequestStatus;
import src.account.AccountManager;
import src.account.student.StudentStatus;
import src.account.supervisor.SupervisorAccount;
import src.command.Command;
import src.exceptions.fypmsExceptions;

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
        int requestAction = fypmsExceptions.validateRequestActionFunction();

        System.out.println("=========================================");

        try {
            String studentId, studentName, studentEmail;
            SupervisorAccount supervisorAccount;
            switch (requestAction) {
                case 1 -> {
                    studentId = registerRequest.getRequesterID();
                    studentName = AccountManager.getStudentName(studentId);
                    studentEmail = AccountManager.getStudentEmail(studentId);

                    fyp.setStatus(FYPStatus.ALLOCATED);
                    fyp.setStudentID(studentId);
                    fyp.setStudentEmail(studentEmail);
                    fyp.setStudentName(studentName);

                    registerRequest.setStatus(RequestStatus.APPROVED);
                    AccountManager.setStudentStatus(studentId, StudentStatus.ASSIGNED_PROJECT, registerRequest.getFypID());

                    supervisorAccount = AccountManager.getSupervisorAccount(fyp.getSupervisorName());

                    if (supervisorAccount == null) {
                        throw new fypmsExceptions.invalidInputException("Supervisor account not found");
                    }

                    if (supervisorAccount.getProjList().size() == 2) {
                        for (FYP proj : FYPList.getSuperFypList(supervisorAccount.getName())) {
                            if (proj.getStatus() == FYPStatus.AVAILABLE || proj.getStatus() == FYPStatus.RESERVED) {
                                proj.setStatus(FYPStatus.UNAVAILABLE);
                                RequestHistory.getRequestHistory().get(2).stream()
                                        .filter(request -> request.getFypID() == proj.getProjectId())
                                        .forEach(request -> {
                                            request.setStatus(RequestStatus.REJECTED);
                                            String studentIdToSetStatus = request.getRequesterID();
                                            AccountManager.setStudentStatus(studentIdToSetStatus, StudentStatus.NO_PROJECT, proj.getProjectId());
                                        });
                            }
                        }

                        supervisorAccount.addProj(fyp.getTitle());
                        throw new fypmsExceptions.supervisorMaxProjectsReachedButStillAcceptedException(supervisorAccount.getName());

                    } else if (supervisorAccount.getProjList().size() < 2) {
                        supervisorAccount.addProj(fyp.getTitle());
                    }
                    else {
                        throw new fypmsExceptions.supervisorMaxProjectsReachedException(supervisorAccount.getName());
                    }
                    System.out.println("Allocated project " + fyp.getTitle() + " to " + studentName);
                    System.out.println("Press enter to continue...");
                    sc.nextLine();
                }

                case 2 -> {
                    registerRequest.setStatus(RequestStatus.REJECTED);
                    AccountManager.setStudentStatus(registerRequest.getRequesterID(), StudentStatus.NO_PROJECT, 0);
                    System.out.println("Rejected " + registerRequest.getRequesterID() + " for " + fyp.getTitle());
                    break;
                }

                default -> {
                    throw new fypmsExceptions.invalidInputException("Invalid choice");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString().subSequence(e.toString().indexOf(":")+2, e.toString().length()-1));
        }

    }

}