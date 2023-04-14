package command.Student;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.FYPMS1;
import FYPMS.project.FYPStatus;
import FYPMS.request.*;
import account.student.StudentAccount;
import account.student.StudentStatus;
import command.Command;

public class RequestCoordDeregisterCommand implements Command {
    private StudentAccount studentAccount;

    public RequestCoordDeregisterCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }

    ArrayList<ArrayList<Object>> requests = FYPMS1.getRequestList();

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

        RequestDeregister request = new RequestDeregister(requests.get(1).size() + 1000, studentAccount.getLoginId(),
                RequestStatus.PENDING, studentAccount.getAssignedProject());
        requests.get(1).add(request);
        System.out.println("Successfully Applied to deregister for project " + studentAccount.getAssignedProject());
    }

}
