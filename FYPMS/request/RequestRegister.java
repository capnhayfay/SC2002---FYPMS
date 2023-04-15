package FYPMS.request;

import account.AccountManager;

public class RequestRegister extends Request {

    public RequestRegister(int requestID, String requesterID, RequestStatus requestStatus,int fypID) {
        super(requestID, requesterID, AccountManager.getCoordinatorList().get(0).getLoginId(), requestStatus, RequestRelationship.STUDENTCoordinator, RequestType.REGISTER_PROJECT, fypID);
    }

}
