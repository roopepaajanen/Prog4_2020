import java.io.File;
import java.util.ArrayList;

public class Album {
    private String albumName; //name of the album
    ArrayList<File> albumSongs = new ArrayList<File>(); //List of songs

    public Album(String albumName){
        this.albumName = albumName;
    }

    private String getAlbumName(){ //gets the name of the album
        return this.albumName;
    }
    private void addSongsToAlbum(File song){ //adds songs to the album
        this.albumSongs.add(song);
    }
    private void removeSongsFromAlbum(int index){ //removes song from the album
        /*
        this.albumSongs.get(index).remove(idk);
         */
    }
    private void removeAlbum(String albumName){ //removes the album
        this.albumName = null;
    }

}
