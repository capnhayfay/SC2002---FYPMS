/**
A command class that implements the Command interface to view all the history of requests that were submitted to a coordinator.
*/
package command.FYPCoord;

import java.util.ArrayList;

import FYPMS.FYPMS1;
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
        ArrayList<ArrayList<Object>> requests = FYPMS1.getRequestList();
        for (ArrayList<Object> request : requests) {
            for (Object indivRequest : request) {
                Request indivCastedRequest = (Request) indivRequest;
                indivCastedRequest.printDetails();
            }
        }
    }
}
