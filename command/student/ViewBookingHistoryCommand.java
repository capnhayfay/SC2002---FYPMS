package command.student;

import command.Command;
import moblima.SilverVillage;

/**
 * Represents a command for Customer to view their prior booking history
 */

public class ViewBookingHistoryCommand implements Command {
	private String name;

	/**
	 * Creates ViewBookingHistoryCommand object which given name
	 * @param name which represents name of Customer stored in Account
	 */
	public ViewBookingHistoryCommand(String name) {
		this.name = name;
	}

	/**
	 * Searches for and prints booking history of Customer using name
	 */
	public void execute() {
		//should search for specific customers
		SilverVillage.getBookingHistory().showUserBookingHistory(name);
	}

}
