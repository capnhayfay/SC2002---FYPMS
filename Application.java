/*
     The Application class represents the main entry point of the FYPMS application.
     It initializes the system, loads data from CSV files, and starts the user interface by prompting the user to select their userType.
     Then, it prompts the user to log in.
     After the user logs in, the appropriate GUI (StudentCLI, SupervisorCLI or FYPCoordinatorCLI) is displayed and the user can interact with the system.
     Once the user is done, they can log out and the application will save any changes made to the data files.
     This class contains a main method that initializes a new instance of the Application class and invokes the run method.
     The run method initializes the FYPMS system, loads data from CSV files, prompts the user to log in and displays the appropriate GUI depending on the user type (student, supervisor or FYP coordinator).
    @see StudentCLI
    @see SupervisorCLI
    @see FYPCoordinatorCLI
    @see LoginUserMenu
    @see FileReader
    @see StudentAccount
    @see SupervisorAccount
    @see FYPCoordinatorAccount
*/

import account.supervisor.SupervisorAccount;
import account.supervisor.FYPCoordinatorAccount;
import account.AccountManager;
import account.student.StudentAccount;
import cli.*;
import FYPMS.*;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {
	/**
	 * The main method initializes a new instance of the Application class and
	 * invokes the run method.
	 * 
	 * @throws IOException If there is an I/O error reading the CSV files.
	 */
	public static void main(String[] args) throws IOException {
		new Application().run();
	}

	/**
	 * The run method initializes the FYPMS system, loads data from CSV files,
	 * prompts the user to log in and
	 * displays the appropriate GUI depending on the user type (student, supervisor
	 * or FYP coordinator).
	 * Once the user is done interacting with the system, any changes made are saved
	 * to the data files.
	 * 
	 * @throws IOException If there is an I/O error reading or writing the CSV
	 *                     files.
	 */
	public void run() throws IOException {

		// load in CSV
		FileReader.readFYPsFromFile("./database/Modified/rollover project.txt");
		FileReader.readSupervisorsFromFile("./database/Modified/faculty_list.txt");
		FileReader.readCoordinatorsFromFile("./database/Modified/FYP coordinator.txt");
		FileReader.readStudentsFromFile("./database/Modified/student list.txt");
		FileReader.readRequestsFromFile("./database/Modified/0requestChangeTitle.txt",
				"./database/Modified/1requestDeregister.txt",
				"./database/Modified/2requestRegister.txt",
				"./database/Modified/3requestTransferSupervisor.txt");
		// end of load in CSV

		// // load in updatedCSV
		// FileReader.readFYPsFromFile("./database/test/rollover project.txt");
		// FileReader.readSupervisorsFromFile("./database/test/faculty_list.txt");
		// FileReader.readCoordinatorsFromFile("./database/test/FYP coordinator.txt");
		// FileReader.readStudentsFromFile("./database/test/student list.txt");
		// FileReader.readRequestsFromFile("./database/test/0requestChangeTitle.txt",
		// "./database/test/1requestDeregister.txt",
		// "./database/test/2requestRegister.txt",
		// "./database/test/3requestTransferSupervisor.txt");
		// // end of load in updatedCSV

		Scanner scanner = new Scanner(System.in);
		String loggedInUserType = "";
		StudentAccount studentAccount = null;
		SupervisorAccount supervisorAccount = null;
		FYPCoordinatorAccount fypCoordinatorAccount = null;
		int logout = 0;

		new GreetUserMenu().display();
		System.out.println();
		while (true) {
			if (loggedInUserType.equals("")) {
				System.out.println("Which type of user are you? ");
				System.out.println("1. Student");
				System.out.println("2. Supervisor");
				System.out.println("3. FYP Coordinator");
				System.out.println("0. Exit");
				System.out.println();
				int choice = -1;
				do {
					try {
						choice = scanner.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("Please enter only numeric values!");
						scanner.next();
						System.out.println();
						System.out.println();
						System.out.println("Which type of user are you? ");
						System.out.println("1. Student");
						System.out.println("2. Supervisor");
						System.out.println("3. FYP Coordinator");
						System.out.println("0. Exit");
						System.out.println();
					}

				} while (choice == -1);
				switch (choice) {
					case 0:
						logout = 1;
						break;
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
						System.out.println();
						System.out.println("Invalid choice. Please try again.");
						System.out.println();
				}
				if (logout == 1) {
					break;
				}
			} else if (loggedInUserType.equals("StudentAccount")) {
				StudentCLI studentCLI = new StudentCLI(studentAccount, loggedInUserType);
				studentCLI.display();
				if (studentCLI.execute() == 0) {
					loggedInUserType = "";
					break;
				}
				loggedInUserType = studentCLI.getUserType();
			} else if (loggedInUserType.equals("Supervisor") &&
					!supervisorAccount.getName().equalsIgnoreCase(AccountManager.getCoordinatorList().get(0).getName())) {
				SupervisorCLI supervisorAccountGUI = new SupervisorCLI(supervisorAccount,
						loggedInUserType);
				supervisorAccountGUI.display();
				if (supervisorAccountGUI.execute() == 0) {
					loggedInUserType = "";
					break;
				}
				loggedInUserType = supervisorAccountGUI.getUserType();
			} else if (loggedInUserType.equals("FYP Coordinator")) {
				FYPCoordinatorCLI fypCoordinatorGUI = new FYPCoordinatorCLI(fypCoordinatorAccount,
						loggedInUserType);
				fypCoordinatorGUI.display();
				if (fypCoordinatorGUI.execute() == 0) {
					loggedInUserType = "";
					break;
				}
				loggedInUserType = fypCoordinatorGUI.getUserType();
			} else {
				System.out.println();
				System.out.println("Invalid user type, please try again");
				System.out.println();
				loggedInUserType = "";
			}
		}

		new EndProgramMenu().display();

		FileUpdater.writeSupervisorToFile("./database/test/faculty_list.txt");
		FileUpdater.writeCoordinatorToFile("./database/test/FYP coordinator.txt");
		FileUpdater.writeStudentToFile("./database/test/student list.txt");
		FileUpdater.writeFYPsToFile("./database/test/rollover project.txt");
		FileUpdater.writeRequestsToFile("./database/test/0requestChangeTitle.txt",
				"./database/test/1requestDeregister.txt", "./database/test/2requestRegister.txt",
				"./database/test/3requestTransferSupervisor.txt");
	}

}