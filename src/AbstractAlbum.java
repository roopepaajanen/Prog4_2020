import java.util.List;

public abstract class AbstractAlbum {
    private List<SoundClip> desiredAlbumSoundClips; //List of sound clips in the album
    protected List<SoundClip> soundClips;
    public AbstractAlbum(){
        //soundClips = new List<>();
    }
    abstract void addSoundClips(List<SoundClip> clipsToAdd);
    abstract void addSoundClip(SoundClip clip);

    abstract void removeSoundClips(List<SoundClip> clipsToRemove);
    abstract void removeSoundClip(SoundClip clip);

    public List<SoundClip> getSoundClipsFromAlbum(){

        return null;
    }
}
