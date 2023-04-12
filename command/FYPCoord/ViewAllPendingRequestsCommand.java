package command.FYPCoord;

import command.Command;
import FYPMS.FYPMS;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import FYPMS.request.RequestStatus;



public class ViewAllPendingRequestsCommand implements Command {

    public void execute(){
        RequestList requests = FYPMS.getRequestList();
		for (Request request : requests) {
			if(request.getRequestStatus() == RequestStatus.PENDING)
                request.printDetails();
		}
    }
}
