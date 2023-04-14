package command.Student;

import FYPMS.FYPMS1;
import FYPMS.project.FYPList;
import account.student.StudentAccount;
import account.student.StudentStatus;
import command.Command;

public class ViewAllAvailableFYPCommand implements Command {
    private StudentAccount studentAccount;

    public ViewAllAvailableFYPCommand(StudentAccount currentAcc) {
        this.studentAccount = currentAcc;
    }

    public void execute() { // View available FYP from csv
        if (studentAccount.getStatus() == StudentStatus.ASSIGNED_PROJECT) {
            System.out.println("Error: You are already registered for an FYP.");
            return;
        } else if (studentAccount.getStatus() == StudentStatus.DEREGISTERED_PROJECT) {
            System.out.println("Error: You have deregistered for an FYP.");
            return;
        } else if (studentAccount.getStatus() == StudentStatus.REQUESTED_PROJECT) {
            System.out.println("Error: You have a pending registration.");
            return;
        }
        FYPList fypList = FYPMS1.getFypList();
        fypList.listAvailableFYPsForStudents();
    }
}
