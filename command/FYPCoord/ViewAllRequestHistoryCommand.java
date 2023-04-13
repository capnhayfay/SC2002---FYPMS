package command.FYPCoord;

import FYPMS.FYPMS1;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import command.Command;

public class ViewAllRequestHistoryCommand implements Command{
    public ViewAllRequestHistoryCommand(){

    }
    public void execute(){
        RequestList requests = FYPMS1.getRequestList();
		for (Request request : requests) {
			request.printDetails();
		}
    }
}
