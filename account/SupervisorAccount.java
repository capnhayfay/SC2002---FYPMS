package account;

/**
 * Account for Cineplex Admin
 */
public class SupervisorAccount extends Account{
    /**
     * Creates a CineplexAdminAccount with the given parameters
     * @param loginId which is the login ID of CineplexAdminAccount
     * @param password which is the password of CineplexAdminAccount
     * @param privilege which is the Privilege of CineplexAdminAccount
     * @param cineplex which is the Cineplex that the account is tagged to
     * @param emailAddress which is the email address of CineplexAdminAccount
     * @param phoneNo which is the phone number of CineplexAdminAccount
     * @param name which is the name of CineplexAdminAccount
     */
    public SupervisorAccount(String userId, String password, UserType userType, String emailAddress, String name){
        super(userId, password,userType,emailAddress,name);
    }

    /**
     * Authenticates login of CineplexAdmin. Extra layer of security (2FA) is included.
     * @param loginId which is entered by user
     * @param password which is entered by user
     * @return Account if login successful, null if login failed
     */
    public UserType login(String loginId, String password){
        if(this.getLoginId().equals(loginId) && this.getPassword().equals(password)){
            return this.getUserType();
        }
        return null;
    }

}