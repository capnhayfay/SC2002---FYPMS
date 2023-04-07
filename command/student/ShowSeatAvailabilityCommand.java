package command.student;

import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.cineplex.Cineplex;
import moblima.show.Show;

import java.util.Scanner;

/**
 * Represents a command for Customer/Guest to view the seat availability of a specific show
 */
public class ShowSeatAvailabilityCommand implements Command {
	private Cineplex cineplex;

	/**
	 * Creates a ShowSeatAvailabilityCommand object with the given cineplex
	 * @param cineplex which is the current cineplex
	 */
	public ShowSeatAvailabilityCommand(Cineplex cineplex) {
		this.cineplex = cineplex;
	}

	/**
	 * Prints the availability of the seats for a specific show
	 */
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("For cineplex location: " + cineplex.getBranchName());
		cineplex.getShowList().listShows();
		System.out.println();

		System.out.print("Please enter the Show ID: ");
		while(true) {
			try {
				if(scanner.hasNextInt() == false) {
					throw new invalidInputException("Show ID");
				}

				int showId = scanner.nextInt();
				Show curShow = cineplex.getShowList().searchShowById(showId);
				if (curShow == null) {
					System.out.println();
					System.out.println("Show ID \"" + showId + "\" does not exist.");
					System.out.println("-----------------------------------------");
					return;
				}
				else if(curShow != (Show)curShow) {
					throw new invalidInputException("Show ID");
				}

				else {
					curShow.showSeating();
					
				}
				break;
			}
			catch (invalidInputException e) {
				System.out.println(e.getMessage());

			}
			System.out.println();
			System.out.print("Please enter the Show ID again: ");
			scanner.nextLine();
			continue;

		}

	}
}
