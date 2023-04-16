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
import src.exceptions.fypmsExceptions;

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

        try{
            if (studentAccount.getStatus() == StudentStatus.NO_PROJECT){
                throw new fypmsExceptions.notRegisteredException();
            }
            else if(studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
                throw new fypmsExceptions.deregisteredException();
            } else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
                throw new fypmsExceptions.pendingRequestException();
            }
        } catch (Exception e){
            System.out.println((e.toString().subSequence(e.toString().indexOf(":")+2, e.toString().length()-1)));
            return;
        }

        for (FYP fyp : fypList) {
            if (fyp.getStudentID() != null && fyp.getStudentID().equals(studentAccount.getLoginId())) {
                fyp.printFYPDetails();
                return;
            }
        }
    }

}
