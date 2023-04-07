package command.student;

import command.Command;
import exceptions.moblimaExceptions.invalidInputException;
import moblima.SilverVillage;
import moblima.booking.Booking;
import moblima.booking.ticket.MovieTicket;
import moblima.cineplex.Cineplex;
import moblima.movie.MovieStatus;
import moblima.show.Show;

import java.util.Scanner;

import static moblima.booking.ticket.MovieTicket.convertToCustomerAge;
/**
 * Represents a command for Guests to select and book tickets for a show
 */
public class GuestBookTicketCommand implements Command{

	private Cineplex cineplex;

	/**
	 * Creates a GuestBookTicketCommand with given Cineplex
	 * @param cineplex which is the current Cineplex Guest is viewing
	 */
	public GuestBookTicketCommand(Cineplex cineplex) {
		this.cineplex = cineplex;
	}

	/**
	 * Gets input from the Guest to book specific seats for a show and generates receipt
	 */
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.println("For cineplex location: " + cineplex.getBranchName());
		cineplex.getShowList().listShows();
		System.out.println();
		System.out.print("Please enter the Show ID: ");
		// handle error later
		while(true) {
			try {
				if(scanner.hasNextInt() == false) {
					throw new invalidInputException("Show ID");
				}
				int showID = scanner.nextInt();
				scanner.nextLine();
				Show show = this.cineplex.getShowList().searchShowById(showID);
				if (show == null) {
					System.out.println();
					System.out.println("Show ID \"" + showID + "\" does not exist. No ticket added.");
					
					
					
					return;
				}
				else if(show != (Show)show) {
					throw new invalidInputException("Show ID");
				}
				else {
					if(show.getMovie().getStatus() == MovieStatus.COMING_SOON) {
						System.out.println();
						System.out.println("Tickets for shows that are 'Coming Soon' are not available for sale.");
						return;
					}
					show.printShowDetails();
					System.out.println();
					System.out.print("Please enter your Name: ");
					String name = scanner.nextLine();
					System.out.print("Please enter your Mobile Number: ");
					String mobileNumber = scanner.nextLine();
					System.out.print("Please enter your Email Address: ");
					String emailAddress = scanner.nextLine();


					//error handling: when mobile number is not numerical and email address doesn't have @ symbol
					while(true) {
						try {

							if (!mobileNumber.matches("[0-9]+") || mobileNumber.length() <= 2) {
								//mobile wrong email wrong
								if(emailAddress.contains("@") == false) {
									throw new invalidInputException("Mobile Number and Email Address");

								}
								//mobile wrong email correct
								else {
									throw new invalidInputException("Mobile Number");
								}


							}
							// mobile correct email wrong
							else if(mobileNumber.matches("[0-9]+") && mobileNumber.length() > 2) {
								if(emailAddress.contains("@") == false) {

									throw new invalidInputException("Email Address");
								}

							}


							break;

						}
						catch (invalidInputException e) {
							System.out.println(e.getMessage());

						}
						System.out.println();
						System.out.print("Please enter your Mobile Number again: ");
						mobileNumber = scanner.nextLine();
						System.out.print("Please enter your Email Address again: ");
						emailAddress = scanner.nextLine();
						continue;
					}

					//to implement transaction ID function
					System.out.println();
					System.out.print("Please enter the Number of tickets to be purchased: ");
					while(true) {
						if(scanner.hasNextInt() == false) {
							System.out.println("Invalid input format for Number of tickets. Please try again");
							System.out.println();
							System.out.print("Please enter the Number of tickets to be purchased again: ");
							scanner.nextLine();
							continue;
						}
						
						break;
						
					}
					int numTickets = scanner.nextInt();
					//scanner.nextLine();
					
					while(true) {
						if(numTickets <= 0) {
							System.out.println("Minimum of 1 ticket must be purchased.");
							System.out.println();
							System.out.print("Please enter the Number of tickets to be purchased again: ");
							//scanner.nextLine();
							numTickets = scanner.nextInt();
							continue;
						}
						
						if(numTickets > 10 ) {
							System.out.println("Maximum of 10 tickets can be purchased at a time.");
							System.out.println();
							System.out.print("Please enter the Number of tickets to be purchased again: ");
							//scanner.nextLine();
							numTickets = scanner.nextInt();
							continue;
						}
					
						scanner.nextLine();
						break;
						
					}
					
					if(numTickets > 0) {
						Booking booking = new Booking(name,mobileNumber,emailAddress);

						for (int i = 0; i<numTickets;i++) {
							show.showSeating();
							System.out.print("Please enter the seat ID in this format (eg. B6): ");
							String seatId = scanner.nextLine();
							System.out.print("Please enter the age of movie goer: ");
							String age = scanner.nextLine();

							//Error handling: when seat ID format wrong or if age format is wrong
							while(true) {
								try {


									if ((Character.isAlphabetic(seatId.charAt(0)) == false) || (Character.isUpperCase(seatId.charAt(0)) == false) || (Character.isDigit(seatId.charAt(1)) == false)) {
										//seatID wrong age wrong
										if(!age.matches("[0-9]+") || age.length() > 2 ) {
											throw new invalidInputException("seat ID and age");

										}
										//seatID wrong age correct
										else {
											throw new invalidInputException("seat ID");
										}


									}
									// seatID correct age wrong
									else if((Character.isAlphabetic(seatId.charAt(0)) == true) && (Character.isUpperCase(seatId.charAt(0)) == true) && (Character.isDigit(seatId.charAt(1)) == true)) {
										if(( !age.matches("[0-9]+") || age.length() > 2)) {

											throw new invalidInputException("age");
										}

									}


									break;

								}
								catch (invalidInputException e) {
									System.out.println(e.getMessage());

								}
								System.out.println();
								System.out.print("Please enter the seat ID in this format (eg. B6) again: ");
								seatId = scanner.nextLine();
								System.out.print("Please enter the age of movie goer again: ");
								age = scanner.nextLine();
								continue;
							}

							//check if the seatID already taken// input out of range
							while(true) {
								if(show.getSeating().bookSeat(seatId) == 1) {
									break;
								}
								else {
									if (show.getSeating().bookSeat(seatId) == 0)
										System.out.println("Seat already taken.");
									else {
										System.out.println("Seat does not exist.");
									}

								}
								System.out.println();
								System.out.print("Please enter the seat ID in this format (eg. B6) again: ");

								seatId = scanner.nextLine();

								continue;
							}

							show.getMovie().incrementTicketSold();
							booking.addTickets(new MovieTicket(seatId,show,convertToCustomerAge(age)));
						}
						SilverVillage.getBookingHistory().addBooking(booking);
						booking.printBookingDetails();

					}
					else {
						System.out.println("No tickets purchased.");
					}


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

		;	}

}
