package com.example.recyclertrial;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class EPLTeamModel implements Parcelable {

    private String teamName;
    private String stadium;
    private String TeamBadge;

    protected EPLTeamModel(Parcel in) {
        teamName = in.readString();
        stadium = in.readString();
        TeamBadge = in.readString();
    }

    EPLTeamModel(){

    }

    public static final Creator<EPLTeamModel> CREATOR = new Creator<EPLTeamModel>() {
        @Override
        public EPLTeamModel createFromParcel(Parcel in) {
            return new EPLTeamModel(in);
        }

        @Override
        public EPLTeamModel[] newArray(int size) {
            return new EPLTeamModel[size];
        }
    };

    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }

    public String getStadium() { return stadium; }
    public void setStadium(String stadium) { this.stadium = stadium; }


    public String getTeamBadge() { return TeamBadge; }
    public void setTeamBadge(String teamBadge) { TeamBadge = teamBadge; }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(teamName);
        parcel.writeString(stadium);
        parcel.writeString(TeamBadge);
    }
}