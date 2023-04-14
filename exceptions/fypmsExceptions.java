package exceptions;

public class fypmsExceptions {

    @SuppressWarnings("serial")
    public static class invalidInputException extends Exception {
        public invalidInputException(String x) {
            // Scanner sc = new Scanner(System.in);
            super("Invalid input format for " + x + ". Please try again.");
            // sc.next();
        }
    }

}
