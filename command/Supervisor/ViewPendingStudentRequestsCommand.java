package command.Supervisor;

import command.Command;
import java.util.ArrayList;

import FYPMS.request.*;

public class ViewPendingStudentRequestsCommand implements Command {
    private String Supervisor;
    private RequestList requests;

    public ViewPendingStudentRequestsCommand(String Supervisor, RequestList requests){
        this.Supervisor = Supervisor;
        this.requests = requests;
    }

    public void execute() {
        int RequestCount = 1;
        RequestList requests = FYPMS.getRequestList();
        System.out.println();
        System.out.println("Pending Request");
        System.out.println();
        for (Request request : requests) {
            if () {
                System.out.println("============= Request No. " + RequestCount++ + " ==============");
                request.printDetails();
                System.out.println();
            }
        }

    }
}
