package com.totem.cricscore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


//import cn.pedant.SweetAlert.SweetAlertDialog;

public class DashboardScreen extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout createLayout;
    private LinearLayout openLayout;
    private LinearLayout resultLayout;
    private GridLayout mainGrid;
    private LinearLayout teamScreen;
    private LinearLayout playerScreen;
    private Bundle bun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Dashboard");
        setContentView(R.layout.activity_dashboard_screen);
        createLayout = findViewById(R.id.create_layout);
        openLayout = findViewById(R.id.open_layout);
        resultLayout = findViewById(R.id.result_layout);
        mainGrid = findViewById(R.id.mainGrid);
        teamScreen = findViewById(R.id.team_screen);
        playerScreen = findViewById(R.id.player_screen);

        createLayout.setOnClickListener(this);
        openLayout.setOnClickListener(this);
        resultLayout.setOnClickListener(this);
        teamScreen.setOnClickListener(this);
        playerScreen.setOnClickListener(this);

        bun = getIntent().getExtras();


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.create_layout:
                Intent create = new Intent(this, CreateMatchScreen.class);
                create.putExtras(bun);
                startActivity(create);
                // startActivity(new Intent(this, CreateMatchScreen.class));
                break;
            case R.id.open_layout:
                Intent open = new Intent(this, OpenMatchScreen.class);
                open.putExtras(bun);
                startActivity(open);
                // startActivity(new Intent(this, OpenMatchScreen.class));
                break;
            case R.id.result_layout:
                Intent result = new Intent(this, ResultMatchScreen.class);
                result.putExtras(bun);
                startActivity(result);
                // startActivity(new Intent(this, ResultMatchScreen.class));
                break;
            case R.id.team_screen:
                Intent team = new Intent(this, TeamScreen.class);
                team.putExtras(bun);
                startActivity(team);
                // startActivity(new Intent(this, TeamScreen.class));
                break;
            case R.id.player_screen:
                Intent player = new Intent(this, PlayerScreen.class);
                player.putExtras(bun);
                startActivity(player);
                //startActivity(new Intent(this, PlayerScreen.class));
                break;
        }

    }



  /*  @Override
    public void onBackPressed() {
       // super.onBackPressed();

        new SweetAlertDialog(DashboardScreen.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Are you sure?")
                .setContentText("Do you want to exit")
                .setCancelText("Yes")
                .setConfirmText("No")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            finishAffinity();
                        }
                    }
                })
                .show();

    }*/

    @Override
    public void onBackPressed() {
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(DashboardScreen.this);
            builder.setTitle("Exit?");
            builder.setMessage("Do you want to Exit");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

}
