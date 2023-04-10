package command.Student;

import FYPMS.request.Request;
import FYPMS.request.RequestList;
import command.Command;

public class ViewSelfRequestRecordsCommand implements Command {
    private String Student;
    private RequestList requests;
    public ViewSelfRequestRecordsCommand(String Student, RequestList requests)
    {
        this.Student = Student;
        this.requests = requests;
    }
    public void execute() {
        int RequestCount = 1;
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
