import account.*;
import gui.*;
import gui.GreetUserMenu;
import moblima.CSVReader;
// import moblima.CSVUpdater;
import moblima.SilverVillage;
import moblima.booking.ticket.MovieTicket;
import moblima.cineplex.Cineplex;
import system.SystemSettings;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Application {


	public static void main(String[] args) throws IOException {
		new Application().run();
	}

	public void run() throws IOException {

		SystemSettings ss = new SystemSettings();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse("2022-12-25 00:00", formatter);
		SystemSettings.addPublicHoliday(dateTime,"Christmas");
		dateTime = LocalDateTime.parse("2022-10-31 00:00", formatter);
		SystemSettings.addPublicHoliday(dateTime, "Halloween");


		CSVReader.readMoviesFromCSV("src/database/movieDB.csv");
		CSVReader.readCineplexFromCSV("src/database/CineplexDB.csv");
		CSVReader.readReviewFromCsv("src/database/reviewListDB.csv");
		CSVReader.readCinemasFromCSV("src/database/CinemaDB.csv");
		CSVReader.readAccountsFromCSV("src/database/accountDB.csv", SilverVillage.getCineplexList());
		CSVReader.readShowsFromCSV("src/database/showDB.csv");
		ArrayList<MovieTicket> movieTicketArrayList =  CSVReader.readTicketsFromCSV("src/database/MovieTicketDB.csv");
		CSVReader.readBookingsFromCSV("src/database/bookingDB.csv", movieTicketArrayList);
		// load in CSV
		
		// end of load in CSV

		// Auto update expired movie status
		SilverVillage.getMovieList().updateExpiredMovieStatus();
		new GreetUserMenu().display();
		Scanner scanner = new Scanner(System.in);
		int userCh = 0;
		Privilege privilege;
		Cineplex cineplex = null;
		Account curAcc = null;

		System.out.println();
		SilverVillage.getCineplexList().listCineplexes();

		while (true) {

			System.out.println();
			System.out.print("Please enter the cineplex location number: ");

			if (scanner.hasNextInt() == false) {
				System.out.println("Invalid input format for location number. Please try again.");
				scanner.nextLine();
				continue;
			}
			int locationCh = scanner.nextInt();
			scanner.nextLine();
			cineplex = SilverVillage.getCineplexList().getCineplexByIndex(locationCh - 1);
			if (cineplex != null) break;
			System.out.println("Option number out of range. Please try again.");
		}

		System.out.println();
		System.out.println("The location you have chosen is: " + cineplex.getBranchName());

		while (true) {
			if (curAcc == null) {
				GuestGUI guestGUI = new GuestGUI(cineplex, curAcc);
				guestGUI.display();
				if (guestGUI.execute() == 0) {
					break;
				}
				curAcc = guestGUI.getAccount();
				cineplex = guestGUI.getCineplex();
			} else if (curAcc.getPrivilege() == Privilege.User) {
				CustomerGUI customerGUI = new CustomerGUI(cineplex, curAcc);
				customerGUI.display();
				if (customerGUI.execute() == 0) {
					break;
				}
				curAcc = customerGUI.getAccount();
				cineplex = customerGUI.getCineplex();
			} else if (curAcc.getPrivilege() == Privilege.CineplexAdmin) {
				CineplexAdminAccount cineplexAdmin = (CineplexAdminAccount) curAcc;
				CineplexAdminGUI cineplexAdminGUI = new CineplexAdminGUI(cineplexAdmin);
				cineplexAdminGUI.display();
				if(cineplexAdminGUI.execute()==0){
					break;
				}
				curAcc = cineplexAdminGUI.getAccount();

			} else if (curAcc.getPrivilege() == Privilege.CompanyAdmin) {
				CompanyAdminGUI companyAdminGUI = new CompanyAdminGUI(curAcc);
				companyAdminGUI.display();
				if(companyAdminGUI.execute()==0){
					break;
				}
				curAcc = companyAdminGUI.getAccount();
			}
		}
		new EndProgramMenu().display();

		CSVUpdater.updateMovies("src/database/movieDB.csv");
		CSVUpdater.updateCineplex("src/database/CineplexDB.csv");
		CSVUpdater.updateReviewList("src/database/reviewListDB.csv");
		CSVUpdater.updateCinema("src/database/CinemaDB.csv");
		CSVUpdater.updateShows("src/database/showDB.csv");
		CSVUpdater.updateTickets("src/database/MovieTicketDB.csv");
		CSVUpdater.updateBooking("src/database/bookingDB.csv");

	}

}