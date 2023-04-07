package moblima.booking;
import moblima.booking.ticket.MovieTicket;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a booking of a customer that contains movie tickets purchased
 */
public class Booking {
    private String customerName;
    private String mobileNumber;
    private String emailAddress;
    private String transactionId;
    private double totalPrice;
    private ArrayList<MovieTicket> tickets = new ArrayList<>();

    /**
     * Creates a booking for a customer
     * @param customerName Name of the customer
     * @param mobileNumber Mobile number of the customer
     * @param emailAddress Email address of the customer
     */
    public Booking(String customerName, String mobileNumber, String emailAddress) {
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
        this.totalPrice = 0;
    }

    /**
     * Generates a transaction ID for the booking when the booking is finalised
     * @param cinemaCode Code of the cinema that the booking is for
     */
    public void generateTransactionId(String cinemaCode) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String yearCode = String.valueOf(currentDateTime.getYear());
        String monthCode = String.valueOf(currentDateTime.getMonthValue());
        String dayCode = String.valueOf(currentDateTime.getDayOfMonth());
        String hourCode = String.valueOf(currentDateTime.getHour());
        String minuteCode = String.valueOf(currentDateTime.getMinute());
        transactionId = yearCode + monthCode + dayCode + hourCode + minuteCode + cinemaCode;
    }

    public String getCustomerName(){
        return customerName;
    }

    /**
     * Adds movie tickets to the booking
     * @param ticket Movie Ticket that is created for a show
     */
    public void addTickets(MovieTicket ticket){
        tickets.add(ticket);
        // sum up the ticket prices
        totalPrice += ticket.getPrice();
        // increment ticket sold for that movie
        ticket.getShow().getMovie().incrementTicketSold();
        // remove seats from the show
        ticket.getShow().getSeating().bookSeat(ticket.getSeatId());
    }


    /**
     * Prints the booking details which includes the customer information and details of all the tickets purchased
     */
    public void printBookingDetails() {
        
    	System.out.println();
        //System.out.println("============ Booking Details ============");
    	System.out.println("-----------------------------------------");
        System.out.println("             Booking Details             ");
        System.out.println("-----------------------------------------");
        System.out.println("Customer: " + customerName);
        System.out.println("Mobile Number: " + mobileNumber);
        System.out.println("Email: " + emailAddress);
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("Transaction ID: " + transactionId);
        System.out.println();
        //System.out.println("============ Ticket Details =============");
        System.out.println("-----------------------------------------");
        System.out.println("             Ticket Details              ");
        System.out.println("-----------------------------------------");
        for(MovieTicket ticket: tickets){
            ticket.printTicketDetails();
        }
    }

    /**
     * Searches for a MovieTicket in this booking using ticket id
     * @param ticketId TicketId to be searched for
     * @return MovieTicket object
     */
    public MovieTicket getTicket(int ticketId){
        for (MovieTicket ticket: tickets){
            if(ticket.getTicketID() == ticketId){
                return ticket;
            }
        }
        return null;
    }

    /**
     * Searches for a MovieTicket in this booking by index
     * @param index Index of ticket to be searched for
     * @return MovieTicket object
     */
    public MovieTicket getTicketByIndex(int index){
        if (tickets.size()==0 || tickets.size()==index) return null;
        else return tickets.get(index);
    }

    /**
     * Helper function to convert tickets to string, for CSV storage
     * @return String form of a ticket
     */
    public String convertTicketsToString(){
        String output = "";
        int count=0;
        for (MovieTicket ticket: tickets){
            if (count++==0) output += Integer.toString(ticket.getTicketID());
            else {
                output += ";";
                output += Integer.toString(ticket.getTicketID());
            }
        }
        return output;
    }
    public ArrayList<MovieTicket> getTickets() {
        return tickets;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

}
