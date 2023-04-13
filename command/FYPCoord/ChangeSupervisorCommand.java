package command.FYPCoord;

import FYPMS.FYPMS1;
import FYPMS.project.*;
import account.supervisor.SupervisorAccount;
import command.Command;

import java.util.ArrayList;

public class ChangeSupervisorCommand implements Command {
    private int FYPId;
    private String newSupervisorId;

    public ChangeSupervisorCommand(int FYPId, String newSupervisorId) {
        this.FYPId = FYPId;
        this.newSupervisorId = newSupervisorId;
    }

    public void execute() {
        FYPList projects = FYPMS1.getFypList();
        FYP project = projects.getFYPById(FYPId);
        String currentSupervisorName = project.getSupervisorName();
        ArrayList<SupervisorAccount> supervisors = FYPMS1.getSupervisorList();
        SupervisorAccount currentSupervisor = null;
        for (SupervisorAccount supervisor: supervisors) {
            if (supervisor.getName().equalsIgnoreCase(currentSupervisorName)){
                currentSupervisor = supervisor;
                break;
            }
        }

        System.out.println();
        System.out.println("Current Supervisor is: " + project.getSupervisorName());
        System.out.println("Change in progress");

        for (SupervisorAccount supervisor : supervisors) {
            if (supervisor.getName().equalsIgnoreCase(newSupervisorId)) {
                if (supervisor.getProj_1().equals("0")) {
                    assert currentSupervisor != null;
                    currentSupervisor.setProj_1(project.getTitle());
                } else if (supervisor.getProj_2().equals("0")) {
                    assert currentSupervisor != null;
                    currentSupervisor.setProj_2(project.getTitle());
                } else {
                    System.out.println("Supervisor has reached maximum number of projects");
                    return;
                }

                if (supervisor.getProj_1().equals(project.getTitle())) {
                    currentSupervisor.setProj_1("0");
                } else {
                    if (currentSupervisor.getProj_2().equals(project.getTitle())) {
                        currentSupervisor.setProj_2("0");
                    } else {
                        return;
                    }
                }

                project.setSupervisorName(supervisor.getName());
                System.out.println("New Supervisor is: " + project.getSupervisorName());
                return;
            }
        }

        System.out.println("Error");
    }
}
