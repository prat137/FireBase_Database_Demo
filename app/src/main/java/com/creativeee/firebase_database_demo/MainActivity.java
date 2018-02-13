package com.creativeee.firebase_database_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    Spinner spinner;
    DatabaseReference databaseArtist;
    ListView listViewartist;
    List<Artist> artistList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseArtist= FirebaseDatabase.getInstance().getReference("artist");
        editText=findViewById(R.id.editTextname);
        button=findViewById(R.id.addartist);
        spinner=findViewById(R.id.spinnergenre);
        listViewartist=findViewById(R.id.listviewartist);
        artistList=new ArrayList<>();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addArtist();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseArtist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artistList.clear();
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){
                    Artist artist=artistSnapshot.getValue(Artist.class);
                    artistList.add(artist);

                }
                ArtistList adapter=new ArtistList(MainActivity.this,artistList);
                listViewartist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addArtist(){
        String name=editText.getText().toString().trim();
        String genre=spinner.getSelectedItem().toString();
        if(!TextUtils.isEmpty(name)){
           String id= databaseArtist.push().getKey();
           Artist artist=new Artist(id,name,genre);
           databaseArtist.child(id).setValue(artist);
           Toast.makeText(this,"artistadded",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Empty",Toast.LENGTH_SHORT).show();
        }
    }
}
