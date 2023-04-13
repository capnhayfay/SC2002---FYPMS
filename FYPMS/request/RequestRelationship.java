package FYPMS.request;

/**
 * Represents the class level of a request hall
 */
public enum RequestRelationship {
    STUDENTSupervisor,
    STUDENTCoordinator,
    SUPERVISORCoordinator;

    public static RequestRelationship convertToRequestRelationship(String classLevel) {
        return switch (classLevel.toLowerCase()) {
            case "STUDENTCoordinator" -> RequestRelationship.STUDENTCoordinator;
            case "STUDENTSupervisor" -> RequestRelationship.STUDENTSupervisor;
            case "SUPERVISORCoordinator" -> RequestRelationship.SUPERVISORCoordinator;
            default -> null;
        };
    }

    public static String convertRequestTypeToString(RequestRelationship requestRelationship) {
        return switch (requestRelationship) {
            case STUDENTSupervisor -> "StudentSupervisor";
            case STUDENTCoordinator -> "StudentCoordinator";
            case SUPERVISORCoordinator -> "SupervisorCoordinator";
            default -> null;
        };
    }
}
