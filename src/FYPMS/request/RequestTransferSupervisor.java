package src.FYPMS.request;

import src.account.AccountManager;

/**
 * Request class for transferring supervisor
 */
public class RequestTransferSupervisor extends Request {

    private final String newSupervisorID;

    /**
     * Constructor class for RequestTransferSupervisor
     *
     * @param requestID       ID of request
     * @param requesterID     ID of requester
     * @param requestStatus   Status of request
     * @param fypID           ID of FYP
     * @param newSupervisorID ID of new supervisor
     */
    public RequestTransferSupervisor(int requestID, String requesterID, RequestStatus requestStatus, int fypID,
                                     String newSupervisorID) {
        super(requestID, requesterID, AccountManager.getCoordinatorList().get(0).getLoginId(), requestStatus,
                RequestRelationship.SUPERVISORCoordinator, RequestType.TRANSFER_SUPERVISOR, fypID);
        this.newSupervisorID = newSupervisorID;
    }

    /**
     * Get ID of new supervisor once project has been transferred
     *
     * @return newSupervisorID: String
     */
    public String getNewSupervisorID() {
        return newSupervisorID;
    }

    /**
     * Prints details of FYP
     */
    public void printDetails() {
        System.out.println("Requester: " + getRequesterID());
        System.out.println("Requestee: " + getRequesteeID());
        System.out.println("Request Type: " + getRequestType());
        System.out.println("Request Status: " + getRequestStatus());
        System.out.println("Request Relationship: " + getRequestRelationship());
        System.out.println("FYP ID: " + getFypID());
        System.out.println("New Supervisor ID: " + getNewSupervisorID());
        System.out.println();
    }

}
