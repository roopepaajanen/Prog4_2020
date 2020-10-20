import java.util.LinkedList;
import java.util.List;

public abstract class AbstractAlbum {
    protected String albumName; //name of the album
    protected List<SoundClip> albumSoundClips;

    public AbstractAlbum(String albumName){
        albumSoundClips = new LinkedList<SoundClip>();
        this.albumName = albumName;
    }
    abstract void addSoundClips(List<SoundClip> clipsToAdd);
    abstract void addSoundClip(SoundClip clip);

    abstract void removeSoundClips(List<SoundClip> clipsToRemove);
    abstract void removeSoundClip(SoundClip clip);

    public List<SoundClip> getSoundClipsFromAlbum(){return albumSoundClips;}
}
