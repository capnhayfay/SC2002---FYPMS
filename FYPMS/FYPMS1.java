package FYPMS;

import account.supervisor.FYPCoordinatorAccount;
import account.supervisor.SupervisorAccount;

import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.request.Request;
import FYPMS.request.RequestChangeTitle;
import FYPMS.request.RequestDeregister;
import FYPMS.request.RequestList;
import FYPMS.request.RequestRegister;
import FYPMS.request.RequestTransferSupervisor;
import account.student.StudentAccount;
import account.student.StudentStatus;

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

    public static RequestChangeTitle getRequestChangeTitle(int requestID) {
        for (Object indivRequest : requests.get(0)) {
            RequestChangeTitle indivCastedRequest = (RequestChangeTitle) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
    }

    public static RequestDeregister getRequestDeregister(int requestID) {
        for (Object indivRequest : requests.get(1)) {
            RequestDeregister indivCastedRequest = (RequestDeregister) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
    }

    public static RequestRegister getRequestRegister(int requestID) {
        for (Object indivRequest : requests.get(2)) {
            RequestRegister indivCastedRequest = (RequestRegister) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
    }

    public static RequestTransferSupervisor getRequestTransferSupervisor(int requestID) {
        for (Object indivRequest : requests.get(3)) {
            RequestTransferSupervisor indivCastedRequest = (RequestTransferSupervisor) indivRequest;
            if (indivCastedRequest.getRequestID() == requestID) {
                return indivCastedRequest;
            }
        }
        return null;
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
                ;
                account.setAssignedProject(fypID);
            }
        }
    }
}
