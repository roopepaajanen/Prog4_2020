import java.io.File;
import java.util.ArrayList;

public class Album { //class to create album instances.
    private String albumName; //name of the album
    ArrayList<SoundClip> albumSongs = new ArrayList<SoundClip>(); //List of songs
    ArrayList<Album> subAlbumList = new ArrayList<Album>(); //List of sub albums for every parent album
    private Album parentAlbum =  null;

    public Album(String albumName){ //creates the root album
        this.albumName = albumName;
        parentAlbum = null;
    }
    public Album(String albumName, Album parentAlbum){ //creates sub albums
        this.albumName = albumName;
        parentAlbum = parentAlbum;
    }

    public String getAlbumName(){ //gets the name of the album
        return this.albumName;
    }

    /*public void addSongsToAlbum(SoundClip song){ //adds songs to the album
        this.albumSongs.add(song);
    }
    public void removeSongsFromAlbum(int index){ //removes song from the album

    }
    public boolean checkAlbumForSong(SoundClip song){ //Returns true if the searched song is in the album, false if not.
        return albumSongs.contains(song);
    }
    */
}
