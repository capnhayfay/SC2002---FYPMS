package command;

import FYPMS.request.Request;
import FYPMS.request.RequestHistory;
import account.Account;
import java.util.ArrayList;

public class ViewOutcomingRequestRecordsCommand implements Command {
    private final Account account;

    public ViewOutcomingRequestRecordsCommand(Account account) {
        this.account = account;
    }

    public void execute() {
        int RequestCount = 0;
        System.out.println();
        System.out.println("Request History");
        System.out.println();
        ArrayList<ArrayList<Request>> requests = RequestHistory.getRequestList();
        for (ArrayList<Request> request : requests) {
            for (Request indivRequest : request) {
                if (indivRequest.getRequesterID().equals(account.getLoginId())) {
                    System.out.println(
                            "============= Request ID " + indivRequest.getRequestID() + " ==============");
                            indivRequest.printDetails();
                    System.out.println();
                    RequestCount++;
                }

            }

        }

        if (RequestCount == 0) {
            System.out.println("You have no incoming requests.");
        } else {
            System.out.print("You have " + RequestCount + " outgoing requests.");
        }
    }
}
