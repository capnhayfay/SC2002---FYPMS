package account.supervisor;

import java.util.ArrayList;

import account.Account;
import account.UserType;

public class SupervisorAccount extends Account {
    private ArrayList<String> proj;

    public SupervisorAccount(String userId, String password, UserType userType, String emailAddress, String name,
            ArrayList<String> proj) {
        super(userId, password, userType, emailAddress, name);
        this.proj = proj;
    }

    public void addProj(String newProj) {
        proj.add(newProj);
    }

    public ArrayList<String> getProjList() {
        return this.proj;
    }

    public UserType login(String loginId, String password) {
        if (this.getLoginId().equals(loginId) && this.getPassword().equals(password)) {
            return this.getUserType();
        }
        return null;
    }

    public void printDetails() {
        System.out.println("Supervisor name: " + this.getName());
        System.out.println("Email: " + this.getEmail());
        if (proj.size() == 0) {
            System.out.println("Supervisor has no projects!");
        } else {
            int counter = 1;
            for (String project : proj) {
                System.out.println("Project " + counter + ": " + project);
                counter++;
                System.out.println();
            }

        }
        System.out.println();
    }

}