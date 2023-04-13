package FYPMS.request;

import FYPMS.FYPMS1;
import FYPMS.project.FYP;

public class RequestTransferSupervisor extends Request {
    private String requesterID;
    private String requesteeID;
    private RequestStatus requestStatus;
    private RequestRelationship requestRelationship; // enum
    private RequestType requestType;
    private int fypID;
    private String newSupervisorID;

    public RequestTransferSupervisor(String requesterID, String requesteeID,
            RequestStatus requestStatus, RequestRelationship requestRelationship, RequestType requestType, int fypID) {
        super(requesterID, FYPMS1.getCoordinatorList().get(0).getLoginId(), requestStatus, requestRelationship, requestType);
        this.fypID = fypID;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getFypID() {
        return fypID;
    }

    public String getNewSupervisorID() {
        return newSupervisorID;
    }
}
