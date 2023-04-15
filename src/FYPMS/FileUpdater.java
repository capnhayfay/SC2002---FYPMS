package src.FYPMS;

import src.FYPMS.project.FYP;
import src.FYPMS.project.FYPList;
import src.FYPMS.request.*;
import src.account.AccountManager;
import src.account.student.StudentAccount;
import src.account.supervisor.FYPCoordinatorAccount;
import src.account.supervisor.SupervisorAccount;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class for updating text files used for our data storage
 */
public class FileUpdater {

    /**
     * Default constructor for the FileUpdate Class
     */
    public FileUpdater() {

    }

    /**
     * Writes the FYP list to file
     *
     * @param fileName the name of the file to write to
     */
    public static void writeFYPsToFile(String fileName) {
        ArrayList<FYP> fypList = FYPList.getFypList();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {

            // Write the header
            bw.write("ProjectID\tSupervisorName\tSupervisorEmail\tTitle\tStatus\tStudentID\tStudentName\tStudentEmail");

            // Write each FYP
            for (FYP fyp : fypList) {
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
     * @param fileName  the name of the file to write RequestChangeTitle to
     * @param filename1 the name of the file to write RequestDeregister to
     * @param filename2 the name of the file to write RequestRegister to
     * @param filename3 the name of the file to write RequestTransferSupervisor to
     */
    public static void writeRequestsToFile(String fileName, String filename1, String filename2, String filename3) {
        ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            // Write the header
            bw.write("requestID\trequesterID\trequesteeID\trequestStatus\tfypID\tnewTitle");
            // Write each request
            for (Request request : requestHistory.get(0)) {
                RequestChangeTitle castedRequest = (RequestChangeTitle) request;
                bw.write("\n");
                bw.write(castedRequest.getRequestID() + "\t" + castedRequest.getRequesterID() + "\t"
                        + castedRequest.getRequesteeID() + '\t' + castedRequest.getRequestStatus() + '\t'
                        + castedRequest.getFypID() + '\t' + castedRequest.getNewTitle());
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
            for (Request request : requestHistory.get(1)) {
                RequestDeregister castedRequest = (RequestDeregister) request;
                bw.write("\n");
                bw.write(castedRequest.getRequestID() + "\t" + castedRequest.getRequesterID() + "\t"
                        + castedRequest.getRequestStatus() + '\t' + castedRequest.getFypID());
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
            for (Request request : requestHistory.get(2)) {
                RequestRegister castedRequest = (RequestRegister) request;
                bw.write("\n");
                bw.write(castedRequest.getRequestID() + "\t" + castedRequest.getRequesterID() + "\t"
                        + castedRequest.getRequestStatus() + '\t' + castedRequest.getFypID());
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
            for (Request request : requestHistory.get(3)) {
                RequestTransferSupervisor castedRequest = (RequestTransferSupervisor) request;
                bw.write("\n");
                bw.write(castedRequest.getRequestID() + "\t" + castedRequest.getRequesterID() + "\t"
                        + castedRequest.getRequestStatus() + '\t' + castedRequest.getFypID() + '\t'
                        + castedRequest.getNewSupervisorID());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Writes the supervisor list to file
     *
     * @param fileName the name of the file to write to
     */
    public static void writeSupervisorToFile(String fileName) {
        ArrayList<SupervisorAccount> supervisorList = AccountManager.getSupervisorList();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            bw.write("Name\tEmail\tPassword\tProject 1\tProject 2");
            for (SupervisorAccount supervisor : supervisorList) {
                bw.newLine();
                bw.write(supervisor.getName() + "\t" + supervisor.getEmail() + "\t" + supervisor.getPassword());
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    /**
     * Writes the FYP coordinator list to file
     *
     * @param fileName the name of the file to write to
     */
    public static void writeCoordinatorToFile(String fileName) {
        ArrayList<FYPCoordinatorAccount> coordinatorList = AccountManager.getCoordinatorList();
        Path pathToFile = Paths.get(fileName);

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            bw.write("Name\tEmail\tPassword\tProject 1\tProject 2");

            for (FYPCoordinatorAccount coordinator : coordinatorList) {
                bw.newLine();
                bw.write(coordinator.getName() + "\t" + coordinator.getEmail() + "\t" + coordinator.getPassword());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Writes the Student list to file
     *
     * @param fileName the name of the file to write to
     */
    public static void writeStudentToFile(String fileName) {
        Path pathToFile = Paths.get(fileName);

        ArrayList<StudentAccount> studentList = AccountManager.getStudentList();

        try (BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            bw.write("Name\tEmail\tPassword\tProjectID\tStatus");

            for (StudentAccount student : studentList) {
                bw.newLine();
                bw.write(student.getName() + "\t" + student.getEmail() + "\t" + student.getPassword() + "\t"
                        + student.getAssignedProject() + "\t" + student.getStatus());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
