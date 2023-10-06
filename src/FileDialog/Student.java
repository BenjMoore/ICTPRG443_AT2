package FileDialog;

public class Student implements Comparable
{
    private String studentName;
    private int xPos;
    private int yPos;

    public Student(String name, int y, int x)
    {
        studentName = name;
        xPos = x;
        yPos = y;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    @Override
    public int compareTo(Object o)
    {
        if (o.getClass() == Student.class)
        {
            Student other = (Student)o;
            return this.studentName.compareToIgnoreCase(other.studentName);
        }
        return this.studentName.compareToIgnoreCase(o.toString());
    }
}
