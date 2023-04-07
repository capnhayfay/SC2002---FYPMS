package moblima.cineplex;

import java.util.ArrayList;

/**
 * Represents the list of Cinplexes that the company owns
 */
public class CineplexList {

    private final ArrayList<Cineplex> cineplexes = new ArrayList<>();

    /**
     * Searches for a cineplex by the cineplex ID
     * @param index ID of cineplex to be found
     * @return Cineplex object
     */
    public Cineplex getCineplexByIndex(int index){
        if (index < 0 || index == cineplexes.size()) {
            return null;
        }
        return cineplexes.get(index);
    }

    /**
     * Add a cineplex to the overall cineplex list of the company
     * @param cineplex Cineplex to be added
     */
    public void addCineplex(Cineplex cineplex){
        cineplexes.add(cineplex);
    }

    /**
     * Prints the details of all cineplexes the company owns
     */
    public void listCineplexes(){
        int i = 1;
        System.out.println();
        System.out.println("Cineplexes");
        System.out.println();
        for (Cineplex cineplex: cineplexes){
            System.out.print((i++) + ". ");
            cineplex.printCineplexDetails();
            System.out.println("\n");
        }
    }

    /**
     * Removes all shows related to a Movie by the Movie ID
     * @param movieId ID of movie whose shows are to be removed
     */
    public void removeShowsByMovieId(int movieId){
        for (Cineplex cineplex: cineplexes){
            cineplex.removeShowByMovieId(movieId);
        }
    }

    /**
     * Searches for a cineplex by the branch name
     * @param branchName Name of cineplex to be found
     * @return Cineplex object
     */
    public Cineplex getCineplexByName(String branchName){
        for (Cineplex cineplex: cineplexes){
            if (branchName.equals(cineplex.getBranchName())){
                return cineplex;
            }
        }
        return null;
    }

    /**
     * Searches for the cineplex that show is happening in
     * @param showId ID of show to find the Cinema and thus Cineplex it is showing in
     * @return Cineplex object
     */
    public Cineplex getCineplexByShow(int showId){
        for (Cineplex cineplex: cineplexes){
            if (cineplex.getShowList().searchShowById(showId) != null) return cineplex;
        }
        return null;
    }

}
