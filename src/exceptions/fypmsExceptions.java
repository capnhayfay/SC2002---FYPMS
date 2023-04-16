/**
 * Class for containing all FYPMS exceptions.
 */
package src.exceptions;

import src.account.student.StudentAccount;
import src.account.student.StudentStatus;
import java.util.Scanner;

public class fypmsExceptions {

    /**
     * Default constructor for Exceptions Class for FYPMS.
     */
    public fypmsExceptions() {}

    /**
     * Exception for invalid inputs by user.
     */
    @SuppressWarnings("serial")
    public static class invalidInputException extends Exception {

        /**
         * Constructor for invalidInputException.
         *
         * @param x: String of where the error was found.
         */
        public invalidInputException(String x) {
            super(x + ". Please try again.");
        }
    }

    /**
     * Exception for supervisor not found.
     */
    public static class supervisorNotFoundException extends Exception {
        public supervisorNotFoundException() {
            super("Supervisor Entered Not Found!");
        }
    }

    /**
     * Exception for project ID not found.
     */
    public static class noSuchProjectException extends Exception {
        /**
         * Constructor
         */
        public noSuchProjectException() {
            super("Project ID Does Not Exist!");
        }
    }

    /**
     * Exception for project not being available.
     */
    public static class projectNotAvailableException extends Exception {
        /**
         * Constructor
         */
        public projectNotAvailableException() {
            super("Project is not available!");
        }
    }

    /**
     * Exception for supervisor reaching maximum projects limit.
     */
    public static class supervisorMaxProjectsReachedException extends Exception {
        /**
         * Default Constructor
         */
        public supervisorMaxProjectsReachedException() {
            super("Warning: You have already reached the maximum number of projects you can manage.");
        }

        /**
         * Non Default Constructor
         */
        public supervisorMaxProjectsReachedException(String supervisorName) {
            super(("Warning: " + supervisorName + " has reached the project limit."));
        }
    }

    /**
     * Exception for supervisor reaching maximum projects limit but still accepting the request.
     */
    public static class supervisorMaxProjectsReachedButStillAcceptedException extends supervisorMaxProjectsReachedException {
        public supervisorMaxProjectsReachedButStillAcceptedException(String supervisorName) {
            super(supervisorName);
            System.out.println("Rejecting all pending registration requests for " + supervisorName + "'s projects.");
            System.out.println("Setting all of " + supervisorName + "'s projects to unavailable");
        }
    }

    /**
     * Exception for invalid login details.
     */
    public static class invalidLoginException extends Exception {
        /**
         * Constructor
         */
        public invalidLoginException() {
            super("Invalid Login Details! Try again.");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Exception for password mismatch.
     */
    public static class passwordMismatchException extends Exception {
        /**
         * Constructor
         */
        public passwordMismatchException() {
            super("Passwords do not match! Try again.");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Exception for having a pending request.
     */
    public static class pendingRequestException extends Exception {
        /**
         * Constructor
         */
        public pendingRequestException() {
            super("Error: You have a pending registration.");
            try {
                Thread.sleep(100);
            } catch (Exception ignored) {}
        }
    }

    /**
     * Exception for not being registered.
     */
    public static class notRegisteredException extends Exception {
        /**
         * Constructor
         */
        public notRegisteredException() {
            super("Error: You are not registered for an FYP.");
            try {
                Thread.sleep(100);
            } catch (Exception ignored) {}
        }
    }

    /**
     * Exception for being deregistered.
     */
    public static class deregisteredException extends Exception {
        /**
         * Constructor for deregisteredException class type
         */
        public deregisteredException(){
            super("Error: You have deregistered for an FYP.");
            try{
                Thread.sleep(100);
            } catch (Exception ignored){

            }
        }

    }

    /**
     * Class for student status already registered
     */
    public static class alreadyRegisteredException extends Exception{
        /**
         * Constructor for alreadyRegisteredException
         */
        public alreadyRegisteredException(){
            super("Error: You are already registered for an FYP.");
            try{
                Thread.sleep(100);
            } catch (Exception ignored){

            }
        }
    }

    /**
     * Authorization access exception
     */
    public static class notAuthorizedException extends Exception{
        /**
         * Constructor for notAuthorizedException
         */
        public notAuthorizedException(){
            super("Error: You are not authorized to perform this function.");
            try{
                Thread.sleep(100);
            } catch (Exception ignored){

            }
        }
    }

    /**
     * Function to provide error checking based on Student Status and throw exceptions
     * @param studentAccount Account of student
     * @param cases the specific case of the item
     * @return returns boolean true if exceptions were present, false otherwise
     */
    public static boolean checkStudentStatusExceptionFunction(StudentAccount studentAccount, int cases){
        try {
            if (studentAccount.getStatus() == StudentStatus.ASSIGNED_PROJECT && cases == 1) {
                throw new fypmsExceptions.alreadyRegisteredException();
            }
            else if (studentAccount.getStatus() == StudentStatus.NO_PROJECT && cases == 2) {
                throw new fypmsExceptions.notRegisteredException();
            }
            else if (studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
                throw new fypmsExceptions.deregisteredException();
            } else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
                throw new fypmsExceptions.pendingRequestException();
            }
        } catch(Exception e){
            System.out.println(e.toString().subSequence(e.toString().indexOf(":")+2, e.toString().length()-1));
            return true;
        }
        return false;
    }

    /**
     * Function to repeatedly ask user for input for two option choices
     * @param Title First line printed
     * @param optionOne Option 1
     * @param optionTwo Option 2
     * @return valid Integer output
     */
    public static int validateRequestActionFunction(String Title, String optionOne, String optionTwo){
        Scanner sc = new Scanner(System.in);
        int requestAction = 0;
        do {
            try {
                System.out.println(Title);
                System.out.println(optionOne);
                System.out.println(optionTwo);
                System.out.println();
                do {
                    try {
                        requestAction = sc.nextInt();
                    } catch (Exception e) {
                        requestAction = -1;
                        System.out.println(new fypmsExceptions.invalidInputException("Invalid input, please try again").toString());
                        sc.nextLine();
                        continue;
                    }
                } while (requestAction == -1);
                if (!(requestAction == 1 || requestAction==2)){
                    throw new fypmsExceptions.invalidInputException("Please enter only 1 or 2");
                }
            } catch (fypmsExceptions.invalidInputException badInput) {
                System.out.println(badInput.toString().subSequence(badInput.toString().indexOf(":")+2, badInput.toString().length()-1));
                sc.nextLine();
            }
        }while(requestAction != 1 && requestAction != 2);
        return requestAction;

    }
}
