package command.student;

import command.Command;
import moblima.SilverVillage;

import java.util.Scanner;

/**
 * Represents a command for user to search for a specific movie with a keyword
 */
public class UserSearchMovieCommand implements Command {
	/**
	 * Gets input for user, searches for Movie object and prints Movie information
	 */
	public UserSearchMovieCommand() {
	}
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		String movieName;
		System.out.println();
		
		System.out.print("Please enter a keyword to search for Movie title: ");
		movieName = scanner.nextLine();
		SilverVillage.getMovieList().searchMovieByKeyword(movieName);
	}
	
}