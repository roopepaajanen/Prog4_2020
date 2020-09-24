import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Album { //class to create album objects.
    private String albumName; //name of the album
    public List<SoundClip> desiredAlbumSoundClips; //List of sound clips
    private List<Album> subAlbumList; //List of sub albums for every parent album
    private Album parentAlbum = null;

    Album(String albumName) { //creates the root album
        this.albumName = albumName;
        parentAlbum = null;
        desiredAlbumSoundClips = new LinkedList<SoundClip>();
        subAlbumList = new LinkedList<Album>();
    }
    Album(String albumName, Album parentAlbum) { //creates sub albums
        this.albumName = albumName;
        this.parentAlbum = parentAlbum;
        desiredAlbumSoundClips = new LinkedList<SoundClip>();
        subAlbumList = new LinkedList<Album>();
        parentAlbum.subAlbumList.add(this);
    }

    //gets the parent album
    public Album getParentAlbum(){
        return parentAlbum;
    }

    //gets the sound clips from the desired album and returns them as a list.
    public List<SoundClip> getSoundClipsFromAlbum(Album desiredAlbum) {
        return desiredAlbum.desiredAlbumSoundClips;
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

    public boolean containsClip(SoundClip clip){
        return desiredAlbumSoundClips.contains(clip);
    }
}
