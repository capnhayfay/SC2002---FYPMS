package command.Supervisor;

import command.Command;
import FYPMS.FYPMS;
import FYPMS.project.*;

public class ModifySubmittedFYPTitleCommand implements Command {
    private FYP project;
    private String newtitle;

    public ModifySubmittedFYPTitleCommand(FYP project, String newtitle){
        this.project = project;
        this.newtitle = newtitle;
    }

    public void execute() {
        System.out.println();
        project.setTitle(newtitle);
        System.out.println("Title has been change to : "+ newtitle);
    }
}
