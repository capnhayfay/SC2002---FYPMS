package command.student;

import command.Command;
import moblima.SilverVillage;

/**
 * Represents a command which lists all available movies (excluding END_OF_SHOWING)
 */
public class UserListMoviesCommand implements Command {
	/**
	 * Prints list of all available movies (excluding END_OF_SHOWING)
	 */
	public void execute() {
		
		SilverVillage.getMovieList().listMoviesForUser();
		System.out.println();
	}
}
