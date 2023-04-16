/**
 * Allows a student to view all available FYPs from a CSV file and select a new project if they have not already been allocated one.
 * If the student has already been allocated a project or has a pending registration or has deregistered their project, an appropriate error message is displayed.
 */
package src.command.Student;

import src.FYPMS.project.FYPList;
import src.account.student.StudentAccount;
import src.account.student.StudentStatus;
import src.command.Command;
import src.exceptions.fypmsExceptions;

import java.util.Scanner;

/**
 * Command to view all available FYPs for a student
 */
public class ViewAllAvailableFYPCommand implements Command {
    private final StudentAccount studentAccount;

    /**
     * Constructs a new src.command to view all available FYPs for a given student src.account.
     * @param currentAcc the student src.account executing the src.command.
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

        if (!fypmsExceptions.checkStudentStatusExceptionFunction(studentAccount)){
            FYPList.listAvailableFYPsForStudents();
        }
    }
}
