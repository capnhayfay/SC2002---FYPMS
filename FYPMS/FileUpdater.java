package FYPMS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import FYPMS.faculty.coordinator.Coordinator;
import FYPMS.faculty.coordinator.CoordinatorList;
import FYPMS.faculty.supervisor.Supervisor;
import FYPMS.faculty.supervisor.SupervisorList;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.request.Request;
import FYPMS.request.RequestList;
import FYPMS.student.Student;
import FYPMS.student.StudentList;

public class FileUpdater {
    /**
     * Writes the FYP list to file
     *
     * @param fileName the name of the CSV file to write to
     */
    public static void writeFYPsToFile(String fileName) {
        FYPList fypList = FYPMS.getFypList();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            // Write the header
            bw.write("Supervisor,Title,Status,Student Name,Requestors,Status Change Date\n");

            // Write each FYP
            for (FYP fyp : fypList.getFYPs()) {
                String supervisor = fyp.getSupervisorName();
                String title = fyp.getTitle();
                String status = fyp.getStatus().toString();
                String studentName = fyp.getStudentName();
                List<String> requestorList = fyp.getRequesterList();
                LocalDateTime statusChangeDate = fyp.getStatusChangeDate();
                String requestorString = String.join(";", requestorList);

                bw.write(String.format("%s,%s,%s,%s,%s,%s,%s\n", supervisor, title, status, studentName, requestorString, statusChangeDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Writes the request list to file
     *
     * @param fileName the name of the CSV file to write to
     */
    public static void writeRequestsToFile(String fileName) {
        RequestList requestList = FYPMS.getRequestList();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {

            // Write the header
            bw.write("Requester Name,Request Type,Status Change Time\n");

            // Write each request
            for (Request request : requestList) {
                String requesterName = request.getRequesterName();
                String requestType = request.getRequestType().toString();
                LocalDateTime statusChangeTime = request.getStatusChangeTime();
                bw.write(String.format("%s,%s,%s\n", requesterName, requestType, statusChangeTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Writes the supervisor list to file
     *
     * @param fileName the name of the CSV file to write to
     */
    public static void writeSupervisorToFile(String fileName) {
        SupervisorList supervisorList = FYPMS.getSupervisorList();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            for (Supervisor supervisor : supervisorList.getSupervisors()) {
                bw.write(supervisor.getName() + "," + supervisor.getEmail()+ "," + supervisor.getProj_1()+ "," + supervisor.getProj_2());
                bw.newLine();
            }
            System.out.println("Supervisors successfully written to " + fileName);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void writeCoordinatorToFile(String fileName) {
        CoordinatorList coordinatorList = FYPMS.getCoordinatorList();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            for (Coordinator coordinator : coordinatorList.getCoordinatorList()) {
                StringBuilder sb = new StringBuilder();
                sb.append(coordinator.getName());
                sb.append(",");
                sb.append(coordinator.getEmail());
                sb.append("\n");
                bw.write(sb.toString());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    public static void writeStudentToFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        StudentList studentList = FYPMS.getStudentList();

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            for (Student student : studentList.getStudentList()) {
                bw.write(student.getName() + "," + student.getEmail() + ",");
                int[] projectRequests = student.getProjectsRequested();
                for (int i = 0; i < projectRequests.length; i++) {
                    bw.write(projectRequests[i] + ";");
                    if (i < projectRequests.length - 1) {
                        bw.write(" ");
                    }
                }
                bw.newLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
