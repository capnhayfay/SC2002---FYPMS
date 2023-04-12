package command.Student;

import java.time.LocalDateTime;
import java.util.Scanner;

import FYPMS.FYPMS;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import FYPMS.request.RequestStatus;
import FYPMS.request.RequestType;
import FYPMS.student.Student;
import FYPMS.student.StudentStatus;
import command.Command;

public class RequestCoordFYPCommand implements Command {
    private Student student;

    public RequestCoordFYPCommand(Student currentAcc) {
        this.student = currentAcc;
    }

    RequestList RequestList = FYPMS.getRequestList();

    public void execute() {
        if (student.getStatus() == StudentStatus.ASSIGNED_PROJECT) {
            System.out.println("Error: You are already registered for an FYP.");
            return;
        } else if (student.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
            System.out.println("Error: You have deregistered for an FYP.");
            return;
        } else if (student.getStatus() == StudentStatus.REQUESTED_PROJECT) {
            System.out.println("Error: You have a pending registration.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Input project ID: ");
        int ID = sc.nextInt();
        LocalDateTime statusChangeTime = LocalDateTime.now();
        Request request = new Request(student.getName(), RequestType.STUDENTCoordinator, statusChangeTime,
                "FYP Coordinator", RequestStatus.PENDING);
        RequestList.add(request);
        // where to input project id for request lmao
    }

}
