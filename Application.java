import account.supervisor.SupervisorAccount;
import account.supervisor.FYPCoordinatorAccount;
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
		FileReader.readSupervisorsFromFile("./database/Modified/faculty_list.txt");
		FileReader.readCoordinatorsFromFile("./database/Modified/FYPcoordinator.txt");
		FileReader.readStudentsFromFile("./database/Modified/student list.txt");
		FileReader.readFYPsFromFile("./database/Modified/rollover project.txt");
		FileReader.readRequestsFromFile("./database/Modified/0requestChangeTitle.txt",
				"./database/Modified/1requestDeregister.txt",
				"./database/Modified/2requestRegister.txt",
				"./database/Modified/3requestTransferSupervisor.txt");
		// end of load in CSV

		// load in CSV
		FileReader.readSupervisorsFromFile("./database/test/faculty_list.txt");
		FileReader.readCoordinatorsFromFile("./database/test/FYP coordinator.txt");
		FileReader.readStudentsFromFile("./database/test/student list.txt");
		FileReader.readFYPsFromFile("./database/test/rollover project.txt");
		FileReader.readRequestsFromFile("./database/test/0requestChangeTitle.txt",
				"./database/test/1requestDeregister.txt", "./database/test/2requestRegister.txt",
				"./database/test/3requestTransferSupervisor.txt");
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
				System.out.println("Which type of user are you? ");
				System.out.println("1. StudentAccount");
				System.out.println("2. Supervisor");
				System.out.println("3. FYP Coordinator");
				System.out.println();
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
				SupervisorGUI supervisorAccountGUI = new SupervisorGUI(supervisorAccount,
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

		FileUpdater.writeSupervisorToFile("./database/test/faculty_list.txt");
		FileUpdater.writeCoordinatorToFile("./database/test/FYP coordinator.txt");
		FileUpdater.writeStudentToFile("./database/test/student list.txt");
		FileUpdater.writeFYPsToFile("./database/test/rollover project.txt");
		FileUpdater.writeRequestsToFile("./database/test/0requestChangeTitle.txt",
				"./database/test/1requestDeregister.txt", "./database/test/2requestRegister.txt",
				"./database/test/3requestTransferSupervisor.txt");

		new EndProgramMenu().display();
	}

}