import java.io.File;
import java.util.LinkedList;
import java.util.List;

//class to create album objects.
public class Album {
    private String albumName; //name of the album
    private List<SoundClip> desiredAlbumSoundClips; //List of sound clips
    private List<Album> subAlbumList; //List of sub albums for every parent album
    private Album parentAlbum;

    Album(String albumName) { //constructor that creates the root album
        this.albumName = albumName;
        parentAlbum = null;
        desiredAlbumSoundClips = new LinkedList<SoundClip>();
        subAlbumList = new LinkedList<Album>();
    }
    Album(String albumName, Album parentAlbum) { //constructor that creates sub albums
        this.albumName = albumName;
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

    //adds the desired sound clip to the desired album's sound clip list
    public void addSoundClip(SoundClip clip) {
        if(parentAlbum != null && !parentAlbum.desiredAlbumSoundClips.contains(clip)) {
            parentAlbum.addSoundClip(clip);
        }
        if (!desiredAlbumSoundClips.contains(clip)){
            desiredAlbumSoundClips.add(clip);
        }
    }

    //removes sound clip from specified Album recursively
    public void removeSoundClip(SoundClip clipToBeRemoved) {
        for (Album album : subAlbumList) {
            album.removeSoundClip(clipToBeRemoved);
        }
        desiredAlbumSoundClips.remove(clipToBeRemoved);
    }

}
