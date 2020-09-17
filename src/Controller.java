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

    private static UserInputGUI IO = new UserInputGUI();
    private ArrayList<Album> listOfAlbums = new ArrayList<Album>();
    private ArrayList<SoundClip> allSongsList = new ArrayList<SoundClip>();
    public Album rootAlbum = new Album("rootAlbum");
    private File file;

    public void run(){

        int choice = Integer.parseInt(IO.getUserInput("Choose your Battle\n1. For Making new album. \n" +
                "2. For making a new subalbum. \n3. Choosing a new song. \n4.Move a song to an other album"));

        if(choice == 2){
            //choose parent album
        }

        choices(choice); //small problem. choices wants to stay static, but none of the other methods want to stay static
                         //hence becoming a endless loop of making one static and then removing the static variable again
                         //and again.


    }
    private void choices(int choice){

        switch(choice) { //because we cannot utilize a GUI, switch case for now.
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

    public void createAlbum(String albumName){
        this.listOfAlbums.add(new Album(albumName,rootAlbum));
    }
    private void createSubAlbum(String albumName, Album parentAlbum){
        this.listOfAlbums.add(new Album(albumName, parentAlbum));
    }
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
    private void newSong(SoundClip song){
        rootAlbum.albumSongs.add(song);
    }
    private void addSongToAlbum(String desiredAlbumName, SoundClip songToBeAdded){
        for (Album alb : rootAlbum.subAlbumList) {
            if(alb.getAlbumName() == desiredAlbumName && !alb.albumSongs.contains(songToBeAdded) ) { //to minimize people from being weird
                alb.albumSongs.add(songToBeAdded);
            }
        }
    }
    public ArrayList<SoundClip> getSongsFromAlbum(String desiredAlbumName) {
        ArrayList<SoundClip> albumSongsList = new ArrayList<SoundClip>(); //empty list of songs, to prevent usage of null.
        for (Album alb : this.listOfAlbums) {
            if (alb.getAlbumName() == desiredAlbumName) {
                return alb.albumSongs;
            }
        }
        return albumSongsList; //in case desiredAlbumName does not exist
    }
    //removes songs from Album
    private void removeSongFromAlbum(String desiredAlbumName, SoundClip songToBeRemoved){
        for (Album alb : rootAlbum.subAlbumList) {
            if(alb.getAlbumName() == desiredAlbumName && alb.albumSongs.contains(songToBeRemoved) ) { //to minimize people from being weird
                alb.albumSongs.remove(songToBeRemoved);
            }
        }
    }
    private void showAlbumInformation(String desiredAlbumName){
        for (Album alb : rootAlbum.subAlbumList){
            if(alb.getAlbumName() == desiredAlbumName){
                System.out.println("Album name: " + alb.getAlbumName() + "\nAlbum songs: \n");
                String songNamesString = "";
                for (int i = 0; i < alb.albumSongs.size(); i++){
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
