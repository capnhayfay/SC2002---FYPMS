package FYPMS.request;

public class RequestChangeTitle extends Request {

    private String requesterID;
    private String requesteeID;
    private RequestStatus requestStatus;
    private RequestRelationship requestRelationship; // enum
    private RequestType requestType;
    private int fypID;
    private String newTitle;

    public RequestChangeTitle(String requesterID, String requesteeID,
            RequestStatus requestStatus, RequestRelationship requestRelationship, RequestType requestType, int fypID,
            String newTitle) {
        super(requesterID, requesteeID, requestStatus, requestRelationship, requestType);
        this.fypID = fypID;
        this.newTitle = newTitle;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getFypID() {
        return fypID;
    }

    public String getNewTitle() {
        return newTitle;
    }

}
