package FYPMS;

import account.supervisor.FYPCoordinatorAccount;
import account.supervisor.SupervisorAccount;

import FYPMS.project.FYPList;
import FYPMS.request.RequestList;
import account.student.StudentAccount;

import java.util.ArrayList;

/**
 * Represents the company this application is created for
 * Contains all bookings, movies and cineplexes the company owns
 */
public class FYPMS1 {

    // private static final CoordinatorList coordinatorList = new CoordinatorList();
    // private static final SupervisorList supervisorList = new SupervisorList();
    // private static final StudentList studentList = new StudentList();
    private static ArrayList<StudentAccount> StudentAccount = new ArrayList<StudentAccount>();
    private static ArrayList<FYPCoordinatorAccount> FYPCoordinatorAccounts = new ArrayList<FYPCoordinatorAccount>();
    private static ArrayList<SupervisorAccount> SupervisorAccounts = new ArrayList<SupervisorAccount>();

    static ArrayList<ArrayList<Object>> requests = new ArrayList<ArrayList<Object>>();
    

    private static final FYPList fypList = new FYPList();

    public static FYPList getFypList() {
        return fypList;
    }

    public static ArrayList<ArrayList<Object>> getRequestList() {
        return requests;
    }

    public static ArrayList<SupervisorAccount> getSupervisorList() {
        return SupervisorAccounts;
    }

    public static ArrayList<FYPCoordinatorAccount> getCoordinatorList() {
        return FYPCoordinatorAccounts;
    }

    public static ArrayList<StudentAccount> getStudentList() {
        return StudentAccount;
    }

    public static StudentAccount loginStudent(String loginId, String password) {
        for (StudentAccount account : StudentAccount) {
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
        for (StudentAccount account : StudentAccount) {
            System.out.println(account.getLoginId() + "+");
            System.out.println(account.getPassword());
        }
    }

    public static String getStudentEmail(String studentID)
    {
        for (StudentAccount account : StudentAccount)
        {
            if (account.getLoginId().equals(studentID))
            {
                return account.getEmail();
            }
        }
        return null;
    }

    public static String getStudentName(String studentID)
    {
        for (StudentAccount account : StudentAccount)
        {
            if (account.getLoginId().equals(studentID))
            {
                return account.getName();
            }
        }
        return null;
    }

    public static String getSupervisorName(String supervisorID)
    {
        for (SupervisorAccount account : SupervisorAccounts)
        {
            if (account.getLoginId().equals(supervisorID))
            {
                return account.getName();
            }
        }
        return null;
    }

    public static String getSupervisorEmail(String supervisorID)
    {
        for (SupervisorAccount account : SupervisorAccounts)
        {
            if (account.getLoginId().equals(supervisorID))
            {
                return account.getEmail();
            }
        }
        return null;
    }
}
