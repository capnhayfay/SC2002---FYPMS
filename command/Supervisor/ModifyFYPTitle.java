package command.Supervisor;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.FYPMS1;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import account.supervisor.SupervisorAccount;
import command.Command;

public class ModifyFYPTitle implements Command {

    private SupervisorAccount supervisor;

    public ModifyFYPTitle(SupervisorAccount supervisor) {
        this.supervisor = supervisor;
    }

    FYPList fypList = FYPMS1.getFypList();

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input projectID to change title: ");
        int projectID = sc.nextInt();
        String oldTitle = "";
        FYPList projects = FYPMS1.getFypList();
        ArrayList<FYP> fyps = projects.getFYPs();
        for (FYP fyp : fyps) {
            if (fyp.getProjectId() == projectID) {
                System.out.println("Input new title: ");
                oldTitle = fyp.getTitle();
                String title = sc.next();
                fyp.setTitle(title);
                ArrayList<String> proj = supervisor.getProjList();
                String temp = "";
                for (String indiproj : proj) {
                    if (indiproj.equals(oldTitle)) {
                        supervisor.getProjList().remove(indiproj);
                        temp = title;
                    }
                }
                if (!temp.equals("")) {
                    supervisor.addProj(temp);
                }

                System.out.println("Project title has been change to" + fyp.getTitle());
                return;
            }
        }

    }
}
