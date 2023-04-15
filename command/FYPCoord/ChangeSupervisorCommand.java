/**

    A class that represents a command to change the supervisor of a project
    already allocated to a student.
    */
package command.FYPCoord;

import FYPMS.project.*;
import FYPMS.request.RequestStatus;
import FYPMS.request.RequestTransferSupervisor;
import account.AccountManager;
import account.supervisor.SupervisorAccount;
import command.Command;

import java.util.ArrayList;
import java.util.Scanner;

public class ChangeSupervisorCommand implements Command {
    private final RequestTransferSupervisor transferRequest;

    /**
     * Constructor for the ChangeSupervisorCommand class.
     * 
     * @param transferRequest the transfer request for the change of supervisor
     */
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
        int requestAction = sc.nextInt();
        System.out.println("=========================================");
        switch (requestAction) {
            case 1:
                int FYPId = transferRequest.getFypID();
                String newSupervisorName = transferRequest.getNewSupervisorID();
                FYP fyp = FYPList.getFYPById(FYPId);
                String currentSupervisorName = fyp.getSupervisorName();
                ArrayList<SupervisorAccount> supervisorList = AccountManager.getSupervisorList();
                SupervisorAccount currentSupervisor = null;
                for (SupervisorAccount supervisorAccount : supervisorList) {
                    if (supervisorAccount.getName().equalsIgnoreCase(currentSupervisorName)) {
                        currentSupervisor = supervisorAccount;
                        break;
                    }
                }
                int idx = 0;
                for (SupervisorAccount newsupervisorAccount : supervisorList) {
                    if (newsupervisorAccount.getLoginId().equalsIgnoreCase(newSupervisorName)) {
                        if (newsupervisorAccount.getProjList().size() >= 2) {
                            System.out.println(
                                    "Warning: " + newSupervisorName + " is already in charge of at least 2 projects");
                            System.out.println("Do you wish to proceed? Y/N");
                            String choice = sc.next();
                            switch (choice) {
                                case "Y":
                                case "Yes":
                                    newsupervisorAccount.addProj(fyp.getTitle());
                                    transferRequest.setStatus(RequestStatus.APPROVED);
                                    fyp.setSupervisorName(newsupervisorAccount.getName());
                                    fyp.setSupervisorEmail(newsupervisorAccount.getEmail());
                                    System.out.println(newsupervisorAccount.getName() + " has a new project "
                                            + newsupervisorAccount.getProjList().get(idx));
                                    break;
                                case "N":
                                case "No":
                                    transferRequest.setStatus(RequestStatus.REJECTED);
                                    System.out.println("Succesfully rejected request.");
                                    return;
                            }
                        } else {
                            newsupervisorAccount.addProj(fyp.getTitle());
                            transferRequest.setStatus(RequestStatus.APPROVED);
                            fyp.setSupervisorName(newsupervisorAccount.getName());
                            fyp.setSupervisorEmail(newsupervisorAccount.getEmail());
                            System.out.println(
                                newsupervisorAccount.getName() + " has a new project " + newsupervisorAccount.getProjList().get(idx));
                            if (newsupervisorAccount.getProjList().size() == 2) {
                                for (FYP project : FYPList.getSuperFypList(newsupervisorAccount.getName())) {
                                    if (project.getStatus().equals(FYPStatus.AVAILABLE)) {
                                        project.setStatus(FYPStatus.UNAVAILABLE);
                                    }

                                }
                            }

                        }
                        idx++;
                    }
                }

                String temp = "";
                for (String currSupProject : currentSupervisor.getProjList()) {
                    if (currSupProject.equals(fyp.getTitle())) {
                        temp = fyp.getTitle();
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
                System.out.println("Current Supervisor is: " + fyp.getSupervisorName());
                System.out.println("Change in progress");

                for (FYP project : FYPList.getSuperFypList(currentSupervisor.getName())) {
                    if (project.getStatus().equals(FYPStatus.UNAVAILABLE)) {
                        project.setStatus(FYPStatus.AVAILABLE);
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
