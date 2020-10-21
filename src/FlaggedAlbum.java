import java.util.LinkedList;
import java.util.List;

public class FlaggedAlbum extends AbstractAlbum {

    public FlaggedAlbum(String albumName) {
        super(albumName);
        desiredAlbumSoundClips = new LinkedList<SoundClip>();
    }

    @Override
    void addSoundClips(List<SoundClip> clipsToAdd) {
        for(SoundClip s : clipsToAdd) {
            this.addSoundClip(s);
        }
    }

    @Override
    void addSoundClip(SoundClip clip) {
        if (!desiredAlbumSoundClips.contains(clip)){
            desiredAlbumSoundClips.add(clip);
        }
    }

    @Override
    boolean containsClip(SoundClip clip) {
        return desiredAlbumSoundClips.contains(clip);
    }

    @Override
    void removeSoundClips(List<SoundClip> clipsToRemove) {
        for(SoundClip s : clipsToRemove) {
            this.removeSoundClip(s);
        }
    }

    @Override
    void removeSoundClip(SoundClip clip) {
        desiredAlbumSoundClips.remove(clip);
    }

    public String toString(){
        return "Flagged Sound Clips";
    }
}
