package command.FYPCoord;

import command.Command;
import FYPMS.FYPMS;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import FYPMS.request.RequestStatus;



public class ViewAllPendingRequestsCommand implements Command {

    public void execute(){
        RequestList test0 = FYPMS.getRequestList();
		for (Request tes : test0) {
			if(tes.getRequestStatus() == RequestStatus.PENDING)
                tes.printDetails();
		}
    }
}
