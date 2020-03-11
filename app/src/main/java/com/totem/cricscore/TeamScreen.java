package com.totem.cricscore;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TeamScreen extends AppCompatActivity {

    private FloatingActionButton fabButton;
    private ListView teamList;
    ArrayAdapter<String> adapter;
    ArrayList<String> listItems = new ArrayList<String>();
    private TextView teamName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Team");

        setContentView(R.layout.activity_team_screen);
        fabButton = findViewById(R.id.fab_button);
        teamList = findViewById(R.id.team_list);
        teamName = findViewById(R.id.teamName);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);


//        adapter = new CustomListAdapter(this, teamList);
        teamList.setAdapter(adapter);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(TeamScreen.this);
                LayoutInflater inflater = TeamScreen.this.getLayoutInflater();
                //this is what I did to added the layout to the alert dialog
                View layout = inflater.inflate(R.layout.prompts, null);
                alert.setView(layout);
                final EditText usernameInput = (EditText) layout.findViewById(R.id.prompt);
                alert.setTitle("Add Team");
                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //listItems.add(usernameInput.getText().toString());
                        adapter.add(usernameInput.getText().toString());
                        adapter.notifyDataSetChanged();
                    }
                });

                alert.show();

            }
        });
    }
}
