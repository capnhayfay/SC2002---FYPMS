package command.Supervisor;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.FYPMS1;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import command.Command;

public class ModifyFYPTitle implements Command {

    public ModifyFYPTitle() {
    }

    FYPList fypList = FYPMS1.getFypList();

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input projectID to change title: ");
        int projectID = sc.nextInt();
        FYPList projects = FYPMS1.getFypList();
        ArrayList<FYP> fyps = projects.getFYPs();
        for (FYP fyp : fyps) {
                if (fyp.getProjectId() == projectID) {
                    System.out.println("Input new title: ");
                    String title = sc.nextLine();
                    fyp.setTitle(title);
                    System.out.println("Project title has been change to" + fyp.getTitle()); 
                }
        }
        
    }
}
