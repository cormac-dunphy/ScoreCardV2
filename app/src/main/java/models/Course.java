package models;

/**
 * Created by Cormac on 05/04/2018.
 */

public class Course {

    public String courseName;
    public String courseLocation;
    public int coursePar;

    public Course(String courseName, String courseLocation, int coursePar)
    {
        this.courseName = courseName;
        this.courseLocation = courseLocation;
        this.coursePar = coursePar;
    }

}
