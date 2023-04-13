package FYPMS.request;

import java.time.LocalDateTime;

/**
 * Represents a Request List
 */
public class Request {

    private final String requesterID;
    private final String requesteeID;
    private final RequestStatus requestStatus;
    private final RequestRelationship requestRelationship; // enum
    private final RequestType requestType;

    /**
     * Creates a request object for FYPMS1 requests
     *
     * @param requesterName    Name of requester
     * @param requestType      Request Type
     * @param statusChangeTime Time of Request
     * @param requestee
     * @param requestStatus
     */
    public Request(String requesterID, String requesteeID,
            RequestStatus requestStatus, RequestRelationship requestRelationship, RequestType requestType) {
        this.requesterID = requesterID;
        this.requesteeID = requesteeID;
        this.requestStatus = requestStatus;
        this.requestRelationship = requestRelationship;
        this.requestType = requestType;
    }

    public static RequestStatus convertToRequestStatus(String classLevel) {
        return switch (classLevel.toLowerCase()) {
            case "pending" -> RequestStatus.PENDING;
            case "approved" -> RequestStatus.APPROVED;
            case "rejected" -> RequestStatus.REJECTED;
            default -> null;
        };
    }

    /**
     * Prints the details of this instance of the request
     */
    public void printDetails() {
        System.out.println("Requester: " + requesterID);
        System.out.println("Requestee: " + requesteeID);
        System.out.println("Request Type: " + requestType);
        System.out.println("Request Status: " + requestStatus);
        System.out.println();
    }

    /**
     * Converts the request class in string form to RequestType enum
     * 
     * @param classLevel Class of request in string form
     * @return RequestType enum
     */
    public static RequestRelationship convertToRequestType(String classLevel) {
        return switch (classLevel.toLowerCase()) {
            case "studentcoord" -> RequestRelationship.STUDENTCoordinator;
            case "studentsupervisor" -> RequestRelationship.STUDENTSupervisor;
            case "supervisorcoordinator" -> RequestRelationship.SUPERVISORCoordinator;
            default -> null;
        };
    }

    /**
     * Helper function to convert RequestType enum to string, for CSV storage
     * 
     * @param requestType RequestType enum
     * @return Class of request in string form
     */
    public static String convertRequestTypeToString(RequestRelationship requestType) {
        return switch (requestType) {
            case STUDENTSupervisor -> "StudentSupervisor";
            case STUDENTCoordinator -> "StudentCoordinator";
            case SUPERVISORCoordinator -> "SupervisorCoordinator";
            default -> null;
        };
    }

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
}
