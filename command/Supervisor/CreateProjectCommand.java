package command.Supervisor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import FYPMS.FYPMS;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.project.FYPStatus;
import command.Command;

public class CreateProjectCommand implements Command {
    private String supervisor;

    public CreateProjectCommand(String supervisor) {
        this.supervisor = supervisor;
    }

    FYPList fypList = FYPMS.getFypList();

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input Title of FYP: ");
        String title = sc.nextLine();
        FYPStatus status = FYPStatus.AVAILABLE;
        String studentName = " ";
        List<String> requestorList = Collections.<String>emptyList();
        LocalDateTime statusChangeDate = LocalDateTime.now();
        FYP fyp = new FYP(supervisor, title, status, studentName, requestorList, statusChangeDate);
        fypList.addFYP(fyp);
    }
}
