package moblima.cineplex;

import moblima.cineplex.cinema.Cinema;
import moblima.show.ShowList;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a Cineplex branch of the company, which is contains the cinema halls and list of shows
 */
public class Cineplex {

    private String branchName; // BEDOK SV
    private String branchAddress;
    private ArrayList<Cinema> cinemas = new ArrayList<>();

    private ShowList showList = new ShowList();

    /**
     * Creates a cineplex branch
     * @param branchName Name of the cineplex
     * @param branchAddress Address of the cineplex
     */
    public Cineplex(String branchName, String branchAddress) {
        this.branchName = branchName;
        this.branchAddress = branchAddress;
    }

    /**
     * Returns the list of shows of the cineplex
     * @return ShowList that manages shows
     */
    public ShowList getShowList() {
        return showList;
    }

    /**
     * Add a cinema to the cineplex branch
     * @param cinema Cinema hall to be added
     */
    public void addCinema(Cinema cinema){
        cinema.generateCinemaCode(branchName.substring(0,2));
        cinemas.add(cinema);
    }

    /**
     * Removes shows associated to a Movie ID
     * @param movieId Id of movie whose shows are to be removed
     */
    public void removeShowByMovieId(int movieId){
        showList.removeShowByMovieId(movieId);
    }

    /**
     * Prints the details related to the Cineplex branch
     */
    public void printCineplexDetails(){
        System.out.print("Branch: " + branchName + ", Address: " + branchAddress);
    }

    public String getBranchName() {
        return this.branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    /**
     * Prints the details of Cinema halls within the Cineplex branch
     */
    public void listCinemas(){
        for(Cinema cinema:cinemas){
            cinema.printDetails();
        }
    }

    /**
     * Search for a cinema by the cinema Id
     * @param cinemaId Id of cinema to be found
     * @return Cinema object
     */
    public Cinema searchCinemaById(String cinemaId){
        for (Cinema cinema: cinemas){
            if (Objects.equals(cinema.getCinemaCode(), cinemaId)){
                return cinema;
            }
        }
        return null;
    }

    public ArrayList<Cinema> getCinemaList() {
        return cinemas;
    }
}
