import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class Album { //class to create album objects.
    private ArrayList<Album> listOfAlbums = new ArrayList<Album>(); //List containing all the albums
    private String albumName; //name of the album
    LinkedList<SoundClip> albumSongs = new LinkedList<SoundClip>(); //List of songs
    LinkedList<Album> subAlbumList = new LinkedList<Album>(); //List of sub albums for every parent album
    private Album parentAlbum = null;
    private File file; //for future convenience.
    //public Album rootAlbum = new Album("rootAlbum"); //initializing the rootAlbum


    Album(String albumName) { //creates the root album
        this.albumName = albumName;
        parentAlbum = null;
    }
    Album(String albumName, Album parentAlbum) { //creates sub albums
        this.albumName = albumName;
        parentAlbum = parentAlbum;
    }
    //method for creating the initial root album
    /*public void createAlbum(String albumName) {
        this.listOfAlbums.add(new Album(albumName, rootAlbum));
    }*/
    public Album getParentAlbum(Album newAlbum){
        parentAlbum = this.getAlbumName();
    }

    public String getAlbumName() { //gets the name of the album
        return this.albumName;
    }

    public void addSong(SoundClip song) {
        albumSongs.add(song);
    }

    public void removeSong(SoundClip song) {
        albumSongs.remove(song);
    }

    public boolean checkAlbumForSong(SoundClip song) {
        return albumSongs.contains(song);
    }


    private void removeAlbum(String albumNameToRemove) {
        for (Album alb : this.subAlbumList) {
            for (Album subAlb : alb.subAlbumList) {
                if (subAlb.getAlbumName() == albumNameToRemove) {
                    this.subAlbumList.remove(subAlb);
                    alb.subAlbumList.remove(subAlb);
                }
            }
        }
    }


    public LinkedList<SoundClip> getSongsFromAlbum(String desiredAlbumName) {
        LinkedList<SoundClip> albumSongsList = new LinkedList<>(); //empty list of songs, to prevent usage of null.
        for (Album alb : this.listOfAlbums) {
            if (alb.getAlbumName() == desiredAlbumName) {
                return alb.albumSongs;
            }
        }
        return albumSongsList; //in case desiredAlbumName does not exist
    }


    //removes songs from specified Album
    private void removeSongFromAlbum(String desiredAlbumName, SoundClip songToBeRemoved) {
        for (Album alb : this.subAlbumList) {
            if (alb.getAlbumName() == desiredAlbumName && alb.albumSongs.contains(songToBeRemoved)) { //to minimize people from being weird
                alb.albumSongs.remove(songToBeRemoved);
            }
        }
    }


    //method for displaying the specified albums information

    //STILL NEEDS TWEAKING...
    private String displayAlbumInformation(String desiredAlbumName) {
        for (Album alb : this.subAlbumList) {
            if (alb.getAlbumName() == desiredAlbumName) {
                System.out.println("Album name: " + alb.getAlbumName() + "\nAlbum songs: \n");
                String songNamesString = "";
                for (int i = 0; i < alb.albumSongs.size(); i++) { //to make the output nicely separated
                    songNamesString += alb.albumSongs.get(i).toString();
                    if (i < alb.albumSongs.size()) {
                        songNamesString += ", ";
                    }
                }
                return(songNamesString);
            }
        }
        return("");
    }
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
