package command.Supervisor;

import command.Command;

import java.util.Scanner;

import FYPMS.project.*;
import FYPMS.request.RequestChangeTitle;
import FYPMS.request.RequestStatus;

public class ModifySubmittedFYPTitleCommand implements Command {
    private FYP project;
    private RequestChangeTitle titleRequest;

    public ModifySubmittedFYPTitleCommand(FYP project, RequestChangeTitle titleRequest) {
        this.project = project;
        this.titleRequest = titleRequest;
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Select option:");
        System.out.println("1. Accept Title request");
        System.out.println("2. Reject Title request");
        switch (sc.nextInt()) {
            case 1:
                project.setTitle(titleRequest.getNewTitle());
                titleRequest.setStatus(RequestStatus.APPROVED);
                System.out.println("Project title has been change to" + project.getTitle());
                break;
            case 2:
                titleRequest.setStatus(RequestStatus.REJECTED);
                System.out.println("Rejected changing of title to" + project.getTitle());
                break;
            default:
                System.out.println("Invalid option");
                break;
        }

    }

}
