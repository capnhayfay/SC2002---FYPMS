package command.student;
import moblima.cineplex.*;

/**
 * Represents a command to list all the available shows in a Cineplex
 */
public class ListShowsCommand {
    private Cineplex cineplex;

    /**
     * Creates a ListShowsCommand Object with the given Cineplex
     * @param cineplex which is the current Cineplex
     */
    public ListShowsCommand(Cineplex cineplex){
        this.cineplex = cineplex;
    }

    /**
     * Prints list of available shows at Cineplex
     */
    public void execute(){
        cineplex.getShowList().listShows();
    }
}
