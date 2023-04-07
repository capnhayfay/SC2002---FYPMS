package moblima.movie.review;

import java.util.ArrayList;

/**
 * Represents a list of Reviews for a particular Movie
 */
public class ReviewList {
    private int totalRating=0;
    private ArrayList<Review> reviews = new ArrayList<>();

    /**
     * Add a review to the list of reviews for a Movie
     * @param review Review object to be added
     */
    public void add(Review review) {
        reviews.add(review);
        totalRating = totalRating + review.getRating();
    }

    /**
     * Prints all reviews for a Movie
     */
    public void listReviews(){
        if(reviews.size()==0){
        	//System.out.println("-----------------------------------------");
            System.out.println("             No reviews yet!             ");
            
        }
        for(Review review: reviews){
        	
            review.printReview();
        }
    }

    /**
     * Calculates the average rating based on all the reviews of the movie
     * @return Average rating of movie in integer form
     */
    public int showAverageRating(){
       if (reviews.size() == 0) return 0;
       return totalRating/reviews.size();
    }

    /**
     * Helper function to convert ratings to string form, for CSV storage
     * @return String form of rating
     */
    public String convertRatingsToString(){
        if (reviews.size() == 0) return null;
        String output = "";
        int count=0;
        for (Review review: reviews){
            if (count++ ==0) output += Integer.toString(review.getRating());
            else{
                output += ";";
                output += Integer.toString(review.getRating());
            }
        }
        return output;
    }

    /**
     * Helper function to convert review description to string form, for CSV storage
     * @return String form of review description
     */
    public String convertDescriptionToString(){
        if (reviews.size() == 0) return null;
        String output = "";
        int count = 0;
        for (Review review: reviews){
            if (count++ ==0) output+= review.getReviewDescription();
            else{
                output += ";";
                output += review.getReviewDescription();
            }
        }
        return output;
    }




}