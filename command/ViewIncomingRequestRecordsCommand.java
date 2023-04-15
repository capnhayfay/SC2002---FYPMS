/**
 * Represents a command that allows an account to view all incoming request records for that account.
 * This class implements the Command interface. It loops through all requests in the system and prints out the details
 * of each request if the requestee ID matches the login ID of the account.
*/

package command;

import FYPMS.request.Request;
import FYPMS.request.RequestHistory;
import account.Account;
import java.util.ArrayList;

public class ViewIncomingRequestRecordsCommand implements Command {
    private final Account account;

    /**
     * Constructs a ViewIncomingRequestRecordsCommand object with the specified account.
     * @param account the account for which the incoming request records need to be displayed.
    */
    public ViewIncomingRequestRecordsCommand(Account account) {
        this.account = account;
    }

    /**
     * Displays the incoming request records for the logged in account.
    */
    public void execute() {
        int RequestCount = 0;
        System.out.println();
        System.out.println("Request History");
        System.out.println();
        ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();
        for (ArrayList<Request> requestList : requestHistory) {
            for (Request request : requestList) {
                if (request.getRequesteeID().equals(account.getLoginId())) {
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
            System.out.print("You have " + RequestCount + " incoming requests.");
        }
    }
}
