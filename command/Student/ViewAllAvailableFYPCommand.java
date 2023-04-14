package command.Student;

import java.util.Scanner;

import FYPMS.FYPMS1;
import FYPMS.project.FYPList;
import account.student.StudentAccount;
import account.student.StudentStatus;
import command.Command;

public class ViewAllAvailableFYPCommand implements Command {
    private StudentAccount studentAccount;

    public ViewAllAvailableFYPCommand(StudentAccount currentAcc) {
        this.studentAccount = currentAcc;
    }

    public void execute() { // View available FYP from csv
        Scanner sc = new Scanner(System.in);

        if (studentAccount.getStatus() == StudentStatus.ASSIGNED_PROJECT) {
            System.out.println("Error: You are already registered for an FYP.");
            System.out.println("Press enter to continue...");
            sc.nextLine();
            return;
        } else if (studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
            System.out.println("Error: You have deregistered for an FYP.");
            System.out.println("Press enter to continue...");
            sc.nextLine();
            return;
        } else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
            System.out.println("Error: You have a pending registration.");
            System.out.println("Press enter to continue...");
            sc.nextLine();
            return;
        }
        FYPList fypList = FYPMS1.getFypList();
        fypList.listAvailableFYPsForStudents();

        System.out.println("Press enter to continue...");
        sc.nextLine();
    }
}
