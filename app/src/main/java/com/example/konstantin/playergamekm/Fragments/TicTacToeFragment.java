package com.example.konstantin.playergamekm.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.konstantin.playergamekm.Activities.AboutActivity;
import com.example.konstantin.playergamekm.Activities.DeletePlayerActivity;
import com.example.konstantin.playergamekm.Activities.MainActivity;
import com.example.konstantin.playergamekm.Activities.SelectPlayerActivity;
import com.example.konstantin.playergamekm.Activities.SelectTicTacToePlayerActivity;
import com.example.konstantin.playergamekm.Activities.TicTacToeActivity;
import com.example.konstantin.playergamekm.Activities.ViewScoreboardActivity;
import com.example.konstantin.playergamekm.Classes.GameDB;
import com.example.konstantin.playergamekm.Classes.GameUser;
import com.example.konstantin.playergamekm.R;

import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.color;
import static android.R.attr.x;
import static android.R.attr.y;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicTacToeFragment extends Fragment implements View.OnClickListener {



    private TextView textViewStart;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;
    int c[][];
    int i, j = 0;
    Button b[][];

    GameDB db;
    private EditText userInput;
    private AlertDialog addPlayerDialog;
    boolean userExists;
    private Button buttonPlayTicTacToe;
    private Button buttonViewScoreboard;
    private String p1;
    private String p2;
    private String p1_play;
    private String p2_play;
    private Integer player_1R;
    private Integer player_2R;
    int turn = 0;
    int currentOrientation;
    boolean gameOver;

    SharedPreferences savedValues;
    final Random randomPlayer = new Random();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_tic_tac_toe, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_select_players:
                Intent selectTicPlayers = new Intent(getActivity(), SelectTicTacToePlayerActivity.class);
                startActivity(selectTicPlayers);
                return true;
            case R.id.menu_add_player:
                showDialogAddPlayer();
                return true;
            case R.id.menu_delete_player:
                Intent deletePlayerActivity = new Intent(getActivity(), DeletePlayerActivity.class);
                startActivity(deletePlayerActivity);
                return true;
            case R.id.menu_emulator:
                Intent mainActivity = new Intent(getActivity(), MainActivity.class);
                startActivity(mainActivity);
                return true;
            case R.id.menu_about:
                Intent aboutActivity = new Intent(getActivity(), AboutActivity.class);
                startActivity(aboutActivity);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                //Log.d(String.valueOf(item), "strange number id message");
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        p1_play = savedValues.getString("p1_name", null);
        p2_play = savedValues.getString("p2_name", null);


        b = new Button[4][4];
        c = new int[4][4];
        //ticPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);

        textViewStart = (TextView) view.findViewById(R.id.textViewStart);
        textViewPlayer1 = (TextView)view.findViewById(R.id.textViewPlayer1);
        textViewPlayer2 = (TextView)view.findViewById(R.id.textViewPlayer2);
        buttonPlayTicTacToe = (Button)view.findViewById(R.id.buttonPlayTicTacToe);
        buttonViewScoreboard = (Button) view.findViewById(R.id.buttonViewScoreboard);
        buttonPlayTicTacToe.setOnClickListener(this);
        buttonViewScoreboard.setOnClickListener(this);

        b[1][3] = (Button) view.findViewById(R.id.one);
        b[1][2] = (Button) view.findViewById(R.id.two);
        b[1][1] = (Button) view.findViewById(R.id.three);

        b[2][3] = (Button) view.findViewById(R.id.four);
        b[2][2] = (Button) view.findViewById(R.id.five);
        b[2][1] = (Button) view.findViewById(R.id.six);

        b[3][3] = (Button) view.findViewById(R.id.seven);
        b[3][2] = (Button) view.findViewById(R.id.eight);
        b[3][1] = (Button) view.findViewById(R.id.nine);
        setBoard();
        currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Landscape
            //Toast.makeText(getActivity().getBaseContext(),"LANDSCAPE", Toast.LENGTH_SHORT).show();
            buttonViewScoreboard.setVisibility(View.INVISIBLE);
        }
        else {
            // Portrait
            buttonViewScoreboard.setVisibility(View.VISIBLE);
        }
        return  view;
    }

public void setRandomPlayer(){
    player_1R = randomPlayer.nextInt(11);
    player_2R = randomPlayer.nextInt(11);
    while(player_1R.equals(player_2R)){
        player_2R = randomPlayer.nextInt(11);
    }
    if(player_1R < player_2R){
        textViewStart.setText("Player's " + p2 + " Turn");
        turn = 2;
    }else{
        textViewStart.setText("Player's " + p1 + " Turn");
        turn = 1;
    }
}

@Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b[1][3].setText("o");b[1][3].setEnabled(false);
        b[1][2].setText("x");b[1][2].setEnabled(false);
        b[1][1].setText("o");b[1][1].setEnabled(false);
        b[2][3].setText("x");b[2][3].setEnabled(false);
        b[2][2].setText("o");b[2][2].setEnabled(false);
        b[2][1].setText("x");b[2][1].setEnabled(false);
        b[3][3].setText("o");b[3][3].setEnabled(false);
        b[3][2].setText("x");b[3][2].setEnabled(false);
        b[3][1].setText("o");b[3][1].setEnabled(false);
}

    public void  lockTheBoard(){
        b[1][3].setEnabled(false);
        b[1][2].setEnabled(false);
        b[1][1].setEnabled(false);
        b[2][3].setEnabled(false);
        b[2][2].setEnabled(false);
        b[2][1].setEnabled(false);
        b[3][3].setEnabled(false);
        b[3][2].setEnabled(false);
        b[3][1].setEnabled(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = savedValues.edit();

        if(textViewPlayer1.getText().toString().equals("No Data")
                || textViewPlayer2.getText().toString().equals("No Data")
                || textViewPlayer1.getText().toString().equals("Player1")
                || textViewPlayer2.getText().toString().equals("Player2")){
            editor.putString("p1_name", null);
            editor.putString("p2_name", null);
        }else {
            editor.putString("p1_name", textViewPlayer1.getText().toString());
            editor.putString("p2_name", textViewPlayer2.getText().toString());
            editor.putString("resetBoard", "");
            editor.putString("playersTurn", textViewStart.getText().toString());
            editor.putInt("turn", turn);
            editor.putBoolean("gameOver", gameOver);
            editor.putString("one", b[1][3].getText().toString());
            editor.putString("two", b[1][2].getText().toString());
            editor.putString("three", b[1][1].getText().toString());

            editor.putString("four", b[2][3].getText().toString());
            editor.putString("five", b[2][2].getText().toString());
            editor.putString("six", b[2][1].getText().toString());

            editor.putString("seven", b[3][3].getText().toString());
            editor.putString("eight", b[3][2].getText().toString());
            editor.putString("nine", b[3][1].getText().toString());
            editor.commit();
        }

    }

    @Override
    public void onResume() {
        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        p1 = savedValues.getString("p1_name", null);
        p2 = savedValues.getString("p2_name", null);

    if((p1 != null) && (p2 !=null)) {
            textViewPlayer1.setText(savedValues.getString("p1_name", "No Data"));
            textViewPlayer2.setText(savedValues.getString("p2_name", "No Data"));
            String resetBoard = savedValues.getString("resetBoard", "");
            turn = savedValues.getInt("turn", 0);
            gameOver = savedValues.getBoolean("gameOver", false);
        if (!resetBoard.equals("resetBoard") || turn != 0) {
            textViewStart.setText(savedValues.getString("playersTurn", ""));
            b[1][3].setText(savedValues.getString("one", ""));
            b[1][2].setText(savedValues.getString("two", ""));
            b[1][1].setText(savedValues.getString("three", ""));
            b[2][3].setText(savedValues.getString("four", ""));
            b[2][2].setText(savedValues.getString("five", ""));
            b[2][1].setText(savedValues.getString("six", ""));
            b[3][3].setText(savedValues.getString("seven", ""));
            b[3][2].setText(savedValues.getString("eight", ""));
            b[3][1].setText(savedValues.getString("nine", ""));
            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++) {
                    if (b[i][j].getText().equals("X")) {
                        b[i][j].setTextColor(Color.parseColor("#55db34"));
                        c[i][j] = 1;
                    } else if (b[i][j].getText().equals("O")) {
                        b[i][j].setTextColor(Color.parseColor("#f9f75e"));
                        c[i][j] = 0;
                    } else {
                        c[i][j] = 2;
                        b[i][j].setEnabled(true);
                    }
                }
            }
            if(gameOver){
                //buttonPlayTicTacToe.setEnabled(true);
                turn = 0;
            }
            checkBoard();
            if(turn == 0){
                lockTheBoard();
                buttonPlayTicTacToe.setEnabled(true);
            }else{
                buttonPlayTicTacToe.setEnabled(false);
            }

        }
    }
        if(p1 == (null) || p2 == (null)){
            Intent selectTicTacToePlayers = new Intent(getActivity(),SelectTicTacToePlayerActivity.class);
            startActivity(selectTicTacToePlayers);
        }

        super.onResume();// AFTER CODE
    }

    // Set up the game board.
    private void setBoard() {

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                c[i][j] = 2;
        }

        textViewStart.setText("Click Play to Start.");

        // add the click listeners for each button
        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new ButtonClickListener(i, j));
                if(!b[i][j].isEnabled()) {
                    b[i][j].setText(" ");
                    b[i][j].setEnabled(true);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonPlayTicTacToe:
                this.setBoard();
                this.setRandomPlayer();
                gameOver = false;
                //this.resetBoard();
                buttonPlayTicTacToe.setEnabled(false);
                break;
            case R.id.buttonViewScoreboard:
                // create an intent
                Intent intent = new Intent(getActivity(), ViewScoreboardActivity.class);
                this.startActivity(intent);
                break;
        }
    }

    class ButtonClickListener implements View.OnClickListener {
        int x;
        int y;

            // class constructor
            public ButtonClickListener(int x, int y) {
                this.x = x;
                this.y = y;
            }
        //set on click to trace turns
        public void onClick(View view) {

            if(turn == 1) {
                if (b[x][y].isEnabled()) {
                    b[x][y].setEnabled(false);
                    b[x][y].setTextColor(Color.parseColor("#f9f75e"));
                    b[x][y].setText("O");
                    c[x][y] = 0;
                    textViewStart.setText("");
                    // will check while true not returned
                    if (!checkBoard()) {
                        textViewStart.setText("Player's " + p2 + " Turn");
                        turn++;
                    }
                }
            }else if(turn == 2) {
                if (b[x][y].isEnabled()) {
                    b[x][y].setEnabled(false);
                    b[x][y].setTextColor(Color.parseColor("#55db34"));
                    b[x][y].setText("X");
                    c[x][y] = 1;
                    textViewStart.setText("");
                    if (!checkBoard()) {
                        textViewStart.setText("Player's " + p1 + " Turn");
                        turn = 1;
                    }
                }
            }
        }
    }

    // check the board to see if someone has won
    private boolean checkBoard() {
        gameOver = false;
        if ((c[1][1] == 0 && c[2][2] == 0 && c[3][3] == 0)
                || (c[1][3] == 0 && c[2][2] == 0 && c[3][1] == 0)
                || (c[1][2] == 0 && c[2][2] == 0 && c[3][2] == 0)
                || (c[1][3] == 0 && c[2][3] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[1][2] == 0 && c[1][3] == 0)
                || (c[2][1] == 0 && c[2][2] == 0 && c[2][3] == 0)
                || (c[3][1] == 0 && c[3][2] == 0 && c[3][3] == 0)
                || (c[1][1] == 0 && c[2][1] == 0 && c[3][1] == 0)) {
            textViewStart.setText("Game Over. " + p1 + " Win!");
            lockTheBoard();
            gameOver = true;
            if(turn != 0){doOneWinsSecondLosses();}
            buttonPlayTicTacToe.setEnabled(true);
        } else if ((c[1][1] == 1 && c[2][2] == 1 && c[3][3] == 1)
                || (c[1][3] == 1 && c[2][2] == 1 && c[3][1] == 1)
                || (c[1][2] == 1 && c[2][2] == 1 && c[3][2] == 1)
                || (c[1][3] == 1 && c[2][3] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[1][2] == 1 && c[1][3] == 1)
                || (c[2][1] == 1 && c[2][2] == 1 && c[2][3] == 1)
                || (c[3][1] == 1 && c[3][2] == 1 && c[3][3] == 1)
                || (c[1][1] == 1 && c[2][1] == 1 && c[3][1] == 1)) {
            //textViewStart.setTextColor(Color.parseColor("#7a0f28"));
            textViewStart.setText("Game Over. " + p2 + " Win!");
            lockTheBoard();
            gameOver = true;
            if(turn != 0){doTwoWinsFirstLosses();}
            buttonPlayTicTacToe.setEnabled(true);
        } else {
            boolean empty = false;
            for(i=1; i<=3; i++) {
                for(j=1; j<=3; j++) {
                    if(c[i][j]==2) {
                        empty = true;
                        break;
                    }
                }
            }
            if(!empty) {
                gameOver = true;
                if(turn != 0){doTie();}
                buttonPlayTicTacToe.setEnabled(true);
                textViewStart.setText("Game over. It's a Draw!");
            }
        }
        return gameOver;
    }

    private void doOneWinsSecondLosses() {
//        //set connection with fragment to check if it is presented on screen
//        FragmentManager fragmentManager = getFragmentManager();
//        ViewScoreboardFragment viewScoreboardFragment = (ViewScoreboardFragment) fragmentManager.findFragmentById(R.id.viewScoreboard_fragment);
//        if (viewScoreboardFragment.isVisible()) {
//            Toast.makeText(getActivity().getBaseContext(),"ViewScoreboardFragment displayed", Toast.LENGTH_SHORT).show();
//        }
        //String winner = p1;
        db = new GameDB(getActivity());
        long update = db.updateWinsAndLosses(p1, p2);
        if(update == 2) {
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Landscape
                updateBoardView();
            }

//            Toast.makeText(getActivity().getBaseContext(),"Winner " + p1 + "\n Loser " + p2, Toast.LENGTH_SHORT).show();
        }

    }

    private void doTwoWinsFirstLosses() {
        //String winner = p2;
        db = new GameDB(getActivity());
        long update = db.updateWinsAndLosses(p2, p1);
        if(update == 2) {
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Landscape
                updateBoardView();
            }
//            Toast.makeText(getActivity().getBaseContext(),"Winner " + p2 + "\n Loser " + p1, Toast.LENGTH_SHORT).show();
        }
    }

    private void doTie() {
        //do Tie
        db = new GameDB(getActivity());
        long updateTie = db.updateTies(p1, p2);
        if(updateTie == 2) {
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                // Landscape
                updateBoardView();
            }
//            Toast.makeText(getActivity().getBaseContext(),"Ties Updated", Toast.LENGTH_SHORT).show();
        }


    }

    private void updateBoardView(){
        //set connection with fragment to update scoreboard directly in land view
        FragmentManager fragmentManager = getFragmentManager();
        ViewScoreboardFragment viewScoreboardFragment = (ViewScoreboardFragment) fragmentManager.findFragmentById(R.id.viewScoreboard_fragment);
        if(viewScoreboardFragment != null) {
            // call ViewScoreboardFragment method updateScoreBoard
            viewScoreboardFragment.updateScoreBoard();
        }
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
    //ADD PLAYER
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




