package command.student;

import command.Command;
import exceptions.moblimaExceptions;
import moblima.SilverVillage;
import moblima.booking.ticket.MovieTicket;
import moblima.movie.review.Review;

import java.util.Scanner;

/**
 * Represents a command for Customer to create a review for a specific movie
 */
public class ReviewMovieCommand implements Command{
    /**
     * Get input from user to create a Review Object for a Movie and appends it to the ReviewList in Movie
     */
    public void execute(){

        Scanner input = new Scanner(System.in);
        System.out.println();
        System.out.print("Please enter the Ticket ID: ");
        while(true) {
            try {
                if (input.hasNextInt() == false) {
                    throw new moblimaExceptions.invalidInputException("Ticket ID");
                }
                int ticketID = input.nextInt();
                input.nextLine();
                MovieTicket ticket = SilverVillage.getBookingHistory().searchTicketByTicketId(ticketID);
                if (ticket == null) {
                    System.out.println();
                    System.out.println("Ticket ID cannot be found. No review created.");
                    return;
                }
                System.out.println();
                System.out.print("Please enter your Review Rating (1-5, 5 being the best): ");

                while (true) {
                    try {
                        if (input.hasNextInt() == false) {
                            throw new moblimaExceptions.invalidInputException("Rating");
                        }
                        int reviewRating = input.nextInt();
                        
                        while(true) {
                        	
	                        if(reviewRating <= 0 || reviewRating > 5) {
	                        	System.out.println("Rating out of range. Please try again.");
	                        	System.out.println();
	                            System.out.print("Please enter your Review Rating (1-5, 5 being the best) again: ");
	                            reviewRating = input.nextInt();
	                        	continue;
	                        }
	                        input.nextLine();
                        	break;
                    	}
                        System.out.println();
                        System.out.print("Please enter your Review: ");
                        String reviewDesc = input.nextLine();
                        
                        Review review = new Review(reviewRating, reviewDesc);
                        ticket.getShow().getMovie().addReview(review);
                        System.out.println();
                        System.out.println("Review added successfully.");
                        return;
                    }
                    catch (moblimaExceptions.invalidInputException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                    System.out.print("Please enter your Review Rating (1-5, 5 being the best) again: ");
                    input.nextLine();
                    
                    continue;
                }
            }
            catch (moblimaExceptions.invalidInputException e) {
                System.out.println(e.getMessage());
                
            }
            System.out.println();
            System.out.print("Please enter the Ticket ID again: ");
            input.nextLine();
            continue;
        }
    }
}

