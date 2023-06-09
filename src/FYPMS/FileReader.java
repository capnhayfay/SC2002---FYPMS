package src.FYPMS;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.FYPMS.project.FYPStatus;
import src.FYPMS.request.*;
import src.account.AccountManager;
import src.account.UserType;
import src.account.student.StudentAccount;
import src.account.student.StudentStatus;
import src.account.supervisor.FYPCoordinatorAccount;
import src.account.supervisor.SupervisorAccount;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static src.account.student.StudentStatus.StudentStatusToEnum;

/**
 * FileReader class for reading text files which serve as the src.database for projects
 */
public class FileReader {

    /**
     * Default constructor for the FileReader Class
     */
    public FileReader() {

    }

    /**
     * Reads supervisors from a CSV file and adds them to the FYPList.
     *
     * @param fileName the name of the CSV file to read from
     */
    public static void readSupervisorsFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<SupervisorAccount> supervisorList = AccountManager.getSupervisorList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                UserType userType = UserType.Supervisor;
                String[] attributes = line.split("\\t");
                String name = attributes[0];
                String email = attributes[1];
                String password = "password";
                if (attributes.length > 2) {
                    password = attributes[2];
                }
                int atIndex = email.indexOf("@");
                String userId = email.substring(0, atIndex);
                ArrayList<String> fypList = getSupervisorFYPs(name);
                SupervisorAccount supervisor = new SupervisorAccount(userId, password, userType, email, name, fypList);
                supervisorList.add(supervisor);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Reads FYP coordinators from file and adds them to the FYPCoordinatorList
     *
     * @param fileName the name of the CSV file to read from
     */
    public static void readCoordinatorsFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<FYPCoordinatorAccount> fypCoordinatorList = AccountManager.getCoordinatorList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                UserType userType = UserType.FYPCoordinator;
                String[] attributes = line.split("\\t");
                String name = attributes[0];
                String email = attributes[1];
                String password = "password";
                if (attributes.length > 2) {
                    password = attributes[2];
                }
                int atIndex = email.indexOf("@");
                String userId = email.substring(0, atIndex);
                ArrayList<String> fypList = getSupervisorFYPs(name);
                FYPCoordinatorAccount fypCoord = new FYPCoordinatorAccount(userId, password, userType, email, name,
                        fypList);
                fypCoordinatorList.add(fypCoord);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Reads FYP coordinators from file and adds them to the FYPCoordinatorList
     *
     * @param fileName the name of the CSV file to read from
     */
    public static void readStudentsFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<StudentAccount> studentList = AccountManager.getStudentList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                UserType userType = UserType.Student;
                String[] attributes = line.split("\\t");
                String name = attributes[0];
                String email = attributes[1];
                String password = attributes[2];
                int fypID = Integer.parseInt(attributes[3]);
                StudentStatus studentStatus = StudentStatusToEnum(attributes[4]);
                int atIndex = email.indexOf("@");
                String userId = email.substring(0, atIndex);

                StudentAccount student = new StudentAccount(userId, password, userType, email, name, fypID,
                        studentStatus);

                studentList.add(student);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Reads FYPs from file and adds them to the FYPList
     *
     * @param fileName the name of the CSV file to read from
     */
    public static void readFYPsFromFile(String fileName) {

        Path pathToFile = Paths.get(fileName);
        ArrayList<FYP> fypList = FYPList.getFypList();
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                FYP fyp = null;
                String[] attributes = line.split("\\t");
                int projectId = Integer.parseInt(attributes[0]);
                String supervisorName = attributes[1];
                supervisorName = supervisorName.replace('\"', ' ').trim();
                String supervisorEmail = attributes[2];
                String title = attributes[3];
                FYPStatus status = FYPStatus.StringtoStatus(attributes[4]);
                String studentID = null;
                String studentName = null;
                String studentEmail = null;
                if (attributes.length >= 6 && attributes[5].length() > 3) {
                    studentID = attributes[5];
                    studentName = attributes[6];
                    studentEmail = attributes[7];
                }

                fyp = new FYP(projectId, supervisorName, supervisorEmail, studentID, studentName, studentEmail, title,
                        status);
                fypList.add(fyp);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Reads requests from files and populates the corresponding request lists.
     *
     * @param fileName  the name of the file containing the RequestChangeTitle
     *                  requests.
     * @param fileName1 the name of the file containing the RequestDeregister
     *                  requests.
     * @param fileName2 the name of the file containing the RequestRegister
     *                  requests.
     * @param fileName3 the name of the file containing the
     *                  RequestTransferSupervisor requests.
     */

    public static void readRequestsFromFile(String fileName, String fileName1, String fileName2, String fileName3) {
        Path pathToFile = Paths.get(fileName);
        ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();

        ArrayList<Request> requestChangeTitleList = new ArrayList<Request>();
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine(); // skip header
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split("\\t");
                int requestId = Integer.parseInt(attributes[0]);
                String requesterID = attributes[1];
                String requesteeID = attributes[2];
                RequestStatus requestStatus = RequestStatus.StringtoRequestStatus(attributes[3]);
                int fypID = Integer.parseInt(attributes[4]);
                String newTitle = attributes[5];
                RequestChangeTitle requestcChangeTitle = new RequestChangeTitle(requestId, requesterID, requesteeID, requestStatus,
                        fypID, newTitle);
                requestChangeTitleList.add(requestcChangeTitle);
                line = br.readLine();
            }
            requestHistory.add(requestChangeTitleList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        pathToFile = Paths.get(fileName1);
        ArrayList<Request> requestDeregisterList = new ArrayList<Request>();
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine(); // skip header
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split("\\t");
                int requestId = Integer.parseInt(attributes[0]);
                String requesterID = attributes[1];
                RequestStatus requestStatus = RequestStatus.StringtoRequestStatus(attributes[2]);
                int fypID = Integer.parseInt(attributes[3]);
                RequestDeregister requestdDeregister = new RequestDeregister(requestId, requesterID, requestStatus, fypID);
                requestDeregisterList.add(requestdDeregister);
                line = br.readLine();
            }
            requestHistory.add(requestDeregisterList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        pathToFile = Paths.get(fileName2);
        ArrayList<Request> requestRegisterList = new ArrayList<Request>();
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine(); // skip header
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split("\\t");
                int requestId = Integer.parseInt(attributes[0]);
                String requesterID = attributes[1];
                RequestStatus requestStatus = RequestStatus.StringtoRequestStatus(attributes[2]);
                int fypID = Integer.parseInt(attributes[3]);
                RequestRegister requestrRegister = new RequestRegister(requestId, requesterID, requestStatus, fypID);
                requestRegisterList.add(requestrRegister);
                line = br.readLine();
            }
            requestHistory.add(requestRegisterList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        pathToFile = Paths.get(fileName3);
        ArrayList<Request> requestTransferSupervisorList = new ArrayList<Request>();
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine(); // skip header
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split("\\t");
                int requestId = Integer.parseInt(attributes[0]);
                String requesterID = attributes[1];
                RequestStatus requestStatus = RequestStatus.StringtoRequestStatus(attributes[2]);
                int fypID = Integer.parseInt(attributes[3]);
                String newSupervisorId = attributes[4];
                RequestTransferSupervisor requestTransferSupervisor = new RequestTransferSupervisor(requestId, requesterID, requestStatus,
                        fypID, newSupervisorId);
                requestTransferSupervisorList.add(requestTransferSupervisor);
                line = br.readLine();
            }
            requestHistory.add(requestTransferSupervisorList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Returns a list of FYP titles allocated to a specific supervisor.
     *
     * @param supervisorName the name of the supervisor to get the FYPs for
     * @return an ArrayList of Strings representing the titles of the FYPs allocated
     * to the specified supervisor
     */
    public static ArrayList<String> getSupervisorFYPs(String supervisorName) {
        ArrayList<String> supervisorFYPList = new ArrayList<String>();
        for (FYP fyp : FYPList.getFypList()) {
            if (fyp.getSupervisorName().equals(supervisorName) && fyp.getStatus() == FYPStatus.ALLOCATED) {
                supervisorFYPList.add(fyp.getTitle());
            }
        }
        return supervisorFYPList;
    }
}
