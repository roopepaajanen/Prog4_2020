import java.util.LinkedList;
import java.util.List;

public class RatedAlbum extends AbstractAlbum {
    private List<SoundClip> desiredAlbumSoundClips; //List of sound clips

    public RatedAlbum(String albumName) {
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

    public String toString(){return "Rated Sound Clips";}
}
