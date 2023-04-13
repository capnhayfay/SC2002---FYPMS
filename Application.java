import account.supervisor.SupervisorAccount;
import account.coordinator.FYPCoordinatorAccount;
import account.student.StudentAccount;
import gui.*;
import FYPMS.*;

import java.io.IOException;
import java.util.Scanner;

import FYPMS.FileReader;

public class Application {
	public static void main(String[] args) throws IOException {
		new Application().run();
	}

	public void run() throws IOException {

		FYPMS1 fypms = new FYPMS1();

		// load in CSV

		// FileReader.readSupervisorsFromFile("./database/Modified/faculty_list.txt");
		// FileReader.readCoordinatorsFromFile("./database/Modified/FYP coordinator.txt");
		FileReader.readStudentsFromFile("./database/Modified/student list.txt");
		// FileReader.readFYPsFromFile("./database/Modified/rollover project.txt");
		// FileReader.readRequestsFromFile("src/database/Modified/faculty_list.txt");
		// end of load in CSV
		Scanner scanner = new Scanner(System.in);
		String loggedInUserType = "";
		StudentAccount studentAccount = null;
		SupervisorAccount supervisorAccount = null;
		FYPCoordinatorAccount fypCoordinatorAccount = null;

		new GreetUserMenu().display();
		System.out.println();
		while (true) {
			if (loggedInUserType == "") {
				System.out.print("Which type of user are you? ");
				System.out.println();
				System.out.println("1. StudentAccount");
				System.out.println("2. Supervisor");
				System.out.println("3. FYP Coordinator");
				int choice = scanner.nextInt();
				switch (choice) {
					case 1:
						LoginUserMenu loginStudentUserMenu = new LoginUserMenu(studentAccount);
						loginStudentUserMenu.display();
						studentAccount = loginStudentUserMenu.getStudentAccount();
						if (studentAccount != null) {
							loggedInUserType = "StudentAccount";
						}
						break;
					case 2:
						LoginUserMenu loginSupervisorUserMenu = new LoginUserMenu(supervisorAccount);
						loginSupervisorUserMenu.display();
						supervisorAccount = loginSupervisorUserMenu.getSupervisorAccount();
						if (supervisorAccount != null) {
							loggedInUserType = "Supervisor";
						}
						break;
					case 3:
						LoginUserMenu loginFYPCoordUserMenu = new LoginUserMenu(fypCoordinatorAccount);
						loginFYPCoordUserMenu.display();
						fypCoordinatorAccount = loginFYPCoordUserMenu.getFYPCoordinatorAccount();
						if (fypCoordinatorAccount != null) {
							loggedInUserType = "FYP Coordinator";
						}
						break;
					default:
						System.out.println("Invalid choice. Please try again.");
				}
			} else if (loggedInUserType == "StudentAccount") {
				StudentGUI studentGUI = new StudentGUI(studentAccount, loggedInUserType);
				studentGUI.display();
				if (studentGUI.execute() == 0) {
					break;
				}
				loggedInUserType = studentGUI.getUserType();
			} else if (loggedInUserType == "Supervisor") {
				SupervisorAccountGUI supervisorAccountGUI = new SupervisorAccountGUI(supervisorAccount,
						loggedInUserType);
				supervisorAccountGUI.display();
				if (supervisorAccountGUI.execute() == 0) {
					break;
				}
				loggedInUserType = supervisorAccountGUI.getUserType();
			} else if (loggedInUserType == "FYP Coordinator") {
				FYPCoordinatorGUI fypCoordinatorGUI = new FYPCoordinatorGUI(fypCoordinatorAccount,
						loggedInUserType);
				fypCoordinatorGUI.display();
				if (fypCoordinatorGUI.execute() == 0) {
					break;
				}
				loggedInUserType = fypCoordinatorGUI.getUserType();
			}
		}
		new EndProgramMenu().display();

		// CSVUpdater.updateMovies("src/database/movieDB.csv");
		// CSVUpdater.updateCineplex("src/database/CineplexDB.csv");
		// CSVUpdater.updateReviewList("src/database/reviewListDB.csv");
		// CSVUpdater.updateCinema("src/database/CinemaDB.csv");
		// CSVUpdater.updateShows("src/database/showDB.csv");
		// CSVUpdater.updateTickets("src/database/MovieTicketDB.csv");
		// CSVUpdater.updateBooking("src/database/bookingDB.csv");

	}

}