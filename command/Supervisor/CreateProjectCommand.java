package command.Supervisor;

import java.time.LocalDateTime;
import java.util.Scanner;

import FYPMS.FYPMS1;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.project.FYPStatus;
import command.Command;

public class CreateProjectCommand implements Command {
    private String supervisor;

    public CreateProjectCommand(String supervisor) {
        this.supervisor = supervisor;
    }

    FYPList fypList = FYPMS1.getFypList();

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input Title of FYP: ");
        String title = sc.nextLine();
        FYPStatus status = FYPStatus.AVAILABLE;
        String studentName = " ";
        String requester = " ";
        LocalDateTime statusChangeDate = LocalDateTime.now();
        FYP fyp = new FYP(fypList.getFYPs().size()+1, supervisor, , status, , requester, statusChangeDate);
        fypList.addFYP(fyp);
    }
}
