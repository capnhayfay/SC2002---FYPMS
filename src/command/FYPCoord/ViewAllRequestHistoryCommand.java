/**
 * A src.command class that implements the Command interface to view all the history of requests that were submitted to a coordinator.
 */
package src.command.FYPCoord;

import src.FYPMS.request.Request;
import src.FYPMS.request.RequestHistory;
import src.command.Command;

import java.util.ArrayList;

/**
 * Command for coordinator to view ALL Requests which were made
 */
public class ViewAllRequestHistoryCommand implements Command {
    /**
     * Constructs a new instance of the ViewAllRequestHistoryCommand.
     */
    public ViewAllRequestHistoryCommand() {

    }

    /**
     * Executes the ViewAllRequestHistoryCommand to display all the history of
     * requests that were submitted to a coordinator.
     */
    public void execute() {
        ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();
        int empty = 1;
        for (ArrayList<Request> requestList : requestHistory) {
            if (requestList.size() != 0) {
                empty = 0;
            }
            for (Request Request : requestList) {
                Request.printDetails();
            }
        }
        if (empty == 1) {
            System.out.println();
            System.out.println("There are no requests.");
        }
    }
}
