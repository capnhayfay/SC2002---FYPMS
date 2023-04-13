package command.Student;

import java.util.ArrayList;

import FYPMS.FYPMS1;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import account.student.StudentAccount;
import command.Command;

public class ViewRegisteredFYPCommand implements Command {
    private StudentAccount studentAccount;

    public ViewRegisteredFYPCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }

    public void execute() {
        FYPList projects = FYPMS1.getFypList();
        ArrayList<FYP> fyps = projects.getFYPs();
        System.out.println();
        for (FYP fyp : fyps) {
            if (fyp.getStudentID().equals(studentAccount.getLoginId())) {
                fyp.printFYPDetails();
                System.out.println();
                break;
            }
        }
    }
}
