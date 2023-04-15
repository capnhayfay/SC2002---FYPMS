/**
 * A src.command to view outgoing request records for a given src.account.
 */
package src.command;

import src.FYPMS.request.Request;
import src.FYPMS.request.RequestHistory;
import src.account.Account;

import java.util.ArrayList;

/**
 * Class for viewing and src.account's outgoing requests
 */
public class ViewOutcomingRequestRecordsCommand implements Command {

    private final Account account;

    /**
     * Constructs a ViewOutcomingRequestRecordsCommand with the given src.account.
     *
     * @param account the src.account whose outgoing requests will be displayed
     */
    public ViewOutcomingRequestRecordsCommand(Account account) {
        this.account = account;
    }

    /**
     * Executes the src.command to display the outgoing request records for the given src.account.
     * Displays the request ID, request details and request status for each outgoing request.
     */
    public void execute() {
        int RequestCount = 0;
        System.out.println();
        System.out.println("Request History");
        System.out.println();
        ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();
        for (ArrayList<Request> requestList : requestHistory) {
            for (Request request : requestList) {
                if (request.getRequesterID().equals(account.getLoginId())) {
                    System.out.println(
                            "============= Request ID " + request.getRequestID() + " ==============");
                    request.printDetails();
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
