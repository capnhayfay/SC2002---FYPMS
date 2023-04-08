package FYPMS;

import FYPMS.booking.BookingHistory;
import FYPMS.cineplex.CineplexList;
import FYPMS.project.FYPList;

/**
 * Represents the company this application is created for
 * Contains all bookings, movies and cineplexes the company owns
 */
public class FYPMS {

    private static final BookingHistory bookingHistory = new BookingHistory();

    private static final FYPList fypList = new FYPList();

    public static FYPList getFypList() {
        return fypList;
    }

    private static final CineplexList cineplexList = new CineplexList();

    public static BookingHistory getBookingHistory() {
        return bookingHistory;
    }
    public static CineplexList getCineplexList() {
        return cineplexList;
    }

}
