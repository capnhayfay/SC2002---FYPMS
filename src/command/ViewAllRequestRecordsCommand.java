package src.command;

import src.FYPMS.request.Request;
import src.FYPMS.request.RequestHistory;
import src.account.Account;

import java.util.ArrayList;

/**
 * This src.command class is used to view all request records of a user.
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
     * Executes the src.command to view all request records.
     */
    public void execute() {
        int RequestCount = 0;
        System.out.println();
        System.out.println("Request History");
        System.out.println();
        ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();
        for (ArrayList<Request> requestList : requestHistory) {
            for (Request request : requestList) {
                if (request.getRequesterID().equals(account.getLoginId())
                        || request.getRequesteeID().equals(account.getLoginId())) {
                    System.out.println(
                            "============= Request ID " + request.getRequestID() + " ==============");
                    request.printDetails();
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
