/**
 * This src.command allows a student to view the details of their registered FYP.
 * If the student has not registered for any FYP, a message indicating that they have not registered will be displayed.
 * If the student has deregistered for an FYP, they will not be able to view their previously registered FYP and an appropriate message will be displayed.
 * If the student has a pending registration, they will not be able to view their FYP and an appropriate message will be displayed.
 * If the student has a registered FYP, its details will be printed.
 */
package src.command.Student;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.account.student.StudentAccount;
import src.account.student.StudentStatus;
import src.command.Command;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Command class for a student to view FYP they have registered for
 */
public class ViewRegisteredFYPCommand implements Command {
    /**
     * Account of the student
     */
    private final StudentAccount studentAccount;

    /**
     * Constructs a ViewRegisteredFYPCommand with the given student src.account.
     *
     * @param studentAccount the student src.account
     */
    public ViewRegisteredFYPCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }

    /**
     * Executes the src.command to view the details of the student's registered FYP.
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
