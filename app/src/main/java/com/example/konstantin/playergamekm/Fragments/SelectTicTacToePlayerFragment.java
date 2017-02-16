package com.example.konstantin.playergamekm.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konstantin.playergamekm.Activities.AboutActivity;
import com.example.konstantin.playergamekm.Activities.DeletePlayerActivity;
import com.example.konstantin.playergamekm.Activities.MainActivity;
import com.example.konstantin.playergamekm.Activities.TicTacToeActivity;
import com.example.konstantin.playergamekm.Classes.GameDB;
import com.example.konstantin.playergamekm.Classes.GameUser;
import com.example.konstantin.playergamekm.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectTicTacToePlayerFragment extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {


    public SelectTicTacToePlayerFragment() {
        // Required empty public constructor
    }
    GameDB db;
    private TextView titleTextView;
    private ListView playersListView;
    private Button buttonStartGame;
    private Button buttonResetPlayers;

    SimpleAdapter adapter;
    private String firstPlayer = null;
    private String secondPlayer = null;
    private int selected_p1 = 0;
    private int selected_p2 = 0;
    private String current_player_1;
    private String current_player_2;
    private String shares_player_1;
    private String shares_player_2;
    SharedPreferences savedValues;
    int selectionCounter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.selectPlayerTitle);
        // turn on the options menu
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_tic_tac_toe_player,container,false);

        titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        playersListView = (ListView)view.findViewById(R.id.playersListView);
        buttonStartGame = (Button)view.findViewById(R.id.buttonStartGame);
        buttonResetPlayers = (Button)view.findViewById(R.id.buttonResetPlayers);
        //buttonStartGame.setVisibility(View.INVISIBLE);
        playersListView.setOnItemClickListener(this);
        buttonStartGame.setOnClickListener(this);
        buttonResetPlayers.setOnClickListener(this);
        titleTextView.setText("Select Player 1");
        updateListView();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_select_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                getActivity().finish();
                return true;
            case R.id.menu_delete_player:
                // User chose the "Metric System" item, set metrics show only
                Intent deletePlayerActivity = new Intent(getActivity(), DeletePlayerActivity.class);
                startActivity(deletePlayerActivity);
                return true;
            case R.id.menu_emulator:
                // User chose the "Metric System" item, set metrics show only
                Intent mainActivity = new Intent(getActivity(), MainActivity.class);
                startActivity(mainActivity);
                return true;
            case R.id.menu_about:
                // User chose the "Metric System" item, set metrics show only
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
    public void onPause() {
        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());

        //String nameCheck = savedValues.getString("p1_name", null);
        SharedPreferences.Editor editor = savedValues.edit();
//        if(nameCheck == null || nameCheck.equals("Player1")){
//            Log.d("SH CHECK NULL ", "GOT NULL ON NAMES");
//        }
        if(firstPlayer != null && secondPlayer != null){
            editor.putString("p1_name", firstPlayer);
            editor.putString("p2_name", secondPlayer);
            editor.putString("resetBoard","resetBoard");
            editor.putInt("turn", 0);
            editor.commit();
        }
//        else if(current_player_1 != firstPlayer || current_player_2 != secondPlayer) {
//            Log.d("SH CHECK NULL ", "GOT NULL ON NAMES");
//            // doo nothing , go back to activity. shared prefs without changes
//        }
//         {
//
//            editor.putString("p1_name", firstPlayer);
//            editor.putString("p2_name", secondPlayer);
//            editor.putString("resetBoard","resetBoard");
//            //editor.putInt("first_p_image",image_1_for_p1 );
//            //editor.putInt("second_p_image", image_2_for_p2);
//            Log.d("Temp onPause 1: ", firstPlayer);
//            Log.d("Temp onPause 2: ", secondPlayer);
//            editor.commit();
//        }
        super.onPause();//goes after code
    }

    @Override
    public void onResume() {
        super.onResume();//goes before code

        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        current_player_1 = savedValues.getString("p1_name", null);
        current_player_2 = savedValues.getString("p2_name", null);
        String nameCheck = savedValues.getString("p1_name", null);
        if(nameCheck == null || nameCheck.equals("Player1")){
            Log.d("SH CHECK NULL ", "GOT NULL ON NAMES");
            updateListView();
        }else {
//            firstPlayer = savedValues.getString("p1_name_temp", null);
//            secondPlayer = savedValues.getString("p2_name_temp", null);
//            Log.d("First Temp onResume: ", firstPlayer);
//            Log.d("Second Temp onResume: ", secondPlayer);
        }
    }

    private void updateListView() {
        selectionCounter = 0;
        titleTextView.setText("Select Player 1");
        buttonStartGame.setVisibility(View.INVISIBLE);
        buttonResetPlayers.setVisibility(View.INVISIBLE);
        db = new GameDB(getActivity());
        ArrayList<GameUser> players = db.getUsers();

        // create a List of Map<String, ?> objects
        ArrayList<HashMap<String, String>> data =
                new ArrayList<HashMap<String, String>>();
        for (GameUser item : players) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", item.getName());
            data.add(map);
        }

        // create the resource, from, and to variables
        int resource = R.layout.player_item;
        String[] from = {"name"};
        int[] to = {R.id.playerTextView};

        playersListView.setBackgroundColor(0);
        // create and set the adapter
        adapter =
                new SimpleAdapter(getActivity(), data, resource, from, to);
        playersListView.setAdapter(adapter);

        //Log.d("GameUser", "Players displayed");
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        selectionCounter++;
        if(selectionCounter == 1){
            // get first player name
            if(playersListView.getChildAt(position).isEnabled())
            {
                view.setBackgroundColor(Color.parseColor("#FF42A5F5"));
                HashMap<String, Object> obj = (HashMap<String, Object>) adapter.getItem(position);
                String name = (String) obj.get("name");
                firstPlayer = name;
                Log.d("Game Player 1 >>>> ", name);
                playersListView.getChildAt(position).setEnabled(false);
                buttonResetPlayers.setVisibility(View.VISIBLE);
                titleTextView.setText("Select Player 2");
                //Toast.makeText(this, "first selected", Toast.LENGTH_SHORT).show();

            }
        }else if (selectionCounter == 2){
            if(playersListView.getChildAt(position).isEnabled())
            {
                view.setBackgroundColor(Color.parseColor("#FF42A5F5"));
                HashMap<String, Object> obj = (HashMap<String, Object>) adapter.getItem(position);
                String name = (String) obj.get("name");
                secondPlayer = name;
                Log.d("Game Player 2 >>>> ", name);
                playersListView.getChildAt(position).setEnabled(false);
                titleTextView.setText("");
                //Toast.makeText(this, "second selected", Toast.LENGTH_SHORT).show();
                selectionCounter++;
                buttonStartGame.setVisibility(View.VISIBLE);
                buttonResetPlayers.setVisibility(View.VISIBLE);
            }
        }else{

            Toast.makeText(getActivity(), "Two Players Only!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonStartGame:
                // do code here
                //Toast.makeText(this, "Button Start Pressed", Toast.LENGTH_LONG).show();
//                SharedPreferences.Editor editor = savedValues.edit();
//                editor.putString("p1_name", firstPlayer);
//                editor.putString("p2_name", secondPlayer);
//                editor.commit();
                Intent gameTicTacToeActivity = new Intent(getActivity(), TicTacToeActivity.class);
                startActivity(gameTicTacToeActivity);
                break;
            case R.id.buttonResetPlayers:
                updateListView();
                break;
        }
    }
}