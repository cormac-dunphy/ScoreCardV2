package models;

/**
 * Created by Cormac on 05/04/2018.
 */

public class Game {

    //public int gameID;
    public String gameDate;
    public String courseName;
    public String playerName1;
    public String playerName2;
    public String playerName3;
    public String playerName4;
    public int playerScore1;
    public int playerScore2;
    public int playerScore3;
    public int playerScore4;
    public int playerPar1;
    public int playerPar2;
    public int playerPar3;
    public int playerPar4;

    public Game(String gameDate, String courseName,
                String playerName1, String playerName2, String playerName3, String playerName4,
                int playerScore1, int playerScore2, int playerScore3, int playerScore4,
                int playerPar1, int playerPar2, int playerPar3, int playerPar4)
    {
        //this.gameID = gameID;
        this.gameDate = gameDate;
        this.courseName = courseName;
        this.playerName1 = playerName1;
        this.playerName2 = playerName2;
        this.playerName3 = playerName3;
        this.playerName4 = playerName4;
        this.playerScore1 = playerScore1;
        this.playerScore2 = playerScore2;
        this.playerScore3 = playerScore3;
        this.playerScore4 = playerScore4;
        this.playerPar1 = playerPar1;
        this.playerPar2 = playerPar2;
        this.playerPar3 = playerPar3;
        this.playerPar4 = playerPar4;
    }
}
