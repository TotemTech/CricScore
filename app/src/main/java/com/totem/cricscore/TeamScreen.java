package com.totem.cricscore;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.totem.cricscore.Other.TeamScreenAdapter;
import com.totem.cricscore.model.Team;

import java.util.ArrayList;

public class TeamScreen extends AppCompatActivity {

    private FloatingActionButton fabButton;
    final ArrayList<Team> teamList = new ArrayList<Team>();
    private TextView teamName;
    private RecyclerView recyclerView;
    private TeamScreenAdapter teamAdapter;
    Team team = new Team();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Team");
        // Team team = new Team();

        setContentView(R.layout.activity_team_screen);
        fabButton = findViewById(R.id.fab_button);
//        teamList = findViewById(R.id.team_list);
        teamName = findViewById(R.id.teamName);

        recyclerView = (RecyclerView) findViewById(R.id.teamRecycler);

        teamAdapter = new TeamScreenAdapter(teamList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(teamAdapter);

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
                        team.setTeamName(usernameInput.getText().toString());
                        teamList.add(team);
                        // adapter.add(usernameInput.getText().toString());
                        teamAdapter.notifyDataSetChanged();
                    }
                });

                alert.show();

            }
        });
    }
}
