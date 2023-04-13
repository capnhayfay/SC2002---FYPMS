package command.Student;

import java.time.LocalDateTime;

import FYPMS.FYPMS1;
import FYPMS.request.*;
import account.student.StudentAccount;
import command.Command;

public class RequestCoordDeregisterCommand implements Command {
    private StudentAccount studentAccount;

    public RequestCoordDeregisterCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }
    public void execute() {
        RequestDeregister request = new RequestDeregister(studentAccount.getLoginId(), FYPMS1.getCoordinatorList().get(0).getLoginId(), RequestStatus.PENDING,
                RequestRelationship.STUDENTCoordinator, RequestType.DEREGISTER_PROJECT, 0);
        RequestList.add(request);
    }
}
