package FYPMS.request;

public enum RequestStatus {
    PENDING,
    REJECTED,
    APPROVED;

    public static RequestStatus StringtoRequestStatus(String str){
        return switch (str) {
            case "PENDING" -> PENDING;
            case "REJECTED" -> REJECTED;
            case "APPROVED" -> APPROVED;
            default -> null;
        };
    }

}