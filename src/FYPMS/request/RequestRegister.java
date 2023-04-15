package src.FYPMS.request;

import src.account.AccountManager;

/**
 * RequestRegister Class Extension of Requests
 */
public class RequestRegister extends Request {

    /**
     * Constructor for RequestRegister type requests
     *
     * @param requestID     ID of requests
     * @param requesterID   ID of requester
     * @param requestStatus Status of ID
     * @param fypID         ID of the current FYP
     */
    public RequestRegister(int requestID, String requesterID, RequestStatus requestStatus, int fypID) {
        super(requestID, requesterID, AccountManager.getCoordinatorList().get(0).getLoginId(), requestStatus, RequestRelationship.STUDENTCoordinator, RequestType.REGISTER_PROJECT, fypID);
    }
}
