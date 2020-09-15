import java.io.File;
import java.util.ArrayList;

public class SubAlbum extends Album{


    public SubAlbum(String albumName) {
        super(albumName);
    }

    public void deleteSubalbum(String albumName){
        this.albumName = null;

    }
    public void addSongsToSubAlbum(ArrayList <File> albumSongs, int index){
        this.albumSongs.add(this.albumSongs.get(index));
    }
}
