package command.FYPCoord;

import FYPMS.FYPMS1;
import FYPMS.project.*;
import command.Command;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class GenerateFilteredProjectDetailsCommand implements Command {
    private FYPStatus filter;
    private int filterType;

    public GenerateFilteredProjectDetailsCommand(int filterType) {
        this.filterType = filterType;
    }

    public void execute() {
        boolean validInput = false;
        int selection;
        Scanner sc = new Scanner(System.in);
        int fypCount = 1;
        FYPList projects = FYPMS1.getFypList();
        ArrayList<FYP> fyps = projects.getFYPs();
        switch (filterType) {
            // Filter by status
            case 1:
                System.out.println();
                System.out.println("==================================");
                System.out.println("Filter by:");
                System.out.println("(1) Available");
                System.out.println("(2) Reserved");
                System.out.println("(3) Unavailable");
                System.out.println("(4) Allocated");
                System.out.println();
                System.out.println("Enter the number of your choice:");
                System.out.println("==================================");

                while (!validInput) {
                    try {
                        selection = sc.nextInt();
                        if (selection < 5 & selection >= 1) {
                            validInput = true;
                            switch (selection) {
                                case 1:
                                    this.filter = FYPStatus.AVAILABLE;
                                    break;
                                case 2:
                                    this.filter = FYPStatus.RESERVED;
                                    break;
                                case 3:
                                    this.filter = FYPStatus.UNAVAILABLE;
                                    break;
                                case 4:
                                    this.filter = FYPStatus.ALLOCATED;
                                    break;
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.print("Your selection can only be an integer!");
                    }
                }
                System.out.println();
                System.out.println("List of " + this.filter.toString().toLowerCase() + " Final Year Projects");
                System.out.println();
                for (FYP fyp : fyps) {
                    if (fyp.getStatus() == this.filter) {
                        System.out.println("============= FYP No. " + fypCount++ + " ==============");
                        fyp.printFYPDetails();
                        System.out.println();
                    }
                }
                System.out.println("===== There are " + (fypCount - 1) + " Final Year Projects "
                        + this.filter.toString().toLowerCase() + "! =====");

                break;

            // Filter by supervisor
            case 2:
                int i = 1;
                String supervisorName = "";
                System.out.println();
                System.out.println("==================================");
                System.out.println("Enter Supervisor's name:");
                Map<Integer, String> supervisorMapIdx = new TreeMap<>();
                // Map<String, Integer> supervisorMapIdx= new HashMap<>();
                for (FYP fyp : fyps) {
                    if (!supervisorMapIdx.containsValue(fyp.getSupervisorName())) {
                        supervisorMapIdx.put(i, fyp.getSupervisorName());
                        i++;
                    }
                }

                for (Map.Entry<Integer, String> pair : supervisorMapIdx.entrySet()) {
                    System.out.print("(" + pair.getKey() + ") ");
                    System.out.println(pair.getValue());
                }
                System.out.println();
                System.out.println("Enter the number of your choice:");
                System.out.println("==================================");
                while (!validInput) {
                    try {
                        selection = sc.nextInt();
                        if (selection < i & selection >= 1) {
                            validInput = true;
                            for (Map.Entry<Integer, String> pair : supervisorMapIdx.entrySet()) {
                                if (pair.getKey() == selection) {
                                    supervisorName = pair.getValue();
                                }
                            }
                        }
                    } catch (InputMismatchException e) {
                        System.out.print("Your selection can only be an integer!");
                    }
                }
                fypCount = 1;
                System.out.println();
                System.out.println("List of all Final Year Projects");
                System.out.println();
                for (FYP filteredFyp : fyps) {
                    if (filteredFyp.getSupervisorName().equals(supervisorName)) {
                        System.out.println("============= FYP No. " + fypCount++ + " ==============");
                        filteredFyp.printFYPDetails();
                        System.out.println();
                    }
                }
                System.out.println("===== There are " + (fypCount - 1) + " Final Year Projects! =====");
                break;

        }

    }
}
