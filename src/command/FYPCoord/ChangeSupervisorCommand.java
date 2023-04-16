/**
 * A class that represents a src.command to change the supervisor of a project
 * already allocated to a student.
 */
package src.command.FYPCoord;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.FYPMS.project.FYPStatus;
import src.FYPMS.request.RequestStatus;
import src.FYPMS.request.RequestTransferSupervisor;
import src.account.AccountManager;
import src.account.supervisor.SupervisorAccount;
import src.command.Command;
import src.exceptions.fypmsExceptions;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Command for a Coordinator to manage Supervisor Transfer Requests
 */
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
            case 1 -> {
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
                            String choice = sc.next().toLowerCase();
                            switch (choice) {
                                case "y", "yes" -> {
                                    newsupervisorAccount.addProj(fyp.getTitle());
                                    transferRequest.setStatus(RequestStatus.APPROVED);
                                    fyp.setSupervisorName(newsupervisorAccount.getName());
                                    fyp.setSupervisorEmail(newsupervisorAccount.getEmail());
                                    System.out.println(newsupervisorAccount.getName() + " has a new project "
                                            + newsupervisorAccount.getProjList().get(idx));
                                }
                                case "n", "no" -> {
                                    transferRequest.setStatus(RequestStatus.REJECTED);
                                    System.out.println("Successfully rejected request.");
                                    return;
                                }
                                default -> {
                                        try {
                                            throw new fypmsExceptions.invalidInputException("Incorrect input, please try again or press 0 to exit");
                                        } catch (Exception e) {
                                            System.out.println(e.toString().subSequence(e.toString().indexOf(":") + 2, e.toString().length() - 1));

                                        }
                                }
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
                assert currentSupervisor != null;
                for (String currSupProject : currentSupervisor.getProjList()) {
                    if (currSupProject.equals(fyp.getTitle())) {
                        temp = fyp.getTitle();
                        break;
                    }

                }
                if (!temp.equals("")) {
                    currentSupervisor.getProjList().remove(temp);
                } else {
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
            }
            case 2 -> transferRequest.setStatus(RequestStatus.REJECTED);
            default -> System.out.print("Error: Invalid input. Returning to main menu....");
        }
    }
}
