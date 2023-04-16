/**
 * A src.command that allows a student to request a coordinator-approved FYP project.
 * If the student is already registered for an FYP, deregistered from an FYP, or has a pending registration,
 * an error message will be displayed and the src.command will exit.
 * The student will be prompted to enter a project ID, and if the project is available, the student's status will be set
 * to REQUESTED_PROJECT and a request to register for the project will be added to the request list.
 * If the project is not available, an error message will be displayed and the src.command will exit.
 *
 * @author A25-Group2
 * @version 1.0
 * @since 2023
 */
package src.command.Student;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.FYPMS.project.FYPStatus;
import src.FYPMS.request.Request;
import src.FYPMS.request.RequestHistory;
import src.FYPMS.request.RequestRegister;
import src.FYPMS.request.RequestStatus;
import src.account.student.StudentAccount;
import src.account.student.StudentStatus;
import src.command.Command;
import src.exceptions.fypmsExceptions;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import static src.cli.InputValidation.scannerValidation;


/**
 * Request FYP from Coordinator Command Class
 */
public class RequestCoordFYPCommand implements Command {
    final ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();
    private final StudentAccount studentAccount;

    /**
     * Constructor for the RequestCoordFYPCommand class.
     *
     * @param studentAccount the student account associated with this src.command
     */
    public RequestCoordFYPCommand(StudentAccount studentAccount) {
        this.studentAccount = studentAccount;
    }

    /**
     * Executes the RequestCoordFYPCommand by prompting the student to enter a project ID,
     * checking if the student is eligible to request a project, and adding a request to register for the project
     * if the project is available.
     */
    public void execute() {
        try {
            if (studentAccount.getStatus() == StudentStatus.ASSIGNED_PROJECT) {
                throw new fypmsExceptions.alreadyRegisteredException();
            } else if (studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
                throw new fypmsExceptions.deregisteredException();
            } else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
                throw new fypmsExceptions.pendingRequestException();
            }
        } catch (Exception e){
            System.out.println(e.toString().subSequence(e.toString().indexOf(":")+2, e.toString().length()-1));
            return;
        }
        Scanner sc = new Scanner(System.in);
        Optional<FYP> checker = Optional.empty();
        int fypID = -1;
        do {
            try {

                do{
                    try {
                        System.out.println("Input project ID, or enter 0 to return to menu: ");
                        fypID = scannerValidation(sc);
                    } catch (InputMismatchException e) {
                        sc.nextLine();
                        fypID = -2;
                        System.out.println("Only Numeric Input Allowed!");
                        System.out.println();
                        continue;
                    }
                } while (fypID == -2);
                checker = FYPList.fypIdExists(fypID);
                try {
                    if (fypID == 0) {
                        System.out.println("Returning to menu...");
                        Thread.sleep(300);
                        return;
                    }
                } catch (Exception e){
                    System.out.println(e.toString());
                }
                if (checker.isEmpty()) {
                    throw new fypmsExceptions.noSuchProjectException();
                }
            } catch (fypmsExceptions.noSuchProjectException noSuchProject) {
                sc.nextLine();
                System.out.println(noSuchProject.toString().
                        subSequence(noSuchProject.toString().indexOf(":")+2, noSuchProject.toString().length()-1));
                System.out.println("If you do not wish to retry, press 0");
            }
        } while (checker.isEmpty() && fypID != 0);




        assert checker.isPresent();
        try{
            if (!checker.get().getStatus().equals(FYPStatus.AVAILABLE)){
                throw new fypmsExceptions.projectNotAvailableException();
            }
        } catch(fypmsExceptions.projectNotAvailableException projectNotAvailableException){
            System.out.println(projectNotAvailableException.toString());
            return;
        }
        checker.get().setStatus(FYPStatus.RESERVED);
        ArrayList<FYP> fypList = FYPList.getFypList();
        studentAccount.setStatus(StudentStatus.REQUESTED_PROJECT);
        RequestRegister registerRequest = new RequestRegister(requestHistory.get(2).size() + 2000,
                studentAccount.getLoginId(), RequestStatus.PENDING,
                fypID);
        requestHistory.get(2).add(registerRequest);
        System.out.println("Successfully Applied for project " + fypID);
    }

}
