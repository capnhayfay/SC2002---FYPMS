package command.Supervisor;

import java.util.Scanner;

import FYPMS.FYPMS1;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.project.FYPStatus;
import account.supervisor.SupervisorAccount;
import command.Command;

public class CreateProjectCommand implements Command {
    private SupervisorAccount supervisor;

    public CreateProjectCommand(SupervisorAccount supervisor) {
        this.supervisor = supervisor;
    }

    FYPList fypList = FYPMS1.getFypList();

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input Title of FYP: ");
        String title = sc.nextLine();
        FYP fyp = new FYP(fypList.getFYPs().size()+1, supervisor.getName(), supervisor.getEmail(), "", "", "", title, FYPStatus.AVAILABLE);
        fypList.addFYP(fyp);
    }
}
