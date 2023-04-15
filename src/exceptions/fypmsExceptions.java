package src.exceptions;

/**
 * Class for containing all src.FYPMS src.exceptions
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
        /**
         * Constructs a new exception with {@code null} as its detail message.
         * The cause is not initialized, and may subsequently be initialized by a
         * call to {@link #initCause}.
         */
        public noSuchProjectException() {
            super("Project ID Does Not Exist!");
        }
    }

    public static class noRegistererdProjectException extends Exception{
        public noRegistererdProjectException(){
            super("No projects registered!");
        }
    }

    public static class projectNotAvailableException extends Exception {
        public projectNotAvailableException() {
            super("Project is not available!");
        }
    }

    public static class supervisorMaxProjectsReachedException extends Exception{
        public supervisorMaxProjectsReachedException(){
            super("Supervisor has reached maximum number of projects they can manage!");
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
}
