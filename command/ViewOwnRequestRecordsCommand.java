package command;

import FYPMS.FYPMS1;
import FYPMS.request.Request;
import account.Account;
import java.util.ArrayList;

public class ViewOwnRequestRecordsCommand implements Command {
    private Account account;

    public ViewOwnRequestRecordsCommand(Account account) {
        this.account = account;
    }

    public void execute() {
        int RequestCount = 0;
        System.out.println();
        System.out.println("Request History");
        System.out.println();
        ArrayList<ArrayList <Object>> requests =  FYPMS1.getRequestList();
        for (ArrayList <Object> request : requests) {
            for (Object indivRequest : request){
                Request indivCastedRequest = (Request) indivRequest;
                if (indivCastedRequest.getRequesterID().equals(account.getLoginId())) {
                    System.out.println("============= Request ID " + indivCastedRequest.getRequestID() + " ==============");
                    indivCastedRequest.printDetails();
                    System.out.println();
                    RequestCount++;
                }

            }  

        }


        if(RequestCount == 0){
            System.out.println("You have no requests.");
        }
        else{
            System.out.print("You have "+ RequestCount + " requests.");
        }
    }
}
