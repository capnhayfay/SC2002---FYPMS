package FYPMS.request;

public enum RequestStatus {
    PENDING("PENDING"),
    REJECTED("REJECTED"),
    APPROVED("APPROVED");

    private final String toString;

    RequestStatus(String toString) {
        this.toString = toString;
    }

    public static FYPMS.request.RequestStatus intToEnum(int num){
        return switch (num) {
            case 1 -> PENDING;
            case 2 -> REJECTED;
            case 3 -> APPROVED;
            default -> null;
        };

    }

}