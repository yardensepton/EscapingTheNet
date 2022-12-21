package com.example.escapingthenet.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.escapingthenet.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Player> {
    private final Context context;
    private final int resource;

    public ListAdapter(Context context, int resource, ArrayList<Player> players){
        super(context,resource,players);
        this.context = context;
        this.resource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource,null);
        }

        Player player = getItem(position);

        if (player != null){
            TextView LV_name = v.findViewById(R.id.LV_name);
            TextView LV_score = v.findViewById(R.id.LV_score);

            if (LV_name != null){
                LV_name.setText(""+player.getName());
            }


            if (LV_score != null){
                LV_score.setText(""+player.getScore());
            }
        }

        return  v;

    }

}
