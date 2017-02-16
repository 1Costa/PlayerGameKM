package com.example.konstantin.playergamekm.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konstantin.playergamekm.Activities.AboutActivity;
import com.example.konstantin.playergamekm.Activities.DeletePlayerActivity;
import com.example.konstantin.playergamekm.Activities.TicTacToeActivity;
import com.example.konstantin.playergamekm.Classes.GameDB;
import com.example.konstantin.playergamekm.Activities.GameEmulatorActivity;
import com.example.konstantin.playergamekm.Classes.GameUser;
import com.example.konstantin.playergamekm.R;
import com.example.konstantin.playergamekm.Activities.SelectPlayerActivity;
import com.example.konstantin.playergamekm.Activities.ViewScoreboardActivity;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener{

    GameDB db;

    private TextView recentlyPlayed;
    private TextView p1_NameTextView;
    private TextView p2_NameTextView;
    private ImageView p1_ImageView;
    private ImageView p2_ImageView;

    private EditText userInput;
    private AlertDialog addPlayerDialog;
    boolean userExists;
    int i1;
    int i2;
    int image_1_for_p1;
    int image_2_for_p2;
    private String image_1 = "";
    private String image_2 = "";
    private String p1;
    private String p2;
    private int p1_image;
    private int p2_image;
    private Button buttonStartGame;
    //private Button buttonSelectPlayer;
    //private Button buttonPlayTicTacToe;
    private Button buttonViewScoreboard;
    final Random randomImage = new Random();
    //private Button buttonAddPlayer;
    private SharedPreferences savedValues;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());


        // turn on the options menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recentlyPlayed = (TextView) view.findViewById(R.id.recentlyPlayed);
        p1_NameTextView = (TextView) view.findViewById(R.id.p1_NameTextView);
        p2_NameTextView = (TextView) view.findViewById(R.id.p2_NameTextView);
        p1_ImageView = (ImageView) view.findViewById(R.id.p1_imageView);
        p2_ImageView = (ImageView) view.findViewById(R.id.p2_imageView);
        i1 = savedValues.getInt("first_p_image", 0);
        i2 = savedValues.getInt("second_p_image", 0);
        p1 = savedValues.getString("p1_name", null);
        p2 = savedValues.getString("p2_name", null);
        if(p1 == (null) || p2 == (null)){
            Intent selectPlayers = new Intent(getActivity(), SelectPlayerActivity.class);
            this.startActivity(selectPlayers);

        }
        else {

            i1 = savedValues.getInt("first_p_image", 0);
            i2 = savedValues.getInt("second_p_image", 0);
            if (i1 != 0 && i2 != 0) {
                p1_ImageView.setImageResource(i1);
                p2_ImageView.setImageResource(i2);
            } else {
                //recentlyPlayed.setText("No Players");
                p1_image = getResources().getIdentifier("no_image", "drawable", getActivity().getPackageName());
                p2_image = getResources().getIdentifier("no_image", "drawable", getActivity().getPackageName());
                p1_ImageView.setImageResource(p1_image);
                p2_ImageView.setImageResource(p2_image);
            }

            buttonStartGame = (Button) view.findViewById(R.id.buttonStartGame);
            //buttonSelectPlayer = (Button) view.findViewById(R.id.buttonSelectPlayer);
            buttonViewScoreboard = (Button) view.findViewById(R.id.buttonViewScoreboard);
            //buttonAddPlayer = (Button) view.findViewById(R.id.buttonAddPlayer);
            //buttonPlayTicTacToe = (Button)view.findViewById(R.id.buttonPlayTicTacToe);

            buttonStartGame.setOnClickListener(this);
            //buttonSelectPlayer.setOnClickListener(this);
            //buttonAddPlayer.setOnClickListener(this);
            buttonViewScoreboard.setOnClickListener(this);
            //buttonPlayTicTacToe.setOnClickListener(this);


        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // attempt to get the fragment
//         ViewScoreboardFragment settingsFragment = (ViewScoreboardFragment) getFragmentManager()
//                .findFragmentById(R.id.viewScoreboard_fragment);
//
//        // if the fragment is null, display the appropriate menu
//        if (settingsFragment == null) {
//            inflater.inflate(R.menu.menu_main, menu);
//        } else {
//            inflater.inflate(R.menu.menu_main, menu);
//        }
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_select_players:
                Intent selectPlayerActivity = new Intent(getActivity(), SelectPlayerActivity.class);
                startActivity(selectPlayerActivity);
                return true;
//            case R.id.menu_add_player:
//                showDialogAddPlayer();
//                return true;
//            case R.id.menu_delete_player:
//                Intent deletePlayerActivity = new Intent(getActivity(), DeletePlayerActivity.class);
//                startActivity(deletePlayerActivity);
//                return true;
            case R.id.menu_tictactoe:
                Intent tictactoeActivity = new Intent(getActivity(), TicTacToeActivity.class);
                startActivity(tictactoeActivity);
                return true;
            case R.id.menu_about:
                Intent aboutActivity = new Intent(getActivity(), AboutActivity.class);
                startActivity(aboutActivity);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.");
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = savedValues.edit();

        if(p1_NameTextView.getText().toString().equals("No Data") || p2_NameTextView.getText().toString().equals("No Data")){
            editor.putString("p1_name", null);
            editor.putString("p2_name", null);
        }else {
            editor.putString("p1_name", p1_NameTextView.getText().toString());
            editor.putString("p2_name", p2_NameTextView.getText().toString());
        }
        if(savedValues.getInt("first_p_image", 0)== 0){
            editor.putInt("first_p_image",image_1_for_p1 );
            editor.putInt("second_p_image", image_2_for_p2);
        }else{
            editor.putInt("first_p_image",i1 );
            editor.putInt("second_p_image", i2);
        }


        editor.commit();
        Log.d("onPauseMain image 1: ", String.valueOf(i1));
        Log.d("onPauseMain image 2: ", String.valueOf(i2));
    }

    @Override
    public void onResume() {
        i1 = savedValues.getInt("first_p_image", 0);
        i2 = savedValues.getInt("second_p_image", 0);
        p1 = savedValues.getString("p1_name", null);
        p2 = savedValues.getString("p2_name", null);

        if((i1 != 0 && i2 != 0) && (p1 != (null) && p2 !=(null)) ) {
            //recentlyPlayed.setText(R.string.recentlyPlayed);
            p1_NameTextView.setText(savedValues.getString("p1_name", "No Data"));
            p2_NameTextView.setText(savedValues.getString("p2_name", "No Data"));
            p1_ImageView.setImageResource(savedValues.getInt("first_p_image", 0));
            p2_ImageView.setImageResource(savedValues.getInt("second_p_image", 0));
        }
        if(p1 == (null) || p2 == (null)){

            //recentlyPlayed.setText("No Recent Game");
            p1_NameTextView.setText("No Data");
            p2_NameTextView.setText("No Data");
            p1_image = getResources().getIdentifier("no_image" , "drawable", getActivity().getPackageName());
            p2_image = getResources().getIdentifier("no_image" , "drawable", getActivity().getPackageName());
            p1_ImageView.setImageResource(p1_image);
            p2_ImageView.setImageResource(p2_image);
            Intent selectPlayers = new Intent(getActivity(),SelectPlayerActivity.class);
            startActivity(selectPlayers);
        }else if((i1 == 0 && i2 == 0) && (p1 != null && p2 != null)){
            //recentlyPlayed.setText(R.string.recentlyPlayed);
            p1_NameTextView.setText(savedValues.getString("p1_name", "No Data"));
            p2_NameTextView.setText(savedValues.getString("p2_name", "No Data"));
            setRandomImagesToImageViews();
            p1_ImageView.setImageResource(image_1_for_p1);
            p2_ImageView.setImageResource(image_2_for_p2);

        }
        Log.d("onResumeMain image 1: ", String.valueOf(savedValues.getInt("first_p_image", 1)));
        Log.d("onResumeMain image 2: ", String.valueOf(savedValues.getInt("second_p_image", 2)));
        super.onResume();// AFTER CODE
    }

    private void setRandomImagesToImageViews() {
        // Images were downloaded from
        // http://all-free-download.com/free-icon/download/free-plush-icons-set-icons-pack_120817.html
        // I have 6 images named img_0 to img_5, so...

        image_1 = "img_" + randomImage.nextInt(6);
        image_2 = "img_" + randomImage.nextInt(6);
        while(image_1.equals(image_2)){
            image_2 = "img_" + randomImage.nextInt(6);
        }

        image_1_for_p1 = getResources().getIdentifier(image_1 , "drawable", getActivity().getPackageName());
        image_2_for_p2 = getResources().getIdentifier(image_2 , "drawable", getActivity().getPackageName());
        Log.d("Gen image 1: ", String.valueOf(i1));
        Log.d("Gen image 2: ", String.valueOf(i2));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonStartGame:
                //Add check for empty references
                if(p1 == null || p2 == null){
                    Intent selectPlayers = new Intent(getActivity(), SelectPlayerActivity.class);
                    this.startActivity(selectPlayers);
                    Toast.makeText(getActivity(), "Select players first", Toast.LENGTH_SHORT).show();
                }else{
                    Intent emulateGame = new Intent(getActivity(), GameEmulatorActivity.class);
                    this.startActivity(emulateGame);
                }
                break;
//            case R.id.buttonSelectPlayer:
//                // create an intent
//                Intent selectPlayer = new Intent(getActivity(), SelectPlayerActivity.class);
//                //intent.putExtra("name", users);
//                this.startActivity(selectPlayer);
//                break;
//            case R.id.buttonAddPlayer:
//                Log.v(" ADD PLAYER: " , "ADD");
//                showDialogAddPlayer();
//                break;
            case R.id.buttonViewScoreboard:
                // create an intent
                Intent intent = new Intent(getActivity(), ViewScoreboardActivity.class);
                this.startActivity(intent);
                break;
            case R.id.buttonPlayTicTacToe:
                Intent startTicTacToe = new Intent(getActivity(), TicTacToeActivity.class);
                this.startActivity(startTicTacToe);
                break;
        }
    }

    private void displayPlayersList() {
        //GameDB dbu = new GameDB(this);
        StringBuilder sb = new StringBuilder();
        ArrayList<GameUser> users = db.getUsers();
        for (GameUser user : users) {
            // display every user in a list view
            // test data to log.i
            sb.append("\n"+" User_ID: " + user.getId() + ", User Name: " + user.getName() +
                    ", Wins: " + user.getWins() + ", Losses: " + user.getLosses() + ", Ties: " + user.getTies());
        }
        Log.i(" DISPLAY LIST: " , sb.toString());

        // create an intent
        Intent intent = new Intent(getActivity(), SelectPlayerActivity.class);
        //intent.putExtra("name", users);
        this.startActivity(intent);

    }

    private void showDialogAddPlayer() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle("Add New Player");
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View insideAlert = inflater.inflate(R.layout.add_user_input, null);
        alert.setView(insideAlert);
        userInput = (EditText) insideAlert.findViewById(R.id.add_user);
        //userInput.setImeOptions(EditorInfo.IME_ACTION_DONE);
        userInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                    //Toast.makeText(MainActivity.this, "GOT YOUR INPUT FROM RETURN KEY DOWN", Toast.LENGTH_SHORT).show();
                    insertPlayerIntoDatabase();
                    addPlayerDialog.dismiss();
                    return true;
                }
                return false;
            }
        });

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Toast.makeText(MainActivity.this, "GOT YOUR INPUT  FROM OK BUTTON", Toast.LENGTH_SHORT).show();
                String inputResult = userInput.getText().toString();
                insertPlayerIntoDatabase();
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Cancelled , no action needed
            }
        });
        addPlayerDialog = alert.show();
    }

    // Get data from dialog input and insert to database
    private void insertPlayerIntoDatabase(){
        String inputResult = userInput.getText().toString().trim();
        userExists = false;
        db = new GameDB(getActivity());
        if (!inputResult.isEmpty()) {
            // display all players from database // test data should be deleted later on
            ArrayList<GameUser> users = db.getUsers();
            for (GameUser user : users) {
                if (inputResult.equals(user.getName())) {
                    userExists = true;
                }
            }

            if (!userExists) {
                GameUser user = new GameUser();
                user.setName(inputResult);
                long rowID = db.insertGameUser(user);
                if(rowID != -1) {
                    Toast.makeText(getActivity(), "New Player " + inputResult +
                            " \nWas Added to Database", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), R.string.user_not_added, Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "Player " + inputResult +
                        " \nAlready Exists!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getActivity(), R.string.null_player_name, Toast.LENGTH_SHORT).show();
            // recreate alert dialog
            showDialogAddPlayer();

        }

    }

}
