import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import java.io.File;
import static junit.framework.TestCase.*;
import static org.junit.Assert.assertNotEquals;

class AlbumTest {
    SoundClip clip = new SoundClip(new File("random_song_name"));
    Album rootAlbum = new Album("root");
    Album subi1 = new Album("subi 1",rootAlbum);
    Album subi2 = new Album("subi 2",rootAlbum);
    Album subiSubi1 = new Album("subi subi 1",subi1);
    Album subiSubi2 = new Album("subi subi 2",subi2);


    /**test if album name is correct and the getAlbumName() method works.*/
    @Test
    void checkForNames() {
        assert (rootAlbum.toString().equals("root"));
        assert (subi1.toString().equals("subi 1"));
        assert (subi2.toString().equals("subi 2"));
        assert (subiSubi1.toString().equals("subi subi 1"));
        assert (subiSubi2.toString().equals("subi subi 2"));
        assertEquals("root",rootAlbum.toString());
    }

    /**testing that the parent albums are correctly corresponded to their sub albums and vice versa. */
    @Test
    void getParentAlbum() {
        assertEquals(subi1.getParentAlbum(), rootAlbum);
        assertNotEquals(rootAlbum.getParentAlbum(), subi1);

        assertEquals(subi2.getParentAlbum(), rootAlbum);
        assertNotEquals(rootAlbum.getParentAlbum(), subi2);

        assertEquals(subiSubi1.getParentAlbum(), subi1);
        assertNotEquals(subi1.getParentAlbum(), subiSubi1);

        assertEquals(subiSubi2.getParentAlbum(), subi2);
        assertNotEquals(subi2.getParentAlbum(), subiSubi2);

        assertNotEquals(subiSubi1.getParentAlbum(), rootAlbum);
        assertNotEquals(rootAlbum.getParentAlbum(), subiSubi1);
        assertNotEquals(subiSubi2.getParentAlbum(), rootAlbum);
        assertNotEquals(rootAlbum.getParentAlbum(), subiSubi2);
    }

    /**test case to check if a sound clip can be added and removed successfully. */
    @Test
    void addAndRemoveSoundClip() {
        rootAlbum.addSoundClip(clip);
        assertTrue("Sound clip added incorrectly", rootAlbum.containsClip(clip));
        rootAlbum.removeSoundClip(clip);
        assertFalse("Sound clip was removed incorrectly", rootAlbum.containsClip(clip));
    }

    @Test
    void checkAlbumGetsRemoved(){
        assert ((subiSubi1.getParentAlbum().equals(subi1)) &&(subi1.getParentAlbum().equals(rootAlbum)));
        assert ((subiSubi2.getParentAlbum().equals(subi2)) &&(subi2.getParentAlbum().equals(rootAlbum)));

    }

    /**test case to check if an album contains certain sound clips. */
    SoundClip clip1 = new SoundClip(new File("testi1"));
    SoundClip clip2 = new SoundClip(new File("testi2"));
    SoundClip clip3 = new SoundClip(new File("testi3"));
    @Test
    void getSoundClipsFromAlbum() {
        rootAlbum.addSoundClip(clip1);
        rootAlbum.addSoundClip(clip2);
        rootAlbum.addSoundClip(clip3);

        assertTrue(rootAlbum.getSoundClipsFromAlbum().contains(clip1));
        assertTrue(rootAlbum.getSoundClipsFromAlbum().contains(clip2));
        assertTrue(rootAlbum.getSoundClipsFromAlbum().contains(clip3));

    }
}