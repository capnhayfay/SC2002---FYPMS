package moblima.movie;

/**
 * Represents the status of movies
 */
public enum MovieStatus {

    COMING_SOON("COMING SOON"),
    PREVIEW("PREVIEW"),
    NOW_SHOWING("NOW SHOWING"),
    END_OF_SHOWING("END OF SHOWING");

    private final String toString;
    private MovieStatus(String toString) {
        this.toString = toString;
    }
    public  static MovieStatus intToEnum(int num){
        switch(num){
            case 1:
                return COMING_SOON;
            case 2:
                return PREVIEW;
            case 3:
                return NOW_SHOWING;

            default:
                return END_OF_SHOWING;
        }

    }

}
