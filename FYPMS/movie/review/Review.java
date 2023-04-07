package moblima.movie.review;

/**
 * Represents a review on a Movie done by a customer
 */
public class Review {

    private String ticketId;
    private int rating;
    private String reviewDescription;

    public int getRating() {
        return rating;
    }


    /**
     * Creates a review for a Movie
     * @param rating Integer rating for the movie
     * @param reviewDescription Qualitative review description
     */
    public Review(int rating, String reviewDescription){
        this.rating = rating;
        this.reviewDescription = reviewDescription;
    }

    /**
     * Prints the relevant details of this instance of Review
     */
    public void printReview() {
    	
        System.out.println("Review Description: " + reviewDescription);
        System.out.println("Review Rating: " + rating);
        System.out.println();
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public String getTicketId() {
        return ticketId;
    }
}