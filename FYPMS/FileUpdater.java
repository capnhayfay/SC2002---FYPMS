package FYPMS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import account.student.StudentAccount;
import account.supervisor.FYPCoordinatorAccount;
import account.supervisor.SupervisorAccount;
import FYPMS.project.FYP;
import FYPMS.project.FYPList;
import FYPMS.request.RequestChangeTitle;
import FYPMS.request.RequestDeregister;
import FYPMS.request.RequestRegister;
import FYPMS.request.RequestTransferSupervisor;

public class FileUpdater {
    /**
     * Writes the FYP list to file
     *
     * @param fileName the name of the CSV file to write to
     */
    public static void writeFYPsToFile(String fileName) {
        FYPList fypList = FYPMS1.getFypList();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {

            // Write the header
            bw.write("ProjectID\tSupervisorName\tSupervisorEmail\tTitle\tStatus\tStudentID\tStudentName\tStudentEmail");

            // Write each FYP
            for (FYP fyp : fypList.getFYPs()) {
                bw.newLine();
                bw.write(fyp.getProjectId() + "\t" + fyp.getSupervisorName() + "\t" + fyp.getSupervisorEmail() + "\t"
                        + fyp.getTitle() + "\t" + fyp.getStatus() + "\t" + fyp.getStudentID() + "\t"
                        + fyp.getStudentName() + "\t" + fyp.getStudentEmail());
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
    public static void writeRequestsToFile(String fileName, String filename1, String filename2, String filename3) {
        ArrayList<ArrayList<Object>> requestList = FYPMS1.getRequestList();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            // Write the header
            bw.write("requestID\trequesterID\trequesteeID\trequestStatus\tfypID\tnewTitle");
            // Write each request
            for (Object request : requestList.get(0)) {
                RequestChangeTitle indivCastedRequest = (RequestChangeTitle) request;
                bw.write("\n");
                bw.write(indivCastedRequest.getRequestID() + "\t" + indivCastedRequest.getRequesterID() + "\t"
                        + indivCastedRequest.getRequesteeID() + '\t' + indivCastedRequest.getRequestStatus() + '\t'
                        + indivCastedRequest.getFypID() + '\t' + indivCastedRequest.getNewTitle());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        pathToFile = Paths.get(filename1);
        try (
                BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            // Write the header
            bw.write("requestID\trequesterID\trequestStatus\tfypID");
            // Write each request
            for (Object request : requestList.get(1)) {
                RequestDeregister indivCastedRequest = (RequestDeregister) request;
                bw.write("\n");
                bw.write(indivCastedRequest.getRequestID() + "\t" + indivCastedRequest.getRequesterID() + "\t"
                        + indivCastedRequest.getRequestStatus() + '\t' + indivCastedRequest.getFypID());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        pathToFile = Paths.get(filename2);
        try (
                BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            // Write the header
            bw.write("requestID\trequesterID\trequestStatus\tfypID");
            // Write each request
            for (Object request : requestList.get(2)) {
                RequestRegister indivCastedRequest = (RequestRegister) request;
                bw.write("\n");
                bw.write(indivCastedRequest.getRequestID() + "\t" + indivCastedRequest.getRequesterID() + "\t"
                        + indivCastedRequest.getRequestStatus() + '\t' + indivCastedRequest.getFypID());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        pathToFile = Paths.get(filename3);
        try (
                BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            // Write the header
            bw.write("requestID\trequesterID\trequestStatus\tfypID\tnewSupervisorId");
            // Write each request
            for (Object request : requestList.get(3)) {
                RequestTransferSupervisor indivCastedRequest = (RequestTransferSupervisor) request;
                bw.write("\n");
                bw.write(indivCastedRequest.getRequestID() + "\t" + indivCastedRequest.getRequesterID() + "\t"
                        + indivCastedRequest.getRequestStatus() + '\t' + indivCastedRequest.getFypID() + '\t'
                        + indivCastedRequest.getNewSupervisorID());
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
        ArrayList<SupervisorAccount> supervisorList = FYPMS1.getSupervisorList();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            bw.write("Name\tEmail\tPassword\tProject 1\tProject 2");
            for (SupervisorAccount supervisor : supervisorList) {
                bw.newLine();
                bw.write(supervisor.getName() + "\t" + supervisor.getEmail() + "\t" + supervisor.getPassword() + "\t"
                        + supervisor.getProj_1() + "\t" + supervisor.getProj_2());
            }
            System.out.println("Supervisors successfully written to " + fileName);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void writeCoordinatorToFile(String fileName) {
        ArrayList<FYPCoordinatorAccount> coordinatorList = FYPMS1.getCoordinatorList();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            bw.write("Name\tEmail\tPassword\tProject 1\tProject 2");

            for (FYPCoordinatorAccount coordinator : coordinatorList) {
                bw.newLine();
                bw.write(coordinator.getName() + "\t" + coordinator.getEmail() + "\t" + coordinator.getPassword() + "\t"
                        + coordinator.getProj_1() + "\t" + coordinator.getProj_2());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void writeStudentToFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<StudentAccount> studentList = FYPMS1.getStudentList();

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            bw.write("Name\tEmail\tPassword\tProjectID\tStatus");

            for (StudentAccount student : studentList) {
                bw.newLine();
                bw.write(student.getName() + "\t" + student.getEmail() + "\t" + student.getPassword() + "\t"
                        + student.getAssignedProject() + "\t" + student.getStatus());
            }
            System.out.println("Students successfully written to " + fileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
