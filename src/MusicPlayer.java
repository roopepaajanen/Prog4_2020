import java.io.File;

public class MusicPlayer {

    //album information

    //add and/or remove sub albums

    //add and/or remove sound files to and from albums

    //check if an album contains another album or a certain sound file


    private void playMusic(){ //play Music

    }
    private void pauseMusic(){ //pause Music

    }
    public void createAlbum(String albumName){ //create instances of class Album
        new Album(albumName);

    }
    private void addSongs(Album albumInstance, SoundClip song){ //add sound files to instance of class Album
        albumInstance.addSongsToAlbum(song);
    }

}