package cormac.activities;

import android.app.UiAutomation;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.Player;

import static cormac.activities.DatabaseHelper.PLAYER_NAME;

public class PlayerNames extends Base {

    //Setting the variables to be used in the class
    public String p1Name, p2Name, p3Name, p4Name;
    public static HashMap<Integer, Player> playerHashMap = new HashMap<>();
    public List<String> playerList = new ArrayList<>();
    public String firstName;

    //database
    public DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_names);
        //Toast.makeText(this, "Before Database Helper",Toast.LENGTH_LONG).show();
        myDb = new DatabaseHelper(this);
        Log.i("scorecard", "onCreate: in PlayerNames");
        //Toast.makeText(this, "After Database Helper",Toast.LENGTH_LONG).show();
        onButtonPressed();
    }


    public void onButtonPressed() {
        Button startGameButton = (Button) findViewById(R.id.startGameButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPlayer();
            }
        });
    }

    //getting the players names from the edit texts and assigning them variables and adding them to array
    //then iterate through array and put players in hashmap with an id
    public void newPlayer() {

        EditText player1Name = (EditText) findViewById(R.id.player1Name);
        EditText player2Name = (EditText) findViewById(R.id.player2Name);
        EditText player3Name = (EditText) findViewById(R.id.player3Name);
        EditText player4Name = (EditText) findViewById(R.id.player4Name);

        p1Name = player1Name.getText().toString();
        p2Name = player2Name.getText().toString();
        p3Name = player3Name.getText().toString();
        p4Name = player4Name.getText().toString();

        //checking if all names are entered and showing a toast if is not entered
        if (p1Name.trim().length() <= 0) {
            Toast.makeText(PlayerNames.this, "Enter Missing Name(s)", Toast.LENGTH_LONG).show();
        } else if (p2Name.trim().length() <= 0) {
            Toast.makeText(PlayerNames.this, "Enter Missing Name(s)", Toast.LENGTH_LONG).show();
        } else if (p3Name.trim().length() <= 0) {
            Toast.makeText(PlayerNames.this, "Enter Missing Name(s)", Toast.LENGTH_LONG).show();
        } else if (p4Name.trim().length() <= 0) {
            Toast.makeText(PlayerNames.this, "Enter Missing Name(s)", Toast.LENGTH_LONG).show();
        } else {
            //adding names to array list
            playerList.add(p1Name);
            playerList.add(p2Name);
            playerList.add(p3Name);
            playerList.add(p4Name);
            //iterating through array list and creating players
            for (int i = 0; i < playerList.size(); i++) {
                firstName = String.valueOf(playerList.get(i)).trim();
                int id = playerHashMap.size() + 1;
                Player player = new Player(firstName);
                playerHashMap.put(id, player);
            }

// Insert Player Names to the Database table Player_Names
            boolean isInserted1 = myDb.insertPlayerData(p1Name);
            boolean isInserted2 = myDb.insertPlayerData(p2Name);
            boolean isInserted3 = myDb.insertPlayerData(p3Name);
            boolean isInserted4 = myDb.insertPlayerData(p4Name);

            if (isInserted1 == true && isInserted2 == true && isInserted3 == true && isInserted4 == true)
                Toast.makeText(PlayerNames.this, "Player Names Added to Database", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(PlayerNames.this, "Error Saving Player Names to Database", Toast.LENGTH_LONG).show();

// End of inserting Player Names to Database
        }
            //start new activity
            Intent startGame = new Intent(PlayerNames.this, EnterCourse.class);
          //  Intent startGame = new Intent(PlayerNames.this, Front9.class);
            startActivity(startGame);
        }

    }