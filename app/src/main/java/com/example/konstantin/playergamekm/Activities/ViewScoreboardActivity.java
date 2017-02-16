package com.example.konstantin.playergamekm.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.konstantin.playergamekm.Classes.GameDB;
import com.example.konstantin.playergamekm.R;

public class ViewScoreboardActivity extends AppCompatActivity {

    GameDB db;
    SimpleAdapter adapter;
    private ListView scoreBoardListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scoreboard);
//
//        scoreBoardListView = (ListView)findViewById(R.id.scoreBoardListView);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setTitle(R.string.scoreboardTable);
//        updateScoreBoard();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        updateScoreBoard();
//    }
//
//    private void updateScoreBoard() {
//        // do update
//        db = new GameDB(this);
//        ArrayList<GameUser> players = db.getUsers();
//
//        // create a List of Map<String, ?> objects
//        ArrayList<HashMap<String, String>> data =
//                new ArrayList<HashMap<String, String>>();
//        for (GameUser item : players) {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("name", item.getName());
//            map.put("wins", String.valueOf(item.getWins()));
//            map.put("losses", String.valueOf(item.getLosses()));
//            map.put("ties", String.valueOf(item.getTies()));
//            data.add(map);
//        }
//
//        // create the resource, from, and to variables
//        int resource = R.layout.scoreboard_row;
//        String[] from = {"name","wins","losses","ties"};
//        int[] to = {R.id.playerName, R.id.winsTextView, R.id.lossesTextView, R.id.tiesTextView};
//
//        // create and set the adapter
//        adapter =
//                new SimpleAdapter(this, data, resource, from, to);
//        scoreBoardListView.setAdapter(adapter);
//
//        Log.d("GameUser", "Players displayed");
//    }
//
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
