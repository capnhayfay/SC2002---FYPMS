package command.FYPCoord;

import java.util.ArrayList;

import FYPMS.FYPMS1;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import command.Command;

public class ViewAllRequestHistoryCommand implements Command{
    public ViewAllRequestHistoryCommand(){

    }
    public void execute(){
        ArrayList<ArrayList <Object>>  requests = FYPMS1.getRequestList();
        for (ArrayList <Object> request : requests) {
            for (Object indivRequest : request){
                Request indivCastedRequest = (Request) indivRequest;
			    indivCastedRequest.printDetails();
		    }
        }
    }
}
