/**
 * The RequestCoordDeregisterCommand class implements the Command interface and
 * allows a student to make a request to a coordinator to deregister from their
 * current project.
 */
package src.command.Student;

import src.FYPMS.request.Request;
import src.FYPMS.request.RequestDeregister;
import src.FYPMS.request.RequestHistory;
import src.FYPMS.request.RequestStatus;
import src.account.student.StudentAccount;
import src.account.student.StudentStatus;
import src.command.Command;

import java.util.ArrayList;

/**
 * Deregister Project Command Class
 */
public class RequestCoordDeregisterCommand implements Command {
    /**
     * The list of requests for the system.
     */
    final ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();
    private final StudentAccount studentAccount;

    /**
     * The studentAccount object to hold student src.account information.
     * @param studentAccount Account of student
     */
    public RequestCoordDeregisterCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }

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
