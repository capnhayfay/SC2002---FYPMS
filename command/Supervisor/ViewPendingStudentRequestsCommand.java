/**
 * Represents a command to view pending student requests. Implements the Command interface.
 * Allows a supervisor to view all pending requests or view pending requests by student ID.
*/
package command.Supervisor;

import java.util.ArrayList;
import java.util.Scanner;

import command.Command;
import FYPMS.request.*;

public class ViewPendingStudentRequestsCommand implements Command {
    /**
     * The supervisor's ID.
     */
    private final String Supervisor;

    /**
     * The number of pending requests.
     */
    private int requestCount = 0;

    /**
     * Constructor for ViewPendingStudentRequestsCommand class.
     * 
     * @param Supervisor The supervisor's ID.
     */
    public ViewPendingStudentRequestsCommand(String Supervisor) {
        this.Supervisor = Supervisor;
    }

    /**
     * Executes the ViewPendingStudentRequestsCommand.
     * Allows a supervisor to view all pending requests or view pending requests by
     * student ID.
     */
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println("Pending Request");
        System.out.println();
        System.out.println("1. View All Pending Requests");
        System.out.println("2. View Pending Requests by Student ID");

        int choice = -1;
        System.out.println("Enter your choice: ");
        do {
            if (choice != -1) {
                System.out.println("Wrong choice, please reselect: ");
            }
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2);

        ArrayList<ArrayList<Object>> requests = RequestHistory.getRequestList();

        switch (choice) {
            case 1:
                for (ArrayList<Object> request : requests) {
                    for (Object indivRequest : request) {
                        Request indivCastedRequest = (Request) indivRequest;
                        if (indivCastedRequest.getRequesteeID().equals(Supervisor)
                                && indivCastedRequest.getRequestStatus() == RequestStatus.PENDING) {
                            System.out.println(
                                    "============= Request ID " + indivCastedRequest.getRequestID()
                                            + " ==============");
                            indivCastedRequest.printDetails();
                            System.out.println();
                            requestCount++;
                        }
                    }

                }
                break;

            case 2:
                System.out.println("Enter Student ID: ");
                String studentID = scanner.next();

                for (ArrayList<Object> request : requests) {
                    for (Object indivRequest : request) {
                        Request indivCastedRequest = (Request) indivRequest;
                        if (indivCastedRequest.getRequesteeID().equals(Supervisor)
                                && indivCastedRequest.getRequestStatus() == RequestStatus.PENDING &&
                                indivCastedRequest.getRequesterID().equalsIgnoreCase(studentID)) {
                            System.out.println(
                                    "============= Request ID " + indivCastedRequest.getRequestID()
                                            + " ==============");
                            indivCastedRequest.printDetails();
                            System.out.println();
                            requestCount++;
                        }
                    }

                }

                break;
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