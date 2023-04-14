package command.Supervisor;

import java.util.ArrayList;

import FYPMS.FYPMS1;
import FYPMS.request.*;

public class CheckPendingRequests {
    private String Supervisor;

    public CheckPendingRequests(String Supervisor) {
        this.Supervisor = Supervisor;
    }

    public int execute() {
        ArrayList<ArrayList<Object>> requests = FYPMS1.getRequestList();
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