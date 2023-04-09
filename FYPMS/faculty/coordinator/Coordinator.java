package FYPMS.faculty.coordinator;

/**
 * Represents a Coordinator for Final Year Projects
 */
public class Coordinator {

    private final String name;
    private final String email;

    /**
     * Creates a Coordinator object for Final Year Projects with a Maximum of 2 Projects
     *
     * @param name  Name of the Coordinator
     * @param email Email of the Coordinator
     */
    public Coordinator(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Prints the details of this instance of the Coordinator
     */
    public void printDetails() {
        System.out.println("Coordinator name: " + name);
        System.out.println("Email: " + email);
        System.out.println();
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}