
package command.Student;

import java.util.Scanner;
import java.util.ArrayList;
import FYPMS.request.*;
import account.student.*;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.project.FYPStatus;
import command.Command;

public class RequestCoordFYPCommand implements Command {
    private final StudentAccount studentAccount;

    public RequestCoordFYPCommand(StudentAccount currentAcc) {
        this.studentAccount = currentAcc;
    }

    final ArrayList<ArrayList<Request>> requests = RequestHistory.getRequestList();

    public void execute() {
        if (studentAccount.getStatus() == StudentStatus.ASSIGNED_PROJECT) {
            System.out.println("Error: You are already registered for an FYP.");
            return;
        } else if (studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
            System.out.println("Error: You have deregistered for an FYP.");
            return;
        } else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
            System.out.println("Error: You have a pending registration.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Input project ID: ");
        int fypID = sc.nextInt();
        ArrayList<FYP> fyps = FYPList.getFypList();
        for (FYP fyp : fyps) {
            if (fyp.getProjectId() == fypID) {
                if (!(fyp.getStatus().equals(FYPStatus.AVAILABLE))) {
                    System.out.println("Error: Project is unavailable for registration.");
                    return;
                }
                fyp.setStatus(FYPStatus.RESERVED);
                break;
            }
        }
        studentAccount.setStatus(StudentStatus.REQUESTED_PROJECT);
        RequestRegister registerRequest = new RequestRegister(requests.get(2).size() + 2000,
                studentAccount.getLoginId(), RequestStatus.PENDING,
                fypID);
        requests.get(2).add(registerRequest);
        System.out.println("Successfully Applied for project " + fypID);
    }

}
