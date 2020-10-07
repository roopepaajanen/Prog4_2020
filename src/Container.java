import java.util.List;

public class Container {
    private Album albumATM; //ATM = At The Moment
    private List<SoundClip> albumATMSoundClips;

    public Container(Album album, List<SoundClip> clips){
        albumATM = album;
        albumATMSoundClips = clips;
    }
    public Album getAlbumATM(){
        return albumATM;
    }
    public List<SoundClip> getAlbumATMSoundClips(){
        return albumATMSoundClips;
    }
}
