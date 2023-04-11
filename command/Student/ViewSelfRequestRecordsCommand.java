package command.Student;

import FYPMS.request.Request;
import FYPMS.request.RequestList;
import command.Command;
import FYPMS.FYPMS;

public class ViewSelfRequestRecordsCommand implements Command {
    private String Student;

    public ViewSelfRequestRecordsCommand(String Student) {
        this.Student = Student;
    }

    public void execute() {
        int RequestCount = 1;
        RequestList requests = FYPMS.getRequestList();
        System.out.println();
        System.out.println("Request History");
        System.out.println();
        for (Request request : requests) {
            if (request.getRequesterName().equals(Student)) {
                System.out.println("============= Request No. " + RequestCount++ + " ==============");
                request.printDetails();
                System.out.println();
            }
        }
    }
}
