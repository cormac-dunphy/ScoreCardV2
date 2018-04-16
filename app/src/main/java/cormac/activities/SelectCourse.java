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
        Log.i("scorecard", "onCreate: SelectCourse 1");
        setContentView(R.layout.activity_select_course);
        Log.i("scorecard", "onCreate: SelectCourse 2");
        myDb = new DatabaseHelper(this);
        Log.i("scorecard", "onCreate: SelectCourse 3");
        courseList = findViewById(R.id.courseList);
        Log.i("scorecard", "onCreate: SelectCourse 4");
        populateCourseList();
        onAddCoursePressed();
    }

    public void populateCourseList()
    {
        Log.i("scorecard", "populateCourseList");
        Cursor courseCursor = myDb.getCourseData();
        Log.i("scorecard", "courseCursor retrieved");
// Find ListView to populate
        ListView lvItems = (ListView) findViewById(R.id.courseList);
        Log.i("scorecard", "found listview id");
// Setup cursor adapter using cursor from last step
        CourseCursorAdapter CourseAdapter = new CourseCursorAdapter(this, courseCursor);
        Log.i("scorecard", "after course cursor adapter bind");
// Attach cursor adapter to the ListView
        lvItems.setAdapter(CourseAdapter);
        Log.i("scorecard", "after course cursor adapter bind");

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectedCourseName = ((TextView) view.findViewById(R.id.listCourseName)).getText().toString();
                selectedCourseParString = ((TextView) view.findViewById(R.id.listCoursePar)).getText().toString();

                courseDataHashMap.put(1, selectedCourseName);
                Log.i("scorecard", "onItemClick: selectedCourseName" + selectedCourseName);
                courseDataHashMap.put(2, selectedCourseParString);
                Log.i("scorecard", "onItemClick: selectedCourseParString" + selectedCourseParString);

                Log.i("scorecard", "from hashmap selectedCourseName: " + String.valueOf(courseDataHashMap.get(1)));
                Log.i("scorecard", "from hashmap selectedCourseParString: " + String.valueOf(courseDataHashMap.get(2)));

                Toast.makeText(SelectCourse.this, selectedCourseName,Toast.LENGTH_LONG).show();

                Intent toFront9 = new Intent(SelectCourse.this, Front9.class);
                startActivity(toFront9);
            }
        });

    }

    public void onAddCoursePressed()
    {
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
