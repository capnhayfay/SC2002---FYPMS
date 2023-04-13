package FYPMS.request;

/**
 * Represents a Request List
 */
public class Request {

    private final String requesterID;
    private final String requesteeID;
    private RequestStatus requestStatus;
    private final RequestRelationship requestRelationship; // enum
    private final RequestType requestType;
    private final int requestID;
    private int fypID;

    /**
     * Creates a request object for FYPMS1 requests
     *
     * @param requesterName    Name of requester
     * @param requestType      Request Type
     * @param statusChangeTime Time of Request
     * @param requestee
     * @param requestStatus
     */
    public Request(int requestID, String requesterID,String requesteeID, RequestStatus requestStatus, RequestRelationship requestRelationship, RequestType requestType, int fypID) {
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
        System.out.println("Request Type: " + getRequestType() );
        System.out.println("Request Status: " + getRequestStatus());
        System.out.println("Request Relationship: " + getRequestRelationship());
        System.out.println("FYP ID: " + getFypID());
        System.out.println();
    }

    /**
     * Converts the request class in string form to RequestType enum
     * 
     * @param classLevel Class of request in string form
     * @return RequestType enum
     */

    /**
     * Helper function to convert RequestType enum to string, for CSV storage
     * 
     * @param requestType RequestType enum
     * @return Class of request in string form
     */
    

    public RequestRelationship getRequestRelationship() {
        return requestRelationship;
    }

    public String getRequesterID() {
        return requesterID;
    }

    public String getRequesteeID() {
        return requesteeID;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public int getFypID(){
        return fypID;
    }

    public int getRequestID(){
        return requestID;
    }

    public RequestType getRequestType(){
        return requestType;
    }

    public void setStatus(RequestStatus requestStatus){
        this.requestStatus = requestStatus;
    }
}
