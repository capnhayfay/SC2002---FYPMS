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
        int RequestCount = 1;

        System.out.println();
        System.out.println("Pending Request");
        System.out.println();
        
        ArrayList<ArrayList <Object>> requests =  FYPMS1.getRequestList();
        for (ArrayList <Object> request : requests) {
            for (Object indivRequest : request){
                Request indivCastedRequest = (Request) indivRequest;
                if (indivCastedRequest.getRequesteeID().equals(Supervisor) && indivCastedRequest.getRequestStatus() == RequestStatus.PENDING) {
                    System.out.println("============= Request No. " + RequestCount++ + " ==============");
                    indivCastedRequest.printDetails();
                    System.out.println();
                }
            }
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
