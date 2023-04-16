/**
 * Represents a src.command to view pending student requests. Implements the Command interface.
 * Allows a supervisor to view all pending requests or view pending requests by student ID.
 */
package src.command.Supervisor;

import src.FYPMS.request.Request;
import src.FYPMS.request.RequestHistory;
import src.FYPMS.request.RequestStatus;
import src.command.Command;
import src.exceptions.fypmsExceptions;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Command for supervisor to view pending requests or view pending requests by student ID
 */
public class ViewPendingStudentRequestsCommand implements Command {
    /**
     * The supervisor's ID.
     */
    private final String supervisorID;

    /**
     * The number of pending requests.
     */
    private int requestCount = 0;

    /**
     * Constructor for ViewPendingStudentRequestsCommand class.
     *
     * @param supervisorID The supervisor's ID.
     */
    public ViewPendingStudentRequestsCommand(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    /**
     * Executes the ViewPendingStudentRequestsCommand.
     * Allows a supervisor to view all pending requests or view pending requests by
     * student ID.
     */
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();

        int choice = fypmsExceptions.validateRequestActionFunction("Pending Request",
                "1. View All Pending Requests", "2. View Pending Requests by Student ID");

        ArrayList<ArrayList<Request>> requestHistory = RequestHistory.getRequestHistory();

        switch (choice) {
            case 1 -> {
                for (ArrayList<Request> requestList : requestHistory) {
                    for (Request request : requestList) {
                        if (request.getRequesteeID().equals(supervisorID)
                                && request.getRequestStatus() == RequestStatus.PENDING) {
                            System.out.println(
                                    "============= Request ID " + request.getRequestID()
                                            + " ==============");
                            request.printDetails();
                            System.out.println();
                            requestCount++;
                        }
                    }

                }
            }
            case 2 -> {
                System.out.println("Enter Student ID: ");
                String studentID = scanner.next();
                for (ArrayList<Request> requestList : requestHistory) {
                    for (Request request : requestList) {
                        if (request.getRequesteeID().equals(supervisorID)
                                && request.getRequestStatus() == RequestStatus.PENDING &&
                                request.getRequesterID().equalsIgnoreCase(studentID)) {
                            System.out.println(
                                    "============= Request ID " + request.getRequestID()
                                            + " ==============");
                            request.printDetails();
                            System.out.println();
                            requestCount++;
                        }
                    }

                }
            }
        }

        if (requestCount == 0) {
            System.out.println("There are no pending requests.");
        } else {
            System.out.println("There are " + requestCount + " pending requests.");
        }

    }

    /**
     * Returns the number of pending requests.
     *
     * @return int The number of pending requests.
     */
    public int getRequestNumber() {
        return requestCount;
    }
}