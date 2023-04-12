package FYPMS.project;

/**
 * Represents the status of movies
 */
public enum FYPStatus {
    AVAILABLE("AVAILABLE"),
    RESERVED("RESERVED"),
    UNAVAILABLE("UNAVAILABLE"),
    ALLOCATED("ALLOCATED");

    private final String toString;

    FYPStatus(String toString) {
        this.toString = toString;
    }

    public static FYPStatus intToEnum(int num) {
        return switch (num) {
            case 1 -> RESERVED;
            case 2 -> UNAVAILABLE;
            case 3 -> ALLOCATED;
            default -> AVAILABLE;
        };

    }
}
