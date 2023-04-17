package com.example.recyclertrial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactAdapter.ContactAdapterListener {

    RecyclerView rvListData;
    ArrayList<EPLTeamModel> listDataEPLTeams;
    private ContactAdapter adapterListContact;

    public void getEPLOnline(){
        ProgressBar progressBar = findViewById(R.id.progressbar);
        String url = "https://www.thesportsdb.com/api/v1/json/3/search_all_teams.php?l=English%20Premier%20League";
        AndroidNetworking.get(url)
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject success) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                        try {
                            JSONArray jsonArrayEPLTeam = success.getJSONArray("teams");
                            for (int i = 0; i <jsonArrayEPLTeam.length(); i++) {
                                EPLTeamModel team = new EPLTeamModel();
                                JSONObject jsonTeam = jsonArrayEPLTeam.getJSONObject(i);
                                team.setTeamName(jsonTeam.getString("strTeam"));
                                team.setStadium(jsonTeam.getString("strStadium"));
                                team.setTeamBadge(jsonTeam.getString("strTeamBadge"));
                                listDataEPLTeams.add(team);

                            }

                            rvListData = findViewById(R.id.rvlistdata);
                            adapterListContact = new ContactAdapter(getApplicationContext(), listDataEPLTeams,MainActivity.this);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            rvListData.setHasFixedSize(true);
                            rvListData.setLayoutManager(mLayoutManager);
                            rvListData.setAdapter(adapterListContact);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.d("failed", "onErrorr: "+error.toString());
                    }
                });
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_layout);
        getSupportActionBar().hide();

        listDataEPLTeams = new ArrayList<>();
        getEPLOnline();

    }

    @Override
    public void onContactSelected(EPLTeamModel team) {
        Toast.makeText(this,"selected name"+ team.getTeamName(), Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(MainActivity.this, DetailTeamPage.class);
//        intent.putExtra("eplteam", team);
//        startActivity(intent);

    }
}