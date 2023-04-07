package moblima.show;

/**
 * Represents the seating of a cinema for a particular show
 */
public class Seating {

    private char[][] seats;

    /**
     * Creates a 10 row by 10 column seating
     */
    public Seating() {
        this.seats = new char[10][10];
    } //instantiate all to 0

    /**
     * Prints the seating arrangement and available seats in a visual form for customers' viewing
     */
    public void printSeats() {
        System.out.println();

        System.out.printf("%-8s", "");
        for (int i = 0; i < seats[0].length; i++) {
            System.out.printf("%-4s", i+1);
        }
        System.out.println();
        for(int row = 0; row<10; row++){
            System.out.print("Row " + (char)(row + 'A') + ": ");
            for(int col = 0; col<10; col++){

                if(seats[row][col] == '\0')
                    System.out.print("[_] ");
                else {
                    System.out.print("[X] ");
                }
            }
            System.out.print("\n");
        }
        System.out.println();

    }

    /**
     * Mark seats as booked when the tickets associated to that seat is purchased
     * @param seatId ID of seat booked
     * @return Success (1) or failure (-1) in booking the selected seat
     */
    public int bookSeat(String seatId){
        char alphabet = seatId.charAt(0);
        int row = alphabet - 'A';
        int col = Integer.parseInt(seatId.substring(1)) - 1;

        if(row >= 10 || col >= 10 || row<0 || col < 0) return -1;
        if (seats[row][col] == 'X') return 0;
        seats[row][col] = 'X';
        return 1;

    }
}
