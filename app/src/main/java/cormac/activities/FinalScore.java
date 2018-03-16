package cormac.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import models.Player;

public class FinalScore extends AppCompatActivity {

    PlayerNames playerNames = new PlayerNames();
    Front9 front9 = new Front9();
    String firstPlayerName, secondPlayerName, thirdPlayerName, fourthPlayerName;
    int firstPlayerScore, secondPlayerScore, thirdPlayerScore, fourthPlayerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        showPlayerNames();
        showScores();
        onNewGameButtonPressed();
    }
/*
    public void showPlayerNames()
    {
        TextView finalScoreP1Name = (TextView) findViewById(R.id.finalScoreP1Name);
        TextView finalScoreP2Name = (TextView) findViewById(R.id.finalScoreP2Name);
        TextView finalScoreP3Name = (TextView) findViewById(R.id.finalScoreP3Name);
        TextView finalScoreP4Name = (TextView) findViewById(R.id.finalScoreP4Name);

        firstPlayerName = playerNames.PlayerMap.get("1");
        secondPlayerName = playerNames.PlayerMap.get("2");
        thirdPlayerName = playerNames.PlayerMap.get("3");
        fourthPlayerName = playerNames.PlayerMap.get("4");

        finalScoreP1Name.setText(firstPlayerName);
        finalScoreP2Name.setText(secondPlayerName);
        finalScoreP3Name.setText(thirdPlayerName);
        finalScoreP4Name.setText(fourthPlayerName);
    }
*/

//shows each players score
    public void showScores()
    {
        TextView finalScoreP1 = (TextView) findViewById(R.id.finalScoreP1);
        TextView finalScoreP2 = (TextView) findViewById(R.id.finalScoreP2);
        TextView finalScoreP3 = (TextView) findViewById(R.id.finalScoreP3);
        TextView finalScoreP4 = (TextView) findViewById(R.id.finalScoreP4);

        firstPlayerScore = front9.scoreList.get(0);
        secondPlayerScore = front9.scoreList.get(1);
        thirdPlayerScore = front9.scoreList.get(2);
        fourthPlayerScore = front9.scoreList.get(3);

        finalScoreP1.setText(String.valueOf(firstPlayerScore));
        finalScoreP2.setText(String.valueOf(secondPlayerScore));
        finalScoreP3.setText(String.valueOf(thirdPlayerScore));
        finalScoreP4.setText(String.valueOf(fourthPlayerScore));
    }

    //when new game starts clear the data from all hashmaps/arrays
    public void onNewGameButtonPressed()
    {
        Button newGameButton = (Button) findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerNames.playerHashMap.clear();
                front9.scoreList.clear();
                front9.scores.clear();
                Intent newGameButtonPressed = new Intent(FinalScore.this, PlayerNames.class);
                startActivity(newGameButtonPressed);
            }
        });
    }
//show players names
    public void showPlayerNames()
    {
        TextView finalScoreP1Name = (TextView) findViewById(R.id.finalScoreP1Name);
        TextView finalScoreP2Name = (TextView) findViewById(R.id.finalScoreP2Name);
        TextView finalScoreP3Name = (TextView) findViewById(R.id.finalScoreP3Name);
        TextView finalScoreP4Name = (TextView) findViewById(R.id.finalScoreP4Name);

        Player player1 = playerNames.playerHashMap.get(1);
        Player player2 = playerNames.playerHashMap.get(2);
        Player player3 = playerNames.playerHashMap.get(3);
        Player player4 = playerNames.playerHashMap.get(4);

        firstPlayerName = player1.firstName;
        secondPlayerName = player2.firstName;
        thirdPlayerName = player3.firstName;
        fourthPlayerName = player4.firstName;

        finalScoreP1Name.setText(firstPlayerName);
        finalScoreP2Name.setText(secondPlayerName);
        finalScoreP3Name.setText(thirdPlayerName);
        finalScoreP4Name.setText(fourthPlayerName);
    }
}