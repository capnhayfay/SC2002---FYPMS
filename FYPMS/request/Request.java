package FYPMS.request;

import java.time.LocalDateTime;

/**
 * Represents a Request hall within a Cineplex branch
 */
public class Request {

    private static final int currentCode=1;
    private final String requesterName;
    private final RequestType requestType; // enum

    private final LocalDateTime statusChangeTime;

    /**
     * Creates a request object for FYPMS requests
     *
     * @param requesterName Name of requester
     * @param requestType Request Type
     * @param statusChangeTime Time of Request
     */
    public Request(String requesterName, RequestType requestType, LocalDateTime statusChangeTime) {
        this.requesterName = requesterName;
        this.requestType = requestType;
        this.statusChangeTime = statusChangeTime;
    }

    /**
     * Prints the details of this instance of the request
     */
    public void printDetails(){
        System.out.println("Requester: " + requesterName);
        System.out.println("Request Class: " + requestType);
        System.out.println();
    }

    /**
     * Converts the request class in string form to RequestType enum
     * @param classLevel Class of request in string form
     * @return RequestType enum
     */
    public static RequestType convertToRequestType(String classLevel){
        return switch (classLevel.toLowerCase()) {
            case "studentcoordinator" -> RequestType.STUDENTCoordinator;
            case "studentsupervisor" -> RequestType.STUDENTSupervisor;
            case "supervisorcoordinator" -> RequestType.SUPERVISORCoordinator;
            default -> null;
        };
    }

    /**
     * Helper function to convert RequestType enum to string, for CSV storage
     * @param requestType RequestType enum
     * @return Class of request in string form
     */
    public static String convertCinemaClassToString(RequestType requestType){
        return switch (requestType){
            case STUDENTSupervisor -> "StudentSupervisor";
            case STUDENTCoordinator -> "StudentCoordinator";
            case SUPERVISORCoordinator -> "SupervisorCoordinator";
            default -> null;
        };
    }
    public RequestType getRequestType(){return requestType;}
    public String getRequesterName(){
        return requesterName;
    }
    public LocalDateTime getStatusChangeTime() {
        return statusChangeTime;
    }

}