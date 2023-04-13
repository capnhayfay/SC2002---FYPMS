package account.supervisor;


import account.Account;
import account.UserType;

public class SupervisorAccount extends Account {
    private String proj1;
    private String proj2;

    public SupervisorAccount(String userId, String password, UserType userType, String emailAddress, String name){
        super(userId, password,userType,emailAddress,name);
        this.proj1="0";
        this.proj2="0";
    }
    public void setProj_1(String proj1) {
        this.proj1 = proj1;
    }

    public String getProj_1() {
        return this.proj1;
    }

    public void setProj_2(String proj2) {
        this.proj2 = proj2;
    }

    public String getProj_2() {
        return this.proj2;
    }

    public UserType login(String loginId, String password){
        if(this.getLoginId().equals(loginId) && this.getPassword().equals(password)){
            return this.getUserType();
        }
        return null;
    }

    public void printDetails() {
        System.out.println("Coordinator name: " + this.getName());
        System.out.println("Email: " + this.getEmail());
        if (this.proj1.equals("0")) {
            System.out.println("Coordinator has no projects!");
        } else {
            System.out.println("Project 1: " + this.proj1);
            if (!this.proj2.equals("0")) {
                System.out.println("Project 2: " + this.proj2);
            }
            System.out.println();
        }
        System.out.println();
    }

}