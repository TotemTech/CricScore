package com.totem.cricscore;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.totem.cricscore.Other.AppSingleton;
import com.totem.cricscore.Other.Constants;
import com.totem.cricscore.Other.TeamScreenAdapter;
import com.totem.cricscore.model.Team;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamScreen extends AppCompatActivity {

    private FloatingActionButton fabButton;
    List<Team> teamList = new ArrayList<Team>();
    private TextView teamName;
    private RecyclerView recyclerView;
    private TeamScreenAdapter teamAdapter;
    String TeamListURL = Constants.listTeam_URL;
    private Bundle bun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Team");
        setContentView(R.layout.activity_team_screen);
        fabButton = findViewById(R.id.fab_button);
        teamName = findViewById(R.id.teamName);
        recyclerView = (RecyclerView) findViewById(R.id.teamRecycler);
        Toast.makeText(this, "Inside Team", Toast.LENGTH_SHORT).show();
       /* final ProgressDialog progressDialog=new ProgressDialog(TeamScreen.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...");*/
        bun = getIntent().getExtras();

        teamAdapter = new TeamScreenAdapter(teamList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(teamAdapter);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, TeamListURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               /* try {
                    progressDialog.dismiss();
                    Toast.makeText(TeamScreen.this, "Progress dialog", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(TeamScreen.this, "Exception in progress bar", Toast.LENGTH_SHORT).show();
                }*/
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray listTeam = jsonObject.getJSONArray("listTeam");
                    Toast.makeText(TeamScreen.this, listTeam.toString(), Toast.LENGTH_SHORT).show();
                    if (listTeam.length() > 0) {
                        for (int i = 0; i < listTeam.length(); i++) {
                            JSONObject team = listTeam.getJSONObject(i);
                            Team team1 = new Team();
                            team1.setTeamId(team.getInt("Team_id"));
                            team1.setTeamName(team.getString("Team_name"));
                            team1.setTournamentId(team.getInt("Tournament_id"));
                            Toast.makeText(TeamScreen.this, team.getString("Team_name"), Toast.LENGTH_SHORT).show();
                            teamList.add(team1);

                        }
                        teamAdapter.notifyDataSetChanged();
                    } else {
                        View linearLayout = findViewById(R.id.teamLinearLayout);
                        TextView valueTV = new TextView(TeamScreen.this);
                        valueTV.setText("hallo hallo");
//                        valueTV.setId(5);
                        valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                        ((LinearLayout) linearLayout).addView(valueTV);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(TeamScreen.this, "Error in Response Lst", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String tourid = String.valueOf(bun.getInt("tour_id"));
                params.put("tour_id", tourid);
                return params;
            }
        };
        AppSingleton.getInstance(TeamScreen.this).addToRequestQueue(stringRequest);
        ;

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
                        Team team = new Team();
                        team.setTeamName(usernameInput.getText().toString());
                        teamList.add(team);
                        teamAdapter.notifyDataSetChanged();
                    }
                });
                alert.show();

            }
        });
        //END of fabButton



    }
}
