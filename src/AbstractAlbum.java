import java.util.LinkedList;
import java.util.List;

public abstract class AbstractAlbum {
    protected String albumName; //name of the album
    protected List<SoundClip> desiredAlbumSoundClips;

    public AbstractAlbum(String albumName){
        desiredAlbumSoundClips = new LinkedList<SoundClip>();
        this.albumName = albumName;
    }
    abstract void addSoundClips(List<SoundClip> clipsToAdd);
    abstract void addSoundClip(SoundClip clip);

    abstract boolean containsClip(SoundClip clip);

    abstract void removeSoundClips(List<SoundClip> clipsToRemove);
    abstract void removeSoundClip(SoundClip clip);

    public List<SoundClip> getSoundClipsFromAlbum(){return desiredAlbumSoundClips;}
}
