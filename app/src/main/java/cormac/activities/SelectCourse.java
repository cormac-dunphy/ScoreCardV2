package cormac.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Course;

public class SelectCourse extends AppCompatActivity {

    ListView courseList;
    public DatabaseHelper myDb;
    public String selectedCourseName;
    public String selectedCourseParString;
    public String selectedCourseLocation;
    public int selectedCourseParInt;


    public static HashMap<Integer, String> courseDataHashMap = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course);
        myDb = new DatabaseHelper(this);
        courseList = findViewById(R.id.courseList);
        populateCourseList();
        onAddCoursePressed();
        deleteCourseRow();
        courseRowSelected();
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Are you sure you want to Delete " + selectedCourseName + "?")
                //.setIcon(R.drawable.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code

                        Course course = new Course(selectedCourseName, selectedCourseLocation, selectedCourseParInt);

                        myDb.deleteCourseRow(course);

                        dialog.dismiss();

                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                    }

                })



                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    public void deleteCourseRow()
    {
        ListView lvItems = (ListView) findViewById(R.id.courseList);

        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                selectedCourseName = ((TextView) view.findViewById(R.id.listCourseName)).getText().toString();
                selectedCourseLocation = ((TextView) view.findViewById(R.id.listCourseLocation)).getText().toString();
                selectedCourseParString = ((TextView) view.findViewById(R.id.listCoursePar)).getText().toString();
                selectedCourseParInt = Integer.parseInt(selectedCourseParString);

                AlertDialog diaBox = AskOption();
                diaBox.show();

                return true;
            }
        });
    }

    public void populateCourseList() {
        Cursor courseCursor = myDb.getCourseData();
        // Find ListView to populate
        ListView lvItems = (ListView) findViewById(R.id.courseList);
        // Setup cursor adapter using cursor from last step
        CourseCursorAdapter CourseAdapter = new CourseCursorAdapter(this, courseCursor);
        // Attach cursor adapter to the ListView
        lvItems.setAdapter(CourseAdapter);
/*
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
*/
    }

    public void courseRowSelected()
    {
        ListView lvItems = (ListView) findViewById(R.id.courseList);

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
