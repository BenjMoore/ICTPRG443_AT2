package FileDialog;

public class Teacher implements Comparable
{
    private String teacherName;
    private String TeacherClass;
    private String TeacherRoom;
    private String Date;



    public Teacher(String TeacherName, String teacherClass, String teacherRoom, String date)
    {
        teacherName = TeacherName;
        TeacherClass = teacherClass;
        TeacherRoom = teacherRoom;
        Date = date;

    }

    public String getteacherName() {
        return teacherName;
    }

    public String getclass() {return TeacherClass;}

    public String getRoom() {
        return TeacherRoom;
    }

    public String getDate(){return Date;}

    @Override
    public int compareTo(Object o)
    {
        if (o.getClass() == Teacher.class)
        {
            Teacher other = (Teacher)o;
            return this.teacherName.compareToIgnoreCase(other.teacherName);
        }
        return this.teacherName.compareToIgnoreCase(o.toString());
    }
}
