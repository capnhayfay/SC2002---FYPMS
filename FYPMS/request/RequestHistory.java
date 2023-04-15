package FYPMS.request;

import java.util.ArrayList;

public class RequestHistory {

    static final ArrayList<ArrayList<Request>> requests = new ArrayList<ArrayList<Request>>();

    public static ArrayList<ArrayList<Request>> getRequestList() {
        return requests;
    }
    

    public static RequestChangeTitle getRequestChangeTitle(int requestID) {
        for (Request indivRequest : requests.get(0)) {
            RequestChangeTitle indivCastedRequest = (RequestChangeTitle) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
    }

    public static RequestDeregister getRequestDeregister(int requestID) {
        for (Request indivRequest : requests.get(1)) {
            RequestDeregister indivCastedRequest = (RequestDeregister) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
    }

    public static RequestRegister getRequestRegister(int requestID) {
        for (Request indivRequest : requests.get(2)) {
            RequestRegister indivCastedRequest = (RequestRegister) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
    }

    public static RequestTransferSupervisor getRequestTransferSupervisor(int requestID) {
        for (Request indivRequest : requests.get(3)) {
            RequestTransferSupervisor indivCastedRequest = (RequestTransferSupervisor) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
    }
    public static int CheckPendingRequests(String Supervisor){
        for (ArrayList<Request> request : requests) {
            for (Request indivRequest : request) {
                if (indivRequest.getRequesteeID().equals(Supervisor)
                        && indivRequest.getRequestStatus() == RequestStatus.PENDING) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
