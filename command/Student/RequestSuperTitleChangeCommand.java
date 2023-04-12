package command.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.FYPMS;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import FYPMS.request.RequestStatus;
import FYPMS.request.RequestType;
import command.Command;

public class RequestSuperTitleChangeCommand implements Command {
    private String Student;

    RequestSuperTitleChangeCommand(String Student) {
        this.Student = Student;
    }

    public void execute() {
        FYPList fyplist = FYPMS.getFypList();
        ArrayList<FYP> fyps = fyplist.getFYPs();
        for (FYP fyp : fyps) {
            if (fyp.getStudentName() == Student) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Input new project title: ");
                String newtitle = sc.nextLine();
                String supervisor = fyp.getSupervisorName();

                LocalDateTime statusChangeTime = LocalDateTime.now();
                Request request = new Request(Student, RequestType.STUDENTSupervisor, statusChangeTime, supervisor, RequestStatus.PENDING);
                // where to submit title
                // RequestList.add(request);
                break;
            }
        }
    }
}
