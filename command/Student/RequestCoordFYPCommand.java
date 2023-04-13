package command.Student;

import java.time.LocalDateTime;
import java.util.Scanner;

import FYPMS.request.*;
import account.student.*;
import FYPMS.FYPMS1;
import account.student.StudentStatus;
import command.Command;
import account.student.*;
public class RequestCoordFYPCommand implements Command {
    private StudentAccount studentAccount;

    public RequestCoordFYPCommand(StudentAccount currentAcc) {
        this.studentAccount = currentAcc;
    }

    RequestList RequestList = FYPMS1.getRequestList();

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
        RequestRegister registerRequest = new RequestRegister(studentAccount.getLoginId(),FYPMS1.getCoordinatorList().get(0).getLoginId(),RequestStatus.PENDING,RequestRelationship.STUDENTCoordinator,RequestType.REGISTER_PROJECT, fypID);
        FYPMS.request.RequestList.add(registerRequest);
    }

}
