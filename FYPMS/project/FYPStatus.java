package FYPMS.project;

/**
 * Represents the status of movies
 */
public enum FYPStatus {
    AVAILABLE("AVAILABLE"),
    RESERVED("RESERVED"),
    ASSIGNED("ASSIGNED"),
    REMOVED("PROJECT REMOVED");

    private final String toString;
    private FYPStatus(String toString) {
        this.toString = toString;
    }

    public static FYPStatus intToEnum(int num){
        return switch (num) {
            case 1 -> RESERVED;
            case 2 -> ASSIGNED;
            case 3 -> REMOVED;
            default -> AVAILABLE;
        };

    }
}
