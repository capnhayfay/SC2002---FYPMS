package FYPMS;

// import FYPMS1.student.StudentAccount;
import FYPMS.request.*;

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

import FYPMS.project.FYP;

import static account.student.StudentStatus.StudentStatusToEnum;

public class FileReader {

    private static final FYP[] FYPList = null;

    /**
     * Reads FYPs from file and adds them to the FYPList
     * 
     * @param fileName the name of the CSV file to read from
     */
    public static void readSupervisorsFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<SupervisorAccount> supervisorList = FYPMS1.getSupervisorList();

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

    public static void readCoordinatorsFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<FYPCoordinatorAccount> fypCoordinatorList = FYPMS1.getCoordinatorList();

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

    public static void readStudentsFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<StudentAccount> studentList = FYPMS1.getStudentList();

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

    public static void readFYPsFromFile(String fileName) {
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd
        // HH:mm");
        Path pathToFile = Paths.get(fileName);
        FYPList fypList = FYPMS1.getFypList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                FYP fyp = null;
                String[] attributes = line.split("\\t");
                int projectId = Integer.parseInt(attributes[0]);
                String supervisorName = attributes[1];
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
                fypList.addFYP(fyp);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readRequestsFromFile(String fileName, String fileName1, String fileName2, String fileName3) {
        Path pathToFile = Paths.get(fileName);
        ArrayList<ArrayList<Object>> requests = FYPMS1.getRequestList();

        ArrayList<Object> requestChangeTitleList = new ArrayList<Object>();
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
        ArrayList<Object> requestDeregisterList = new ArrayList<Object>();
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
        ArrayList<Object> requestRegisterList = new ArrayList<Object>();
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
        ArrayList<Object> requestTransferSupervisorList = new ArrayList<Object>();
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

    public static ArrayList<String> getSupervisorFYPs(String supervisorName) {
        ArrayList<String> supervisorFYPs = new ArrayList<String>();
        for (FYP fyp : FYPMS1.getFypList().getFYPs()) {
            if (fyp.getSupervisorName().equals(supervisorName) && fyp.getStatus() == FYPStatus.ALLOCATED) {
                supervisorFYPs.add(fyp.getTitle());
            }
        }
        return supervisorFYPs;
    }
}
