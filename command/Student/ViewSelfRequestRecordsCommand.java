package command.Student;

import FYPMS.FYPMS1;
import FYPMS.request.Request;
import account.student.StudentAccount;
import command.Command;
import java.util.ArrayList;

public class ViewSelfRequestRecordsCommand implements Command {
    private StudentAccount student;

    public ViewSelfRequestRecordsCommand(StudentAccount student) {
        this.student = student;
    }

    public void execute() {
        int RequestCount = 0;
        // RequestList requests = FYPMS1.getRequestList();
        System.out.println();
        System.out.println("Request History");
        System.out.println();
        int idx = 0;
        ArrayList<ArrayList <Object>> requests =  FYPMS1.getRequestList();
        for (ArrayList <Object> request : requests) {
            for (Object indivRequest : request){
                Request indivCastedRequest = (Request) indivRequest;
                if (indivCastedRequest.getRequesterID().equals(student.getLoginId())) {
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
