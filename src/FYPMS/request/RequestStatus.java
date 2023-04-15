package src.FYPMS.request;

/**
 * Enum for request status
 */
public enum RequestStatus {
    /**
     * Pending Request
     */
    PENDING,
    /**
     * Rejected Request
     */
    REJECTED,
    /**
     * Approved Request
     */
    APPROVED;

    /**
     * Convert string to enum
     *
     * @param str String rep of status
     * @return enum of request status: RequestStatus
     */
    public static RequestStatus StringtoRequestStatus(String str) {
        return switch (str) {
            case "PENDING" -> PENDING;
            case "REJECTED" -> REJECTED;
            case "APPROVED" -> APPROVED;
            default -> null;
        };
    }

}