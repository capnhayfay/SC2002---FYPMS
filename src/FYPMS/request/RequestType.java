package src.FYPMS.request;

/**
 * Enum for request type
 */
public enum RequestType {
    /**
     * Request to Register Project
     */
    REGISTER_PROJECT,
    /**
     * Request to Deregister Project
     */
    DEREGISTER_PROJECT,
    /**
     * Request to Change Project Title
     */
    CHANGE_TITLE,
    /**
     * Request to Transfer Project to Different Supervisor
     */
    TRANSFER_SUPERVISOR;

    /**
     * Converts request type string to enum
     *
     * @param string format of request type in string
     * @return enum format of request type: RequestType
     */
    public static RequestType convertToRequestType(String string) {
        return switch (string) {
            case "REGISTER_PROJECT" -> RequestType.REGISTER_PROJECT;
            case "DEREGISTER_PROJECT" -> RequestType.DEREGISTER_PROJECT;
            case "CHANGE_TITLE" -> RequestType.CHANGE_TITLE;
            case "TRANSFER_SUPERVISOR" -> RequestType.TRANSFER_SUPERVISOR;
            default -> null;
        };
    }

    /**
     * Converts to string
     *
     * @param requestType enum
     * @return string of enum
     */
    public static String convertRequestTypeToString(RequestType requestType) {
        return switch (requestType) {
            case REGISTER_PROJECT -> "REGISTER_PROJECT";
            case DEREGISTER_PROJECT -> "DEREGISTER_PROJECT";
            case CHANGE_TITLE -> "CHANGE_TITLE";
            case TRANSFER_SUPERVISOR -> "TRANSFER_SUPERVISOR";
            default -> null;
        };
    }
}
