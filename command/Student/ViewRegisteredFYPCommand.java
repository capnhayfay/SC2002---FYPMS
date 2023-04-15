/**
 * This command allows a student to view the details of their registered FYP.
 * If the student has not registered for any FYP, a message indicating that they have not registered will be displayed.
 * If the student has deregistered for an FYP, they will not be able to view their previously registered FYP and an appropriate message will be displayed.
 * If the student has a pending registration, they will not be able to view their FYP and an appropriate message will be displayed.
 * If the student has a registered FYP, its details will be printed.
*/
package command.Student;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import account.student.StudentAccount;
import account.student.StudentStatus;
import command.Command;

public class ViewRegisteredFYPCommand implements Command {
    private final StudentAccount studentAccount;

    /**
     * Constructs a ViewRegisteredFYPCommand with the given student account.
     * 
     * @param studentAccount the student account
    */
    public ViewRegisteredFYPCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }

    /**
     * Executes the command to view the details of the student's registered FYP.
    */
    public void execute() {
        Scanner sc = new Scanner(System.in);
        ArrayList<FYP> fypList = FYPList.getFypList();
        System.out.println();

        if (studentAccount.getStatus() == StudentStatus.NO_PROJECT ||
                studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
            System.out.println("You have not registered for any FYP.");
            System.out.println("Press enter to continue...");
            sc.nextLine();
            return;
        } else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
            System.out.println("Error: Your registration is still pending.");
            System.out.println("Press enter to continue...");
            sc.nextLine();
            return;
        }

        for (FYP fyp : fypList) {
            if (fyp.getStudentID() != null && fyp.getStudentID().equals(studentAccount.getLoginId())) {
                fyp.printFYPDetails();
                System.out.println("Press enter to continue...");
                sc.nextLine();
                return;
            }
        }
    }

}
