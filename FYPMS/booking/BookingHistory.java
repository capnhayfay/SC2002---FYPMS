package moblima.booking;

import moblima.booking.ticket.MovieTicket;

import java.util.ArrayList;

/**
 * Represents the entire booking history for the company, aggregates every Booking performed
 */
public class BookingHistory {
    private ArrayList<Booking> bookings = new ArrayList<>();

    /**
     * Display the Bookings associated with a particular user
     * @param username Username of the customer to display
     */
    public void showUserBookingHistory(String username){
    	System.out.println();
        int i = 1;
        boolean hasPastTransaction = false;
        System.out.println("-----------------------------------------");
        System.out.println("             Booking History             ");
        System.out.println("-----------------------------------------");
        for(Booking booking: bookings){
            if (booking.getCustomerName().equals(username)){
                hasPastTransaction = true;
                
                System.out.println("Booking " + i );
                booking.printBookingDetails();
                i++;
                System.out.println();
            }

        }
        if (!hasPastTransaction) {
        	
        	
            System.out.println("No Booking History found for this name.");
            
        }
    }

    /**
     * Search for a movie ticket by ticket id
     * @param ticketId Id of ticket to be found
     * @return MovieTicket object
     */
    public MovieTicket searchTicketByTicketId(int ticketId){
        for (Booking booking:bookings){
            MovieTicket tempTicket = booking.getTicket(ticketId);
            if(tempTicket != null)
                return tempTicket;
        }
        return null;
    }

    /**
     * Add a completed booking to the overall booking history
     * @param booking Finalised booking that contains tickets purchased
     */
    public void addBooking(Booking booking){
        booking.generateTransactionId(booking.getTickets().get(0).getShow().getCinema().getCinemaCode());
        bookings.add(booking);
    }

    /**
     * Find a booking from the booking history list by index
     * @param index Index of booking to be found
     * @return Booking object
     */
    public Booking getBookingByIndex(int index){
        if (index<0 || index>= bookings.size()){
            return null;
        }
        return bookings.get(index);

    }

//    public ArrayList<Booking> getBookings() {
//        return bookings;
//    }


}
