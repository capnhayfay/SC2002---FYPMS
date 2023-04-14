package command.Student;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.FYPMS1;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import account.student.StudentAccount;
import account.student.StudentStatus;
import command.Command;

public class ViewRegisteredFYPCommand implements Command {
    private StudentAccount studentAccount;

    public ViewRegisteredFYPCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }

    public void execute() {
        Scanner sc = new Scanner(System.in);
        FYPList projects = FYPMS1.getFypList();
        ArrayList<FYP> fyps = projects.getFYPs();
        System.out.println();
        if (studentAccount.getStatus() == StudentStatus.NO_PROJECT
                || studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
            System.out.println("Error: You are not registered for any FYP.");
            System.out.println("Press enter to continue...");
            sc.nextLine();
            return;
        }
        // else if (studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT ) {
        // System.out.println("Error: You have deregistered for an FYP.");
        // System.out.println("Press enter to continue...");
        // sc.nextLine();
        // return;
        // }
        else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
            System.out.println("Error: Your registration is still pending.");
            System.out.println("Press enter to continue...");
            sc.nextLine();
            return;
        }
        for (FYP fyp : fyps) {
            if (fyp.getStudentID() != null) {
                if (fyp.getStudentID().equals(studentAccount.getLoginId())) {
                    fyp.printFYPDetails();
                    System.out.println("Press enter to continue...");
                    sc.nextLine();
                    break;
                }
            }
        }
    }
}
