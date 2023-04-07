package moblima.cineplex.cinema;

/**
 * Represents a Cinema hall within a Cineplex branch
 */
public class Cinema {

    private static int currentCode=1;
    private String cinemaCode;
    private CinemaClass classLevel; // enum

    /**
     * Creates a cinema object in a cineplex
     * @param classLevel Class of the cinema hall
     */
    public Cinema(CinemaClass classLevel) {
        this.classLevel = classLevel;
    }

    /**
     * Generates a cinema code based on the cineplex code
     * @param cineplexCode Code of the cineplex in which the cinema belongs to
     */
    public void generateCinemaCode(String cineplexCode) {
        this.cinemaCode = cineplexCode.toUpperCase() + currentCode++;
    }

    /**
     * Prints the details of this instance of the cinema
     */
    public void printDetails(){
        System.out.println("Cinema Code: " + cinemaCode);
        System.out.println("Cinema Class: " + classLevel);
        System.out.println();
    }
    public CinemaClass getClassLevel(){return classLevel;}
    public String getCinemaCode(){
        return cinemaCode;
    }

    /**
     * Helper function to convert the cinema code for CSV storage
     * @return
     */
    public String convertCinemaCodeToCurrentCode(){
        String output = cinemaCode.substring(2);
        return output;

    }

    /**
     * Converts the cinema class in string form to CinemaClass enum
     * @param classLevel Class of cinema in string form
     * @return CinemaClass enum
     */
    public static CinemaClass convertToCinemaClass(String classLevel){
        switch(classLevel.toLowerCase()){
            case "normal":
                return CinemaClass.NORMAL;
            case "gold":
                return CinemaClass.GOLD;
            case "platinum":
                return CinemaClass.PLATINUM;
        }
        return null;
    }

    /**
     * Helper function to convert CinemaClass enum to string, for CSV storage
     * @param classLevel CinemaClass enum
     * @return Class of cinema in string form
     */
    public static String convertCinemaClassToString(CinemaClass classLevel){
        if (classLevel == CinemaClass.GOLD) return "GOLD";
        else if (classLevel == CinemaClass.NORMAL) return "NORMAL";
        else return "PLATINUM";
    }

}
