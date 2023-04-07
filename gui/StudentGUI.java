package gui;
import account.*;
import command.customer.*;
import system.SystemSettings;

import java.util.Scanner;

/**
 * GUI which is shown to the Customer
 */
public class StudentGUI implements Menu, Logout, GetCommand{
    private Account curAcc;

    /**
     * Creates a CustomerGui with the given Customer Account and Cineplex
     * @param curAcc which is the Account of the customer
     */
    public CustomerGUI(Account curAcc){
        this.curAcc = curAcc;
    }
    /**
     * Prints list of possible actions that can be performed by Customer
     */
    public void display(){
    	System.out.println();
        //System.out.println("-----------------------------------------");
        System.out.println("=========================================");
        System.out.println("               Student Menu             ");
        System.out.println("=========================================");
        //System.out.println("-----------------------------------------");
        System.out.println();
        System.out.println("Logged in as User: " + curAcc.getLoginId());
        System.out.println();
        System.out.println("1. Search Movie");
        System.out.println("2. List Movies");
        System.out.println("3. List Shows");
        System.out.println("4. View Seat Availability");
        System.out.println("5. Book Tickets");
        System.out.println("6. View Booking History");
        System.out.println("7. Review Movie");
        System.out.println("8. Top 5 Ranking");
        System.out.println("9. Change Location");
        System.out.println("10. Logout");
        System.out.println("11. Exit");
        System.out.println("=========================================");
        System.out.println();
    }
    /**
     * Gets input from Customer and executes the required instruction
     * @return 0 to exit the program entirely, 1 to continue program
     */
    public int execute(){
        Scanner scanner = new Scanner(System.in);
        //System.out.print("Please enter the option number: ");
      //Error handling for invalid input 
        
        System.out.print("Please enter the option number: ");
        while(true) {
	        if(scanner.hasNextInt() == false) {
				
				System.out.println("Invalid input format for option number. Please try again.");
				System.out.println();
				System.out.print("Please enter option number again: ");
				scanner.nextLine();
				continue;
			}
       
	        int userCh = scanner.nextInt();
	        scanner.nextLine();
	        System.out.println();
	        System.out.println("=========================================");
	        
	        if (userCh == 11) {
	            return 0;
	        }

        switch (userCh) {
            case 1:
                new UserSearchMovieCommand().execute();
                break;
            case 2:
                new UserListMoviesCommand().execute();
                break;
            case 3:
            	System.out.println();
        		System.out.println("For cineplex location: " + cineplex.getBranchName());
                new ListShowsCommand(cineplex).execute();
                break;
            case 4:
                new ShowSeatAvailabilityCommand(cineplex).execute();
                break;
            case 5:
                new BookTicketCommand(cineplex, curAcc).execute();
                break;
            case 6:
                new ViewBookingHistoryCommand(curAcc.getName()).execute();
                break;

            case 7:
                new ReviewMovieCommand().execute();
                break;
            case 8:
                if(SystemSettings.getTop5MovieTicketsBool() && SystemSettings.getTop5MovieRatingsBool()){
                    showTop5OptionsMenu();
                    while(true) {
	                    System.out.print("Please enter the option number: ");
	                    if(scanner.hasNextInt() == false) {
	        				
	        				System.out.println("Invalid input format for option number. Please try again.");
	        				scanner.nextLine();
	        				System.out.println();
	        				continue;
	        			}
	                    break;
                    }
                    userCh = scanner.nextInt();
                    scanner.nextLine();
                    if (userCh != 1  && userCh!=2){
                    	System.out.println();
                    	System.out.println("Option number out of range. Please try again.");
                        break;
                    }
                    if (userCh == 1){
                        new RankTicketSalesCommand().execute();
                    }
                    else{
                        new RankReviewRatingsCommand().execute();
                    }
                }
                else if (SystemSettings.getTop5MovieRatingsBool()){
                    new RankReviewRatingsCommand().execute();
                }
                else if (SystemSettings.getTop5MovieTicketsBool()){
                    new RankTicketSalesCommand().execute();
                }
                else{
                    System.out.println("Data unavailable.");
                }
                break;

            case 9:
                ChangeLocationCommand CLC = new ChangeLocationCommand(cineplex);
                CLC.execute();
                cineplex = CLC.getCineplex();
                break;
            case 10:
                logout();
                System.out.println();
                System.out.println("Logged out successfully.");
                break;

            default:
                System.out.println();
                System.out.println("Option number out of range. Please try again.");
                break;
        }
        return 1;
       }
    }

    /**
     * Prints possible options when SystemSettings enable showing of the top 5 movies ranked based on ticket sales or reviews
     */
    public void showTop5OptionsMenu(){
        System.out.println();
        System.out.println("============== Option Menu ==============");
        System.out.println("1. Show Top 5 Movies by Ticket Sales");
        System.out.println("2. Show Top 5 Movies by Reviews");
        System.out.println("=========================================");
        System.out.println();
    }
    /**
     * Logout from account by setting Customer Account to null
     */
    public void logout(){
        this.curAcc = null;
    }

    /**
     * Returns Account in CustomerGUI
     * @return Account
     */
    public Account getAccount(){
        return this.curAcc;
    }
    /**
     * Returns Cineplex in CustomerGUI
     * @return Cineplex
     */
    public Cineplex getCineplex(){
        return this.cineplex;
    }

}
