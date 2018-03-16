package cormac.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Player;

/**
 * Created by Cormac on 02/03/2018.
 */

public class Base extends AppCompatActivity
{
    public Map<Integer, Player> playerMap = new HashMap<Integer, Player>();

    public static List<Integer> scoreList = new ArrayList<>();

    public static HashMap<Integer, Player> playerHashMap = new HashMap<>();




    public HashMap<Integer, Player> getPlayerMap()
    {
        return (HashMap<Integer, Player>) playerMap;
    }

    //public void newPlayer(String firstName)
    {
        //int id = playerMap.size() + 1;
        //Player player = new Player(id, firstName);
        //Player player = new Player(0, "homer");

        //playerMap.put(id, player);
        //playerMap.put(0, player);
    }
}
