package command.Student;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import account.student.StudentAccount;
import account.student.StudentStatus;
import command.Command;

public class ViewAllAvailableFYPCommand implements Command {
    private final StudentAccount studentAccount;

    public ViewAllAvailableFYPCommand(StudentAccount currentAcc) {
        this.studentAccount = currentAcc;
    }

    public void execute() { // View available FYP from csv
        Scanner sc = new Scanner(System.in);

        if (studentAccount.getStatus() == StudentStatus.ASSIGNED_PROJECT) {
            System.out
                    .println("You are currently allocated to a FYP and do not have access to available project list.");
            System.out.println("Press enter to continue...");
            sc.nextLine();
            return;
        } else if (studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
            System.out.println("You are not allowed to make selection again as you deregistered your FYP.");
            System.out.println("Press enter to continue...");
            sc.nextLine();
            return;
        } else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
            System.out.println("Error: You have a pending registration.");
            System.out.println("Press enter to continue...");
            sc.nextLine();
            return;
        }
        FYPList.listAvailableFYPsForStudents();

        System.out.println("Press enter to continue...");
        sc.nextLine();
    }
}
