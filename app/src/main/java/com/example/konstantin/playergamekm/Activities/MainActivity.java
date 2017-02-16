package com.example.konstantin.playergamekm.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konstantin.playergamekm.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
//implements View.OnClickListener {

//    GameDB db;
//
//    private TextView recentlyPlayed;
//    private TextView p1_NameTextView;
//    private TextView p2_NameTextView;
//    private ImageView p1_ImageView;
//    private ImageView p2_ImageView;
//
//    private EditText userInput;
//    private AlertDialog addPlayerDialog;
//    boolean userExists;
//    int i1;
//    int i2;
//    private String p1;
//    private String p2;
//    private int p1_image;
//    private int p2_image;
//    private Button buttonStartGame;
//    private Button buttonSelectPlayer;
//    private Button buttonSelectPlayer2;
//    private Button buttonViewScoreboard;
//    private Button buttonAddPlayer;
//    private SharedPreferences savedValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        savedValues = PreferenceManager.getDefaultSharedPreferences(getApplication());
//
//        recentlyPlayed = (TextView)findViewById(R.id.recentlyPlayed);
//        p1_NameTextView = (TextView)findViewById(R.id.p1_NameTextView);
//        p2_NameTextView = (TextView)findViewById(R.id.p2_NameTextView);
//        p1_ImageView = (ImageView)findViewById(R.id.p1_imageView);
//        p2_ImageView = (ImageView)findViewById(R.id.p2_imageView);
//        i1 = savedValues.getInt("first_p_image", 0);
//        i2 = savedValues.getInt("second_p_image", 0);
//        if(i1 != 0 && i2 != 0) {
//            p1_ImageView.setImageResource(i1);
//            p2_ImageView.setImageResource(i2);
//        }else{
//            recentlyPlayed.setText("No Players");
//            p1_image = getResources().getIdentifier("no_image" , "drawable", getPackageName());
//            p2_image = getResources().getIdentifier("no_image" , "drawable", getPackageName());
//            p1_ImageView.setImageResource(p1_image);
//            p2_ImageView.setImageResource(p2_image);
//        }
//
//        buttonStartGame = (Button) findViewById(R.id.buttonStartGame);
//        buttonSelectPlayer = (Button) findViewById(R.id.buttonSelectPlayer);
//        buttonViewScoreboard = (Button) findViewById(R.id.buttonViewScoreboard);
//        buttonAddPlayer = (Button) findViewById(R.id.buttonAddPlayer);
//
//        buttonStartGame.setOnClickListener(this);
//        buttonSelectPlayer.setOnClickListener(this);
//        buttonAddPlayer.setOnClickListener(this);
//        buttonViewScoreboard.setOnClickListener(this);
//        db = new GameDB(this);
//        StringBuilder sb = new StringBuilder();
//        // display all players from database // test data should be deleted later on
//        ArrayList<GameUser> users = db.getUsers();
//        for (GameUser user : users) {
//            sb.append("\n"+" User_ID: " + user.getId() + ", User Name: " + user.getName() +
//                    ", Wins: " + user.getWins() + ", Losses: " + user.getLosses() + ", Ties: " + user.getTies());
//        }
//
//        Log.i(" GAMEUSER TABLE: " + "\n", sb.toString());
//
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowHomeEnabled(true);

        //savedValues = getSharedPreferences("savedValues", MODE_PRIVATE);

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        return true;
//        //return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_delete_player:
//                // User chose the "Metric System" item, set metrics show only
//                Intent deletePlayerActivity = new Intent(this, DeletePlayerActivity.class);
//                startActivity(deletePlayerActivity);
//                return true;
//            case R.id.menu_about:
//                // User chose the "Metric System" item, set metrics show only
//                Intent aboutActivity = new Intent(this, AboutActivity.class);
//                startActivity(aboutActivity);
//                return true;
//
//
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                //Log.d(String.valueOf(item), "strange number id message");
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        SharedPreferences.Editor editor = savedValues.edit();
//
//        if(p1_NameTextView.getText().toString()== "No Data" || p2_NameTextView.getText().toString()== "No Data"){
//            editor.putString("p1_name", null);
//            editor.putString("p2_name", null);
//        }else {
//            editor.putString("p1_name", p1_NameTextView.getText().toString());
//            editor.putString("p2_name", p2_NameTextView.getText().toString());
//        }
//        editor.putInt("first_p_image",i1 );
//        editor.putInt("second_p_image", i2);
//        editor.commit();
//        Log.d("onPauseMain image 1: ", String.valueOf(i1));
//        Log.d("onPauseMain image 2: ", String.valueOf(i2));
//    }
//
//    @Override
//    protected void onResume() {
//        i1 = savedValues.getInt("first_p_image", 0);
//        i2 = savedValues.getInt("second_p_image", 0);
//        p1 = savedValues.getString("p1_name", null);
//        p2 = savedValues.getString("p2_name", null);
//
//            if((i1 != 0 && i2 != 0) && (p1 != (null) && p2 !=(null)) ) {
//                recentlyPlayed.setText(R.string.recentlyPlayed);
//                p1_NameTextView.setText(savedValues.getString("p1_name", "No Data"));
//                p2_NameTextView.setText(savedValues.getString("p2_name", "No Data"));
//                p1_ImageView.setImageResource(savedValues.getInt("first_p_image", 0));
//                p2_ImageView.setImageResource(savedValues.getInt("second_p_image", 0));
//            }
//            if(p1 == (null) || p2 == (null)){
//                recentlyPlayed.setText("No Recent Game");
//                p1_NameTextView.setText("No Data");
//                p2_NameTextView.setText("No Data");
//                p1_image = getResources().getIdentifier("no_image" , "drawable", getPackageName());
//                p2_image = getResources().getIdentifier("no_image" , "drawable", getPackageName());
//                p1_ImageView.setImageResource(p1_image);
//                p2_ImageView.setImageResource(p2_image);
//            }
//        Log.d("onResumeMain image 1: ", String.valueOf(savedValues.getInt("first_p_image", 1)));
//        Log.d("onResumeMain image 2: ", String.valueOf(savedValues.getInt("second_p_image", 2)));
//        super.onResume();// AFTER CODE
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.buttonStartGame:
//                //Add check for empty references
//                if(p1 == null || p2 == null){
//                    Intent selectPlayers = new Intent(this, SelectPlayerActivity.class);
//                    this.startActivity(selectPlayers);
//                    Toast.makeText(this, "Select players first", Toast.LENGTH_SHORT).show();
//                }else{
//                    Intent emulateGame = new Intent(this, GameEmulatorActivity.class);
//                    this.startActivity(emulateGame);
//                }
//                break;
//            case R.id.buttonSelectPlayer:
//                displayPlayersList();
//                break;
//            case R.id.buttonAddPlayer:
//                Log.v(" ADD PLAYER: " , "ADD");
//                showDialogAddPlayer();
//                break;
//            case R.id.buttonViewScoreboard:
//                // create an intent
//                Intent intent = new Intent(this, ViewScoreboardActivity.class);
//                this.startActivity(intent);
//                break;
//        }
//    }
//
//    private void displayPlayersList() {
//        //GameDB dbu = new GameDB(this);
//        StringBuilder sb = new StringBuilder();
//        ArrayList<GameUser> users = db.getUsers();
//        for (GameUser user : users) {
//            // display every user in a list view
//            // test data to log.i
//            sb.append("\n"+" User_ID: " + user.getId() + ", User Name: " + user.getName() +
//                    ", Wins: " + user.getWins() + ", Losses: " + user.getLosses() + ", Ties: " + user.getTies());
//        }
//        Log.i(" DISPLAY LIST: " , sb.toString());
//
//        // create an intent
//        Intent intent = new Intent(this, SelectPlayerActivity.class);
//        //intent.putExtra("name", users);
//        this.startActivity(intent);
//
//    }
//
//    private void showDialogAddPlayer() {
//        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
//        alert.setTitle("Add New Player");
//        LayoutInflater inflater = (LayoutInflater) this
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        final View insideAlert = inflater.inflate(R.layout.add_user_input, null);
//        alert.setView(insideAlert);
//        userInput = (EditText) insideAlert.findViewById(R.id.add_user);
//        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED ){
//                    //Toast.makeText(MainActivity.this, "GOT YOUR INPUT FROM RETURN KEY DOWN", Toast.LENGTH_SHORT).show();
//                    insertPlayerIntoDatabase();
//                    addPlayerDialog.dismiss();
//                    return true;
//                }
//                return false;
//            }
//        });
//
//        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                //Toast.makeText(MainActivity.this, "GOT YOUR INPUT  FROM OK BUTTON", Toast.LENGTH_SHORT).show();
//                String inputResult = userInput.getText().toString();
//                insertPlayerIntoDatabase();
//            }
//        });
//        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                // Cancelled , no action needed
//            }
//        });
//        addPlayerDialog = alert.show();
//    }
//
//// Get data from dialog input and insert to database
//    private void insertPlayerIntoDatabase(){
//        String inputResult = userInput.getText().toString().trim();
//        userExists = false;
//        db = new GameDB(this);
//        if (!inputResult.isEmpty()) {
//            // display all players from database // test data should be deleted later on
//            ArrayList<GameUser> users = db.getUsers();
//            for (GameUser user : users) {
//                if (inputResult.equals(user.getName())) {
//                    userExists = true;
//                }
//            }
//        }else{
//            Toast.makeText(getBaseContext(), R.string.null_player_name, Toast.LENGTH_SHORT).show();
//            // recreate alert dialog
//            showDialogAddPlayer();
//
//        }
//
//        if (!userExists) {
//            GameUser user = new GameUser();
//            user.setName(inputResult);
//            long rowID = db.insertGameUser(user);
//            if(rowID != -1) {
//                Toast.makeText(getBaseContext(), "New Player " + inputResult +
//                        " \nWas Added to Database", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(getBaseContext(), R.string.user_not_added, Toast.LENGTH_SHORT).show();
//            }
//        }
//        else{
//            Toast.makeText(getBaseContext(), "XERNYA KAKAYA-TA", Toast.LENGTH_SHORT).show();
//            // recreate alert dialog
//            showDialogAddPlayer();
//
//        }
//    }

}
