package FYPMS.request;

import java.util.ArrayList;

public class RequestHistory {

    static final ArrayList<ArrayList<Request>> requestHistory = new ArrayList<ArrayList<Request>>();

    public static ArrayList<ArrayList<Request>> getRequestHistory() {
        return requestHistory;
    }
    

    public static RequestChangeTitle getRequestChangeTitle(int requestID) {
        for (Request request : requestHistory.get(0)) {
            RequestChangeTitle castedRequest = (RequestChangeTitle) request;
            if (castedRequest.getRequestID() == requestID) {
                return castedRequest;
            }
        }
        return null;
    }

    public static RequestDeregister getRequestDeregister(int requestID) {
        for (Request request : requestHistory.get(1)) {
            RequestDeregister castedRequest = (RequestDeregister) request;
            if (castedRequest.getRequestID() == requestID) {
                return castedRequest;
            }
        }
        return null;
    }

    public static RequestRegister getRequestRegister(int requestID) {
        for (Request request : requestHistory.get(2)) {
            RequestRegister castedRequest = (RequestRegister) request;
            if (castedRequest.getRequestID() == requestID) {
                return castedRequest;
            }
        }
        return null;
    }

    public static RequestTransferSupervisor getRequestTransferSupervisor(int requestID) {
        for (Request request : requestHistory.get(3)) {
            RequestTransferSupervisor castedRequest = (RequestTransferSupervisor) request;
            if (castedRequest.getRequestID() == requestID) {
                return castedRequest;
            }
        }
        return null;
    }
    public static int CheckPendingRequests(String Supervisor){
        for (ArrayList<Request> requestList : requestHistory) {
            for (Request request : requestList) {
                if (request.getRequesteeID().equals(Supervisor)
                        && request.getRequestStatus() == RequestStatus.PENDING) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
