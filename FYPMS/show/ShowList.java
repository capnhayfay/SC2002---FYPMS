package moblima.show;

import java.util.ArrayList;

/**
 * Represents the list of shows in a Cineplex branch
 */
public class ShowList {
    private ArrayList<Show> shows = new ArrayList<>();

    /**
     * Add a show to the ShowList of a Cineplex
     * @param show Show to be added
     */
    public void addShow(Show show){ //for admin
        shows.add(show);
    }

    /**
     * Removes a show from the ShowList by its show ID
     * @param showId ID of show to be removed
     */
    public void removeShowById(int showId){
        for(Show show : shows){
            if(show.getShowId()==showId){
                shows.remove(show);
                System.out.println();
                System.out.println("Show " + showId + " has been removed.");
                System.out.println();
                System.out.println("Show successfully deleted.");
                return;
            }
        }
        System.out.println();
        System.out.println("Show " + showId + " does not exist. No Shows removed.");
        
    }

    /**
     * Removes all shows from ShowList associated to a particular movie
     * @param movieId ID of movie whose shows are to be removed
     */
    public void removeShowByMovieId(int movieId){
        ArrayList<Show> foundShows = new ArrayList<Show>();
        for (Show show : this.shows){
            if (show.getMovie().getMovieId() == movieId){
                foundShows.add(show);
            }
        }
        this.shows.removeAll(foundShows);
    }

    /**
     * Prints the details of all upcoming shows for the Cineplex
     */
    public void listShows(){
        System.out.println();
        
        System.out.println("-----------------------------------------");
        System.out.println("           Available Show List           ");
        System.out.println("-----------------------------------------");
        if(shows.size() == 0) {
        	System.out.println();
        	System.out.println("             No shows exist              ");
        	System.out.println();
        	System.out.println("-----------------------------------------");
        	return;
        }
        for(Show show: shows){
            if(show.isShowing()){
                show.printShowDetails();
            }
        }
        System.out.println();
        System.out.println("-----------------------------------------");
       
    }

    /**
     * Search for a Show by its show ID
     * @param showId ID of show to be found
     * @return Show object
     */
    public Show searchShowById(int showId){
        for(Show show: shows){
            if(show.getShowId()==showId){
                return show;
            }
        }
        return null;
    }

    public ArrayList<Show> getShows() {
        return shows;
    }

}
