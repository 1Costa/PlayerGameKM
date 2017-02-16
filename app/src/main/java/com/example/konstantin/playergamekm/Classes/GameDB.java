package com.example.konstantin.playergamekm.Classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Konstantin on 03/02/2017.
 */

public class GameDB {

    public static final String DB_NAME = "gameDatabase.db";
    public static final int    DB_VERSION = 1;

    // task table constants
    public static final String GAMEUSER_TABLE = "gameuser";

    public static final String GAMEUSER_ID = "_id";
    public static final int    GAMEUSER_ID_COL = 0;

    public static final String GAMEUSER_NAME = "username";
    public static final int    GAMEUSER_NAME_COL = 1;

    public static final String GAMEUSER_WINS = "wins";
    public static final int GAMEUSER_WINS_COL = 2;

    public static final String GAMEUSER_LOSSES = "losses";
    public static final int GAMEUSER_LOSSES_COL = 3;

    public static final String GAMEUSER_TIES = "ties";
    public static final int GAMEUSER_TIES_COL = 4;

    public static final String CREATE_GAMEUSER_TABLE =
            "CREATE TABLE " + GAMEUSER_TABLE + " (" +
                    GAMEUSER_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    GAMEUSER_NAME    + " TEXT NOT NULL, " +
                    GAMEUSER_WINS       + " INTEGER," +
                    GAMEUSER_LOSSES      + " INTEGER," +
                    GAMEUSER_TIES       + " INTEGER);";
    public static final String DROP_GAMEUSER_TABLE =
            "DROP TABLE IF EXISTS " + GAMEUSER_TABLE;



    private static  class DBHelper extends  SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create table
            db.execSQL(CREATE_GAMEUSER_TABLE);

            // insert sample tips
            db.execSQL("INSERT INTO gameuser VALUES (1, 'Joel', 4, 1, 5)");
            db.execSQL("INSERT INTO gameuser VALUES (2, 'Ray', 3, 7, 10)");
            db.execSQL("INSERT INTO gameuser VALUES (3, 'Mike', 3, 2, 5)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            Log.d("Task list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            db.execSQL(GameDB.DROP_GAMEUSER_TABLE);
            //db.execSQL(TaskListDB.DROP_TASK_TABLE);
            onCreate(db);
        }
    }

    // database and database helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public GameDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }


    public ArrayList<GameUser> getUsers() {

        this.openReadableDB();
        Cursor cursor = db.query(GAMEUSER_TABLE, null,null,null,null,null,GAMEUSER_WINS + " DESC");
        //Cursor cursor = db.rawQuery("SELECT * FROM " + GAMEUSER_TABLE, null);
        ArrayList<GameUser> users = new ArrayList<GameUser>();
        while (cursor.moveToNext()) {
            GameUser user = new GameUser(
                    cursor.getInt(GAMEUSER_ID_COL),
                    cursor.getString(GAMEUSER_NAME_COL),
                    cursor.getInt(GAMEUSER_WINS_COL),
                    cursor.getInt(GAMEUSER_LOSSES_COL),
                    cursor.getInt(GAMEUSER_TIES_COL));
            users.add(user);
        }
        if (cursor != null)
            cursor.close();
        this.closeDB();
        return users;
    }

    public long insertGameUser(GameUser user){
        ContentValues cv = new ContentValues();
        cv.put(GAMEUSER_NAME, user.getName());
        this.openWriteableDB();
        long rowID = db.insert(GAMEUSER_TABLE, null, cv);
        this.closeDB();
        // will return rowID or will return -1 if no row inserted
        return rowID;
    }

    public long updateWinsAndLosses(String winner, String loser) {
        this.openReadableDB();
        Cursor cursorWins =  db.rawQuery("select " + GAMEUSER_WINS + " from " +
                GAMEUSER_TABLE + " where " + GAMEUSER_NAME + "='" + winner + "'" , null);
        Cursor cursorLosses =  db.rawQuery("select " + GAMEUSER_LOSSES + " from " +
                GAMEUSER_TABLE + " where " + GAMEUSER_NAME + "='" + loser + "'" , null);
        int wins = 0;
        int losses = 0;
        if (cursorWins != null) {
            cursorWins.moveToFirst();
            wins = cursorWins.getInt(cursorWins.getColumnIndex(GAMEUSER_WINS));
            cursorWins.close();
        }
        if (cursorLosses != null){
            cursorLosses.moveToFirst();
            losses = cursorLosses.getInt(cursorLosses.getColumnIndex(GAMEUSER_LOSSES));
            cursorLosses.close();
        }

        this.openWriteableDB();
        ContentValues cvVins = new ContentValues();
        cvVins.put(GAMEUSER_WINS, wins + 1 ); // update user's wins
        String whereClause = GAMEUSER_NAME + "=?";
        String[] whereArgs = new String[]{winner};
        long rowID = db.update(GAMEUSER_TABLE, cvVins, whereClause, whereArgs);
        ContentValues cvLosses = new ContentValues();
        cvLosses.put(GAMEUSER_LOSSES, losses + 1 ); // update user's losses
        String whereClauseL = GAMEUSER_NAME + "=?";
        String[] whereArgsL = new String[]{loser};
        rowID += db.update(GAMEUSER_TABLE, cvLosses, whereClauseL, whereArgsL);


        this.closeDB();
        // will return rowID or will return -1 if no row inserted
        return rowID;
    }

    public long updateTies(String player_1, String player_2) {
        long updates = 0;
        int p1_ties = getPlayersTiesToUpdate(player_1);
        updates = updatePlayersTies(player_1, p1_ties);
        int p2_ties = getPlayersTiesToUpdate(player_2);
        updates += updatePlayersTies(player_2, p2_ties);
        return updates;
    }

    private int getPlayersTiesToUpdate(String player) {
        this.openReadableDB();
        Cursor cursor =  db.rawQuery("select " + GAMEUSER_TIES + " from " +
                GAMEUSER_TABLE + " where " + GAMEUSER_NAME + "='" + player + "'" , null);
        int ties = 0;
        if (cursor != null) {
            cursor.moveToFirst();
            ties = cursor.getInt(cursor.getColumnIndex(GAMEUSER_TIES));
        }
        if (cursor != null)
            cursor.close();
        return ties;
    }

    private long updatePlayersTies(String player, int ties) {
        ContentValues cv = new ContentValues();
        cv.put(GAMEUSER_TIES, ties + 1 ); // update user's ties
        this.openWriteableDB();
        String whereClause = GAMEUSER_NAME + "=?";
        String[] whereArgs = new String[]{player};
        long rowID = db.update(GAMEUSER_TABLE, cv, whereClause, whereArgs);
        this.closeDB();
        return rowID;
    }

    public int deletePlayer(String player) {
        String where = GAMEUSER_NAME + "= ?";
        String[] whereArgs = { String.valueOf(player) };
        this.openWriteableDB();
        int rowCount = db.delete(GAMEUSER_TABLE, where, whereArgs);
        this.closeDB();

        return rowCount;
    }

}
