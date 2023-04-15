/**
 * A class that implements the Command interface to view all pending requests.
*/
package command.FYPCoord;

import command.Command;

import java.util.ArrayList;

import FYPMS.request.Request;
import FYPMS.request.RequestHistory;
import FYPMS.request.RequestStatus;

public class ViewAllPendingRequestsCommand implements Command {
    /**
     * Executes the command to view all pending requests.
     * Retrieves the list of requests from the FYPMS system and iterates through
     * each request.
     * If a request is pending, its details are printed to the console.
     */

    public void execute() {
        ArrayList<ArrayList<Request>> requests = RequestHistory.getRequestList();
        for (ArrayList<Request> request : requests) {
            for (Request indivRequest : request) {
                if (indivRequest.getRequestStatus() == RequestStatus.PENDING) {
                    indivRequest.printDetails();
                }
            }
        }
    }
}
