/**
 * The ModifyFYPTitle class is responsible for modifying the title of an FYP project in the FYPMS system.
 * It prompts the supervisor to input the project ID of the project to be modified, and then prompts them
 * to input the new title for the project. If the supervisor is authorized to modify the project (i.e., they
 * are the supervisor assigned to the project), the old title is removed from their project list and the new
 * title is set for the project in the FYP list. The supervisor's project list is then updated with the new title.
 * If the supervisor is not authorized to modify the project, an error message is displayed.
*/
package command.Supervisor;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import account.supervisor.SupervisorAccount;
import command.Command;

public class ModifyFYPTitle implements Command {

    private final SupervisorAccount supervisorAccount;

    public ModifyFYPTitle(SupervisorAccount supervisorAccount) {
        this.supervisorAccount = supervisorAccount;
    }

    ArrayList<FYP> fypList = FYPList.getFypList();

    /**
     * This method prompts the user to input the project ID of the project to be
     * modified, and then prompts
     * them to input the new title for the project. If the supervisor is authorized
     * to modify the project
     * (i.e., they are the supervisor assigned to the project), the old title is
     * removed from their project list
     * and the new title is set for the project in the FYP list. The supervisor's
     * project list is then updated
     * with the new title. If the supervisor is not authorized to modify the
     * project, an error message is displayed.
     */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input projectID to change title: ");
        int projectID = sc.nextInt();
        String oldTitle = "";
        for (FYP fyp : fypList) {
            if (fyp.getProjectId() == projectID) {
                oldTitle = fyp.getTitle();
                ArrayList<String> fypList = supervisorAccount.getProjList();
                String temp = "";
                for (String indiproj : fypList) {
                    if (indiproj.equals(oldTitle)) {
                        System.out.println("Input new title: ");
                        String title = sc.next();
                        fyp.setTitle(title);
                        temp = title;
                    }
                }
                if (!temp.equals("")) {
                    supervisorAccount.getProjList().remove(oldTitle);
                    supervisorAccount.addProj(temp);
                    System.out.println("Project title has been change to " + fyp.getTitle());
                } else {
                    System.out.println("Error: You are not in charge of project " + oldTitle);
                }
            }
        }

    }
}
