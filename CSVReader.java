// package moblima;

// import account.*;
// import moblima.booking.Booking;
// import moblima.cineplex.CineplexList;
// import moblima.cineplex.cinema.Cinema;
// import moblima.cineplex.Cineplex;
// import moblima.movie.Movie;
// import moblima.movie.MovieList;
// import moblima.movie.MovieStatus;
// import moblima.movie.review.Review;
// import moblima.show.Show;
// import moblima.show.ShowList;
// import moblima.booking.ticket.MovieTicket;
// import moblima.booking.BookingHistory;
// import system.SystemSettings;


// import java.io.*;
// import java.nio.charset.StandardCharsets;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;

// //import static moblima.cineplex.Cinema.convertToCinemaClass;

// //import static moblima.show.ticket.MovieTicket.checkCustomerAge;

// public class CSVReader{

//    public static void readMoviesFromCSV(String fileName) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        Path pathToFile = Paths.get(fileName);
//        MovieList movieList = SilverVillage.getMovieList();
//        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
//            String line = br.readLine();


//            while (line != null) {
//                //movieId,title,synopsis,director,casts,status,expiryDate
//                String[] attributes = line.split(",");
//                //String movieId = attributes[0];
//                String title = attributes[1];
//                String synopsis = attributes[2];
//                String director = attributes[3];
//                String castString = attributes[4]; // to change to list
//                String[] casts = castString.split(";");
//                List<String> castList = Arrays.asList(casts);
//                MovieStatus status = Movie.convertToMovieStatus(attributes[5]);
// //               String expiry = attributes[6];
//                LocalDateTime expiryDate = LocalDateTime.parse(attributes[6],formatter);

//                Movie movie = new Movie(title, status, synopsis, director, castList, expiryDate);

//                movieList.addMovie(movie);
//                line = br.readLine();
//            }

//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }

//     public static void readCinemasFromCSV(String fileName) {

//         CineplexList cineplexList = SilverVillage.getCineplexList();
//         Path pathToFile = Paths.get(fileName);
//         try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
//             String line = br.readLine();
//             while (line != null) {
//                 // branchName,cinemaCode,classLevel
//                 String[] attributes = line.split(",");
//                 String branchName = attributes[0];
//                 //String cinemaCode = attributes[1];
//                 String classLevel = attributes[2];
//                 Cineplex cineplex = cineplexList.getCineplexByName(branchName);
//                 Cinema cinema = new Cinema(Cinema.convertToCinemaClass(classLevel));
//                 cineplex.addCinema(cinema);
//                 line = br.readLine();
//             }
//         } catch (IOException ioe) {
//             ioe.printStackTrace();
//         }
//     }

//    public static void readReviewFromCsv(String fileName){
//        Path pathToFile = Paths.get(fileName);

//        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
//            String line = br.readLine();

//            while (line != null) {
//                //movieId,rating,reviewDescription,totalRating
//                String[] attributes = line.split(",");
//                String movieId = attributes[0];
//                String rating = attributes[1];
//                String reviewDesc = attributes[2];
//                String totalRating = attributes[3];
//                Movie movie = SilverVillage.getMovieList().searchMovieById(Integer.parseInt(movieId));
//                String[] ratings = rating.split(";");
//                String[] reviewDescriptions = reviewDesc.split(";");
//                int len = ratings.length;
//                if (!(rating.equals("NA") && reviewDesc.equals("NA"))){
//                    for (int i=0; i<len; i++){
//                        Review review = new Review(Integer.parseInt(ratings[i]), reviewDescriptions[i]);
//                        movie.addReview(review);
//                    }
//                }
//                line = br.readLine();
//            }
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }




//     public static void readAccountsFromCSV(String fileName, CineplexList cineplexList) {

//        ArrayList<Account> accounts = SystemSettings.getAccounts();
//        Path pathToFile = Paths.get(fileName);
//        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
//            String line = br.readLine();

//            while (line != null) {

//                String[] attributes = line.split(",");
//                Privilege privilege = Account.convertIntToPrivilege(Integer.parseInt(attributes[0]));
//                String userId = attributes[1];
//                String password = attributes[2];
//                String email = attributes[3];
//                String mobile = attributes[4];
//                String name = attributes[5];
//                String branchName = attributes[6];

//                if (privilege == Privilege.User) { // user
//                    // String userId, String password, Privilege privilege,String emailAddress, String phoneNo, String name
//                    CustomerAccount customerAccount = new CustomerAccount(userId, password, privilege, email, mobile, name);
//                    accounts.add(customerAccount);
//                } else if (privilege == Privilege.CineplexAdmin) { // cineplex admin
//                    Cineplex cineplex = cineplexList.getCineplexByName(branchName );
//                    //String userId, String password, Privilege privilege, Cineplex cineplex, String emailAddress, String phoneNo, String name
//                    CineplexAdminAccount cineplexAdminAccount = new CineplexAdminAccount(userId, password, privilege, cineplex, email, mobile, name );
//                    accounts.add(cineplexAdminAccount);
//                } else { // company admin
//                    // String userId, String password, Privilege privilege, String emailAddress, String phoneNo, String name
//                    CompanyAdminAccount companyAdminAccount =  new CompanyAdminAccount(userId, password, privilege, email, mobile, name);
//                    accounts.add(companyAdminAccount);
//                }
//                line = br.readLine();
//            }
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//     }

//     public static void readCineplexFromCSV(String fileName){
//         Path pathToFile = Paths.get(fileName);
//         CineplexList cineplexList = SilverVillage.getCineplexList();
//         try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
//             String line = br.readLine();
//             while (line != null) {
//                 //cineplexName,cineplexLocation
//                 String[] attributes = line.split(",");
//                 String cineplexName = attributes[0];
//                 String cineplexLocation = attributes[1];

//                 Cineplex cineplex = new Cineplex(cineplexName, cineplexLocation);
//                 cineplexList.addCineplex(cineplex);
//                 line = br.readLine();
//             }
//         } catch (IOException ioe) {
//             ioe.printStackTrace();
//         }
//     }



//     public static void readShowsFromCSV(String fileName) {


//         CineplexList cineplexList = SilverVillage.getCineplexList();
//         Path pathToFile = Paths.get(fileName);
//         try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
//             String line = br.readLine();

//             while (line != null) {

//                 // showId,showTime,cinemaCode,movie
//                 String[] attributes = line.split(",");
//                 //int showId = Integer.parseInt(attributes[0]);
//                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//                 LocalDateTime showTime = LocalDateTime.parse(attributes[1],formatter);
//                 String branchName = attributes[2];
//                 String cinemaCode = attributes[3];
//                 int movieId = Integer.parseInt(attributes[4]);

//                 Cineplex cineplex = cineplexList.getCineplexByName(branchName);
//                 Cinema cinema = cineplex.searchCinemaById(cinemaCode);
//                 Movie movie = SilverVillage.getMovieList().searchMovieById(movieId);
//                 Show show = new Show(showTime, cinema, movie);
//                 ShowList showList = cineplex.getShowList();
//                 showList.addShow(show);
//                 line = br.readLine();
//             }
//         }
//         catch (IOException ioe) {
//             ioe.printStackTrace();
//         }
//     }

//     public static ArrayList<MovieTicket> readTicketsFromCSV(String movieTicketFile) {

//         CineplexList cineplexList = SilverVillage.getCineplexList();
//         ArrayList<MovieTicket> movieTickets = new ArrayList<MovieTicket>();
//         Path pathToFile = Paths.get(movieTicketFile);
//         try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
//             String line = br.readLine();
//             while (line != null) {
//                 //showId,seatId,ticketId,price,age
//                 String[] attributes = line.split(",");
//                 int showId = Integer.parseInt(attributes[0]);
//                 String seatId = attributes[1];
//                 //int ticketId = Integer.parseInt(attributes[2]);
//                 //double price = Double.parseDouble(attributes[3]);
//                 String age = attributes[4];
//                 // String seatId, Show show, double price, String age
//                 Cineplex cineplex = cineplexList.getCineplexByShow(showId);
//                 Show show = cineplex.getShowList().searchShowById(showId);

//                 MovieTicket movieTicket = new MovieTicket(seatId, show, MovieTicket.convertStringToCustomerAge(age));
//                 show.getSeating().bookSeat(seatId);
//                 movieTickets.add(movieTicket);
//                 line = br.readLine();
//             }
//         } catch (IOException ioe) {
//             ioe.printStackTrace();
//         }
//         return movieTickets;
//     }

//     public static void readBookingsFromCSV(String fileName, ArrayList<MovieTicket> arrayTicket) {

//         BookingHistory bookingHistory = SilverVillage.getBookingHistory();
//         Path pathToFile = Paths.get(fileName);
//         try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
//             String line = br.readLine();

//             while (line != null) {
//                 // ticketId, customerName, mobileNumber, emailAddress, totalPrice
//                 int index=0;
//                 String[] attributes = line.split(",");
//                 String[] ticketIdList = attributes[0].split(";");
//                 String customerName = attributes[1];
//                 String mobileNumber = attributes[2];
//                 String emailAddress = attributes[3];
//                 double totalPrice = Double.parseDouble(attributes[4]);

//                 Booking booking = new Booking(customerName, mobileNumber, emailAddress);


//                 for (MovieTicket ticket: arrayTicket){
//                     if (index == ticketIdList.length) break;
//                     if (Integer.parseInt(ticketIdList[index]) == ticket.getTicketID()){
//                         booking.addTickets(ticket);
//                         index++;
//                     }
//                 }
//                 bookingHistory.addBooking(booking);
//                 line = br.readLine();
//             }
//         } catch (IOException ioe) {
//             ioe.printStackTrace();
//         };
//     }
// }