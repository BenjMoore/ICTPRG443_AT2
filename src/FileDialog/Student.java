package FileDialog;

/**
 * The Student class represents a student with a name and a position (x, y).
 * This class implements the Comparable interface to allow for sorting by student name.
 */
public class Student implements Comparable {
    // The name of the student
    private String studentName;
    // The x-position of the student (could represent a seat position, etc.)
    private int xPos;
    // The y-position of the student (could represent a seat position, etc.)
    private int yPos;

    /**
     * Constructor to initialize a Student object with a name, y-position, and x-position.
     *
     * @param name The name of the student.
     * @param y    The y-position of the student.
     * @param x    The x-position of the student.
     */
    public Student(String name, int y, int x) {
        // Initialize the student's name
        studentName = name;
        // Initialize the x-position of the student
        xPos = x;
        // Initialize the y-position of the student
        yPos = y;
    }

    /**
     * Gets the name of the student.
     *
     * @return The student's name.
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Gets the x-position of the student.
     *
     * @return The student's x-position.
     */
    public int getxPos() {
        return xPos;
    }

    /**
     * Gets the y-position of the student.
     *
     * @return The student's y-position.
     */
    public int getyPos() {
        return yPos;
    }

    /**
     * Compares this student object to another object for ordering.
     * If the other object is a Student, compares by student name (case-insensitive).
     * Otherwise, compares by converting the other object to a string.
     *
     * @param o The object to compare this student to.
     * @return A negative integer, zero, or a positive integer if this student is less than,
     * equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object o) {
        // Check if the object is an instance of Student
        if (o.getClass() == Student.class) {
            // Cast the object to Student for comparison
            Student other = (Student) o;
            // Compare student names ignoring case
            return this.studentName.compareToIgnoreCase(other.studentName);
        }
        // If not a Student, compare using the string representation of the object
        return this.studentName.compareToIgnoreCase(o.toString());
    }
}
