import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class Album { //class to create album objects.
    private ArrayList<Album> listOfAlbums = new ArrayList<Album>(); //List containing all the albums
    private String albumName; //name of the album
    LinkedList<SoundClip> albumSoundClips = new LinkedList<SoundClip>(); //List of sound clips
    private LinkedList<Album> subAlbumList = new LinkedList<Album>(); //List of sub albums for every parent album
    private Album parentAlbum = null;
    private File file; //for future convenience.

    Album(String albumName) { //creates the root album
        this.albumName = albumName;
        parentAlbum = null;
    }
    Album(String albumName, Album parentAlbum) { //creates sub albums
        this.albumName = albumName;
        this.parentAlbum = parentAlbum;
    }

    //method for finding the root album inside the Album class
    public Album getRootAlbum(Album desiredAlbum){
        for (Album album :listOfAlbums) {
            if(desiredAlbum.parentAlbum == null){
                return album;
            }
        }
        return null;
    }

    public Album getParentAlbum(Album desiredAlbum){
        return desiredAlbum.parentAlbum;
    }

    public String getAlbumName() { //gets the name of the album
        return this.albumName;
    }

    public void addSoundClip(SoundClip song) {
        albumSoundClips.add(song);
    }

    public void removeSong(SoundClip song) {
        albumSoundClips.remove(song);
    }

    public boolean checkAlbumForSong(SoundClip song) {
        return albumSoundClips.contains(song);
    }


    private void removeAlbum(Album albumToRemove) {
        for (Album alb : this.subAlbumList) {
            for (Album subAlb : alb.subAlbumList) {
                if (listOfAlbums.contains(albumToRemove)) {
                    this.subAlbumList.remove(subAlb);
                    alb.subAlbumList.remove(subAlb);
                }
            }
        }
    }

    public LinkedList<SoundClip> getSongsFromAlbum(Album desiredAlbum) {
        /*LinkedList<SoundClip> albumSongsList = new LinkedList<>(); //empty list of songs, to prevent usage of null.
        for (Album alb : this.listOfAlbums) {
            if(alb.equals(desiredAlbum)) {
                return alb.albumSongs;
            }
        }
        return albumSongsList; //in case desiredAlbumName does not exist*/
        return desiredAlbum.albumSoundClips;
    }


    //MAKE RECURSIVE
    //removes sound clip from specified Album
    private void removeSongFromAlbum(String desiredAlbumName, SoundClip clipToBeRemoved) {
        for (Album alb : this.subAlbumList) {
            if (alb.getAlbumName() == desiredAlbumName && alb.albumSoundClips.contains(clipToBeRemoved)) { //to minimize people from being weird
                alb.albumSoundClips.remove(clipToBeRemoved);
            }
        }
    }

    /*
    //method for displaying the specified albums information
    //STILL NEEDS TWEAKING...
    private String displayAlbumInformation(String desiredAlbumName) {
        for (Album alb : this.subAlbumList) {
            if (alb.getAlbumName() == desiredAlbumName) {
                System.out.println("Album name: " + alb.getAlbumName() + "\nAlbum songs: \n");
                String songNamesString = "";
                for (int i = 0; i < alb.albumSoundClips.size(); i++) { //to make the output nicely separated
                    songNamesString += alb.albumSoundClips.get(i).toString();
                    if (i < alb.albumSoundClips.size()) {
                        songNamesString += ", ";
                    }
                }
                return(songNamesString);
            }
        }
        return("");
    }
     */

    //toString method to return the name of the album as a String
    public String toString(){
        return this.getAlbumName();
    }

    /*public void addSongsToAlbum(SoundClip song){ //adds songs to the album
        this.albumSongs.add(song);
    }
    public void removeSongsFromAlbum(int index){ //removes song from the album

    }
    public boolean checkAlbumForSong(SoundClip song){ //Returns true if the searched song is in the album, false if not.
        return albumSongs.contains(song);
    }
    */


}
