package cormac.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import models.Course;
import models.Game;
import models.Player;

public class Front9 extends SelectCourse {

    SelectCourse selectCourse = new SelectCourse();
    PlayerNames playerNames = new PlayerNames();

    String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    public static List<Integer> scoreList = new ArrayList<>();
    public static List<Integer> parList = new ArrayList<>();
    public List<String> scores = new ArrayList<>();

    String firstPlayerName, secondPlayerName, thirdPlayerName, fourthPlayerName;
    public String s1p1, s2p1, s3p1, s4p1, s5p1, s6p1, s7p1, s8p1, s9p1;
    public String s1p2, s2p2, s3p2, s4p2, s5p2, s6p2, s7p2, s8p2, s9p2;
    public String s1p3, s2p3, s3p3, s4p3, s5p3, s6p3, s7p3, s8p3, s9p3;
    public String s1p4, s2p4, s3p4, s4p4, s5p4, s6p4, s7p4, s8p4, s9p4;
    public String courseParString;
    public int coursePar;
    public String courseName;

    //database
    public DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDb = new DatabaseHelper(this);
        setContentView(R.layout.activity_front9);
        showPlayerNames();
        getCourseData();
        displayCourseName();
        onFinishGameButtonPressed();
    }

    //when button is pressed get the scores entered and check if any value edit text is null
    public void onFinishGameButtonPressed() {
        Button finishGameButton = (Button) findViewById(R.id.finishGameButton);

        finishGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getScores();

                if (getScores() == false) {
                    Toast.makeText(Front9.this, "Enter Missing Score(s)", Toast.LENGTH_LONG).show();
                } else {
                    calculateScores();
                    Intent finishGame = new Intent(Front9.this, FinalScore.class);
                    startActivity(finishGame);
                }
            }
        });
    }

    //gets entries from edit texts and puts them into an array then iterates through the array
    //pops up a toast if any element in the array is null
    public boolean getScores() {
        EditText score1P1 = (EditText) findViewById(R.id.score1P1);
        EditText score2P1 = (EditText) findViewById(R.id.score2P1);
        EditText score3P1 = (EditText) findViewById(R.id.score3P1);
        EditText score4P1 = (EditText) findViewById(R.id.score4P1);
        EditText score5P1 = (EditText) findViewById(R.id.score5P1);
        EditText score6P1 = (EditText) findViewById(R.id.score6P1);
        EditText score7P1 = (EditText) findViewById(R.id.score7P1);
        EditText score8P1 = (EditText) findViewById(R.id.score8P1);
        EditText score9P1 = (EditText) findViewById(R.id.score9P1);

        s1p1 = score1P1.getText().toString();
        s2p1 = score2P1.getText().toString();
        s3p1 = score3P1.getText().toString();
        s4p1 = score4P1.getText().toString();
        s5p1 = score5P1.getText().toString();
        s6p1 = score6P1.getText().toString();
        s7p1 = score7P1.getText().toString();
        s8p1 = score8P1.getText().toString();
        s9p1 = score9P1.getText().toString();

        EditText score1P2 = (EditText) findViewById(R.id.score1P2);
        EditText score2P2 = (EditText) findViewById(R.id.score2P2);
        EditText score3P2 = (EditText) findViewById(R.id.score3P2);
        EditText score4P2 = (EditText) findViewById(R.id.score4P2);
        EditText score5P2 = (EditText) findViewById(R.id.score5P2);
        EditText score6P2 = (EditText) findViewById(R.id.score6P2);
        EditText score7P2 = (EditText) findViewById(R.id.score7P2);
        EditText score8P2 = (EditText) findViewById(R.id.score8P2);
        EditText score9P2 = (EditText) findViewById(R.id.score9P2);

        s1p2 = score1P2.getText().toString();
        s2p2 = score2P2.getText().toString();
        s3p2 = score3P2.getText().toString();
        s4p2 = score4P2.getText().toString();
        s5p2 = score5P2.getText().toString();
        s6p2 = score6P2.getText().toString();
        s7p2 = score7P2.getText().toString();
        s8p2 = score8P2.getText().toString();
        s9p2 = score9P2.getText().toString();

        EditText score1P3 = (EditText) findViewById(R.id.score1P3);
        EditText score2P3 = (EditText) findViewById(R.id.score2P3);
        EditText score3P3 = (EditText) findViewById(R.id.score3P3);
        EditText score4P3 = (EditText) findViewById(R.id.score4P3);
        EditText score5P3 = (EditText) findViewById(R.id.score5P3);
        EditText score6P3 = (EditText) findViewById(R.id.score6P3);
        EditText score7P3 = (EditText) findViewById(R.id.score7P3);
        EditText score8P3 = (EditText) findViewById(R.id.score8P3);
        EditText score9P3 = (EditText) findViewById(R.id.score9P3);

        s1p3 = score1P3.getText().toString();
        s2p3 = score2P3.getText().toString();
        s3p3 = score3P3.getText().toString();
        s4p3 = score4P3.getText().toString();
        s5p3 = score5P3.getText().toString();
        s6p3 = score6P3.getText().toString();
        s7p3 = score7P3.getText().toString();
        s8p3 = score8P3.getText().toString();
        s9p3 = score9P3.getText().toString();

        EditText score1P4 = (EditText) findViewById(R.id.score1P4);
        EditText score2P4 = (EditText) findViewById(R.id.score2P4);
        EditText score3P4 = (EditText) findViewById(R.id.score3P4);
        EditText score4P4 = (EditText) findViewById(R.id.score4P4);
        EditText score5P4 = (EditText) findViewById(R.id.score5P4);
        EditText score6P4 = (EditText) findViewById(R.id.score6P4);
        EditText score7P4 = (EditText) findViewById(R.id.score7P4);
        EditText score8P4 = (EditText) findViewById(R.id.score8P4);
        EditText score9P4 = (EditText) findViewById(R.id.score9P4);

        s1p4 = score1P4.getText().toString();
        s2p4 = score2P4.getText().toString();
        s3p4 = score3P4.getText().toString();
        s4p4 = score4P4.getText().toString();
        s5p4 = score5P4.getText().toString();
        s6p4 = score6P4.getText().toString();
        s7p4 = score7P4.getText().toString();
        s8p4 = score8P4.getText().toString();
        s9p4 = score9P4.getText().toString();

        scores.add(s1p1);
        scores.add(s2p1);
        scores.add(s3p1);
        scores.add(s4p1);
        scores.add(s5p1);
        scores.add(s6p1);
        scores.add(s7p1);
        scores.add(s8p1);
        scores.add(s9p1);

        scores.add(s1p2);
        scores.add(s2p2);
        scores.add(s3p2);
        scores.add(s4p2);
        scores.add(s5p2);
        scores.add(s6p2);
        scores.add(s7p2);
        scores.add(s8p2);
        scores.add(s9p2);

        scores.add(s1p3);
        scores.add(s2p3);
        scores.add(s3p3);
        scores.add(s4p3);
        scores.add(s5p3);
        scores.add(s6p3);
        scores.add(s7p3);
        scores.add(s8p3);
        scores.add(s9p3);

        scores.add(s1p4);
        scores.add(s2p4);
        scores.add(s3p4);
        scores.add(s4p4);
        scores.add(s5p4);
        scores.add(s6p4);
        scores.add(s7p4);
        scores.add(s8p4);
        scores.add(s9p4);

        for (int i = 0; i < scores.size(); i++) {
            if (String.valueOf(scores.get(i)).trim().length() <= 0) {
                scores.clear();
                return false;
            }
        }
        return true;
    }


    //gets the int value of the entered strings and adds each players score
    public void calculateScores() {
        int n1p1, n2p1, n3p1, n4p1, n5p1, n6p1, n7p1, n8p1, n9p1;

        n1p1 = Integer.parseInt(s1p1);
        n2p1 = Integer.parseInt(s2p1);
        n3p1 = Integer.parseInt(s3p1);
        n4p1 = Integer.parseInt(s4p1);
        n5p1 = Integer.parseInt(s5p1);
        n6p1 = Integer.parseInt(s6p1);
        n7p1 = Integer.parseInt(s7p1);
        n8p1 = Integer.parseInt(s8p1);
        n9p1 = Integer.parseInt(s9p1);

        int front9ScoreP1 = n1p1 + n2p1 + n3p1 + n4p1 + n5p1 + n6p1 + n7p1 + n8p1 + n9p1;

        scoreList.add(front9ScoreP1);

        int n1p2, n2p2, n3p2, n4p2, n5p2, n6p2, n7p2, n8p2, n9p2;

        n1p2 = Integer.parseInt(s1p2);
        n2p2 = Integer.parseInt(s2p2);
        n3p2 = Integer.parseInt(s3p2);
        n4p2 = Integer.parseInt(s4p2);
        n5p2 = Integer.parseInt(s5p2);
        n6p2 = Integer.parseInt(s6p2);
        n7p2 = Integer.parseInt(s7p2);
        n8p2 = Integer.parseInt(s8p2);
        n9p2 = Integer.parseInt(s9p2);

        int front9ScoreP2 = n1p2 + n2p2 + n3p2 + n4p2 + n5p2 + n6p2 + n7p2 + n8p2 + n9p2;

        scoreList.add(front9ScoreP2);

        int n1p3, n2p3, n3p3, n4p3, n5p3, n6p3, n7p3, n8p3, n9p3;

        n1p3 = Integer.parseInt(s1p3);
        n2p3 = Integer.parseInt(s2p3);
        n3p3 = Integer.parseInt(s3p3);
        n4p3 = Integer.parseInt(s4p3);
        n5p3 = Integer.parseInt(s5p3);
        n6p3 = Integer.parseInt(s6p3);
        n7p3 = Integer.parseInt(s7p3);
        n8p3 = Integer.parseInt(s8p3);
        n9p3 = Integer.parseInt(s9p3);

        int front9ScoreP3 = n1p3 + n2p3 + n3p3 + n4p3 + n5p3 + n6p3 + n7p3 + n8p3 + n9p3;

        scoreList.add(front9ScoreP3);

        int n1p4, n2p4, n3p4, n4p4, n5p4, n6p4, n7p4, n8p4, n9p4;

        n1p4 = Integer.parseInt(s1p4);
        n2p4 = Integer.parseInt(s2p4);
        n3p4 = Integer.parseInt(s3p4);
        n4p4 = Integer.parseInt(s4p4);
        n5p4 = Integer.parseInt(s5p4);
        n6p4 = Integer.parseInt(s6p4);
        n7p4 = Integer.parseInt(s7p4);
        n8p4 = Integer.parseInt(s8p4);
        n9p4 = Integer.parseInt(s9p4);

        int front9ScoreP4 = n1p4 + n2p4 + n3p4 + n4p4 + n5p4 + n6p4 + n7p4 + n8p4 + n9p4;

        scoreList.add(front9ScoreP4);

        Log.i("scorecard", "about to calculate pars");
        //get pars
        int playerPar1 = front9ScoreP1 - coursePar;
        int playerPar2 = front9ScoreP2 - coursePar;
        int playerPar3 = front9ScoreP3 - coursePar;
        int playerPar4 = front9ScoreP4 - coursePar;

        parList.add(playerPar1);
        parList.add(playerPar2);
        parList.add(playerPar3);
        parList.add(playerPar4);

        Log.i("scorecard", "playerPar1 " + String.valueOf(playerPar1));

        //database stuff
        Log.i("scorecard", "course name " + courseName);
        Game game = new Game(date, courseName,
                firstPlayerName, secondPlayerName, thirdPlayerName, fourthPlayerName,
                front9ScoreP1, front9ScoreP2, front9ScoreP3, front9ScoreP4,
                playerPar1, playerPar2, playerPar3, playerPar4);
        boolean isInserted1;
        Log.i("scorecard", "before going into myDb.insertGamedata");
        isInserted1 = myDb.insertGameData(game);

        if (isInserted1)
            Toast.makeText(Front9.this, "Game Added to Database", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(Front9.this, "Error Saving Game to Database", Toast.LENGTH_LONG).show();
    }


    //shows player names from previous activity
    public void showPlayerNames() {
        TextView player1Name = (TextView) findViewById(R.id.player1);
        TextView player2Name = (TextView) findViewById(R.id.player2);
        TextView player3Name = (TextView) findViewById(R.id.player3);
        TextView player4Name = (TextView) findViewById(R.id.player4);

        Player player1 = playerNames.playerHashMap.get(1);
        Player player2 = playerNames.playerHashMap.get(2);
        Player player3 = playerNames.playerHashMap.get(3);
        Player player4 = playerNames.playerHashMap.get(4);

        firstPlayerName = player1.firstName;
        secondPlayerName = player2.firstName;
        thirdPlayerName = player3.firstName;
        fourthPlayerName = player4.firstName;

        player1Name.setText(firstPlayerName);
        player2Name.setText(secondPlayerName);
        player3Name.setText(thirdPlayerName);
        player4Name.setText(fourthPlayerName);
    }

    //gets course data from HashMap
    public void getCourseData() {
        courseName = selectCourse.courseDataHashMap.get(1);
        Log.i("scorecard", "in getCourseData courseName: " + courseName);
        courseParString = selectCourse.courseDataHashMap.get(2);
        Log.i("scorecard", "in getCourseData courseParString: " + courseParString);
        coursePar = Integer.parseInt(courseParString);
        Log.i("scorecard", "in getCourseData coursePar: " + String.valueOf(coursePar));
    }

    public void displayCourseName() {
        TextView front9Title = (TextView) findViewById(R.id.front9Title);

        front9Title.setText(courseName);
    }

}