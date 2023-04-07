package moblima.booking.ticket;

import moblima.cineplex.cinema.CinemaClass;
import system.SystemSettings;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

/**
 * Represents a calculator to calculate ticket price based on customer age, cinema class, and date of show
 */
public class TicketPriceCalculator {

    private double basePrice = 6;
    CustomerAge customerAge;
    CinemaClass cinemaClass;
    LocalDateTime date;

    /**
     * Creates a ticket price calculator
     * @param customerAge CustomerAge enum
     * @param cinemaClass CinemaClass enum
     * @param date Date of show
     */
    public TicketPriceCalculator(CustomerAge customerAge, CinemaClass cinemaClass, LocalDateTime date){
        this.cinemaClass = cinemaClass;
        this.customerAge = customerAge;
        this.date = date;
        this.basePrice = SystemSettings.getBasePrice();
    }


    /**
     * Method to calculate the price of ticket by adding additional charges to the base price
     * @return Calculated price of the ticket
     */
    public double calculatePrice(){
        double additionalCharge = 0;

        // Add price based on Customer age bracket
        switch(customerAge){
        case CHILD:
            additionalCharge += 1;
            break;
        case ADULT:
            additionalCharge += 4;
            break;
        case SENIOR:
            additionalCharge += 2;
            break;
        }

        // Scale price based on Cinema Class level
        switch(cinemaClass){
        case NORMAL:
            additionalCharge += 0;
            break;
        case GOLD:
            additionalCharge += 1.5;
            break;
        case PLATINUM:
            additionalCharge += 2.5;
            break;
        }

        // Public Holiday and Weekend Surcharge of $3
        if(SystemSettings.isPublicHoliday(date) || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY){
            additionalCharge += 3.0;
        }

        return basePrice + additionalCharge;
    }
}
