package command.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.FYPMS1;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.request.*;
import account.student.StudentAccount;
import account.student.StudentStatus;
import command.Command;

public class RequestSuperTitleChangeCommand implements Command {
    private StudentAccount student;

    public RequestSuperTitleChangeCommand(StudentAccount currentAcc) {
        this.student = currentAcc;
    }

    public void execute() {
        if (student.getStatus() == StudentStatus.NO_PROJECT) {
            System.out.println("Error: You are not registered for any FYP.");
            return;
        } else if (student.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
            System.out.println("Error: You have deregistered for an FYP.");
            return;
        } else if (student.getStatus() == StudentStatus.REQUESTED_PROJECT) {
            System.out.println("Error: Your registration is still pending.");
            return;
        }

        FYPList fyplist = FYPMS1.getFypList();
        ArrayList<FYP> fyps = fyplist.getFYPs();
        for (FYP fyp : fyps) {
            if (fyp.getStudentID().equals(student.getLoginId())){
                Scanner sc = new Scanner(System.in);
                System.out.println("Input new project title: ");
                String newtitle = sc.nextLine();
                String supervisor = fyp.getSupervisorName();

                RequestChangeTitle request = new RequestChangeTitle(student.getLoginId(), supervisor,RequestStatus.PENDING, RequestRelationship.STUDENTSupervisor,RequestType.CHANGE_TITLE, student.getAssignedProject(), newtitle);
                RequestList.add(request);
                System.out.println("Request for title change submitted.");
            }
        }
    }
}
