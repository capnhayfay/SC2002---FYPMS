package moblima.show;

import moblima.cineplex.cinema.Cinema;
import moblima.movie.Movie;
import moblima.movie.MovieStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a showing of a Movie in a Cinema, with the Show date and time and available seating
 */
public class Show {

    private static int currentId=1;
    private int showId;
    private LocalDateTime showTime;
    private Cinema cinema;
    private Movie movie;
    private Seating seating;

    /**
     * Creates a movie showing in a cinema
     * @param showTime Date and Time of show
     * @param cinema Cinema which the movie is showing in
     * @param movie Movie film that to be showed
     */
    public Show(LocalDateTime showTime, Cinema cinema, Movie movie) {
        this.showId = currentId++;
        this.showTime = showTime;
        this.cinema = cinema;
        this.movie = movie;
        this.seating = new Seating();
    }

    public boolean isShowing(){
        if (movie.getStatus() == MovieStatus.END_OF_SHOWING){
            return false;
        }
        return true;
    }

    public Movie getMovie() {
        return movie;
    }
    public Seating getSeating() {
        return seating;
    }
    public int getShowId() {
        return showId;
    }

    /**
     * Displays the current seating arrangement and availability for the show
     */
    public void showSeating(){ //for user
        System.out.println();
        System.out.println("X means it is occupied, [_] means it is available");

        seating.printSeats();
    }

    /**
     * Prints the relevant details of a Show
     */
    public void printShowDetails(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println();
        System.out.println("ShowID: " + showId);
        System.out.println("Movie Title: " + movie.getTitle());
        System.out.println("Cinema Hall: " + cinema.getCinemaCode());
        System.out.println("Cinema Class: " + Cinema.convertCinemaClassToString(cinema.getClassLevel()));
        System.out.println("Day & Time: " + showTime.format(formatter));
    }


    public Cinema getCinema() {
        return cinema;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    /**
     * Update the showtime of this instance of Show
     * @param showTime New show time
     */
    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}
