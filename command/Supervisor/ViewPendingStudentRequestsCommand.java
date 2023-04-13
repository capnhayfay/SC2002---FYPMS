package command.Supervisor;

import FYPMS.FYPMS1;
import command.Command;

import FYPMS.request.*;

public class ViewPendingStudentRequestsCommand implements Command {
    private String Supervisor;

    public ViewPendingStudentRequestsCommand(String Supervisor) {
        this.Supervisor = Supervisor;
    }

    public void execute() {
        int RequestCount = 1;
        RequestList requests = FYPMS1.getRequestList();
        System.out.println();
        System.out.println("Pending Request");
        System.out.println();
        for (Request request : requests) {
            if (request.getRequesteeName().equals(Supervisor) && request.getRequestStatus() == RequestStatus.PENDING) {
                System.out.println("============= Request No. " + RequestCount++ + " ==============");
                request.printDetails();
                System.out.println();
            }
        }
    }

    public int checkUpdates() {
        RequestList requests = FYPMS1.getRequestList();
        for (Request request : requests) {
            if (request.getRequesteeName().equals(Supervisor) && request.getRequestStatus() == RequestStatus.PENDING) {
                return 1;
            }
        }
        return 0;
    }
}
