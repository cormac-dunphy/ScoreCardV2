package cormac.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectCourse extends AppCompatActivity {

    ListView courseList;
    public DatabaseHelper myDb;
    public String selectedCourseName;
    public String selectedCourseParString;

    public static HashMap<Integer, String> courseDataHashMap = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);
        myDb = new DatabaseHelper(this);
        courseList = findViewById(R.id.courseList);
        populateCourseList();
        onAddCoursePressed();
    }

    public void populateCourseList() {
        Cursor courseCursor = myDb.getCourseData();
        // Find ListView to populate
        ListView lvItems = (ListView) findViewById(R.id.courseList);
        // Setup cursor adapter using cursor from last step
        CourseCursorAdapter CourseAdapter = new CourseCursorAdapter(this, courseCursor);
        // Attach cursor adapter to the ListView
        lvItems.setAdapter(CourseAdapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCourseName = ((TextView) view.findViewById(R.id.listCourseName)).getText().toString();
                selectedCourseParString = ((TextView) view.findViewById(R.id.listCoursePar)).getText().toString();

                courseDataHashMap.put(1, selectedCourseName);
                courseDataHashMap.put(2, selectedCourseParString);

                Intent toFront9 = new Intent(SelectCourse.this, Front9.class);
                startActivity(toFront9);
            }
        });

    }

    public void onAddCoursePressed() {
        Button toAddCourseButton = (Button) findViewById(R.id.toAddCourseButton);

        toAddCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toAddcourse = new Intent(SelectCourse.this, EnterCourse.class);
                startActivity(toAddcourse);
            }
        });
    }
}
