package account;

import account.supervisor.FYPCoordinatorAccount;
import account.supervisor.SupervisorAccount;
import account.student.StudentAccount;
import account.student.StudentStatus;

import java.util.ArrayList;

public class AccountManager {
    private static final ArrayList<StudentAccount> StudentAccount = new ArrayList<StudentAccount>();
    private static final ArrayList<FYPCoordinatorAccount> FYPCoordinatorAccounts = new ArrayList<FYPCoordinatorAccount>();
    private static final ArrayList<SupervisorAccount> SupervisorAccounts = new ArrayList<SupervisorAccount>();

    public static SupervisorAccount getSupervisorAccount(String SuperName) {
        for (SupervisorAccount account : SupervisorAccounts) {
            if (account.getName().equals(SuperName)) {
                return account;
            }
        }
        return null; // account not found
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

    public static String getStudentEmail(String studentID) {
        for (StudentAccount account : StudentAccount) {
            if (account.getLoginId().equals(studentID)) {
                return account.getEmail();
            }
        }
        return null;
    }

    public static String getStudentName(String studentID) {
        for (StudentAccount account : StudentAccount) {
            if (account.getLoginId().equals(studentID)) {
                return account.getName();
            }
        }
        return null;
    }

    public static String getSupervisorName(String supervisorID) {
        for (SupervisorAccount account : SupervisorAccounts) {
            if (account.getLoginId().equals(supervisorID)) {
                return account.getName();
            }
        }
        return null;
    }

    public static String getSupervisorEmail(String supervisorID) {
        for (SupervisorAccount account : SupervisorAccounts) {
            if (account.getLoginId().equals(supervisorID)) {
                return account.getEmail();
            }
        }
        return null;
    }

    public static void setStudentStatus(String studentID, StudentStatus studentStatus, int fypID) {
        for (StudentAccount account : StudentAccount) {
            if (account.getLoginId().equals(studentID)) {
                account.setStatus(studentStatus);
                account.setAssignedProject(fypID);
            }
        }
    }
    
}
