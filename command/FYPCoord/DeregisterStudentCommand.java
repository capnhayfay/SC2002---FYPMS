package command.FYPCoord;

import FYPMS.project.*;
import command.Command;

public class DeregisterStudentCommand implements Command {

    private String studentName;
    private FYP project;

    public DeregisterStudentCommand(FYP project, String studentName) {
        this.project = project;
        this.studentName = studentName;
    }

    public void execute() {
        System.out.println();
        project.setStatus(FYPStatus.AVAILABLE);
        project.setStudentName("");
        System.out.println("Student " + studentName + " has been deregistered");
    }

}
