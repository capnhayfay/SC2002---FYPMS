package FYPMS.request;

public class RequestChangeTitle extends Request {
    private String newTitle;

    public RequestChangeTitle(int requestID, String requesterID, String requesteeID, RequestStatus requestStatus, int fypID,
            String newTitle) {
        super(requestID, requesterID, requesteeID, requestStatus, RequestRelationship.STUDENTSupervisor, RequestType.CHANGE_TITLE, fypID);
        this.newTitle = newTitle;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void printDetails() {
        System.out.println("Requester: " + getRequesterID());
        System.out.println("Requestee: " + getRequesteeID());
        System.out.println("Request Type: " + getRequestType() );
        System.out.println("Request Status: " + getRequestStatus());
        System.out.println("Request Relationship: " + getRequestRelationship());
        System.out.println("FYP ID: " + getFypID());
        System.out.println("New Project Title: " + newTitle);
        System.out.println();
    }

}
