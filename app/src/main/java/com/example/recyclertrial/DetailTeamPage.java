package com.example.recyclertrial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailTeamPage extends AppCompatActivity {

    Intent i;
    EPLTeamModel eplteammodel;
    TextView tvTeamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team_page);

        i = getIntent();
        eplteammodel = (EPLTeamModel) i.getParcelableExtra("eplteam");
        System.out.println("my team name : "+ eplteammodel.getTeamName());
        tvTeamName = findViewById(R.id.tvTeamName);
        tvTeamName.setText(eplteammodel.getTeamName());
    }
}