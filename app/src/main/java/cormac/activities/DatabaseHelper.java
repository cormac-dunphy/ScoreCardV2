package cormac.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

import models.Course;
import models.Game;

import static android.content.ContentValues.TAG;

/**
 * Created by Cormac on 30/03/2018.
 */


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "scorecard.db";
    //player table
    public static final String PLAYER_TABLE = "player_table";
    public static final String PLAYER_NAME = "name";
    //course table
    public static final String COURSES = "courses";
    public static final String COURSE_NAME = "coursename";
    public static final String COURSE_LOCATION = "courselocation";
    public static final String COURSE_PAR = "coursepar";
    //game table
    public static final String GAME = "game";
    public static final String GAME_DATE = "gamedate";
    public static final String GAME_COURSENAME = "coursename";
    public static final String GAME_PLAYER1 = "player1";
    public static final String GAME_PLAYER2 = "player2";
    public static final String GAME_PLAYER3 = "player3";
    public static final String GAME_PLAYER4 = "player4";
    public static final String GAME_SCORE1 = "score1";
    public static final String GAME_SCORE2 = "score2";
    public static final String GAME_SCORE3 = "score3";
    public static final String GAME_SCORE4 = "score4";
    public static final String GAME_PAR1 = "par1";
    public static final String GAME_PAR2 = "par2";
    public static final String GAME_PAR3 = "par3";
    public static final String GAME_PAR4 = "par4";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("drop table " +COURSES);
        //db.execSQL("drop table " +PLAYER_NAME);
        //db.execSQL("drop table " +GAME);
        db.execSQL("create table if not exists " + PLAYER_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
        db.execSQL("create table if not exists " + COURSES + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, coursename TEXT, courselocation TEXT, coursepar INT)");
        db.execSQL("create table if not exists " + GAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, gamedate TEXT, coursename TEXT, player1 TEXT, player2 TEXT, player3 TEXT, player4 TEXT, score1 INT, score2 INT, score3 INT, score4 INT, par1 INT, par2 INT, par3 INT, par4 INT)");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean insertPlayerData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PLAYER_NAME, name);
        long result = db.insert(PLAYER_TABLE, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertCourseData(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_NAME, course.courseName);
        contentValues.put(COURSE_LOCATION, course.courseLocation);
        contentValues.put(COURSE_PAR, course.coursePar);
        long result = db.insert(COURSES, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public void deleteCourseRow(Course course)
    {
        Log.i("scorecard", "deleteCourseRow: in delete course row");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + COURSES+ " WHERE "+COURSE_NAME+"='"+course.courseName+"'");
        db.close();
    }


    public boolean insertGameData(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GAME_DATE, game.gameDate);
        contentValues.put(GAME_COURSENAME, game.courseName);
        contentValues.put(GAME_PLAYER1, game.playerName1);
        contentValues.put(GAME_PLAYER2, game.playerName2);
        contentValues.put(GAME_PLAYER3, game.playerName3);
        contentValues.put(GAME_PLAYER4, game.playerName4);
        contentValues.put(GAME_SCORE1, game.playerScore1);
        contentValues.put(GAME_SCORE2, game.playerScore2);
        contentValues.put(GAME_SCORE3, game.playerScore3);
        contentValues.put(GAME_SCORE4, game.playerScore4);
        contentValues.put(GAME_PAR1, game.playerPar1);
        contentValues.put(GAME_PAR2, game.playerPar2);
        contentValues.put(GAME_PAR3, game.playerPar3);
        contentValues.put(GAME_PAR4, game.playerPar4);

        long result = db.insert(GAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getCourseData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + COURSES, null);
        return res;
    }

    public Cursor getGameData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + GAME, null);
        return res;
    }
}
