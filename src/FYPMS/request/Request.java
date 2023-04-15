package src.FYPMS.request;

/**
 * Represents a Request List
 */
public class Request {

    private final String requesterID;
    private final String requesteeID;
    private final RequestRelationship requestRelationship; // enum
    private final RequestType requestType;
    private final int requestID;
    private final int fypID;
    private RequestStatus requestStatus;

    /**
     * Creates a request object for SCSE requests
     *
     * @param requestID           ID of request
     * @param requesterID         ID of requester
     * @param requesteeID         ID of person recieivng request
     * @param requestStatus       Enum for Status of request - Pending by default
     * @param requestRelationship Enum for relationship type (StudentSupervisor, StudentCoordinator, SupervisorCoordinator)
     * @param requestType         Enum for the type of request
     * @param fypID               ID of the FYP
     */
    public Request(int requestID, String requesterID, String requesteeID, RequestStatus requestStatus,
                   RequestRelationship requestRelationship, RequestType requestType, int fypID) {
        this.fypID = fypID;
        this.requestID = requestID;
        this.requesterID = requesterID;
        this.requesteeID = requesteeID;
        this.requestStatus = requestStatus;
        this.requestRelationship = requestRelationship;
        this.requestType = requestType;
    }

    /**
     * Prints the details of this instance of the request
     */
    public void printDetails() {
        System.out.println("Requester: " + getRequesterID());
        System.out.println("Requestee: " + getRequesteeID());
        System.out.println("Request Type: " + getRequestType());
        System.out.println("Request Status: " + getRequestStatus());
        System.out.println("Request Relationship: " + getRequestRelationship());
        System.out.println("FYP ID: " + getFypID());
        System.out.println();
    }

    /*
      Converts the request class in string form to RequestType enum

      @param classLevel Class of request in string form
     * @return RequestType enum
     */

    /**
     * Helper function to convert RequestType enum to string, for CSV storage
     *
     * @return Class of request in string form
     */

    public RequestRelationship getRequestRelationship() {
        return requestRelationship;
    }

    /**
     * Gets requester ID
     *
     * @return requesterID: String
     */
    public String getRequesterID() {
        return requesterID;
    }

    /**
     * Gets requestee ID
     *
     * @return requesteeID: String
     */
    public String getRequesteeID() {
        return requesteeID;
    }

    /**
     * Gets request status
     *
     * @return requestStatus: RequestStatus
     */
    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    /**
     * Gets FYP ID
     *
     * @return fypID: int
     */
    public int getFypID() {
        return fypID;
    }

    /**
     * Gets request ID
     *
     * @return requestID: int
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * Gets request type
     *
     * @return requestType: RequestType
     */
    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Sets request status
     *
     * @param requestStatus status to set for the request
     */
    public void setStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
