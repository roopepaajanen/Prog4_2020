import java.io.File;

/**
 * SoundClip is a class representing a digital
 * sound clip file on disk.
 */
public class SoundClip {

    private final File file;
    private int rating;

    /**
     * Make a SoundClip from a file.
     * Requires file != null.
     */
    public SoundClip(File file) {
        assert file != null;
        this.file = file;
        this.rating = 0;
    }

    /**
     * @return the file containing this sound clip.
     */
    public File getFile() {
        return file;
    }

    public String toString(){
        return file.getName();
    }

    public int getRating(){
        return rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    @Override
    public boolean equals(Object obj) {
        return
                obj instanceof SoundClip
                        && ((SoundClip)obj).file.equals(file);
    }

    @Override
    public int hashCode() {
        return file.hashCode();
    }
}
