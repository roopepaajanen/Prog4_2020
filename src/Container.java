import java.util.List;

/**
 * If undo and redo would be called after each other the code would not recognize which album was selected since
 * it is an input that can only be invoked by the user. That is why we have a Container class that saves every single
 * album selected and every list of SoundClips for every action made.
 */
public class Container {
    private Album albumATM; //ATM = At The Moment
    private List<SoundClip> albumATMSoundClips;

    public Container(Album album, List<SoundClip> clips){
        albumATM = album;
        albumATMSoundClips = clips;
    }
    public Album getAlbumATM(){ /**	Gets the latest album */
        return albumATM;
    }
    public List<SoundClip> getAlbumATMSoundClips(){ /**	Gets the latest sound clip */
        return albumATMSoundClips;
    }
}
