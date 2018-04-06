package cormac.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Start extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        myDb = new DatabaseHelper(this);
        onPlayButtonPressed();
        viewGames();
        viewCourses();
    }

    public void onPlayButtonPressed()
    {
        Button playButton = (Button) findViewById(R.id.playButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play = new Intent(Start.this, PlayerNames.class);

                startActivity(play);
            }
        });
    }

    //viewing game data
    public void viewGames() {
        Button viewGamesButton = (Button) findViewById(R.id.viewGamesButton);
        viewGamesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.getGameData();
                        if(res.getCount() == 0) {
                            //show no results message
                            showMessageGame("Error", "No Games Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            Log.i("scorecard", "Course " + res.getString(2));
                            buffer.append("Date: "+ res.getString(1)+ " Course: "+res.getString(2)+"\n");
                            buffer.append(res.getString(3)+ " Score: "+res.getInt(7)+" Par: "+res.getInt(11)+"\n");
                            buffer.append(res.getString(4)+ " Score: "+res.getInt(8)+" Par: "+res.getInt(12)+"\n");
                            buffer.append(res.getString(5)+ " Score: "+res.getInt(9)+" Par: "+res.getInt(13)+"\n");
                            buffer.append(res.getString(6)+ " Score: "+res.getInt(10)+" Par: "+res.getInt(14)+"\n\n");
                        }
                        //Show the game data
                        showMessageGame("Previous Games", buffer.toString());
                    }
                }
        );
    }

    public void showMessageGame(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void viewCourses() {
        Button viewCoursesButton = (Button) findViewById(R.id.viewCoursesButton);
        viewCoursesButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDb.getCourseData();
                        if(res.getCount() == 0) {
                            //show no results message
                            showMessageCourse("Error", "No Courses Found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Course Name : "+ res.getString(1)+"\n");
                            buffer.append("Course Location : "+ res.getString(2)+"\n");
                            buffer.append("Course Par : "+ String.valueOf(res.getInt(3))+"\n\n");
                        }
                        //Show the course data
                        showMessageCourse("Courses", buffer.toString());
                    }
                }
        );
    }

    public void showMessageCourse(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
