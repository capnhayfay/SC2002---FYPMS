package account;

public class StudentAccount extends Account{
    /**
     * Creates CustomerAccount with given parameters
     * @param userId which is the login ID of Customer
     * @param password which is the password of Customer
     * @param userType which is the UserType of Customer
     * @param emailAddress which is the email address of Customer
     * @param name which is the name of Customer
     */


    public StudentAccount(String userId, String password, UserType userType, String emailAddress, String name){
        super(userId, password,userType,emailAddress,name);
    }
    /**
     * Authenticates login of CustomerAccount
     * @param userId which is entered by user
     * @param password which is entered by user
     * @return Account if login successful, null if login failed
     */
    public UserType login(String userId, String password){
        if(this.getLoginId().equals(userId) && this.getPassword().equals(password)){
            return this.getUserType();
        }
        return null;
    }
}