/**
A command class that implements the Command interface to view all the history of requests that were submitted to a coordinator.
*/
package command.FYPCoord;

import java.util.ArrayList;

import FYPMS.request.Request;
import FYPMS.request.RequestHistory;
import command.Command;

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
        ArrayList<ArrayList<Request>> requests = RequestHistory.getRequestList();
        int empty = 1;
        for (ArrayList<Request> request : requests) {
            if (request.size() != 0) {
                empty = 0;
            }
            for (Request indivRequest : request) {
                indivRequest.printDetails();
            }
        }
        if (empty == 1) {
            System.out.println();
            System.out.println("There is no requests.");
        }
    }
}
