package FileDialog;

/**
 * The Teacher class represents a teacher with a name, class, room, and date.
 * This class implements the Comparable interface to allow for sorting by teacher name.
 */
public class Teacher implements Comparable {
    // The name of the teacher
    private String teacherName;
    // The class taught by the teacher
    private String TeacherClass;
    // The room where the teacher conducts classes
    private String TeacherRoom;
    // The date related to the teacher's schedule or record
    private String Date;

    /**
     * Constructor to initialize a Teacher object with name, class, room, and date.
     *
     * @param TeacherName The name of the teacher.
     * @param teacherClass The class taught by the teacher.
     * @param teacherRoom The room where the teacher conducts classes.
     * @param date The date related to the teacher's schedule or record.
     */
    public Teacher(String TeacherName, String teacherClass, String teacherRoom, String date) {
        // Initialize the teacher's name
        teacherName = TeacherName;
        // Initialize the class taught by the teacher
        TeacherClass = teacherClass;
        // Initialize the room where the teacher conducts classes
        TeacherRoom = teacherRoom;
        // Initialize the date related to the teacher's schedule or record
        Date = date;
    }

    /**
     * Gets the name of the teacher.
     *
     * @return The teacher's name.
     */
    public String getteacherName() {
        return teacherName;
    }

    /**
     * Gets the class taught by the teacher.
     *
     * @return The teacher's class.
     */
    public String getclass() {
        return TeacherClass;
    }

    /**
     * Gets the room where the teacher conducts classes.
     *
     * @return The teacher's room.
     */
    public String getRoom() {
        return TeacherRoom;
    }

    /**
     * Gets the date related to the teacher's schedule or record.
     *
     * @return The date.
     */
    public String getDate() {
        return Date;
    }

    /**
     * Compares this teacher object to another object for ordering.
     * If the other object is a Teacher, compares by teacher name (case-insensitive).
     * Otherwise, compares by converting the other object to a string.
     *
     * @param o The object to compare this teacher to.
     * @return A negative integer, zero, or a positive integer if this teacher is less than,
     * equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object o) {
        // Check if the object is an instance of Teacher
        if (o.getClass() == Teacher.class) {
            // Cast the object to Teacher for comparison
            Teacher other = (Teacher) o;
            // Compare teacher names ignoring case
            return this.teacherName.compareToIgnoreCase(other.teacherName);
        }
        // If not a Teacher, compare using the string representation of the object
        return this.teacherName.compareToIgnoreCase(o.toString());
    }
}
