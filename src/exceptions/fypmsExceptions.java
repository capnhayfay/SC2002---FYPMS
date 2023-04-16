package src.exceptions;

import src.account.student.StudentAccount;
import src.account.student.StudentStatus;

import java.util.Scanner;

/**
 * Class for containing all src.FYPMS exceptions
 */
public class fypmsExceptions {
    /**
     * Default constructor for Exceptions Class for src.FYPMS
     */
    public fypmsExceptions() {
    }

    /**
     * Exception for invalidInputs by user
     */
    @SuppressWarnings("serial")
    /**
     * Inner static class for invalidInput Exceptions
     */
    public static class invalidInputException extends Exception {
        /**
         * Constructor for invalidInputException
         *
         * @param x: String of where the error was found
         */
        public invalidInputException(String x) {
            super(x + ". Please try again.");
        }
    }



    public static class supervisorNotFoundException extends Exception {
        public supervisorNotFoundException() {
            super("Supervisor Entered Not Found!");
        }
    }

    public static class noSuchProjectException extends Exception{
        public noSuchProjectException() {
            super("Project ID Does Not Exist!");
        }
    }

    public static class projectNotAvailableException extends Exception {
        public projectNotAvailableException() {
            super("Project is not available!");
        }
    }

    public static class supervisorMaxProjectsReachedException extends Exception{
        public supervisorMaxProjectsReachedException(){
            super("Warning: You have already reached the maximum number of projects you can manage.");
        }
        public supervisorMaxProjectsReachedException(String supervisorName){
            super(("Warning: " + supervisorName + " has reached the project limit."));
        }
    }

    public static class supervisorMaxProjectsReachedButStillAcceptedException extends supervisorMaxProjectsReachedException{
        public supervisorMaxProjectsReachedButStillAcceptedException(String supervisorName){
            super(supervisorName);
            System.out.println("Rejecting all pending registration requests for " + supervisorName + "'s projects.");
            System.out.println("Setting all of " + supervisorName + "'s projects to unavailable");
        }
    }

    public static class invalidLogin extends Exception{
        public invalidLogin(){
            super("Invalid Login Details! Returning to Login Page");
            try{
                Thread.sleep(100);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    public static class pendingRequestException extends Exception{
        public pendingRequestException(){
            super("Error: You have a pending registration.");
            try{
                Thread.sleep(100);
            } catch (Exception ignored){

            }
        }

    }

    public static class notRegisteredException extends Exception{
        public notRegisteredException(){
            super("Error: You are not registered for an FYP.");
            try{
                Thread.sleep(100);
            } catch (Exception ignored){

            }
        }
    }

    public static class deregisteredException extends Exception{
        public deregisteredException(){
            super("Error: You have deregistered for an FYP.");
            try{
                Thread.sleep(100);
            } catch (Exception ignored){

            }
        }

    }
    public static class alreadyRegisteredException extends Exception{
        public alreadyRegisteredException(){
            super("Error: You are already registered for an FYP.");
            try{
                Thread.sleep(100);
            } catch (Exception ignored){

            }
        }
    }

    public static class notAuthorizedException extends Exception{
        public notAuthorizedException(){
            super("Error: You are not authorized to perform this function.");
            try{
                Thread.sleep(100);
            } catch (Exception ignored){

            }
        }
    }

    public static boolean checkStudentStatusExceptionFunction(StudentAccount studentAccount){
        try {
            if (studentAccount.getStatus() == StudentStatus.NO_PROJECT) {
                throw new fypmsExceptions.notRegisteredException();
            } else if (studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
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

    public static int validateRequestActionFunction(){
        Scanner sc = new Scanner(System.in);
        int requestAction = 0;
        do {
            try {
                System.out.println("Select option:");
                System.out.println("1. Accept registration request");
                System.out.println("2. Reject registration request");
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
