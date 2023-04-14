package command.Supervisor;

import java.util.ArrayList;
import java.util.Scanner;

import FYPMS.FYPMS1;
import command.Command;

import FYPMS.request.*;

public class ViewPendingStudentRequestsCommand implements Command {
    private String Supervisor;
    private int requestCount = 0;

    public ViewPendingStudentRequestsCommand(String Supervisor) {
        this.Supervisor = Supervisor;
    }

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

        ArrayList<ArrayList<Object>> requests = FYPMS1.getRequestList();

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

    public int getRequestNumber() {
        return requestCount;
    }
}
