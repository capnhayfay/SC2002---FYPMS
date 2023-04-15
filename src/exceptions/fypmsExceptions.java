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
     * Inner static class for invalidInpput Exceptions
     */
    public static class invalidInputException extends Exception {
        /**
         * Constructor for invalidInputException
         *
         * @param x: String of where the error was found
         */
        public invalidInputException(String x) {
            // Scanner sc = new Scanner(System.in);
            super("Invalid input format for " + x + ". Please try again.");
            // sc.next();
        }
    }

    public static class noRegistererdProjectException extends Exception{

    }

}
