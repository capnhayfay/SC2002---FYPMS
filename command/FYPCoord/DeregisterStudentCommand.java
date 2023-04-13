package command.FYPCoord;

import FYPMS.FYPMS1;
import FYPMS.project.*;
import FYPMS.request.RequestDeregister;
import FYPMS.request.RequestStatus;
import command.Command;
import java.util.Scanner;

public class DeregisterStudentCommand implements Command {
    private FYP project;
    private RequestDeregister requestDeregister;

    public DeregisterStudentCommand(FYP project, RequestDeregister requestDeregister) {
        this.project = project;
        this.requestDeregister = requestDeregister;
    }

    public void execute() {
    Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Select option:");
        System.out.println("1. Accept deregistration request");
        System.out.println("2. Reject deregistration request");
        switch(sc.nextInt()) {
            case 1:
                String StudentName = FYPMS1.getStudentName(requestDeregister.getRequesterID());
                String StudentEmail = FYPMS1.getStudentEmail(requestDeregister.getRequesterID());
                project.setStatus(FYPStatus.AVAILABLE);
                project.setStudentID(requestDeregister.getRequesterID());
                project.setStudentEmail(StudentEmail);
                project.setStudentName(StudentName);
                requestDeregister.setStatus(RequestStatus.APPROVED);              
                System.out.println("Deregistered project " + project.getTitle() + " to " + StudentName); 
                break;
            case 2:
                requestDeregister.setStatus(RequestStatus.REJECTED);
                System.out.println("Rejected "+ requestDeregister.getRequesterID()+ " for "  + project.getTitle());
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }

}
