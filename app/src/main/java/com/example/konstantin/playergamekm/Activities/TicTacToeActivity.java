package com.example.konstantin.playergamekm.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.konstantin.playergamekm.R;

public class TicTacToeActivity extends AppCompatActivity {
//
//    int c[][];
//    int i, j = 0;
//    Button b[][];
//    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        //setBoard();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        MenuItem item = menu.add("Play Tic Tac Toe Game");
//        return true;
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        setBoard();
//        return true;
//    }
//
//
//    // Set up the game board.
//    private void setBoard() {
//        b = new Button[4][4];
//        c = new int[4][4];
//
//
//        textView = (TextView) findViewById(R.id.dialogue);
//
//
//        b[1][3] = (Button) findViewById(R.id.one);
//        b[1][2] = (Button) findViewById(R.id.two);
//        b[1][1] = (Button) findViewById(R.id.three);
//
//
//        b[2][3] = (Button) findViewById(R.id.four);
//        b[2][2] = (Button) findViewById(R.id.five);
//        b[2][1] = (Button) findViewById(R.id.six);
//
//
//        b[3][3] = (Button) findViewById(R.id.seven);
//        b[3][2] = (Button) findViewById(R.id.eight);
//        b[3][1] = (Button) findViewById(R.id.nine);
//
//        for (i = 1; i <= 3; i++) {
//            for (j = 1; j <= 3; j++)
//                c[i][j] = 2;
//        }
//
//
//        textView.setText("Click a button to start.");
//
//    }

}
