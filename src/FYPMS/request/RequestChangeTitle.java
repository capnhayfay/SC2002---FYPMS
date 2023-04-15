package src.FYPMS.request;

/**
 * Change title RequestClass extension of Requests
 */
public class RequestChangeTitle extends Request {
    private final String newTitle;

    /**
     * Constructor for RequestTitleChange
     *
     * @param requestID     ID of request
     * @param requesterID   ID of requester
     * @param requesteeID   ID of person getting request
     * @param requestStatus Enum status of request - Pending by default
     * @param fypID         ID of fyp
     * @param newTitle      New title of FYP
     */
    public RequestChangeTitle(int requestID, String requesterID, String requesteeID, RequestStatus requestStatus, int fypID,
                              String newTitle) {
        super(requestID, requesterID, requesteeID, requestStatus, RequestRelationship.STUDENTSupervisor, RequestType.CHANGE_TITLE, fypID);
        this.newTitle = newTitle;
    }

    /**
     * Gets new title
     *
     * @return newTitle: String
     */
    public String getNewTitle() {
        return newTitle;
    }

    /**
     * Prints details of the Change Title Request
     */
    public void printDetails() {
        System.out.println("Requester: " + getRequesterID());
        System.out.println("Requestee: " + getRequesteeID());
        System.out.println("Request Type: " + getRequestType());
        System.out.println("Request Status: " + getRequestStatus());
        System.out.println("Request Relationship: " + getRequestRelationship());
        System.out.println("FYP ID: " + getFypID());
        System.out.println("New Project Title: " + newTitle);
        System.out.println();
    }

}
