package command.Student;

import java.util.ArrayList;

import FYPMS.FYPMS;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import command.Command;

public class ViewRegisteredFYPCommand implements Command {
    private String Student;

    public ViewRegisteredFYPCommand(String Student) {
        this.Student = Student;
    }

    public void execute() {
        FYPList projects = FYPMS.getFypList();
        ArrayList<FYP> fyps = projects.getFYPs();
        System.out.println();
        for (FYP fyp : fyps) {
            if (fyp.getStudentName().equals(Student)) {
                fyp.printFYPDetails();
                System.out.println();
                break;
            }
        }
    }
}
