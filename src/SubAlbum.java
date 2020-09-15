import java.io.File;
import java.util.ArrayList;

public class SubAlbum extends Album{

    ArrayList<File> subAlbum = new ArrayList<File>(); //List of songs in subalbum

    public SubAlbum(String albumName) {
        super(albumName);
    }

    public void deleteSubAlbum(String albumName){
        this.albumName = null;

    }
    public void addSongsToSubAlbum(ArrayList <File> albumSongs, int index){
        this.albumSongs.add(this.albumSongs.get(index));
    }
}
