package FYPMS.request;

import java.util.ArrayList;

public class RequestHistory {

    static final ArrayList<ArrayList<Object>> requests = new ArrayList<ArrayList<Object>>();

    public static ArrayList<ArrayList<Object>> getRequestList() {
        return requests;
    }

    public static RequestChangeTitle getRequestChangeTitle(int requestID) {
        for (Object indivRequest : requests.get(0)) {
            RequestChangeTitle indivCastedRequest = (RequestChangeTitle) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
    }

    public static RequestDeregister getRequestDeregister(int requestID) {
        for (Object indivRequest : requests.get(1)) {
            RequestDeregister indivCastedRequest = (RequestDeregister) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
    }

    public static RequestRegister getRequestRegister(int requestID) {
        for (Object indivRequest : requests.get(2)) {
            RequestRegister indivCastedRequest = (RequestRegister) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
    }

    public static RequestTransferSupervisor getRequestTransferSupervisor(int requestID) {
        for (Object indivRequest : requests.get(3)) {
            RequestTransferSupervisor indivCastedRequest = (RequestTransferSupervisor) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
    }
}
