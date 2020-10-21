import java.io.File;

/**
 * SoundClip is a class representing a digital
 * sound clip file on disk.
 */
public class SoundClip {

    private final File file;
    private int rating;
    private String color;

    /**
     * Make a SoundClip from a file.
     * Requires file != null.
     */
    public SoundClip(File file) {
        assert file != null;
        this.file = file;
        this.rating = 0;
        color = "#000080"; // Navy Blue, lol XD
    }

    /**
     * @return the file containing this sound clip.
     */
    public File getFile() {
        return file;
    }

    /**
     * @return a little fun html and hexadecimal color combination to display a nice GUI. XD
     */
    public String toString(){
        String originalString = "<html><pre><font color= \"" + color + "\">" + file.getName() + "</font>";
        String ratingString = "<font color=" + "#EE82EE" + ">"+ rating + "</font></pre></html>";
        String spacing = "<font color=" + "#696969" + ">" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Rate: " + "</font>";

        return (originalString + spacing + ratingString);
    }

    /**
     * @set the color of the text
     */
    public void setColor(String color){
        this.color = color;
    }

    /*public int getRating(){
        return rating;
    }*/

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
