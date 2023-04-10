package FYPMS.request;

import java.time.LocalDateTime;

/**
 * Represents a Request List
 */
public class Request {

    private static final int currentCode = 1;
    private final String requesterName;
    private final RequestType requestType; // enum
    private final LocalDateTime statusChangeTime;
    private final String requestee;
    private final RequestStatus requestStatus; // enum

    /**
     * Creates a request object for FYPMS requests
     *
     * @param requesterName    Name of requester
     * @param requestType      Request Type
     * @param statusChangeTime Time of Request
     * @param requestee
     * @param requestStatus
     */
    public Request(String requesterName, RequestType requestType, LocalDateTime statusChangeTime, String requestee,
            RequestStatus requestStatus) {
        this.requesterName = requesterName;
        this.requestType = requestType;
        this.statusChangeTime = statusChangeTime;
        this.requestee = requestee;
        this.requestStatus = requestStatus;
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
        System.out.println("Requester: " + requesterName);
        System.out.println("Request Class: " + requestType);
        System.out.println("Time of Last Update: " + statusChangeTime);
        System.out.println("Request Status: " + requestStatus);
        System.out.println();
    }

    /**
     * Converts the request class in string form to RequestType enum
     * 
     * @param classLevel Class of request in string form
     * @return RequestType enum
     */
    public static RequestType convertToRequestType(String classLevel) {
        return switch (classLevel.toLowerCase()) {
            case "studentcoord" -> RequestType.STUDENTCoordinator;
            case "studentsupervisor" -> RequestType.STUDENTSupervisor;
            case "supervisorcoordinator" -> RequestType.SUPERVISORCoordinator;
            default -> null;
        };
    }

    /**
     * Helper function to convert RequestType enum to string, for CSV storage
     * 
     * @param requestType RequestType enum
     * @return Class of request in string form
     */
    public static String convertRequestTypeToString(RequestType requestType) {
        return switch (requestType) {
            case STUDENTSupervisor -> "StudentSupervisor";
            case STUDENTCoordinator -> "StudentCoordinator";
            case SUPERVISORCoordinator -> "SupervisorCoordinator";
            default -> null;
        };
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public LocalDateTime getStatusChangeTime() {
        return statusChangeTime;
    }

}
