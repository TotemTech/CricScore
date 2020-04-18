package com.totem.cricscore;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.totem.cricscore.Other.AppSingleton;
import com.totem.cricscore.Other.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity {
    AlertDialog.Builder builder;
    private TextView mMemberLoginTv;
    private EditText mUsernameEdtext;
    private EditText mPasswdEdtext;
    private Button mLoginButton;
    private TextView mForgotPasswdTv;
    private ScrollView mScrollView;
    private ImageView mView1;
    String LoginURL= Constants.login_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        builder = new AlertDialog.Builder(LoginScreen.this);
        mMemberLoginTv = findViewById(R.id.member_login_tv);
        mUsernameEdtext = findViewById(R.id.username_edtext);
        mPasswdEdtext = findViewById(R.id.passwd_edtext);
        mLoginButton = findViewById(R.id.login_button);
        mForgotPasswdTv = findViewById(R.id.forgot_passwd_tv);
        mScrollView = findViewById(R.id.ScrollView);
        mView1 = findViewById(R.id.view_1);


        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog=new ProgressDialog(LoginScreen.this);
                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Please wait...");
                String userName = mUsernameEdtext.getText().toString();
                String password = mPasswdEdtext.getText().toString();
                if (userName.equals("") || password.equals("")) {
                    builder.setTitle("Credentials Required");
                    displayAlert("Enter UserName and Password");

                } else {
                    try {
                        progressDialog.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, LoginURL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        progressDialog.dismiss();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        JSONArray login = jsonObject.getJSONArray("login");
                                        JSONObject user=login.getJSONObject(0);
                                        String Bun_userid = user.getString("USERID");
                                        String Bun_username = user.getString("USERNAME");
                                        int Bun_tourid = user.getInt("TOURNAMENT_ID");
                                        String Bun_tourname = user.getString("TOURNAMENT_NAME");
                                        Intent intent = new Intent(LoginScreen.this, DashboardScreen.class);
                                        Bundle bun = new Bundle();
                                        bun.putString("Bun_userid", Bun_userid);
                                        bun.putString("Bun_username", Bun_username);
                                        bun.putString("Bun_tourname", Bun_tourname);
                                        bun.putInt("tour_id", Bun_tourid);
                                        intent.putExtras(bun);
                                        finish();
                                        startActivity(intent);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        builder.setTitle("Error!!!");
                                        displayAlert("Invalid Credentials");
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            try {
                                progressDialog.dismiss();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            builder.setTitle("Error!!!");
                            displayAlert("Please check network connection");
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("name", mUsernameEdtext.getText().toString());
                            params.put("pass", mPasswdEdtext.getText().toString());
                            return params;
                        }
                    };
                    AppSingleton.getInstance(LoginScreen.this).addToRequestQueue(stringRequest);
                    // startActivity(new Intent(LoginScreen.this,DashboardScreen.class));
                }
            }
        });
    }


    public void displayAlert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mUsernameEdtext.setText("");
                mPasswdEdtext.setText("");

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
