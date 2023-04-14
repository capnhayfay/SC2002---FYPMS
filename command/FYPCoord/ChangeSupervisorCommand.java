package command.FYPCoord;

import FYPMS.FYPMS1;
import FYPMS.project.*;
import FYPMS.request.RequestStatus;
import FYPMS.request.RequestTransferSupervisor;
import account.supervisor.SupervisorAccount;
import command.Command;

import java.util.ArrayList;
import java.util.Scanner;

public class ChangeSupervisorCommand implements Command {
    private RequestTransferSupervisor transferRequest;

    public ChangeSupervisorCommand(RequestTransferSupervisor transferRequest) {
        this.transferRequest = transferRequest;
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        transferRequest.printDetails();
        System.out.println();
        System.out.println("Select option:");
        System.out.println("1. Accept transfer request");
        System.out.println("2. Reject transfer request");
        switch (sc.nextInt()) {
            case 1:
                int FYPId = transferRequest.getFypID();
                String newSupervisorName = transferRequest.getNewSupervisorID();
                FYP project = FYPList.getFYPById(FYPId);
                String currentSupervisorName = project.getSupervisorName();
                ArrayList<SupervisorAccount> supervisors = FYPMS1.getSupervisorList();
                SupervisorAccount currentSupervisor = null;
                for (SupervisorAccount supervisor : supervisors) {
                    if (supervisor.getName().equalsIgnoreCase(currentSupervisorName)) {
                        currentSupervisor = supervisor;
                        break;
                    }
                }

                String temp = "";
                for (String currSupProject : currentSupervisor.getProjList()) {
                    if (currSupProject.equals(project.getTitle())) {
                        temp = project.getTitle();
                        break;
                    }
                }

                if (!temp.equals("")) {
                    currentSupervisor.getProjList().remove(temp);
                } else if (temp.equals("")) {
                    System.out.println("The requester is not in charge of this project");
                    transferRequest.setStatus(RequestStatus.REJECTED);
                    return;
                }
                System.out.println();
                System.out.println("Current Supervisor is: " + project.getSupervisorName());
                System.out.println("Change in progress");
                int idx = 0;
                for (SupervisorAccount nsupervisor : supervisors) {
                    if (nsupervisor.getLoginId().equalsIgnoreCase(newSupervisorName)) {
                        if (nsupervisor.getProjList().size() >= 2) {
                            System.out.println("Warning: Supervisor is already in charge of at least 2 projects");
                            System.out.println("Do you wish to proceed? Y/N");
                            String choice = sc.next();
                            switch (choice) {
                                case "Y":
                                case "Yes":
                                    nsupervisor.addProj(project.getTitle());
                                    transferRequest.setStatus(RequestStatus.APPROVED);
                                    project.setSupervisorName(nsupervisor.getName());
                                    project.setSupervisorEmail(nsupervisor.getEmail());
                                    System.out.println(nsupervisor.getName() + " has a new project "
                                            + nsupervisor.getProjList().get(idx));
                                    break;
                                case "N":
                                case "No":
                                    transferRequest.setStatus(RequestStatus.REJECTED);
                                    System.out.println("Succesfully rejected request.");
                                    break;
                            }
                        } else {
                            nsupervisor.addProj(project.getTitle());
                            transferRequest.setStatus(RequestStatus.APPROVED);
                            project.setSupervisorName(nsupervisor.getName());
                            project.setSupervisorEmail(nsupervisor.getEmail());
                            System.out.println(
                                    nsupervisor.getName() + " has a new project " + nsupervisor.getProjList().get(idx));
                        }
                        idx++;
                    }
                }
                break;
            case 2:
                transferRequest.setStatus(RequestStatus.REJECTED);
                break;
            default:
                System.out.print("Error: Invalid input. Returning to main menu....");
                break;
        }
    }
}
