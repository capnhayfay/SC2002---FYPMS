package moblima.booking.ticket;

import moblima.show.Show;

/**
 * Represents a movie ticket for a cinema show, related to one seat in Seating
 */
public class MovieTicket {

    private String seatId;
    private static int staticTicketID=1;
    private int ticketID;
    private Show show;
    private double price;
    private CustomerAge age;

    /**
     * Creates a Movie Ticket for a seat, for a show
     * @param seatId Unique ID to represent seat in the cinema hall
     * @param show Show that the ticket is for
     * @param age Age category of the movie-goer
     */
    public MovieTicket(String seatId, Show show, CustomerAge age) {
        this.seatId = seatId;
        this.show = show;
        this.age = age;
        this.price = calculateTicketPrice(show, age);
        this.ticketID = staticTicketID++;
    }

    /**
     * Prints the details related to the instance of the ticket
     */
    public void printTicketDetails(){
        System.out.println("Seat: " + seatId);
        System.out.println("Price: $" + price);
        System.out.println("Type: " + age);
        System.out.println("Ticket ID: " + ticketID);
        show.printShowDetails();
        System.out.println("-----------------------------------------");
        //System.out.println();
    }

    public Show getShow() {
        return show;
    }

    /**
     * Calculates the ticket price based on the related information on Show, and age category of movie-goer
     * @param show Show that is associated with the ticket, contains information about the cinema class, and show date and time
     * @param age Age category of movie-goer to determine additional charges
     * @return Price of the ticket
     */
    public double calculateTicketPrice(Show show, CustomerAge age){
        TicketPriceCalculator ticketPriceCalculator = new TicketPriceCalculator(age, show.getCinema().getClassLevel(), show.getShowTime());
        return ticketPriceCalculator.calculatePrice();
    }

    public String getSeatId() {
        return seatId;
    }

    public CustomerAge getAge() {
        return age;
    }

    public double getPrice() {
        return price;
    }
    public int getTicketID() {
        return ticketID;
    }

    /**
     * Converts age number in string form to CustomerAge enum
     * @param ageString Age value of movie-goer
     * @return CustomerAge enum
     */
    public static CustomerAge convertToCustomerAge(String ageString){
        int ageInt = Integer.parseInt(ageString);
        if(ageInt<=12){
            return CustomerAge.CHILD;
        } else if(ageInt<=54){
            return CustomerAge.ADULT;
        } else{
            return CustomerAge.SENIOR;
        }
    }

    /**
     * Converts CustomerAge enum to string form, for CSV use
     * @param customerAge Age category of movie-goer
     * @return CustomerAge in string form
     */
    public static String convertCustomerAgeToString(CustomerAge customerAge){
        if (customerAge == CustomerAge.CHILD) return "CHILD";
        else if (customerAge == CustomerAge.ADULT) return "ADULT";
        else return "SENIOR";
        }

    /**
     * Converts string age category to CustomerAge num, for CSV use
     * @param string  Age category in string form of movie-goer
     * @return CustomerAge enum
     */
    public static CustomerAge convertStringToCustomerAge(String string){
        if (string.equals("CHILD")) return CustomerAge.CHILD;
        else if (string.equals("ADULT")) return CustomerAge.ADULT;
        else return CustomerAge.SENIOR;
    }
    }

