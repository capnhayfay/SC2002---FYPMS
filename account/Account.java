package account;

/**
 * Abstract class for Account
 */
public abstract class Account{
	private String email;
	private String name;
    private String userId;
    private String password;
    private UserType userType;
    //0 for user, 1 for cineplexAdmin, 2 for companyAdmin

    /**
     * Creates an Account Object with given parameters
     * @param userId which is the login ID of user
     * @param password which is the password of user
     * @param userType which is the userType of user
     * @param emailAddress which is the email address of user
     * @param name which is the name of user
     */
    public Account(String userId, String password, UserType userType, String emailAddress, String name){

        this.userId = userId;
        this.password = password;
        this.userType = userType;
        this.name = name;
        this.email = emailAddress;
    }

    public final String getLoginId(){
        return userId;
    }


    public final String getPassword(){
        return password;
    }

    public final void setPassword(String newPass){
        this.password = newPass;
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

    /**
     * Converts Int to UserType
     * @param i which is an integer
     * @return UserType corresponding to integer
     */
    public static UserType convertIntToUserType(int i){
        switch(i){
            case 0:
                return UserType.Student;
            case 1:
                return UserType.Supervisor;
            default:
                return UserType.FYPCoordinator;
        }
    }

    /**
     * Converts UserType to Int
     * @param userType of Account
     * @return integer corresponding to UserType
     */
    public static int convertUserTypeToInt(UserType userType){
        if (userType == UserType.Student) return 0;
        else if (userType == UserType.Supervisor) return 1;
        else return 0;
    }
    public abstract UserType login(String userId, String password);
}