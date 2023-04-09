package FYPMS.student;

import java.util.ArrayList;

/**
 * Represents the list of students registered with the department for Final Year Project (FYP) supervision
 */
public class StudentList {
    private final ArrayList<Student> students = new ArrayList<>();

    /**
     * Adds a student to the list of students registered with the department for FYP supervision
     *
     * @param student Student to be added
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * Removes a student from the list of students registered with the department for FYP supervision
     *
     * @param student Student to be removed
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**
     * Prints the list of students registered with the department for FYP supervision
     */
    public void listStudents() {
        int studentCount = 1;
        System.out.println();
        System.out.println("List of Registered Students");
        System.out.println();
        for (Student student : students) {
            System.out.println("============= Student No. " + studentCount++ + " ==============");
            student.printStudentDetails();
            System.out.println();
        }
        System.out.println("===== There are " + (studentCount - 1) + " students registered! =====");
    }

    /**
     * Searches for a student using a keyword inputted by users
     *
     * @param keyword Keyword used to search for a student's name
     */
    public void searchStudentByName(String keyword) {
        int numOfResults = 0;

        System.out.println();
        System.out.println("Search Results for Students named \"" + keyword + "\"");
        System.out.println();

        for (Student student : students) {
            if (student.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("============= Student No. " + ++numOfResults + " ==============");
                student.printStudentDetails();
                System.out.println();
            }
        }
        if (numOfResults == 0) {
            System.out.println("No such student found in the system!");
        } else {
            System.out.println("===== " + numOfResults + " students found! =====");
        }
    }

    /**
     * Prints the list of students who have not been assigned an FYP
     */
    public void listStudentsWithoutProjects() {
        int studentCount = 1;
        System.out.println();
        System.out.println("List of Students who have not been assigned an FYP");
        System.out.println();
        for (Student student : students) {
            if (!student.hasProject()) {
                System.out.println("============= Student No. " + studentCount++ + " ==============");
                student.printStudentDetails();
                System.out.println();
            }
        }
        System.out.println("===== " + (studentCount - 1) + " students have not submitted their FYP proposals! =====");
    }

    /**
     * Returns the list of students in the system
     *
     * @return List of students
     */
    public ArrayList<Student> getStudentList() {
        return students;
    }
}
