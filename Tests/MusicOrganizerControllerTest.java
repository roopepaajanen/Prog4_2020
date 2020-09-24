import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicOrganizerControllerTest {

    Album rootAlbum = new Album("root");
    Album subi1 = new Album("subi 1",rootAlbum);
    Album subi2 = new Album("subi 2",rootAlbum);
    Album subiSubi1 = new Album("subi subi 1",subi1);
    Album subiSubi2 = new Album("subi subi 2",subi2);
    MusicOrganizerController controller = new MusicOrganizerController();
    MusicOrganizerWindow view = new MusicOrganizerWindow(controller);
    private int albumAmount = 0;

    /**
    @Test
    void checkToRemoveAlbum() {
        Album deleteAlbum = subiSubi2;
        controller.deleteAlbum();
        if(albumAmount >0){
            subiSubi2.
        }
        assertTrue();
    }
    */
}