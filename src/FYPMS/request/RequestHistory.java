package src.FYPMS.request;

import java.util.ArrayList;

/**
 * Class to manage request history
 */
public class RequestHistory {

    static final ArrayList<ArrayList<Request>> requestHistory = new ArrayList<ArrayList<Request>>();

    /**
     * Default constructor for request history manager
     */
    public RequestHistory() {
    }

    /**
     * Gets request history
     *
     * @return requestHistory: Nested ArrayList of Requests
     */
    public static ArrayList<ArrayList<Request>> getRequestHistory() {
        return requestHistory;
    }


    /**
     * Gets request change title type of requests
     *
     * @param requestID ID of request
     * @return request: RequestChangeTitle
     */
    public static RequestChangeTitle getRequestChangeTitle(int requestID) {
        for (Request request : requestHistory.get(0)) {
            RequestChangeTitle castedRequest = (RequestChangeTitle) request;
            if (castedRequest.getRequestID() == requestID) {
                return castedRequest;
            }
        }
        return null;
    }

    /**
     * Gets deregister request by ID
     *
     * @param requestID ID of request
     * @return request: RequestDeregister
     */
    public static RequestDeregister getRequestDeregister(int requestID) {
        for (Request request : requestHistory.get(1)) {
            RequestDeregister castedRequest = (RequestDeregister) request;
            if (castedRequest.getRequestID() == requestID) {
                return castedRequest;
            }
        }
        return null;
    }

    /**
     * Find and return register request by ID
     *
     * @param requestID ID to search by
     * @return request: RequestRegister
     */
    public static RequestRegister getRequestRegister(int requestID) {
        for (Request request : requestHistory.get(2)) {
            RequestRegister castedRequest = (RequestRegister) request;
            if (castedRequest.getRequestID() == requestID) {
                return castedRequest;
            }
        }
        return null;
    }

    /**
     * Returns Transfer Supervisor Request by ID
     *
     * @param requestID ID to be searched by
     * @return request: RequestTransferSupervisor
     */
    public static RequestTransferSupervisor getRequestTransferSupervisor(int requestID) {
        for (Request request : requestHistory.get(3)) {
            RequestTransferSupervisor castedRequest = (RequestTransferSupervisor) request;
            if (castedRequest.getRequestID() == requestID) {
                return castedRequest;
            }
        }
        return null;
    }

    /**
     * Check pending requests for a given supervisor
     *
     * @param Supervisor Name of supervisor
     * @return status of pending requests, 1 if there are 0 otherwise: int
     */
    public static int CheckPendingRequests(String Supervisor) {
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
