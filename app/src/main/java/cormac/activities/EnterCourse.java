package cormac.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Course;
import models.Player;

public class EnterCourse extends AppCompatActivity {

    public static HashMap<Integer, Course> courseHashMap = new HashMap<>();
    public String courseName, courseLocation, courseParText;
    public int coursePar;

    public List<String> courseData = new ArrayList<>();
    Button btnviewAll;

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_course);
        myDb = new DatabaseHelper(this);
        //btnviewAll = (Button)findViewById(R.id.ViewCoursesButton);
        onAddCoursePressed();
    }

    public void onAddCoursePressed()
    {
        //Button addCourseButton = (Button) findViewById(R.id.addCourseButton);
        Button addCourseButton = (Button) findViewById(R.id.addCourseToDatabase);

        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newCourse();
            }
        });
    }

    public void newCourse()
    {
        EditText courseNameEditText = (EditText) findViewById(R.id.courseNameEditText);
        EditText courseLocationEditText = (EditText) findViewById(R.id.courseLocationEditText);
        EditText courseParEditText = (EditText) findViewById(R.id.courseParEditText);

        courseName = courseNameEditText.getText().toString();
        courseLocation = courseLocationEditText.getText().toString();
        courseParText = courseParEditText.getText().toString();
        coursePar = Integer.parseInt(courseParText);

        Log.i("scorecard", "Course Par Text: " + courseParText);
        Log.i("scorecard", "Course Par: " + String.valueOf(coursePar));

        //checking if all names are entered and showing a toast if is not entered
        if (courseName.trim().length() <= 0) {
            Toast.makeText(EnterCourse.this, "Enter Missing Course Name", Toast.LENGTH_LONG).show();
        } else if (courseLocation.trim().length() <= 0) {
            Toast.makeText(EnterCourse.this, "Enter Missing Course Location", Toast.LENGTH_LONG).show();
        } else if (courseParText.trim().length() <= 0) {
            Toast.makeText(EnterCourse.this, "Enter Missing Course Par", Toast.LENGTH_LONG).show();
        } else {
            Course course = new Course(courseName, courseLocation, coursePar);

            //putting into hashmap
            int id = 1;
            courseHashMap.put(id, course);

            Log.i("scorecard", "just before calling insertcoursedata Course Par: " + String.valueOf(course.coursePar));
            boolean isInserted1 = myDb.insertCourseData(course);

            if (isInserted1 == true)
                Toast.makeText(EnterCourse.this, "Course Added to Database", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(EnterCourse.this, "Error Saving Course to Database", Toast.LENGTH_LONG).show();
        }

        Intent toFront9 = new Intent(EnterCourse.this, SelectCourse.class);
        startActivity(toFront9);

    }


}
