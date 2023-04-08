package FYPMS;
//  package moblima;

//  import java.awt.print.Book;
//  import java.io.BufferedReader;
//  import java.io.File;
//  import java.io.FileWriter;
//  import java.io.IOException;
//  import java.lang.reflect.Array;
//  import java.util.ArrayList;
//  import java.util.Arrays;
//  import java.util.List;

//  import account.*;
import com.opencsv.CSVWriter;
//  import moblima.booking.Booking;
//  import moblima.booking.ticket.MovieTicket;
//  import moblima.cineplex.Cineplex;
//  import moblima.cineplex.cinema.Cinema;
//  import moblima.movie.Movie;
//  import moblima.show.Show;
//  import moblima.FYPMS;
//  import system.SystemSettings;

//  import java.nio.charset.StandardCharsets;
//  import java.nio.file.Files;
//  import java.nio.file.Path;
//  import java.nio.file.Paths;
//  import java.time.LocalDateTime;
//  import java.time.format.DateTimeFormatter;

//  import static system.SystemSettings.getAccounts;


//  public class CSVUpdater {

//      //CineplexDB
//      public static void updateCineplex(String filePath) throws IOException{
//          File cineplexDb = new File(filePath);
//          FileWriter outputFile = new FileWriter(cineplexDb);
//          CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
//          int count=0;
//          try {
//              while (FYPMS.getCineplexList().getCineplexByIndex(count) != null){
//                  String[] input = new String[2];
//                  input[0] = FYPMS.getCineplexList().getCineplexByIndex(count).getBranchName();
//                  input[1] = FYPMS.getCineplexList().getCineplexByIndex(count++).getBranchAddress();
//                  writer.writeNext(input);
//              }
//              writer.close();
//          }
//          catch (IOException e){
//              System.out.println("An error occurred");
//          }
//      }


//      //CinemaDB
//      public static void updateCinema(String filePath) throws IOException{
//          File cinemaDb = new File(filePath);
//          FileWriter outputFile = new FileWriter(cinemaDb);
//          CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
//          int count=0;
//          try {
//              while (FYPMS.getCineplexList().getCineplexByIndex(count) != null){
//                  for (Cinema cinema: FYPMS.getCineplexList().getCineplexByIndex(count).getCinemaList()){
//                      String[] input = new String[3];
//                      input[0] = FYPMS.getCineplexList().getCineplexByIndex(count).getBranchName();
//                      input[1] = cinema.convertCinemaCodeToCurrentCode();
//                      input[2] = cinema.getClassLevel().toString();
//                      writer.writeNext(input);
//                  }
//                  count++;
//              }
//              writer.close();
//          }
//          catch (IOException e){
//              System.out.println("An error occurred");
//          }
//      }


//      //BookingDB
//      public static void updateBooking(String filePath) throws IOException{
//          File bookingDb = new File(filePath);
//          FileWriter outputFile = new FileWriter(bookingDb);
//          CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
//          int count=0;
//          try {
//              while (FYPMS.getBookingHistory().getBookingByIndex(count) != null){
//                  Booking booking = FYPMS.getBookingHistory().getBookingByIndex(count++);
//                  String[] input = new String[5];
//                  input[0] = booking.convertTicketsToString();
//                  input[1] = booking.getCustomerName();
//                  input[2] = booking.getMobileNumber();
//                  input[3] = booking.getEmailAddress();
//                  input[4] = Double.toString(booking.getTotalPrice());
//                  writer.writeNext(input);
//              }
//              writer.close();
//          }
//          catch (IOException e){
//              System.out.println("An error occurred");
//          }
//      }


//      // AccountsDB
//      public static void updateAccounts(String filePath) throws IOException {
//          File accountsFile = new File(filePath);
//          FileWriter outputFile = new FileWriter(accountsFile);
//          CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

//          try {
//              for (Account account: SystemSettings.getAccounts()){
//                  String[] input = new String[7];
//                  input[0] = Integer.toString(Account.convertPrivilegeToInt(account.getPrivilege()));
//                  input[1] = account.getLoginId();
//                  input[2] = account.getPassword();
//                  input[3] = account.getEmail();
//                  input[4] = account.getPhoneNo();
//                  input[5] = account.getName();
//                  if (Account.convertPrivilegeToInt(account.getPrivilege()) == 1){
//                      CineplexAdminAccount cineplexAdmin = (CineplexAdminAccount) account;
//                      input[6] = cineplexAdmin.getCineplex().getBranchName();
//                  }
//                  else input[6] = "NA";
//                  writer.writeNext(input);

//              }
//              writer.close();
//          }
//          catch (IOException e){
//              System.out.println("An error occurred");
//          }
//      }
//      // MoviesDB
//      public static void updateMovies(String filePath) throws IOException {
//          File movieFile = new File(filePath);
//          FileWriter outputFile = new FileWriter(movieFile);
//          CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
//          int count=0;
//          try {
//              while (FYPMS.getMovieList().getMovieByIndex(count) != null){
//                  Movie movie = FYPMS.getMovieList().getMovieByIndex(count++);
//                  String[] input = new String[7];
//                  input[0] = Integer.toString(movie.getMovieId());
//                  input[1] = movie.getTitle();
//                  input[2] = movie.getSynopsis();
//                  input[3] = movie.getDirector();
//                  input[4] = movie.getCasts(); //  movie.getCast();
//                  input[5] = Movie.convertMovieStatusToString(movie.getStatus());
//                  String date = movie.getExpiryDate().toString();
//                  input[6] = date.substring(0,10) + " " + date.substring(11);
//                  writer.writeNext(input);
//              }
//              writer.close();
//          }
//          catch (IOException e){
//              System.out.println("An error occurred");
//          }
//      }


//      // ShowDB
//      public static void updateShows(String filePath) throws IOException {
//          File showFile = new File(filePath);
//          FileWriter outputFile = new FileWriter(showFile);
//          CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
//          int count=0;
//          try {
//              while (FYPMS.getCineplexList().getCineplexByIndex(count) != null){
//                  Cineplex cineplex = FYPMS.getCineplexList().getCineplexByIndex(count);
//                  for (Show show: cineplex.getShowList().getShows()){
//                      String[] input = new String[5];
//                      input[0] = Integer.toString(show.getShowId());
//                      String time = show.getShowTime().toString();
//                      input[1] = time.substring(0,10) + " " + time.substring(11);
//                      input[2] = FYPMS.getCineplexList().getCineplexByIndex(count).getBranchName();
//                      input[3] = show.getCinema().getCinemaCode();
//                      input[4] = Integer.toString(show.getMovie().getMovieId());
//                      writer.writeNext(input);
//                  }
//                  count++;
//              }
//              writer.close();
//          }
//          catch (IOException e){
//              System.out.println("An error occurred");
//          }
//      }

//      public static void updateTickets(String filePath) throws IOException {
//          File ticketFile = new File(filePath);
//          FileWriter outputFile = new FileWriter(ticketFile);
//          CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
//          int count=0;
//          try {
//              while (FYPMS.getBookingHistory().getBookingByIndex(count) != null){
//                  Booking booking = FYPMS.getBookingHistory().getBookingByIndex(count);
//                  int ticketCount=0;
//                  while (booking.getTicketByIndex(ticketCount) != null){
//                      MovieTicket ticket = booking.getTicketByIndex(ticketCount);
//                      String[] input = new String[5];
//                      input[0] = Integer.toString(ticket.getShow().getShowId());
//                      input[1] = ticket.getSeatId();
//                      input[2] = Integer.toString(ticket.getTicketID());
//                      input[3] = Double.toString(ticket.getPrice());
//                      input[4] = MovieTicket.convertCustomerAgeToString(ticket.getAge());
//                      writer.writeNext(input);
//                      ticketCount++;
//                  }
//                  count++;
//              }
//              writer.close();
//          }
//          catch (IOException e){
//              System.out.println("An error occurred");
//          }
//      }

//      //ReviewList
//      public static void updateReviewList(String filePath) throws IOException {
//          File reviewListFile = new File(filePath);
//          FileWriter outputFile = new FileWriter(reviewListFile);
//          CSVWriter writer = new CSVWriter(outputFile, ',', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
//          try {
//              int count=0;
//              while (FYPMS.getMovieList().getMovieByIndex(count) != null){
//                  Movie movie = FYPMS.getMovieList().getMovieByIndex(count++);
//                  String[] input = new String[4];
//                  input[0] = Integer.toString(movie.getMovieId());
//                  input[1] = (movie.getReviews().convertRatingsToString() != null) ? movie.getReviews().convertRatingsToString() : "NA";
//                  input[2] = (movie.getReviews().convertDescriptionToString() != null) ? movie.getReviews().convertDescriptionToString() : "NA";
//                  input[3] = Integer.toString(movie.getReviews().showAverageRating());
//                  writer.writeNext(input);
//              }
//              writer.close();
//          }
//          catch (IOException e){
//              System.out.println("An error occurred");
//          }
//      }

//  }
