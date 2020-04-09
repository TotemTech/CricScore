package com.totem.cricscore.Other;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.totem.cricscore.R;
import com.totem.cricscore.model.Team;

import java.util.List;

public class TeamScreenAdapter extends RecyclerView.Adapter<TeamScreenAdapter.MyViewHolder> {

    private List<Team> teamList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView teamName;

        public MyViewHolder(View view) {
            super(view);
            teamName = (TextView) view.findViewById(R.id.teamName);

        }
    }

    public TeamScreenAdapter(List<Team> teamList) {
        this.teamList = teamList;
    }

    public TeamScreenAdapter() {
    }


    @NonNull
    @Override
    public TeamScreenAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.teamName.setText(team.getTeamName());
       /* holder.genre.setText(movie.getGenre());
        holder.year.setText(movie.getYear());*/
    }


    @Override
    public int getItemCount() {
        return teamList.size();
    }
}
