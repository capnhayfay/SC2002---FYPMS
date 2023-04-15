/**
    A command that allows a student to request a coordinator-approved FYP project.
    If the student is already registered for an FYP, deregistered from an FYP, or has a pending registration,
    an error message will be displayed and the command will exit.
    The student will be prompted to enter a project ID, and if the project is available, the student's status will be set
    to REQUESTED_PROJECT and a request to register for the project will be added to the request list.
    If the project is not available, an error message will be displayed and the command will exit.
    @author A25-Group2
    @version 1.0
    @since 2023
*/
package command.Student;

import java.util.Scanner;
import java.util.ArrayList;
import FYPMS.request.*;
import account.student.*;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.project.FYPStatus;
import command.Command;


public class RequestCoordFYPCommand implements Command {
    private final StudentAccount studentAccount;

    /**
     * Constructor for the RequestCoordFYPCommand class.
     *
     * @param studentAccount the student account associated with this command
    */
    public RequestCoordFYPCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }

    final ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();

    /**
     * Executes the RequestCoordFYPCommand by prompting the student to enter a project ID,
     * checking if the student is eligible to request a project, and adding a request to register for the project
     * if the project is available.
    */
    public void execute() {
        if (studentAccount.getStatus() == StudentStatus.ASSIGNED_PROJECT) {
            System.out.println("Error: You are already registered for an FYP.");
            return;
        } else if (studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
            System.out.println("Error: You have deregistered for an FYP.");
            return;
        } else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
            System.out.println("Error: You have a pending registration.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Input project ID: ");
        int fypID = sc.nextInt();
        ArrayList<FYP> fypList = FYPList.getFypList();
        for (FYP fyp : fypList) {
            if (fyp.getProjectId() == fypID) {
                if (!(fyp.getStatus().equals(FYPStatus.AVAILABLE))) {
                    System.out.println("Error: Project is unavailable for registration.");
                    return;
                }
                fyp.setStatus(FYPStatus.RESERVED);
                break;
            }
        }
        studentAccount.setStatus(StudentStatus.REQUESTED_PROJECT);
        RequestRegister registerRequest = new RequestRegister(requestHistory.get(2).size() + 2000,
                studentAccount.getLoginId(), RequestStatus.PENDING,
                fypID);
                requestHistory.get(2).add(registerRequest);
        System.out.println("Successfully Applied for project " + fypID);
    }

}
