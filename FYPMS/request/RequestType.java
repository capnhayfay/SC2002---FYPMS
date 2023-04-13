package FYPMS.request;

public enum RequestType {
    REGISTER_PROJECT,
    DEREGISTER_PROJECT,
    CHANGE_TITLE,
    TRANSFER_SUPERVISOR;

    public static RequestType convertToRequestType(String string) {
        return switch (string) {
            case "REGISTER_PROJECT" -> RequestType.REGISTER_PROJECT;
            case "DEREGISTER_PROJECT" -> RequestType.DEREGISTER_PROJECT;
            case "CHANGE_TITLE" -> RequestType.CHANGE_TITLE;
            case "TRANSFER_SUPERVISOR" -> RequestType.TRANSFER_SUPERVISOR;
            default -> null;
        };
    }

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
