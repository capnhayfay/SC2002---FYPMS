package command.FYPCoord;

import FYPMS.project.*;
import command.Command;

public class AllocateProjectCommand implements Command {
    private FYP project;
    private String studentName;

    public AllocateProjectCommand(FYP project, String studentName) {
        this.project = project;
        this.studentName = studentName;
    }

    public void execute() {
        System.out.println();
        project.setStatus(FYPStatus.ASSIGNED);
        project.setStudentName(studentName);
        System.out.println("Allocated project " + project.getTitle() + " to " + studentName);
    }

}
