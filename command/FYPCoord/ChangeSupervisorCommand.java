package command.FYPCoord;

import FYPMS.faculty.supervisor.Supervisor;
import FYPMS.project.*;
import command.Command;
import FYPMS.FYPMS;
import FYPMS.faculty.supervisor.SupervisorList;

public class ChangeSupervisorCommand implements Command {
    private int FYPId;
    private int newSupervisorId;

    public ChangeSupervisorCommand(int FYPId, int newSupervisorId) {
        this.FYPId = FYPId;
        this.newSupervisorId = newSupervisorId;
    }

    public void execute() {
        FYPList projects = FYPMS.getFypList();
        FYP project = projects.getFYPById(FYPId);
        String currentSupervisorName = project.getSupervisorName();
        SupervisorList supervisors = FYPMS.getSupervisorList();
        Supervisor currentSupervisor = supervisors.getSupervisor(currentSupervisorName);

        System.out.println();
        System.out.println("Current Supervisor is: " + project.getSupervisorName());
        System.out.println("Change in progress");

        for (Supervisor supervisor : supervisors.getSupervisors()) {
            if (supervisor.getSupervisorId().equals(newSupervisorId)) {
                if (supervisor.getProj_1().equals("0")) {
                    supervisor.setProj_1(project.getTitle());
                } else if (supervisor.getProj_2().equals("0")) {
                    supervisor.setProj_2(project.getTitle());
                } else {
                    System.out.println("Supervisor has reached maximum number of projects");
                    return;
                }

                if (currentSupervisor.getProj_1().equals(project.getTitle())) {
                    currentSupervisor.setProj_1("0");
                } else if (currentSupervisor.getProj_2().equals(project.getTitle())) {
                    currentSupervisor.setProj_2("0");
                } else {
                    return;
                }

                project.setSupervisorName(supervisor.getName());
                System.out.println("New Supervisor is: " + project.getSupervisorName());
                return;
            }
        }

        System.out.println("Error");
    }
}
