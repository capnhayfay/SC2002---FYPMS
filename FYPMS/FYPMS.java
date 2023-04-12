package FYPMS;

import account.StudentAccount;
import account.FYPCoordinatorAccount;
import account.SupervisorAccount;

import FYPMS.faculty.coordinator.CoordinatorList;
import FYPMS.faculty.supervisor.SupervisorList;
import FYPMS.project.FYPList;
import FYPMS.request.RequestList;
import FYPMS.student.StudentList;

import java.util.ArrayList;

/**
 * Represents the company this application is created for
 * Contains all bookings, movies and cineplexes the company owns
 */
public class FYPMS {

    // private static final CoordinatorList coordinatorList = new CoordinatorList();
    // private static final SupervisorList supervisorList = new SupervisorList();
    // private static final StudentList studentList = new StudentList();
    private static ArrayList<StudentAccount> StudentAccounts = new ArrayList<StudentAccount>();
    private static ArrayList<FYPCoordinatorAccount> FYPCoordinatorAccounts = new ArrayList<FYPCoordinatorAccount>();
    private static ArrayList<SupervisorAccount> SupervisorAccounts = new ArrayList<SupervisorAccount>();

    private static final RequestList requestList = new RequestList();
    private static final FYPList fypList = new FYPList();

    public static FYPList getFypList() {
        return fypList;
    }

    public static RequestList getRequestList() {
        return requestList;
    }

    public static ArrayList<SupervisorAccount> getSupervisorList() {
        return SupervisorAccounts;
    }

    public static ArrayList<FYPCoordinatorAccount> getCoordinatorList() {
        return FYPCoordinatorAccounts;
    }

    public static ArrayList<StudentAccount> getStudentList() {
        return StudentAccounts;
    }

    public static StudentAccount loginStudent(String loginId, String password) {
        for (StudentAccount account : StudentAccounts) {
            if (account.getLoginId().equals(loginId) && account.login(loginId, password) != null) {
                return account;
            }
        }
        return null; // account not found
    }

    public static SupervisorAccount loginSupervisorAccount(String loginId, String password) {
        for (SupervisorAccount account : SupervisorAccounts) {
            if (account.getLoginId().equals(loginId) && account.login(loginId, password) != null) {
                return account;
            }
        }
        return null; // account not found
    }

    public static FYPCoordinatorAccount loginFypCoordinatorAccount(String loginId, String password) {
        for (FYPCoordinatorAccount account : FYPCoordinatorAccounts) {
            if (account.getLoginId().equals(loginId) && account.login(loginId, password) != null) {
                return account;
            }
        }
        return null; // account not found
    }

    public void printStudentList() {
        for (StudentAccount account : StudentAccounts) {
            System.out.println(account.getLoginId() + "+");
            System.out.println(account.getPassword());
        }
    }
}
