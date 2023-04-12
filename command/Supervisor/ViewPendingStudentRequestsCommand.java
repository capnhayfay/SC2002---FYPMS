package command.Supervisor;

import command.Command;

import FYPMS.FYPMS;
import FYPMS.request.*;

public class ViewPendingStudentRequestsCommand implements Command {
    private String Supervisor;

    public ViewPendingStudentRequestsCommand(String Supervisor) {
        this.Supervisor = Supervisor;
    }

    public void execute() {
        int RequestCount = 1;
        RequestList requests = FYPMS.getRequestList();
        System.out.println();
        System.out.println("Pending Request");
        System.out.println();
        for (Request request : requests) {
            if (request.getRequesteeName().equals(Supervisor)) {
                System.out.println("============= Request No. " + RequestCount++ + " ==============");
                request.printDetails();
                System.out.println();
            }
        }

    }
}
