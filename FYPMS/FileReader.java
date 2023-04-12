package FYPMS;

import FYPMS.faculty.coordinator.Coordinator;
import FYPMS.faculty.coordinator.CoordinatorList;
import FYPMS.faculty.supervisor.Supervisor;
import FYPMS.faculty.supervisor.SupervisorList;
// import FYPMS.student.Student;
import FYPMS.student.StudentList;
import FYPMS.request.*;

import account.UserType;
import account.StudentAccount;
import account.FYPCoordinatorAccount;
import account.SupervisorAccount;

import FYPMS.project.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import FYPMS.project.FYP;

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
        FYPList fypList = FYPMS.getFypList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split("\\t");
                String supervisor = attributes[0];
                String title = attributes[1];
                FYPStatus status = FYP.convertToFYPStatus(attributes[2]);
                String studentName = attributes[3];
                String requestorList = attributes[4];
                LocalDateTime statusChangeDate = LocalDateTime.now();
                FYP fyp = new FYP(supervisor, title, status, studentName, requestorList, statusChangeDate);
                fypList.addFYP(fyp);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readRequestsFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);
        RequestList requests = FYPMS.getRequestList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine(); // skip header
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split("\\t");
                String requesterName = attributes[0];
                RequestType requestType = Request.convertToRequestType(attributes[1]);
                LocalDateTime statusChangeTime = LocalDateTime.now();
                String requestee = attributes[3];
                RequestStatus requestStatus = Request.convertToRequestStatus(attributes[4]);
                Request request = new Request(requesterName, requestType, statusChangeTime, requestee, requestStatus);
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

    public static void readSupervisorFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<SupervisorAccount> studentList = FYPMS.getSupervisorList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                UserType userType = UserType.Supervisor;
                String[] attributes = line.split("\\t");
                String name = attributes[0];
                String email = attributes[1];
                String password = "password";
                if (!attributes[2].isEmpty()) {
                    password = attributes[2];
                }
                int atIndex = email.indexOf("@");
                String userId = email.substring(0, atIndex);
                SupervisorAccount student = new SupervisorAccount(userId, password, userType, email, name);

                studentList.add(student);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readCoordinatorFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<FYPCoordinatorAccount> studentList = FYPMS.getCoordinatorList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                UserType userType = UserType.FYPCoordinator;
                String[] attributes = line.split("\\t");
                String name = attributes[0];
                String email = attributes[1];
                String password = "password";
                if (!attributes[2].isEmpty()) {
                    password = attributes[2];
                }
                int atIndex = email.indexOf("@");
                String userId = email.substring(0, atIndex);
                FYPCoordinatorAccount student = new FYPCoordinatorAccount(userId, password, userType, email, name);

                studentList.add(student);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readStudentFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<StudentAccount> studentList = FYPMS.getStudentList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                UserType userType = UserType.Student;
                String[] attributes = line.split("\\t");
                String name = attributes[0];
                String email = attributes[1];
                String password = "password";
                if (!attributes[2].isEmpty()) {
                    password = attributes[2];
                }
                int atIndex = email.indexOf("@");
                String userId = email.substring(0, atIndex);
                StudentAccount student = new StudentAccount(userId, password, userType, email, name);

                studentList.add(student);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}