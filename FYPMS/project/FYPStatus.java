package FYPMS.project;

/**
 * Represents the status of movies
 */
public enum FYPStatus {
    AVAILABLE("AVAILABLE"),
    UNAVAILABLE("UNAVAILABLE"),
    RESERVED("RESERVED"),
    ASSIGNED("ASSIGNED");

    private final String toString;

    FYPStatus(String toString) {
        this.toString = toString;
    }

    public static FYPStatus intToEnum(int num) {
        return switch (num) {
            case 1 -> UNAVAILABLE;
            case 2 -> RESERVED;
            case 3 -> ASSIGNED;
            default -> AVAILABLE;
        };

    }
}
