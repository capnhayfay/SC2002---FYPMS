/**
 * The RequestCoordDeregisterCommand class implements the Command interface and 
 * allows a student to make a request to a coordinator to deregister from their 
 * current project.
*/
package command.Student;

import java.util.ArrayList;

import FYPMS.request.*;
import account.student.StudentAccount;
import account.student.StudentStatus;
import command.Command;

public class RequestCoordDeregisterCommand implements Command {
    private final StudentAccount studentAccount;

    /**
     * The studentAccount object to hold student account information.
     */
    public RequestCoordDeregisterCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }

    /**
     * The list of requests for the system.
     */
    final ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();

    /**
     * Executes the request for deregistration.
     */
    public void execute() {
        if (studentAccount.getStatus() == StudentStatus.NO_PROJECT) {
            System.out.println("Error: You have not registered for an FYP.");
            return;
        } else if (studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
            System.out.println("Error: You have deregistered for an FYP.");
            return;
        } else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
            System.out.println("Error: You have a pending registration.");
            return;
        }
        // Temporary requestID = 1
        for (Request Request : requestHistory.get(1)) {
            if (Request.getRequesterID().equals(studentAccount.getLoginId())) {
                System.out.println();
                System.out.println("You have already requested to deregister.");
                return;
            }

        }

        RequestDeregister requestDeregister = new RequestDeregister(requestHistory.get(1).size() + 1000, studentAccount.getLoginId(),
                RequestStatus.PENDING, studentAccount.getAssignedProject());
                requestHistory.get(1).add(requestDeregister);
        System.out.println("Successfully Applied to deregister for project " + studentAccount.getAssignedProject());
    }

}
