package FYPMS.request;

import FYPMS.FYPMS1;

public class RequestRegister extends Request {

    public RequestRegister(int requestID, String requesterID, RequestStatus requestStatus,int fypID) {
        super(requestID, requesterID, FYPMS1.getCoordinatorList().get(0).getLoginId(), requestStatus, RequestRelationship.STUDENTCoordinator, RequestType.REGISTER_PROJECT, fypID);
    }

    public void printDetails() {
        System.out.println("Requester: " + getRequesterID());
        System.out.println("Requestee: " + getRequesteeID());
        System.out.println("Request Type: " + getRequestType() );
        System.out.println("Request Status: " + getRequestStatus());
        System.out.println("Request Relationship: " + getRequestRelationship());
        System.out.println("FYP ID: " + getFypID());
        System.out.println();
    }
}
