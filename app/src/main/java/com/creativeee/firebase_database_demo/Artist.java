package com.creativeee.firebase_database_demo;

/**
 * Created by Dell on 13-02-2018.
 */

public class Artist {
    String artistid;
    String artistname;
    String artistGenre;
     public Artist()
     {

     }

    public Artist(String artistid, String artistname, String artistGenre) {
        this.artistid = artistid;
        this.artistname = artistname;
        this.artistGenre = artistGenre;
    }

    public String getArtistid() {
        return artistid;
    }

    public String getArtistname() {
        return artistname;
    }

    public String getArtistGenre() {
        return artistGenre;
    }
}
