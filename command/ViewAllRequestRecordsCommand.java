package command;

import FYPMS.request.Request;
import FYPMS.request.RequestHistory;
import account.Account;
import java.util.ArrayList;

/**
 * 
 * This command class is used to view all request records of a user.
 */
public class ViewAllRequestRecordsCommand implements Command {
    private final Account account;

    /**
     * Constructor for the ViewAllRequestRecordsCommand class.
     *
     * @param account the account whose request records will be viewed
     */
    public ViewAllRequestRecordsCommand(Account account) {
        this.account = account;
    }

    /**
     * Executes the command to view all request records.
     */
    public void execute() {
        int RequestCount = 0;
        System.out.println();
        System.out.println("Request History");
        System.out.println();
        ArrayList<ArrayList<Object>> requests = RequestHistory.getRequestList();
        for (ArrayList<Object> request : requests) {
            for (Object indivRequest : request) {
                Request indivCastedRequest = (Request) indivRequest;
                if (indivCastedRequest.getRequesterID().equals(account.getLoginId())
                        || indivCastedRequest.getRequesteeID().equals(account.getLoginId())) {
                    System.out.println(
                            "============= Request ID " + indivCastedRequest.getRequestID() + " ==============");
                    indivCastedRequest.printDetails();
                    System.out.println();
                    RequestCount++;
                }

            }

        }

        if (RequestCount == 0) {
            System.out.println("You have no requests.");
        } else {
            System.out.print("You have " + RequestCount + " requests.");
        }
    }
}
