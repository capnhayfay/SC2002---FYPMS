package command.student;

import command.Command;
import moblima.SilverVillage;

public class RankTicketSalesCommand implements Command {
	/**
	 * Represents command to rank top 5 movies according to ticket sales
	 */
	public RankTicketSalesCommand() {
	}

	/**
	 * Prints the top 5 movies according to ticket sales
	 */
	public void execute() {
		SilverVillage.getMovieList().showTopMoviesBySale();
	}

}
