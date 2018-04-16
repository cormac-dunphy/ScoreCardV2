package cormac.activities;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Cormac on 14/04/2018.
 */

public class CourseCursorAdapter extends CursorAdapter {
    public CourseCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.course_list_layout, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView listCourseName = (TextView) view.findViewById(R.id.listCourseName);
        TextView listCourseLocation = (TextView) view.findViewById(R.id.listCourseLocation);
        TextView listCoursePar = (TextView) view.findViewById(R.id.listCoursePar);
        // Extract properties from cursor
        String courseName = cursor.getString(cursor.getColumnIndexOrThrow("coursename"));
        String courseLocation = cursor.getString(cursor.getColumnIndexOrThrow("courselocation"));
        String coursePar = cursor.getString(cursor.getColumnIndexOrThrow("coursepar"));
        // Populate fields with extracted properties"
        listCourseName.setText(courseName);
        listCourseLocation.setText(courseLocation);
        listCoursePar.setText(coursePar);
    }
}
