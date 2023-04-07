package command.student;

import command.Command;
import moblima.SilverVillage;

/**
 * Represents command to rank top 5 movies according to review ratings
 */
public class RankReviewRatingsCommand implements Command {
	/**
	 * Prints the top 5 movies according to review ratings
	 */
	public void execute() {
		SilverVillage.getMovieList().showTopMoviesByRating();
	}

}
