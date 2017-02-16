package com.example.konstantin.playergamekm.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konstantin.playergamekm.Classes.GameDB;
import com.example.konstantin.playergamekm.Classes.GameUser;
import com.example.konstantin.playergamekm.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SelectPlayerActivity extends AppCompatActivity{
        //implements AdapterView.OnItemClickListener, View.OnClickListener{

//    GameDB db;
//    private TextView titleTextView;
//    private ListView playersListView;
//    private Button buttonStartGame;
//    private Button buttonResetPlayers;
//
//    SimpleAdapter adapter;
//    private String firstPlayer;
//    private String secondPlayer;
//    private int selected_p1 = 0;
//    private int selected_p2 = 0;
//    String image_1 = "";
//    String image_2 = "";
//    int image_1_for_p1;
//    int image_2_for_p2;
//    final Random randomImage = new Random();
//    SharedPreferences savedValues;
//    int selectionCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);
        //firstPlayer = "";
        //secondPlayer = "";

        //savedValues = PreferenceManager.getDefaultSharedPreferences(getApplication());

//        titleTextView = (TextView) findViewById(R.id.titleTextView);
//        playersListView = (ListView)findViewById(R.id.playersListView);
//        buttonStartGame = (Button)findViewById(R.id.buttonStartGame);
//        buttonResetPlayers = (Button)findViewById(R.id.buttonResetPlayers);
//        //buttonStartGame.setVisibility(View.INVISIBLE);

//        playersListView.setOnItemClickListener(this);
//        buttonStartGame.setOnClickListener(this);
//        buttonResetPlayers.setOnClickListener(this);
//        titleTextView.setText("Select Player 1");
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setTitle(R.string.selectPlayerTitle);
//        this.updateListView();

    }

//    @Override
//    protected void onPause() {
//        savedValues = PreferenceManager.getDefaultSharedPreferences(getApplication());
//        String nameCheck = savedValues.getString("p1_name", null);
//        if(nameCheck == null){
//            Log.d("SH CHECK NULL ", "GOT NULL ON NAMES");
//        }else {
//            SharedPreferences.Editor editor = savedValues.edit();
//            editor.putString("p1_name_temp", firstPlayer);
//            editor.putString("p2_name_temp", secondPlayer);
//            //editor.putInt("first_p_image",image_1_for_p1 );
//            //editor.putInt("second_p_image", image_2_for_p2);
//            Log.d("Temp onPause 1: ", firstPlayer);
//            Log.d("Temp onPause 2: ", secondPlayer);
//            editor.commit();
//        }
//        super.onPause();//goes after code
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();//goes before code
//
//        savedValues = PreferenceManager.getDefaultSharedPreferences(getApplication());
//        String nameCheck = savedValues.getString("p1_name", null);
//        if(nameCheck == null){
//            Log.d("SH CHECK NULL ", "GOT NULL ON NAMES");
//            updateListView();
//        }else {
//            firstPlayer = savedValues.getString("p1_name_temp", null);
//            secondPlayer = savedValues.getString("p2_name_temp", null);
//            Log.d("First Temp onResume: ", firstPlayer);
//            Log.d("Second Temp onResume: ", secondPlayer);
//        }
//    }
//
//    private void updateListView() {
//        selectionCounter = 0;
//        titleTextView.setText("Select Player 1");
//        buttonStartGame.setVisibility(View.INVISIBLE);
//        buttonResetPlayers.setVisibility(View.INVISIBLE);
//        db = new GameDB(this);
//        ArrayList<GameUser> players = db.getUsers();
//
//        // create a List of Map<String, ?> objects
//        ArrayList<HashMap<String, String>> data =
//                new ArrayList<HashMap<String, String>>();
//        for (GameUser item : players) {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("name", item.getName());
//            data.add(map);
//        }
//
//        // create the resource, from, and to variables
//        int resource = R.layout.player_item;
//        String[] from = {"name"};
//        int[] to = {R.id.playerTextView};
//
//        playersListView.setBackgroundColor(0);
//        // create and set the adapter
//        adapter =
//                new SimpleAdapter(this, data, resource, from, to);
//        playersListView.setAdapter(adapter);
//
//        Log.d("GameUser", "Players displayed");
//    }
//
//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//        selectionCounter++;
//        if(selectionCounter < 2){
//            // get first player name
//            if(playersListView.getChildAt(position).isEnabled())
//            {
//                view.setBackgroundColor(Color.parseColor("#FF42A5F5"));
//                HashMap<String, Object> obj = (HashMap<String, Object>) adapter.getItem(position);
//                String name = (String) obj.get("name");
//                firstPlayer = name;
//                Log.d("Game Player 1 >>>> ", name);
//                playersListView.getChildAt(position).setEnabled(false);
//                titleTextView.setText("Select Player 2");
//                //Toast.makeText(this, "first selected", Toast.LENGTH_SHORT).show();
//
//            }
//        }else if (selectionCounter == 2){
//            if(playersListView.getChildAt(position).isEnabled())
//            {
//                view.setBackgroundColor(Color.parseColor("#FF42A5F5"));
//                HashMap<String, Object> obj = (HashMap<String, Object>) adapter.getItem(position);
//                String name = (String) obj.get("name");
//                secondPlayer = name;
//                Log.d("Game Player 2 >>>> ", name);
//                playersListView.getChildAt(position).setEnabled(false);
//                titleTextView.setText("");
//                //Toast.makeText(this, "second selected", Toast.LENGTH_SHORT).show();
//                selectionCounter++;
//                buttonStartGame.setVisibility(View.VISIBLE);
//                buttonResetPlayers.setVisibility(View.VISIBLE);
//            }
//        }else{
//            Toast.makeText(this, "Two Players Only!", Toast.LENGTH_LONG).show();
//        }
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.buttonStartGame:
//                // do code here
//                //Toast.makeText(this, "Button Start Pressed", Toast.LENGTH_LONG).show();
//                setRandomImagesToImageViews();
//                SharedPreferences.Editor editor = savedValues.edit();
//                editor.putString("p1_name", firstPlayer);
//                editor.putString("p2_name", secondPlayer);
//                editor.putInt("first_p_image",image_1_for_p1 );
//                editor.putInt("second_p_image", image_2_for_p2);
//                editor.commit();
//                Intent gameActivity = new Intent(SelectPlayerActivity.this, GameEmulatorActivity.class);
//                //gameActivity.putExtra("firstPlayer", firstPlayer);
//                //gameActivity.putExtra("secondPlayer", secondPlayer);
//                startActivity(gameActivity);
//                break;
//            case R.id.buttonResetPlayers:
//                updateListView();
//                break;
//        }
//    }
//
//    private void setRandomImagesToImageViews() {
//        // Images were downloaded from
//        // http://all-free-download.com/free-icon/download/free-plush-icons-set-icons-pack_120817.html
//        // I have 6 images named img_0 to img_5, so...
//
//        image_1 = "img_" + randomImage.nextInt(6);
//        image_2 = "img_" + randomImage.nextInt(6);
//        while(image_1.equals(image_2)){
//            image_2 = "img_" + randomImage.nextInt(6);
//        }
//
//        image_1_for_p1 = getResources().getIdentifier(image_1 , "drawable", getPackageName());
//        image_2_for_p2 = getResources().getIdentifier(image_2 , "drawable", getPackageName());
//        Log.d("Gen image 1: ", String.valueOf(image_1_for_p1));
//        Log.d("Gen image 2: ", String.valueOf(image_2_for_p2));
//
//    }
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                // app icon in action bar clicked; goto parent activity.
//                this.finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
