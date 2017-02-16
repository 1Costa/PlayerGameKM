package com.example.konstantin.playergamekm.Activities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konstantin.playergamekm.Classes.GameDB;
import com.example.konstantin.playergamekm.Classes.GameUser;
import com.example.konstantin.playergamekm.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DeletePlayerActivity extends AppCompatActivity{
        //implements AdapterView.OnItemLongClickListener{


//    GameDB db;
//    private TextView deletePlayerTextView;
//    private ListView playersListView;
//    private String playerToDelete;
//    private String p1_name;
//    private String p2_name;
//    SharedPreferences savedValues;
//
//    SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_player);
//
//        savedValues = PreferenceManager.getDefaultSharedPreferences(getApplication());
//
//        deletePlayerTextView = (TextView) findViewById(R.id.deletePlayerTextView);
//        playersListView = (ListView)findViewById(R.id.playersListView);
//
//
//        playersListView.setOnItemLongClickListener(this);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        this.UpdateListView();
    }

//    private void UpdateListView() {
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
//        // create and set the adapter
//        adapter =
//                new SimpleAdapter(this, data, resource, from, to);
//        playersListView.setAdapter(adapter);
//
//        Log.d("GameUser", "Players displayed");
//    }
//
//    @Override
//    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
//            // get first player name
//            if(playersListView.getChildAt(position).isEnabled())
//            {
//                view.setBackgroundColor(Color.parseColor("#FF42A5F5"));
//                HashMap<String, Object> obj = (HashMap<String, Object>) adapter.getItem(position);
//                String name = (String) obj.get("name");
//                playerToDelete = name;
//                Log.d("Player To Delete >>>> ", name);
////                playersListView.getChildAt(position).setEnabled(false);
//
//                //Toast.makeText(this, "first selected", Toast.LENGTH_SHORT).show();
//
//                AlertDialog diaBox = AskOption(playerToDelete, view);
//                diaBox.show();
//
//
//            }
//        return false;
//    }
//
//    private AlertDialog AskOption(final String player, final View view)
//    {
//        savedValues = PreferenceManager.getDefaultSharedPreferences(getApplication());
//        p1_name = savedValues.getString("p1_name","name1");
//        p2_name = savedValues.getString("p2_name","name2");
//        Log.d("Shares Name 1: ", p1_name);
//        Log.d("Shares Name 2: ", p2_name);
////        SharedPreferences.Editor editor = savedValues.edit();
////        if(player.equals(p1_name) || player.equals(p2_name)) {
////
////            editor.clear();
//////            editor.putString("p1_name", null);
//////            editor.putString("p2_name", null);
//////            editor.putInt("first_p_image", 0);
//////            editor.putInt("second_p_image", 0);
//////            editor.putString("p1_name_temp", null);
//////            editor.putString("p2_name_temp", null);
////            editor.commit();
////
////
////            Log.d("Delete from Shares 1: ", p1_name);
////            Log.d("Delete from Shares  2: ", p2_name);
////        }
//
//        AlertDialog deletePlayerDialogBox =new AlertDialog.Builder(this)
//                //sets in default order: icon, title and message
//                //The icon original 256x256 was taken from:
//                // https://www.iconfinder.com/icons/112426/bin_trash_can_waste_icon
//                .setIcon(R.drawable.delete)
//                .setTitle("Delete")
//                .setMessage("Delete Player:  " +'"'+player+'"'+ " ?")
//
//                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        //do delete player code
//                        db = new GameDB(DeletePlayerActivity.this);
//                        int rowDeleted = db.deletePlayer(player);
//                        if(rowDeleted == 1){
//                            DeletePlayerActivity.this.UpdateListView();
//                            Toast.makeText(DeletePlayerActivity.this, "Player " + player.toUpperCase() + " deleted!", Toast.LENGTH_SHORT).show();
//                            if( (p1_name.equals(player)) || (p2_name.equals(player)) ) {
//                                SharedPreferences.Editor editor = savedValues.edit();
//                                editor.clear();
//                                editor.commit();
//                                Log.d("Delete from Shares 1: ", p1_name);
//                                Log.d("Delete from Shares  2: ", p2_name);
//                            }
//                        }
//                        dialog.dismiss();
//                    }
//                })
//
//                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        view.setBackgroundColor(0);
//                        dialog.dismiss();
//                    }
//                })
//                .create();
//        return deletePlayerDialogBox;
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
