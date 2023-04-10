package command;

import java.util.Scanner;

import account.Account;

public class ChangePassword implements Command {
    private Account user;

    public ChangePassword(Account user) {
        this.user = user;
    }

    public void execute() {
        int triesLeft = 5;
        Scanner sc = new Scanner(System.in);
        while(triesLeft != 0){
            System.out.println("Enter current password:");
            String curPass = sc.next();
            if(curPass.equals(user.getPassword())){
                System.out.println("Enter new password:");
                String newPass = sc.next();
                System.out.println("Confirm new password:");
                String finalPass = sc.next();
                if(finalPass.equals(newPass)){
                    user.setPassword(newPass);
                    break;
                }
                else{
                    System.out.println("Password does not match!");
                    triesLeft--;
                }
            }
            else{
                System.out.println("Wrong password");
                triesLeft--;
            }
        }
        if(triesLeft == 0){
            System.out.println("Exceeded number of tries possible");
            System.out.println("Exiting Application ...");
        }

    }
}
