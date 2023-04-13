// import FYPMS1.*;
// import account.*;
// import gui.*;
// import gui.GreetUserMenu;
// import FYPMS1.CSVReader;
// // import moblima.CSVUpdater;
// import FYPMS1.FYPMS1;
// import FYPMS1.student.ticket.MovieTicket;
// // import system.SystemSettings;
// import java.io.IOException;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.Scanner;
//
//
// public class Application {
//
//
// 	public static void main(String[] args) throws IOException {
// 		new Application().run();
// 	}
//
// 	public void run() throws IOException {
//
// 		SystemSettings ss = new SystemSettings();
// 		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
// 		LocalDateTime dateTime = LocalDateTime.parse("2022-12-25 00:00", formatter);
// 		SystemSettings.addPublicHoliday(dateTime,"Christmas");
// 		dateTime = LocalDateTime.parse("2022-10-31 00:00", formatter);
// 		SystemSettings.addPublicHoliday(dateTime, "Halloween");
//
//
// 		FileReader.readMoviesFromCSV("src/database/movieDB.csv");
// 		FileReader.readCineplexFromCSV("src/database/CineplexDB.csv");
// 		FileReader.readReviewFromCsv("src/database/reviewListDB.csv");
// 		FileReader.readCinemasFromCSV("src/database/CinemaDB.csv");
// 		FileReader.readAccountsFromCSV("src/database/accountDB.csv", FYPMS1.getCineplexList());
// 		FileReader.readShowsFromCSV("src/database/showDB.csv");
// 		ArrayList<MovieTicket> movieTicketArrayList =  FileReader.readTicketsFromCSV("src/database/MovieTicketDB.csv");
// 		FileReader.readBookingsFromCSV("src/database/bookingDB.csv", movieTicketArrayList);
// 		// load in CSV
//
// 		// end of load in CSV
//
// 		// Auto update expired movie status
// 		FYPMS1.getMovieList().updateExpiredMovieStatus();
// 		new GreetUserMenu().display();
// 		Scanner scanner = new Scanner(System.in);
// 		int userCh = 0;
// 		Privilege privilege;
// 		Cineplex cineplex = null;
// 		Account curAcc = null;
//
// 		System.out.println();
// 		FYPMS1.getCineplexList().listCineplexes();
//
// 		while (true) {
//
// 			System.out.println();
// 			System.out.print("Please enter the cineplex location number: ");
//
// 			if (!scanner.hasNextInt()) {
// 				System.out.println("Invalid input format for location number. Please try again.");
// 				scanner.nextLine();
// 				continue;
// 			}
// 			int locationCh = scanner.nextInt();
// 			scanner.nextLine();
// 			cineplex = FYPMS1.getCineplexList().getCineplexByIndex(locationCh - 1);
// 			if (cineplex != null) break;
// 			System.out.println("Option number out of range. Please try again.");
// 		}
//
// 		System.out.println();
// 		System.out.println("The location you have chosen is: " + cineplex.getBranchName());
//
// 		while (true) {
// 			if (curAcc == null) {
// 				GuestGUI guestGUI = new GuestGUI(cineplex, curAcc);
// 				guestGUI.display();
// 				if (guestGUI.execute() == 0) {
// 					break;
// 				}
// 				curAcc = guestGUI.getAccount();
// 				cineplex = guestGUI.getCineplex();
// 			} else if (curAcc.getPrivilege() == Privilege.User) {
// 				CustomerGUI customerGUI = new CustomerGUI(cineplex, curAcc);
// 				customerGUI.display();
// 				if (customerGUI.execute() == 0) {
// 					break;
// 				}
// 				curAcc = customerGUI.getAccount();
// 				cineplex = customerGUI.getCineplex();
// 			} else if (curAcc.getPrivilege() == Privilege.CineplexAdmin) {
// 				CineplexAdminAccount cineplexAdmin = (CineplexAdminAccount) curAcc;
// 				CineplexAdminGUI cineplexAdminGUI = new CineplexAdminGUI(cineplexAdmin);
// 				cineplexAdminGUI.display();
// 				if(cineplexAdminGUI.execute()==0){
// 					break;
// 				}
// 				curAcc = cineplexAdminGUI.getAccount();
//
// 			} else if (curAcc.getPrivilege() == Privilege.CompanyAdmin) {
// 				CompanyAdminGUI companyAdminGUI = new CompanyAdminGUI(curAcc);
// 				companyAdminGUI.display();
// 				if(companyAdminGUI.execute()==0){
// 					break;
// 				}
// 				curAcc = companyAdminGUI.getAccount();
// 			}
// 		}
// 		new EndProgramMenu().display();
//
// 		CSVUpdater.updateMovies("src/database/movieDB.csv");
// 		CSVUpdater.updateCineplex("src/database/CineplexDB.csv");
// 		CSVUpdater.updateReviewList("src/database/reviewListDB.csv");
// 		CSVUpdater.updateCinema("src/database/CinemaDB.csv");
// 		CSVUpdater.updateShows("src/database/showDB.csv");
// 		CSVUpdater.updateTickets("src/database/MovieTicketDB.csv");
// 		CSVUpdater.updateBooking("src/database/bookingDB.csv");
//
// 	}
//
// }

import FYPMS.*;

;

public class ApplicationTest {
	public static void main(String[] args) {
		// FileReader.readSupervisorFromFile("./database2/faculty_list.txt");
		// SupervisorList sups = FYPMS1.getSupervisorList();
		// sups.listAllSupervisors();
		FileReader.readFYPsFromFile("./database2/rollover project.csv");

		// // To set all project status as available first
		// FYPList projects = FYPMS1.getFypList();
		// ArrayList<FYP> fyps = projects.getFYPs();
		// for (FYP proj : fyps) {
		// proj.setStatus(FYPStatus.AVAILABLE);
		// // System.out.println(proj.getSupervisorName());
		// // proj.printFYPDetails();
		// }

		// projects.listAvailableFYPsForStudents();
		// System.out.println(projects.getFYPs());
		// projects.listAllFYPsForFaculty();

		// GenerateFilteredProjectDetailsCommand ------------
		// GenerateFilteredProjectDetailsCommand test = new
		// GenerateFilteredProjectDetailsCommand(1,projects); // filter by status
		// GenerateFilteredProjectDetailsCommand test = new
		// GenerateFilteredProjectDetailsCommand(2,projects); // filter by supervisor
		// test.execute();

		// ViewAllFYPCommand
		// ViewAllFYPCommand test = new ViewAllFYPCommand();
		// test.execute();

		// ViewAllPendingRequestsCommand
		// RequestCoordDeregisterCommand test2 = new
		// RequestCoordDeregisterCommand("zoey");
		// test2.execute(); // Sample request
		// ViewAllPendingRequestsCommand view = new ViewAllPendingRequestsCommand();
		// view.execute();

		// ViewAllRequestHistoryCommand
		// RequestCoordDeregisterCommand test2 = new
		// RequestCoordDeregisterCommand("zoey");
		// test2.execute(); // Sample request
		// ViewAllRequestHistoryCommand view = new ViewAllRequestHistoryCommand();
		// view.execute();

		// Change Password
		// StudentAccount user = new StudentAccount("zoey", "12345", UserType.StudentAccount,
		// "test@gmail.com", "Zoey Lam");
		// System.out.println(user.getPassword());
		// ChangePassword change = new ChangePassword(user);
		// change.execute();
		// System.out.println(user.getPassword());

		// Create new project
		// CreateProjectCommand test = new CreateProjectCommand("Bo An");
		// test.execute();
		// for (FYP proj : fyps) {
		// proj.printFYPDetails();
		// }

		// // Deregistration request
		// RequestList Requests = FYPMS1.getRequestList();
		// List<Request> requests = Requests.getRequests();
		// RequestCoordDeregisterCommand test1 = new
		// RequestCoordDeregisterCommand("jovin");
		// test1.execute();
		// RequestCoordDeregisterCommand test2 = new
		// RequestCoordDeregisterCommand("zoey");
		// test2.execute();
		// for (Request request : requests) {
		// request.printDetails();
		// }

		// View Submitted FYPs
		// ViewSubmittedFYPCommand test = new ViewSubmittedFYPCommand("Bo An",
		// projects);
		// test.execute();

		// View Own Requests
		// RequestList Requests = FYPMS1.getRequestList();
		// RequestCoordDeregisterCommand test1 = new
		// RequestCoordDeregisterCommand("jovin");
		// test1.execute();
		// RequestCoordDeregisterCommand test2 = new
		// RequestCoordDeregisterCommand("zoey");
		// test2.execute();
		// RequestCoordDeregisterCommand test3 = new
		// RequestCoordDeregisterCommand("jovin");
		// test3.execute();
		// ViewSelfRequestRecordsCommand test = new
		// ViewSelfRequestRecordsCommand("jovin", Requests);
		// test.execute();

	}
}