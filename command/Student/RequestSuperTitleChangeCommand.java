/**
 * The RequestSuperTitleChangeCommand class is responsible for creating and submitting a request to change the title of a student's assigned project.
 * @author A25 - Group 2
 * @version 1.0
*/
package command.Student;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.request.*;
import account.AccountManager;
import account.student.StudentAccount;
import account.student.StudentStatus;
import command.Command;

public class RequestSuperTitleChangeCommand implements Command {
    private final StudentAccount studentAccount;

    /**
     * Constructs a new RequestSuperTitleChangeCommand object.
     *
     * @param studentAccount the student account that wants to submit the request for title change
    */
    public RequestSuperTitleChangeCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }

    /**
     * Executes the command to create and submit a request for changing the title of the student's assigned project.
     * Checks the student's registration status and displays an error message if necessary.
     * If the student is registered for a project, prompts them to input a new project title and submits a request for title change.
    */
    public void execute() {
        if (studentAccount.getStatus() == StudentStatus.NO_PROJECT) {
            System.out.println("Error: You are not registered for any FYP.");
            return;
        } else if (studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
            System.out.println("Error: You have deregistered for an FYP.");
            return;
        } else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
            System.out.println("Error: Your registration is still pending.");
            return;
        }
        ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();
        ArrayList<FYP> fypList = FYPList.getFypList();

        for (FYP fyp : fypList) {
            if (fyp.getStudentID() != null) {
                if (fyp.getStudentID().equals(studentAccount.getLoginId())) {
                    Scanner sc = new Scanner(System.in);
                    System.out.println("Input new project title: ");
                    String newtitle = sc.nextLine();
                    String supervisorID = AccountManager.getSupervisorAccount(fyp.getSupervisorName()).getLoginId();
                    RequestChangeTitle requestChangeTitle = new RequestChangeTitle(requestHistory.get(0).size(), studentAccount.getLoginId(),
                            supervisorID, RequestStatus.PENDING, studentAccount.getAssignedProject(), newtitle);
                    requestHistory.get(0).add(requestChangeTitle);
                    System.out.println("Request for title change submitted.");
                }
            }
        }
    }
}
