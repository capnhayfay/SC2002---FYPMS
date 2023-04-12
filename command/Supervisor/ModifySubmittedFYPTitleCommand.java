package command.Supervisor;

import command.Command;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.FYPMS;
import FYPMS.project.*;

public class ModifySubmittedFYPTitleCommand implements Command {

    public ModifySubmittedFYPTitleCommand() {
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input project ID: ");
        int ID = sc.nextInt();
        System.out.println("Input new project title: ");
        String newtitle = sc.nextLine();

        FYPList fyplist = FYPMS.getFypList();
        ArrayList<FYP> fyps = fyplist.getFYPs();
        for (FYP fyp : fyps) {
            if (fyp.getProjectId() == ID) {
                fyp.setTitle(newtitle);
                System.out.println("Title has been changed to : " + newtitle);
                break;
            }
        }
    }
}
