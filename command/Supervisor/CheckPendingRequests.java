/**
 * The CheckPendingRequests class is responsible for checking if there are any pending requests
 * for a supervisor in the FYPMS system.
*/
package command.Supervisor;

import java.util.ArrayList;

import FYPMS.SCSE;
import FYPMS.request.*;

public class CheckPendingRequests {
    private final String Supervisor;

    public CheckPendingRequests(String Supervisor) {
        this.Supervisor = Supervisor;
    }

    /**
     * This method checks if there are any pending requests for the supervisor and
     * returns an integer
     * value based on the result. If there is at least one pending request for the
     * supervisor, the method
     * returns 1. Otherwise, it returns 0.
     *
     * @return 1 if there is at least one pending request for the supervisor, 0
     *         otherwise.
     */
    public int execute() {
        ArrayList<ArrayList<Object>> requests = SCSE.getRequestList();
        for (ArrayList<Object> request : requests) {
            for (Object indivRequest : request) {
                Request indivCastedRequest = (Request) indivRequest;
                if (indivCastedRequest.getRequesteeID().equals(Supervisor)
                        && indivCastedRequest.getRequestStatus() == RequestStatus.PENDING) {
                    return 1;
                }
            }
        }
        return 0;
    }
}