package command.FYPCoord;

import FYPMS.FYPMS;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import command.Command;

public class ViewAllRequestHistoryCommand implements Command{
    public ViewAllRequestHistoryCommand(){

    }
    public void execute(){
        RequestList test0 = FYPMS.getRequestList();
		for (Request tes : test0) {
			tes.printDetails();
		}
    }
}
