package FYPMS;

// import FYPMS1.student.StudentAccount;
import FYPMS.request.*;

import account.UserType;
import account.student.StudentAccount;
import account.coordinator.FYPCoordinatorAccount;
import account.student.StudentStatus;
import account.supervisor.SupervisorAccount;

import FYPMS.project.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
        import java.util.ArrayList;

        import FYPMS.project.FYP;

import static account.student.StudentStatus.StudentStatusToEnum;

public class FileReader {

    /**
     * Reads FYPs from file and adds them to the FYPList
     * 
     * @param fileName the name of the CSV file to read from
     */
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
                String supervisor = attributes[1];
                String title = attributes[2];
                FYPStatus status = FYP.convertToFYPStatus(attributes[3]);
                String studentID = attributes[4];
                fyp = new FYP(projectId, supervisor, studentID, title, status);
                fypList.addFYP(fyp);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readRequestsFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);
        RequestList requests = FYPMS1.getRequestList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine(); // skip header
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split("\\t");
                String requesterName = attributes[0];
                RequestRelationship requestType = Request.convertToRequestType(attributes[1]);
                LocalDateTime statusChangeTime = LocalDateTime.now();
                String requestee = attributes[3];
                RequestStatus requestStatus = Request.convertToRequestStatus(attributes[4]);
                Request request = new Request(requesterName, requestee, requestType, statusChangeTime, requestee, requestStatus);
                requests.add(request);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // Print the details of each request
        for (Request request : requests) {
            request.printDetails();
        }
    }

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
                SupervisorAccount student = new SupervisorAccount(userId, password, userType, email, name);

                supervisorList.add(student);
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
                FYPCoordinatorAccount student = new FYPCoordinatorAccount(userId, password, userType, email, name);

                fypCoordinatorList.add(student);
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

                StudentAccount student = new StudentAccount(userId, password, userType, email, name, fypID,studentStatus);

                studentList.add(student);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}