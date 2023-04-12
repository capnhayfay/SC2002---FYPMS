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
                FYP fyp = null;
                String[] attributes = line.split("\\t");
                int projectId = Integer.parseInt(attributes[0]);
                String supervisor = attributes[1];
                String supervisorEmail = "";
                for (SupervisorAccount s : FYPMS.getSupervisorList()) {
                    if (s.getName().equals(supervisor)) {
                        supervisorEmail = s.getEmail();
                        break;
                    }
                }
                String title = attributes[2];
                FYPStatus status = FYP.convertToFYPStatus("");

                if (attributes.length > 3) {
                    status = FYP.convertToFYPStatus(attributes[3]);
                    if (attributes.length > 4) {
                        String student = attributes[4];
                        String studentEmail = "";
                        for (StudentAccount s : FYPMS.getStudentList()) {
                            if (s.getName().equals(student)) {
                                studentEmail = s.getEmail();
                                break;
                            }
                        }
                        fyp = new FYP(projectId, supervisor, supervisorEmail, student, studentEmail, title, status);
                    }
                } else {
                    fyp = new FYP(projectId, supervisor, supervisorEmail, title, status);
                }
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

    public static void readSupervisorsFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<SupervisorAccount> supervisorList = FYPMS.getSupervisorList();

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

        ArrayList<FYPCoordinatorAccount> fypCoordinatorList = FYPMS.getCoordinatorList();

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
                if (attributes.length > 2) {
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