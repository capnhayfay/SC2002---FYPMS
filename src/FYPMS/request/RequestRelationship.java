package src.FYPMS.request;

/**
 * Represents the class level of a request
 */
public enum RequestRelationship {
    /**
     * Student to supervisor request
     */
    STUDENTSupervisor,
    /**
     * Student to coordinator request
     */
    STUDENTCoordinator,
    /**
     * Supervisor to coordinator request
     */
    SUPERVISORCoordinator;

    /**
     * Convert string to enum
     *
     * @param classLevel the string representation of the class level
     * @return Enum status of request relation: RequestRelationship
     */
    public static RequestRelationship convertToRequestRelationship(String classLevel) {
        return switch (classLevel.toLowerCase()) {
            case "studentcoordinator" -> RequestRelationship.STUDENTCoordinator;
            case "studentsupervisor" -> RequestRelationship.STUDENTSupervisor;
            case "supervisorcoordinatr" -> RequestRelationship.SUPERVISORCoordinator;
            default -> null;
        };
    }

    /**
     * Returns request type to string
     *
     * @param requestRelationship Enum of request relation
     * @return string version of request: String
     */
    public static String convertRequestTypeToString(RequestRelationship requestRelationship) {
        return switch (requestRelationship) {
            case STUDENTSupervisor -> "StudentSupervisor";
            case STUDENTCoordinator -> "StudentCoordinator";
            case SUPERVISORCoordinator -> "SupervisorCoordinator";
            default -> null;
        };
    }
}
