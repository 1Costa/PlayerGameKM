package com.example.konstantin.playergamekm.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.konstantin.playergamekm.Classes.GameDB;
import com.example.konstantin.playergamekm.Classes.GameUser;
import com.example.konstantin.playergamekm.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewScoreboardFragment extends Fragment {
    GameDB db;
    SimpleAdapter adapter;
    private ListView scoreBoardListView;

    public ViewScoreboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setTitle(R.string.scoreboardTable);
        //updateScoreBoard();
        setHasOptionsMenu(true);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_scoreboard, container, false);

        scoreBoardListView = (ListView)view.findViewById(R.id.scoreBoardListView);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateScoreBoard();
    }

    public void updateScoreBoard() {
        // do update
        db = new GameDB(getActivity());
        ArrayList<GameUser> players = db.getUsers();

        // create a List of Map<String, ?> objects
        ArrayList<HashMap<String, String>> data =
                new ArrayList<HashMap<String, String>>();
        for (GameUser item : players) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", item.getName());
            map.put("wins", String.valueOf(item.getWins()));
            map.put("losses", String.valueOf(item.getLosses()));
            map.put("ties", String.valueOf(item.getTies()));
            data.add(map);
        }

        // create the resource, from, and to variables
        int resource = R.layout.scoreboard_row;
        String[] from = {"name","wins","losses","ties"};
        int[] to = {R.id.playerName, R.id.winsTextView, R.id.lossesTextView, R.id.tiesTextView};

        // create and set the adapter
        adapter =
                new SimpleAdapter(this.getActivity(), data, resource, from, to);
        scoreBoardListView.setAdapter(adapter);

        Log.d("GameUser", "Players displayed");
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
