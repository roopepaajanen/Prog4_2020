import org.junit.jupiter.api.Test;
import java.io.File;
import static junit.framework.TestCase.*;

class AlbumTest {

    Album rootAlbum = new Album("root");
    Album subi1 = new Album("subi 1",rootAlbum);
    Album subi2 = new Album("subi 2",rootAlbum);
    Album subiSubi1 = new Album("subi subi 1",subi1);
    Album subiSubi2 = new Album("subi subi 2",subi2);

    //test if album name is correct and the getAlbumName() method works.
    @Test
    void checkForNames() {
        assert (rootAlbum.toString().equals("root"));
        assert (subi1.toString().equals("subi 1"));
        assert (subi2.toString().equals("subi 2"));
        assert (subiSubi1.toString().equals("subi subi 1"));
        assert (subiSubi2.toString().equals("subi subi 2"));
        assertEquals("root",rootAlbum.toString());

    }

    //testing that the parent albums are correctly corresponded to their sub albums and vice versa
    @Test
    void getParentAlbum() {
        assertTrue(subi1.getParentAlbum().equals(rootAlbum));
        assertFalse(rootAlbum.getParentAlbum().equals(subi1));

        assertTrue(subi2.getParentAlbum().equals(rootAlbum));
        assertFalse(rootAlbum.getParentAlbum().equals(subi2));

        assertTrue(subiSubi1.getParentAlbum().equals(subi1));
        assertFalse(subi1.getParentAlbum().equals(subiSubi1));

        assertTrue(subiSubi2.getParentAlbum().equals(subi2));
        assertFalse(subi2.getParentAlbum().equals(subiSubi2));

        assertFalse(subiSubi1.getParentAlbum().equals(rootAlbum));
        assertFalse(rootAlbum.getParentAlbum().equals(subiSubi1));
        assertFalse(subiSubi2.getParentAlbum().equals(rootAlbum));
        assertFalse(rootAlbum.getParentAlbum().equals(subiSubi2));
    }

    @Test
    void testToString() {

    }

    @Test
    void addSoundClip() {

    }

    @Test
    void getSoundClipsFromAlbum() {

    }

    @Test
    void removeSoundClip() {

    }
}