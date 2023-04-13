package command.FYPCoord;

import command.Command;

import java.util.ArrayList;

import FYPMS.FYPMS1;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import FYPMS.request.RequestStatus;



public class ViewAllPendingRequestsCommand implements Command {

    public void execute(){
        ArrayList<ArrayList <Object>> requests =  FYPMS1.getRequestList();
        for (ArrayList <Object> request : requests) {
            for (Object indivRequest : request){
                Request indivCastedRequest = (Request) indivRequest;
                if(indivCastedRequest.getRequestStatus() == RequestStatus.PENDING){
                    indivCastedRequest.printDetails();
                }
            }
        }
    }
}
