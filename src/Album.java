import java.io.File;
import java.util.ArrayList;

public class Album extends MusicPlayer{
    protected String albumName; //name of the album
    ArrayList<SoundClip> albumSongs = new ArrayList<SoundClip>(); //List of songs
    private Album parentAlbum =  null;

    public Album(String albumName){ //creates root albums
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
    public ArrayList<SoundClip> getAlbumSongs(String albumName){
        return
    }
    public void addSongsToAlbum(SoundClip song){ //adds songs to the album
        this.albumSongs.add(song);
    }
    public void removeSongsFromAlbum(int index){ //removes song from the album
        /*
        this.albumSongs.get(index).remove(idk);
         */
    }
    private void removeAlbum(String albumName){ //removes the album
        this.albumName = null;
    }

    //rename method

    private boolean checkAlbumsForSong(String song){ //Returns true if the searchd song is in the album, false if not.
        for(int i=0; i<albumSongs.size(); i++) {
            if (albumSongs.get(i).equals(song)) {
                return true;
            }
        }
        return false;

    }

}
