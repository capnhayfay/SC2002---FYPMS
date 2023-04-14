package command.Supervisor;

import java.util.ArrayList;
import java.util.Scanner;

import command.Command;
import FYPMS.FYPMS1;
import FYPMS.project.FYPList;
import FYPMS.project.FYPStatus;
import FYPMS.request.*;
import account.supervisor.SupervisorAccount;

public class RequestTransfertoCoordCommand implements Command {
    private SupervisorAccount supervisorAccount;

    public RequestTransfertoCoordCommand(SupervisorAccount supervisorAccount) {
        this.supervisorAccount = supervisorAccount;
    }

    ArrayList<ArrayList<Object>> requests = FYPMS1.getRequestList();

    public void execute() {

        System.out.println("Input project ID to transfer: ");
        Scanner sc = new Scanner(System.in);
        int fypId = sc.nextInt();

        if (FYPList.getFYPById(fypId).getStatus().equals(FYPStatus.ALLOCATED)) {
            System.out.println("Input new supervisor ID: ");
            String newSupervisorID = sc.next();

            ArrayList<SupervisorAccount> supervisors = FYPMS1.getSupervisorList();
            for (SupervisorAccount indi : supervisors) {
                if (indi.getLoginId().equals(newSupervisorID)
                        && !(newSupervisorID.equals(supervisorAccount.getLoginId()))) {
                    RequestTransferSupervisor request = new RequestTransferSupervisor(requests.get(3).size() + 3000,
                            supervisorAccount.getLoginId(), RequestStatus.PENDING, fypId, newSupervisorID);
                    requests.get(3).add(request);
                    return;
                }
            }
            System.out.println();
            System.out.println("Invalid SupervisorID entered");

        } else {
            System.out.println("Error: FYP is not allocated to any student");
        }
    }
}
