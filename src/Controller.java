/*package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/

import java.io.File;
import java.util.ArrayList;

public class Controller{

    private static UserInputGUI IO = new UserInputGUI(); //input reader
    private ArrayList<Album> listOfAlbums = new ArrayList<Album>(); //List containing all the albums
    //private ArrayList<SoundClip> allSongsList = new ArrayList<SoundClip>();
    public Album rootAlbum = new Album("rootAlbum"); //initializing the rootAlbum
    private File file; //for future convenience.

    public void run(){

        int choice = Integer.parseInt(IO.getUserInput("Choose your Battle\n1. For Making the root album. \n" +
                "2. For making a new subalbum. \n3. To remove an album. \n4. Entering a new song. \n5. Add a song to a " +
                "specifed album. \n6. Retrieve the songs from an album. \n7. Remove a song from an album."));

        choices(choice); //small problem. choices wants to stay static, but none of the other methods want to stay static
                         //hence becoming a endless loop of making one static and then removing the static variable again
                         //and again.

                         //*TEMPORARY FIX* main() was changed to a run() method instead to avoid all the static.
                         //Unfortunately we cannot therefore run our code...


    }
    private void choices(int choice){
        //switch case to work as the eventual event handlers that will come with the GUI.
        switch(choice) {
            case (1) :{
                String albumName = IO.getUserInput("Define the Album name");

                createAlbum(albumName);
            }
            case (2) :{
                String albumName = IO.getUserInput("Define the Album name");

                //createSubAlbum(albumName, parentAlbum);

            }
            case (3):{

                //removeAlbum(albumNameToRemove);
            }
            case (4) :{
                SoundClip song = new SoundClip(file);
                newSong(song);
            }
            case (5):{
                for (Album alb : rootAlbum.subAlbumList){

                }
                //addSongToAlbum(desiredAlbumName, songToBeAdded);
            }
            case (6):{

                //getSongsFromAlbum(desiredAlbumName);
            }
            case (7):{

                //removeSongFromAlbum(desiredAlbumName, songToBeRemoved);
            }
        }
    }

    //method for creating the initial root album
    public void createAlbum(String albumName){
        this.listOfAlbums.add(new Album(albumName,rootAlbum));
    }
    //method for creating a sub album. This method will be used for the rest of the albums created.
    private void createSubAlbum(String albumName, Album parentAlbum){
        this.listOfAlbums.add(new Album(albumName, parentAlbum));
    }
    //method for removing an album
    private void removeAlbum(String albumNameToRemove){
        for (Album alb : rootAlbum.subAlbumList) {
            for (Album subAlb : alb.subAlbumList) {
                if (subAlb.getAlbumName() == albumNameToRemove){
                    rootAlbum.subAlbumList.remove(subAlb);
                    alb.subAlbumList.remove(subAlb);
                }
            }
        }
    }
    //method for creating a new song
    private void newSong(SoundClip song){
        rootAlbum.albumSongs.add(song);
    }
    //method for adding a song to a specified album
    private void addSongToAlbum(String desiredAlbumName, SoundClip songToBeAdded){
        for (Album alb : rootAlbum.subAlbumList) {
            if(alb.getAlbumName() == desiredAlbumName && !alb.albumSongs.contains(songToBeAdded) ) { //to minimize people from being weird
                alb.albumSongs.add(songToBeAdded);
            }
        }
    }
    //method for retrieving all songs in a specified album
    public ArrayList<SoundClip> getSongsFromAlbum(String desiredAlbumName) {
        ArrayList<SoundClip> albumSongsList = new ArrayList<SoundClip>(); //empty list of songs, to prevent usage of null.
        for (Album alb : this.listOfAlbums) {
            if (alb.getAlbumName() == desiredAlbumName) {
                return alb.albumSongs;
            }
        }
        return albumSongsList; //in case desiredAlbumName does not exist
    }
    //removes songs from specified Album
    private void removeSongFromAlbum(String desiredAlbumName, SoundClip songToBeRemoved){
        for (Album alb : rootAlbum.subAlbumList) {
            if(alb.getAlbumName() == desiredAlbumName && alb.albumSongs.contains(songToBeRemoved) ) { //to minimize people from being weird
                alb.albumSongs.remove(songToBeRemoved);
            }
        }
    }
    //method for displaying the specified albums information
    private void showAlbumInformation(String desiredAlbumName){
        for (Album alb : rootAlbum.subAlbumList){
            if(alb.getAlbumName() == desiredAlbumName){
                System.out.println("Album name: " + alb.getAlbumName() + "\nAlbum songs: \n");
                String songNamesString = "";
                for (int i = 0; i < alb.albumSongs.size(); i++){ //to make the output nicely separated
                    songNamesString += alb.albumSongs.get(i).toString();
                    if (i < alb.albumSongs.size()){
                        songNamesString += ", ";
                    }
                }
                System.out.println(songNamesString);
            }
        }
    }


}
