import java.util.LinkedList;
import java.util.List;

public class FlaggedAlbum extends AbstractAlbum {
    private List<SoundClip> desiredAlbumSoundClips; //List of sound clips

    public FlaggedAlbum(String albumName) {
        super(albumName);
        desiredAlbumSoundClips = new LinkedList<SoundClip>();
    }

    @Override
    void addSoundClips(List<SoundClip> clipsToAdd) {

    }

    @Override
    void addSoundClip(SoundClip clip) {

    }

    @Override
    void removeSoundClips(List<SoundClip> clipsToRemove) {

    }

    @Override
    void removeSoundClip(SoundClip clip) {

    }

    public String toString(){return "Flagged Sound Clips";}

}
