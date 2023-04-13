package FYPMS.request;

import FYPMS.FYPMS1;
import FYPMS.project.FYP;

public class RequestRegister extends Request {
    private String requesterID;
    private String requesteeID;
    private RequestStatus requestStatus;
    private RequestRelationship requestRelationship; // enum
    private RequestType requestType;
    private int fypID;

    public RequestRegister(String requesterID, String requesteeID,
            RequestStatus requestStatus, RequestRelationship requestRelationship, RequestType requestType, int fypID) {
        super(requesterID, FYPMS1.getCoordinatorList().get(0).getLoginId(), requestStatus, requestRelationship, requestType);
        this.fypID = fypID;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequesterID() {
        return requesterID;
    }

    public int getFypID() {
        return fypID;
    }
}
