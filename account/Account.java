package account;

/**
 * Abstract class for Account
 */
public abstract class Account{
	private String email;
	private String name;
	private String phoneNo;
    private String loginId;
    private String password;
    private UserType userType;
    //0 for user, 1 for cineplexAdmin, 2 for companyAdmin

    /**
     * Creates an Account Object with given parameters
     * @param loginId which is the login ID of user
     * @param password which is the password of user
     * @param userType which is the userType of user
     * @param emailAddress which is the email address of user
     * @param phoneNo which is the phone number of user
     * @param name which is the name of user
     */
    public Account(String loginId, String password, UserType userType, String emailAddress, String phoneNo, String name){

        this.loginId = loginId;
        this.password = password;
        this.userType = userType;
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = emailAddress;
    }

    public final String getLoginId(){
        return loginId;
    }


    public final String getPassword(){
        return password;
    }


    public final UserType getUserType(){
        return userType;
    }


    public final String getName() {
    	return name;
    }

    public final String getEmail() {
    	return email;
    }

    public final String getPhoneNo(){
    	return phoneNo;
    }

    /**
     * Converts Int to UserType
     * @param i which is an integer
     * @return UserType corresponding to integer
     */
    public static UserType convertIntToUserType(int i){
        switch(i){
            case 0:
                return UserType.User;
            case 1:
                return UserType.CineplexAdmin;
            default:
                return UserType.CompanyAdmin;
        }
    }

    /**
     * Converts UserType to Int
     * @param userType of Account
     * @return integer corresponding to UserType
     */
    public static int convertUserTypeToInt(UserType userType){
        if (userType == UserType.User) return 0;
        else if (userType == UserType.CineplexAdmin) return 1;
        else return 0;
    }
    public abstract UserType login(String loginId, String password);
}