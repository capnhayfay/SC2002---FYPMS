package FYPMS;

import FYPMS.request.*;
import account.AccountManager;
import account.UserType;
import account.student.StudentAccount;
import account.supervisor.FYPCoordinatorAccount;
import account.student.StudentStatus;
import account.supervisor.SupervisorAccount;

import FYPMS.project.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static account.student.StudentStatus.StudentStatusToEnum;

public class FileReader {

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
                if (attributes[5].length() > 3) {
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
     * 
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
        ArrayList<ArrayList<Request>> requests = RequestHistory.getRequestList();

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
                RequestChangeTitle request = new RequestChangeTitle(requestId, requesterID, requesteeID, requestStatus,
                        fypID, newTitle);
                requestChangeTitleList.add(request);
                line = br.readLine();
            }
            requests.add(requestChangeTitleList);
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
                RequestDeregister request = new RequestDeregister(requestId, requesterID, requestStatus, fypID);
                requestDeregisterList.add(request);
                line = br.readLine();
            }
            requests.add(requestDeregisterList);
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
                RequestRegister request = new RequestRegister(requestId, requesterID, requestStatus, fypID);
                requestRegisterList.add(request);
                line = br.readLine();
            }
            requests.add(requestRegisterList);
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
                RequestTransferSupervisor request = new RequestTransferSupervisor(requestId, requesterID, requestStatus,
                        fypID, newSupervisorId);
                requestTransferSupervisorList.add(request);
                line = br.readLine();
            }
            requests.add(requestTransferSupervisorList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * 
     * Returns a list of FYP titles allocated to a specific supervisor.
     * 
     * @param supervisorName the name of the supervisor to get the FYPs for
     * @return an ArrayList of Strings representing the titles of the FYPs allocated
     *         to the specified supervisor
     */
    public static ArrayList<String> getSupervisorFYPs(String supervisorName) {
        ArrayList<String> supervisorFYPs = new ArrayList<String>();
        for (FYP fyp : FYPList.getFypList()) {
            if (fyp.getSupervisorName().equals(supervisorName) && fyp.getStatus() == FYPStatus.ALLOCATED) {
                supervisorFYPs.add(fyp.getTitle());
            }
        }
        return supervisorFYPs;
    }
}
