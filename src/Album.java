import java.io.File;
import java.util.ArrayList;

public class Album extends MusicPlayer{
    protected String albumName; //name of the album
    ArrayList<File> albumSongs = new ArrayList<File>(); //List of songs
    private Album parentAlbum =  null;

    public Album(String albumName){
        this.albumName = albumName;
        parentAlbum = null;
    }
    public Album(String albumName, Album parentAlbum){
        this.albumName = albumName;
        parentAlbum = parentAlbum;
    }

    public Object getAlbumName(){ //gets the name of the album
        return this.albumName;
    }
    public void addSongsToAlbum(File song){ //adds songs to the album
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
