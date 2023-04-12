package command.FYPCoord;

import FYPMS.FYPMS;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import command.Command;

public class ViewAllRequestHistoryCommand implements Command{
    public ViewAllRequestHistoryCommand(){

    }
    public void execute(){
        RequestList requests = FYPMS.getRequestList();
		for (Request request : requests) {
			request.printDetails();
		}
    }
}
