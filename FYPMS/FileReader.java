package FYPMS;

import FYPMS.faculty.coordinator.Coordinator;
import FYPMS.faculty.coordinator.CoordinatorList;
import FYPMS.faculty.supervisor.Supervisor;
import FYPMS.faculty.supervisor.SupervisorList;
import FYPMS.student.Student;
import FYPMS.student.StudentList;
import FYPMS.request.*;

import FYPMS.project.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class FileReader{

    /**
     * Reads FYPs from file and adds them to the FYPList
     * @param fileName the name of the CSV file to read from
     */
    public static void readFYPsFromFile(String fileName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Path pathToFile = Paths.get(fileName);
        FYPList fypList = FYPMS.getFypList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                String supervisor = attributes[0];
                String title = attributes[1];
                FYPStatus status = FYP.convertToFYPStatus(attributes[2]);
                String studentName = attributes[3];
                List <String> requestorList = List.of(attributes[4].split(";"));
                LocalDateTime statusChangeDate = LocalDateTime.now();
                FYP fyp;
                fyp = new FYP(supervisor, title, status, studentName, requestorList, statusChangeDate);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine(); // skip header
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
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

        SupervisorList supervisorList = FYPMS.getSupervisorList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                line = line.trim();
                String[] attributes = line.split(",");
                String name = attributes[0];
                String email = attributes[1];
                String project1 = attributes[2].equals("0") ? "0" : attributes[2];
                String project2 = attributes[3].equals("0") ? "0" : attributes[3];

                Supervisor supervisor = new Supervisor(name, email, project1, project2);

                supervisorList.addSupervisor(supervisor);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readCoordinatorFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        CoordinatorList coordinatorList = FYPMS.getCoordinatorList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                String name = attributes[0];
                String email = attributes[1];

                Coordinator coordinator = new Coordinator(name, email);

                coordinatorList.addCoordinator(coordinator);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void readStudentFromFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        StudentList studentList = FYPMS.getStudentList();

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                String name = attributes[0];
                String email = attributes[1];
                int[] projectRequests = Arrays.stream(attributes[2].split(";")).mapToInt(Integer::parseInt).toArray();

                Student student = new Student(name, email, projectRequests);

                studentList.addStudent(student);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}