import java.io.File;
import java.util.LinkedList;
import java.util.List;

//class to create album objects.
public class Album extends AbstractAlbum{
    private List<Album> subAlbumList; //List of sub albums for every parent album
    private Album parentAlbum;

    Album(String albumName) { //constructor that creates the root album
        super(albumName);
        parentAlbum = null;
        desiredAlbumSoundClips = new LinkedList<SoundClip>();
        subAlbumList = new LinkedList<Album>();
    }
    Album(String albumName, Album parentAlbum) { //constructor that creates sub albums
        super(albumName);
        this.parentAlbum = parentAlbum;
        desiredAlbumSoundClips = new LinkedList<SoundClip>();
        subAlbumList = new LinkedList<Album>();
        parentAlbum.subAlbumList.add(this);
    }

    //accessor that returns the parent album
    public Album getParentAlbum(){
        return parentAlbum;
    }

    //gets the sound clips from the desired album and returns them as a list.
    public List<SoundClip> getSoundClipsFromAlbum() {
        return desiredAlbumSoundClips;
    }

    //checks if a sound clip is present in an album's list of sound clips.
    public boolean containsClip(SoundClip clip){
        return desiredAlbumSoundClips.contains(clip);
    }

    //toString method to return the name of the album as a String
    public String toString() {
        return albumName;
    }

    //recursive method to add a list of sound clips using the addSoundClip method that adds them individually.
    public void addSoundClips(List<SoundClip> clipsToAdd) {
        for(SoundClip s : clipsToAdd) {
            this.addSoundClip(s);
        }
    }

    //adds the desired sound clip to the desired album's sound clip list
    public void addSoundClip(SoundClip clip) {
        if(parentAlbum != null && !parentAlbum.desiredAlbumSoundClips.contains(clip)) {
            parentAlbum.addSoundClip(clip);
        }
        if (!desiredAlbumSoundClips.contains(clip)){
            desiredAlbumSoundClips.add(clip);
        }
    }

    //recursive method to remove a list of sound clips using the removeSoundClip method that removes them individually.
    public void removeSoundClips(List<SoundClip> clipsToRemove) {
        for(SoundClip s : clipsToRemove) {
            this.removeSoundClip(s);
        }
    }

    //removes sound clip from specified Album recursively
    public void removeSoundClip(SoundClip clip) {
        for (Album album : subAlbumList) {
            album.removeSoundClip(clip);
        }
        desiredAlbumSoundClips.remove(clip);
    }
}
