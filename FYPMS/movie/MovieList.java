package moblima.movie;

import moblima.SilverVillage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents the list of movies the company has purchased and are entitled to show in their theatres
 */
public class MovieList {
    private final ArrayList<Movie> movies = new ArrayList<>();

    /**
     * Updates movie status to 'End of Showing' automatically (upon starting the application) when the Movie has passed its expiry
     */
    public void updateExpiredMovieStatus(){
        for(Movie movie: movies){
            if(movie.getExpiryDate().isBefore(LocalDateTime.now())){
                if(movie.getStatus()!= MovieStatus.END_OF_SHOWING){
                    movie.setStatus(MovieStatus.END_OF_SHOWING);
                }
            }
        }
    }

    /**
     * Add a movie to the list of movies the company can show
     * @param movie Movie to be added
     */
    public void addMovie(Movie movie){
        movies.add(movie);
    }

    /**
     * Delists a movie by setting its status to "End of Showing"
     * @param movieId Id of movie to be delisted
     */
    public void delistMovie(int movieId){
        this.updateMovieStatus(movieId,MovieStatus.END_OF_SHOWING);
    }

    /**
     * Prints the available movies (Preview, Now Showing) for showing to customer users
     */
    public void listMoviesForUser() {
        int movieCount = 1;
        System.out.println();
        System.out.println("List of Movies showing in Silver Village");
        System.out.println();
        for (Movie movie : movies) {
            if (movie.getStatus() != MovieStatus.END_OF_SHOWING) {
                System.out.println("============= Result No. " + movieCount++ + " ==============");
                movie.printMovieDetails();
                System.out.println();
            }
        }
        System.out.println("===== There are " + (movieCount - 1) + " movies available! =====");
    }

    /**
     * Prints all movies in the system for admin users
     */
    public void listMoviesForAdmin(){
        int movieCount = 1;
        System.out.println();
        System.out.println("List of Movies");
        System.out.println();
        for(Movie movie: movies){
            System.out.println("============= Result No. " + movieCount++ + " ==============");
            movie.printMovieDetails();
            System.out.println();
        }
        System.out.println("===== There are " + movies.size() + " movies available! =====");
        System.out.println();
        System.out.println("-----------------------------------------");
    }

    /**
     * Searches for a movie using a keyword inputted by users
     * @param keyword Keyword used to search for a movie name
     */
    public void searchMovieByKeyword(String keyword){
        int numOfResults = 0;
        
       
        System.out.println();
        System.out.println("Search Results for movie titled \"" + keyword + "\"");
        System.out.println();
        
        for(Movie movie: movies){
        	
            if(movie.getTitle().toLowerCase().contains(keyword.toLowerCase())){
            	
                System.out.println("============= Result No. " + ++numOfResults + " ==============");
                movie.printMovieDetails();
                System.out.println();
            }
        }
       
        if(numOfResults == 0) {
        	System.out.println("No such movie exists.");
        }
        else {
        	System.out.println("====== There are " + numOfResults + " search results! ======");
        }

    }

    /**
     * Search for a movie by its movie ID
     * @param movieId ID of movie to be found
     * @return Movie object
     */
    public Movie searchMovieById(int movieId){
        for(Movie movie: movies){
            if(movie.getMovieId()==movieId){
                return movie;
            }
        }
        return null;
    }

    /**
     * Updates the movie status of a movie
     * @param movieId ID of movie whose status is to be updated
     * @param status Resulting status of the movie
     */
    public void updateMovieStatus(int movieId, MovieStatus status){
        SilverVillage.getMovieList().searchMovieById(movieId).setStatus(status);
    }

    /**
     * Displays the top 5 Movies by its Rating, from highest to lowest
     */
    public void showTopMoviesByRating(){
        //reversed cause highest should be at top
        Collections.sort(movies, Comparator.comparingInt(Movie::getRating).reversed());
        System.out.println();
        System.out.println("Top 5 Movies by Ratings");
        if(movies.size()<=5){
            for(int i=0; i<movies.size(); i++){
                movies.get(i).printMovieDetails();
            }
        } else {
            for(int i=0; i<5; i++){
                movies.get(i).printMovieDetails();
            }
        }
    }

    /**
     * Displays the top 5 Movies by its Sale, from highest to lowest
     */
    public void showTopMoviesBySale(){
        Collections.sort(movies, Comparator.comparingInt(Movie::getTicketSold).reversed());
        System.out.println();
        System.out.println("Top 5 Movies by Ticket Sales");
        if(movies.size()<=5){
            for(int i=0; i<movies.size(); i++){
                movies.get(i).printMovieDetails();
            }
        } else {
            for(int i=0; i<5; i++){
                movies.get(i).printMovieDetails();
            }
        }
    }

    /**
     * Helper function to search for a movie by its index in the list of movies, for CSV storage
     * @param index Index of movie to be found
     * @return Movie object
     */
    public Movie getMovieByIndex(int index){
        if (index<0 || index>= movies.size()){
            return null;
        }
        return movies.get(index);
    }

}
