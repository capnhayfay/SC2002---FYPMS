/**
 * Allows a student to view all available FYPs from a CSV file and select a new project if they have not already been allocated one.
 * If the student has already been allocated a project or has a pending registration or has deregistered their project, an appropriate error message is displayed.
*/
package command.Student;

import java.util.Scanner;

import FYPMS.project.FYPList;
import account.student.StudentAccount;
import account.student.StudentStatus;
import command.Command;

public class ViewAllAvailableFYPCommand implements Command {
    private final StudentAccount studentAccount;

    /**
     * Constructs a new command to view all available FYPs for a given student account.
     * @param currentAcc the student account executing the command.
    */
    public ViewAllAvailableFYPCommand(StudentAccount currentAcc) {
        this.studentAccount = currentAcc;
    }

    /**
     * Executes the command to list all available FYPs and allows the student to select a new project if they have not already been allocated one.
     * If the student has already been allocated a project or has a pending registration or has deregistered their project, an appropriate error message is displayed.
    */
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
