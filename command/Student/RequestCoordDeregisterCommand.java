package command.Student;

import java.time.LocalDateTime;

import FYPMS.FYPMS1;
import FYPMS.request.*;
import account.student.StudentAccount;
import account.student.StudentStatus;
import command.Command;

public class RequestCoordDeregisterCommand implements Command {
    private StudentAccount studentAccount;

    public RequestCoordDeregisterCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }
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

        RequestDeregister request = new RequestDeregister(studentAccount.getLoginId(), FYPMS1.getCoordinatorList().get(0).getLoginId(), RequestStatus.PENDING,
                RequestRelationship.STUDENTCoordinator, RequestType.DEREGISTER_PROJECT, 0);
        RequestList.add(request);
    }
}
