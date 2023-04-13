package command.Student;

import FYPMS.FYPMS1;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import account.student.StudentAccount;
import command.Command;

public class ViewSelfRequestRecordsCommand implements Command {
    private StudentAccount student;

    public ViewSelfRequestRecordsCommand(StudentAccount student) {
        this.student = student;
    }

    public void execute() {
        int RequestCount = 1;
        RequestList requests = FYPMS1.getRequestList();
        System.out.println();
        System.out.println("Request History");
        System.out.println();
        for (Request request : requests) {
            if (request.getRequesterID().equals(student.getLoginId())) {
                System.out.println("============= Request No. " + RequestCount++ + " ==============");
                request.printDetails();
                System.out.println();
            }
        }
    }
}
