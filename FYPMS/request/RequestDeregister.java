package FYPMS.request;

import FYPMS.project.FYP;

public class RequestDeregister extends Request {
    private String requesterID;
    private String requesteeID;
    private RequestStatus requestStatus;
    private RequestRelationship requestRelationship; // enum
    private RequestType requestType;
    private int fypID;

    public RequestDeregister(String requesterID, String requesteeID,
            RequestStatus requestStatus, RequestRelationship requestRelationship, RequestType requestType, int fypID) {
        super(requesterID, requesteeID, requestStatus, requestRelationship, requestType);
        this.fypID = fypID;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
