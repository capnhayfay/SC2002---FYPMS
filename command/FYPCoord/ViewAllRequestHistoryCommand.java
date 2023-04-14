/**
A command class that implements the Command interface to view all the history of requests that were submitted to a coordinator.
*/
package command.FYPCoord;

import java.util.ArrayList;

import FYPMS.SCSE;
import FYPMS.request.Request;
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
        ArrayList<ArrayList<Object>> requests = SCSE.getRequestList();
        int empty = 1;
        for (ArrayList<Object> request : requests) {
            if (request.size() != 0) {
                empty = 0;
            }
            for (Object indivRequest : request) {
                Request indivCastedRequest = (Request) indivRequest;
                indivCastedRequest.printDetails();
            }
        }
        if (empty == 1) {
            System.out.println();
            System.out.println("There is no requests.");
        }
    }
}
