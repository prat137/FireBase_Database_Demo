package com.creativeee.firebase_database_demo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Dell on 13-02-2018.
 */

public class ArtistList extends ArrayAdapter <Artist>{
    private Activity context;
    private List<Artist> artistList;
    public ArtistList(Activity context,List<Artist> artistList){
        super(context,R.layout.list_layout,artistList);
        this.context=context;
        this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.list_layout,null,true);
        TextView textViewname= listViewItem.findViewById(R.id.textViewname);
        TextView textViewgenre= listViewItem.findViewById(R.id.textviewgenre);
        Artist artist=artistList.get(position);
        textViewname.setText(artist.getArtistname());
        textViewgenre.setText(artist.getArtistGenre());
        return listViewItem;
    }
}
