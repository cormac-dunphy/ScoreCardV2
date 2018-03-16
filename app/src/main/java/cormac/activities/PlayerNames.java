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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import models.Player;

public class PlayerNames extends Base {
    Base base = new Base();
    public String p1Name, p2Name, p3Name, p4Name;
    public static HashMap<String, String> PlayerMap = new HashMap<String, String>();

    //trying to use player model in hashmap
    public static HashMap<Integer, Player> playerHashMap = new HashMap<>();
    public List<String> playerList = new ArrayList<>();
    public String firstName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_names);
        onButtonPressed();
    }

    public void newPlayers() {
        EditText player1Name = (EditText) findViewById(R.id.player1Name);
        EditText player2Name = (EditText) findViewById(R.id.player2Name);
        EditText player3Name = (EditText) findViewById(R.id.player3Name);
        EditText player4Name = (EditText) findViewById(R.id.player4Name);

        p1Name = player1Name.getText().toString();
        p2Name = player2Name.getText().toString();
        p3Name = player3Name.getText().toString();
        p4Name = player4Name.getText().toString();


        PlayerMap.put("1", p1Name);
        PlayerMap.put("2", p2Name);
        PlayerMap.put("3", p3Name);
        PlayerMap.put("4", p4Name);

        playerList.add(p1Name);
        playerList.add(p2Name);
        playerList.add(p3Name);
        playerList.add(p4Name);

    }

    public void onButtonPressed() {
        Button startGameButton = (Button) findViewById(R.id.startGameButton);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //newPlayers();
                newPlayer();

                if (p1Name.trim().length() <= 0) {
                    Toast.makeText(PlayerNames.this, "Enter Missing Name(s)", Toast.LENGTH_LONG).show();
                } else if (p2Name.trim().length() <= 0) {
                    Toast.makeText(PlayerNames.this, "Enter Missing Name(s)", Toast.LENGTH_LONG).show();
                } else if (p3Name.trim().length() <= 0) {
                    Toast.makeText(PlayerNames.this, "Enter Missing Name(s)", Toast.LENGTH_LONG).show();
                } else if (p4Name.trim().length() <= 0) {
                    Toast.makeText(PlayerNames.this, "Enter Missing Name(s)", Toast.LENGTH_LONG).show();
                } else {
                    Intent startGame = new Intent(PlayerNames.this, Front9.class);
                    startActivity(startGame);
                }


            }
        });
    }

    //trying to use the player model
    public void newPlayer() {

        EditText player1Name = (EditText) findViewById(R.id.player1Name);
        EditText player2Name = (EditText) findViewById(R.id.player2Name);
        EditText player3Name = (EditText) findViewById(R.id.player3Name);
        EditText player4Name = (EditText) findViewById(R.id.player4Name);

        p1Name = player1Name.getText().toString();
        p2Name = player2Name.getText().toString();
        p3Name = player3Name.getText().toString();
        p4Name = player4Name.getText().toString();

        playerList.add(p1Name);
        playerList.add(p2Name);
        playerList.add(p3Name);
        playerList.add(p4Name);

        //Toast.makeText(this, p1Name, Toast.LENGTH_SHORT).show();

        for (int i = 0; i < playerList.size(); i++)
        {
            firstName = String.valueOf(playerList.get(i)).trim();
            //Toast.makeText(this, firstName, Toast.LENGTH_SHORT).show();
            int id = playerHashMap.size() + 1;
            Player player = new Player(firstName);
            playerHashMap.put(id, player);
            //Toast.makeText(this, String.valueOf(player.firstName), Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, String.valueOf(playerHashMap), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, String.valueOf(playerHashMap.size()), Toast.LENGTH_SHORT).show();
    }
}