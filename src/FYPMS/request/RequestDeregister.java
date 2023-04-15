package src.FYPMS.request;

import src.account.AccountManager;

/**
 * Deregister Project Request extension of Requests
 */
public class RequestDeregister extends Request {

    /**
     * Constructor for RequestDeregister
     *
     * @param requestID     ID of request
     * @param requesterID   ID of requester
     * @param requestStatus ID of request receiver
     * @param fypID         ID of fyp
     */
    public RequestDeregister(int requestID, String requesterID, RequestStatus requestStatus, int fypID) {
        super(requestID, requesterID, AccountManager.getCoordinatorList().get(0).getLoginId(), requestStatus, RequestRelationship.STUDENTCoordinator, RequestType.DEREGISTER_PROJECT, fypID);
    }
}
