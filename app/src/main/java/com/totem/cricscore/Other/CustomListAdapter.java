package com.totem.cricscore.Other;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.totem.cricscore.R;
import com.totem.cricscore.model.Team;

import java.util.List;
import java.util.Locale;

public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Team> teamList;
    private List<Team> filteredList;

    public CustomListAdapter(Activity activity, ListView listView) {
        this.activity = activity;
//        this.teamList = ticketList;
//        this.filteredList = ticketList;
    }

    public long getId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return teamList.size();
    }

    @Override
    public Object getItem(int position) {
        return teamList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE
            );
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

//        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView desc = (TextView) convertView.findViewById(R.id.teamName);

//        ImageView image = (ImageView) convertView.findViewById(R.id.thumbnail);

        Team team = teamList.get(position);
//        id.setText("T" + team.getTeamId());
        desc.setText(team.getTeamName());

       /* String img = team.getstatus();
        if (img.equals("1")) {
            image.setImageResource(R.drawable.red);

        } else if (img.equals("2")) {
            image.setImageResource(R.drawable.yellow);

        } else if (img.equals("3")) {
            image.setImageResource(R.drawable.yellow_warning);

        } else if (img.equals("4")) {
            image.setImageResource(R.drawable.green);

        } else if (img.equals("5")) {
            image.setImageResource(R.drawable.black);

        } else if (img.equals("10")) {
            image.setImageResource(R.drawable.error);

        }*/
//        image.setImageResource(R.drawable.red);
        //Toast.makeText(convertView.getContext(),img,Toast.LENGTH_LONG).show();

        return convertView;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        teamList.clear();
        if (charText.length() == 0) {
            teamList.addAll(filteredList);
        } else {
            for (Team wp : filteredList) {
                if (wp.getTeamName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    teamList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
