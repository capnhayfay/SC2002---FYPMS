package FYPMS.request;
import account.AccountManager;

public class RequestDeregister extends Request {

    public RequestDeregister(int requestID, String requesterID, RequestStatus requestStatus,int fypID) {
        super(requestID, requesterID, AccountManager.getCoordinatorList().get(0).getLoginId(), requestStatus, RequestRelationship.STUDENTCoordinator, RequestType.DEREGISTER_PROJECT, fypID);
    }
}
