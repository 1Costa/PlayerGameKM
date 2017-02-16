package com.example.konstantin.playergamekm.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konstantin.playergamekm.Activities.MainActivity;
import com.example.konstantin.playergamekm.Classes.GameDB;
import com.example.konstantin.playergamekm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameEmulatorFragment extends Fragment implements View.OnClickListener {


    public GameEmulatorFragment() {
        // Required empty public constructor
    }
    private TextView p1_NameTextView;
    private TextView p2_NameTextView;
    private ImageView p1_ImageView;
    private ImageView p2_ImageView;
    private Button buttonPlayerOneWins;
    private Button buttonPlayerTwoWins;
    private Button buttonTie;
    private Button buttonQuitGame;
    private String p1_name = "";
    private String p2_name = "";
    int i1;
    int i2;
    //    String image_1 = "";
//    String image_2 = "";
//    int image_1_for_p1;
//    int image_2_for_p2;
//    final Random randomImage = new Random();
    GameDB db;
    SharedPreferences savedValues;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_emulator, container, false);

        p1_NameTextView = (TextView)view.findViewById(R.id.p1_NameTextView);
        p2_NameTextView = (TextView)view.findViewById(R.id.p2_NameTextView);
        p1_ImageView = (ImageView)view.findViewById(R.id.p1_imageView);
        p2_ImageView = (ImageView)view.findViewById(R.id.p2_imageView);
        buttonPlayerOneWins = (Button)view.findViewById(R.id.buttonPlayerOneWins);
        buttonPlayerTwoWins = (Button)view.findViewById(R.id.buttonPlayerTwoWins);
        buttonTie = (Button)view.findViewById(R.id.buttonTie);
        buttonQuitGame = (Button)view.findViewById(R.id.buttonQuitGame);

        buttonPlayerOneWins.setOnClickListener(this);
        buttonPlayerTwoWins.setOnClickListener(this);
        buttonTie.setOnClickListener(this);
        buttonQuitGame.setOnClickListener(this);

        p1_name = savedValues.getString("p1_name", "n1");
        p2_name = savedValues.getString("p2_name", "n2");
        p1_NameTextView.setText(p1_name);
        p2_NameTextView.setText(p2_name);
        //setRandomImagesToImageViews();
        buttonPlayerOneWins.setText(p1_name + " Wins");
        buttonPlayerTwoWins.setText(p2_name + " Wins");

        i1 = savedValues.getInt("first_p_image", 1);
        i2 = savedValues.getInt("second_p_image", 2);
        p1_ImageView.setImageResource(i1);
        p2_ImageView.setImageResource(i2);



        return  view;
        //return inflater.inflate(R.layout.fragment_game_emulator, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();

        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        String nameCheck = savedValues.getString("p1_name", null);
        if(nameCheck == null){
            Log.d("SH CHECK NULL ", "GOT NULL ON NAMES");
            p1_name = null;
            p2_name = null;
        }
        else {
            SharedPreferences.Editor editor = savedValues.edit();

            editor.putString("p1_name", p1_name);
            editor.putString("p2_name", p2_name);
            editor.putInt("first_p_image", i1);
            editor.putInt("second_p_image", i2);
            editor.commit();
            Log.d("onPause image 1: ", String.valueOf(i1));
            Log.d("onPause image 2: ", String.valueOf(i2));
        }
    }

    @Override
    public void onResume() {
        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());
        String nameCheck = savedValues.getString("p1_name", null);
        if(nameCheck == null){
            Log.d("SH CHECK NULL ", "GOT NULL ON NAMES");
            p1_NameTextView.setText(savedValues.getString("p1_name", "No Data"));
            p2_NameTextView.setText(savedValues.getString("p2_name", "No Data"));
            buttonPlayerOneWins.setText("No Data");
            buttonPlayerTwoWins.setText("No Data");
            buttonTie.setText("No Data");
            buttonPlayerOneWins.setEnabled(false);
            buttonPlayerTwoWins.setEnabled(false);
            buttonTie.setEnabled(false);

            p1_ImageView.setImageResource(getResources().getIdentifier("no_image" , "drawable", getActivity().getPackageName()));
            p2_ImageView.setImageResource(getResources().getIdentifier("no_image" , "drawable", getActivity().getPackageName()));
        }
        else {
            p1_NameTextView.setText(savedValues.getString("p1_name", "No Data"));
            p2_NameTextView.setText(savedValues.getString("p2_name", "No Data"));
            buttonPlayerOneWins.setText(p1_name + " Wins");
            buttonPlayerTwoWins.setText(p2_name + " Wins");
            buttonTie.setText(R.string.buttonTie);
            buttonPlayerOneWins.setEnabled(true);
            buttonPlayerTwoWins.setEnabled(true);
            buttonTie.setEnabled(true);
            p1_ImageView.setImageResource(savedValues.getInt("first_p_image", 1));
            p2_ImageView.setImageResource(savedValues.getInt("second_p_image", 2));
            Log.d("onResume image 1: ", String.valueOf(savedValues.getInt("first_p_image", 1)));
            Log.d("onResume image 2: ", String.valueOf(savedValues.getInt("second_p_image", 2)));

        }
        super.onResume();// AFTER CODE
    }

    /*private void setRandomImagesToImageViews() {
        // Images were downloaded from
        // http://all-free-download.com/free-icon/download/free-plush-icons-set-icons-pack_120817.html
        // I have 6 images named img_0 to img_5, so...

        image_1 = "img_" + randomImage.nextInt(6);
        image_2 = "img_" + randomImage.nextInt(6);
        while(image_1.equals(image_2)){
            image_2 = "img_" + randomImage.nextInt(6);
            }
        image_1_for_p1 = getResources().getIdentifier(image_1 , "drawable", getPackageName());
        image_2_for_p2 = getResources().getIdentifier(image_2 , "drawable", getPackageName());
        p1_ImageView.setImageResource(image_1_for_p1);
        p2_ImageView.setImageResource(image_2_for_p2);
        Log.d("Gen image 1: ", String.valueOf(image_1_for_p1));
        Log.d("Gen image 2: ", String.valueOf(image_2_for_p2));

    }*///setRandomImagesToImageViews();

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonPlayerOneWins:
                doOneWinsSecondLosses();
                break;
            case R.id.buttonPlayerTwoWins:
                doTwoWinsFirstLosses();
                break;
            case R.id.buttonTie:
                doTie();
                break;
            case R.id.buttonQuitGame:
                Intent returnToMainActivity = new Intent(getActivity(), MainActivity.class);
                startActivity(returnToMainActivity);
        }

    }


    private void doOneWinsSecondLosses() {
        //String winner = p1_name;
        db = new GameDB(getActivity());
        long update = db.updateWinsAndLosses(p1_name, p2_name);
        if(update == 2) {
            Toast.makeText(getActivity().getBaseContext(),"Winner " + p1_name + "\n Loser " + p2_name, Toast.LENGTH_SHORT).show();
        }

    }

    private void doTwoWinsFirstLosses() {
        //String winner = p2_name;
        db = new GameDB(getActivity());
        long update = db.updateWinsAndLosses(p2_name, p1_name);
        if(update == 2) {
            Toast.makeText(getActivity().getBaseContext(),"Winner " + p2_name + "\n Loser " + p1_name, Toast.LENGTH_SHORT).show();
        }
    }

    private void doTie() {
        //do Tie
        db = new GameDB(getActivity());
        long updateTie = db.updateTies(p1_name, p2_name);
        if(updateTie == 2) {
            Toast.makeText(getActivity().getBaseContext(),"Ties Updated", Toast.LENGTH_SHORT).show();
        }


    }

}
