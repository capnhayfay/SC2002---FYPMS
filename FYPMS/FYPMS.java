package FYPMS;

import FYPMS.faculty.coordinator.CoordinatorList;
import FYPMS.faculty.supervisor.SupervisorList;
import FYPMS.project.FYPList;
import FYPMS.request.RequestList;
import FYPMS.student.StudentList;

/**
 * Represents the company this application is created for
 * Contains all bookings, movies and cineplexes the company owns
 */
public class FYPMS {

    private static final CoordinatorList coordinatorList = new CoordinatorList();
    private static final SupervisorList supervisorList = new SupervisorList();
    private static final StudentList studentList = new StudentList();

    private static final RequestList requestList = new RequestList();

    private static final FYPList fypList = new FYPList();

    public static FYPList getFypList() {
        return fypList;
    }

    public static SupervisorList getSupervisorList() {
        return supervisorList;
    }

    public static RequestList getRequestList() {
        return requestList;
    }

    public static CoordinatorList getCoordinatorList() {return coordinatorList;
    }

    public static StudentList getStudentList() {
        return studentList;
    }
}
