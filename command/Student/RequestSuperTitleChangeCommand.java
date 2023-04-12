package command.Student;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.FYPMS;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.request.Request;
import FYPMS.request.RequestStatus;
import FYPMS.request.RequestType;
import FYPMS.student.StudentStatus;
import command.Command;

public class RequestSuperTitleChangeCommand implements Command {
    private String Student;
    private Student student;

    RequestSuperTitleChangeCommand(String Student, Student currentAcc) {
        this.Student = Student;
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
        
        FYPList fyplist = FYPMS.getFypList();
        ArrayList<FYP> fyps = fyplist.getFYPs();
        for (FYP fyp : fyps) {
            if (fyp.getStudentName().equals(Student) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Input new project title: ");
                String newtitle = sc.nextLine();
                String supervisor = fyp.getSupervisorName();

                LocalDateTime statusChangeTime = LocalDateTime.now();
                Request request = new Request(Student, RequestType.STUDENTSupervisor, statusChangeTime, supervisor, RequestStatus.PENDING);
                // where to submit title
                // RequestList.add(request);
                return;
                System.out.println("Request for title change submitted.");
            }
        }
    }
}
