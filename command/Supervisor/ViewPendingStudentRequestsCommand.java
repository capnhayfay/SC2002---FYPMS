package command.Supervisor;

import java.util.ArrayList;

import FYPMS.FYPMS1;
import command.Command;

import FYPMS.request.*;

public class ViewPendingStudentRequestsCommand implements Command {
    private String Supervisor;

    public ViewPendingStudentRequestsCommand(String Supervisor) {
        this.Supervisor = Supervisor;
    }

    public void execute() {

        System.out.println();
        System.out.println("Pending Request");
        System.out.println();
        
        ArrayList<ArrayList <Object>> requests =  FYPMS1.getRequestList();
        int requestCount = 0;
        for (ArrayList <Object> request : requests) {
            for (Object indivRequest : request){
                Request indivCastedRequest = (Request) indivRequest;
                if (indivCastedRequest.getRequesteeID().equals(Supervisor) && indivCastedRequest.getRequestStatus() == RequestStatus.PENDING) {
                    System.out.println("============= Request ID " +indivCastedRequest.getRequestID() + " ==============");
                    indivCastedRequest.printDetails();
                    System.out.println();
                    requestCount++;
                }
            }
        }

        if (requestCount ==0)
        {
            System.out.println("There are no pending requests.");
        }
        else{
            System.out.println("There are " +requestCount+" pending requests.");
        }

    }

    public int checkUpdates() {
        ArrayList<ArrayList <Object>> requests =  FYPMS1.getRequestList();
        for (ArrayList <Object> request : requests) {
            for (Object indivRequest : request){
                Request indivCastedRequest = (Request) indivRequest;
                if (indivCastedRequest.getRequesteeID().equals(Supervisor) && indivCastedRequest.getRequestStatus() == RequestStatus.PENDING) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
