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

public class Controller{
    private static MusicPlayer musicPlayer = new MusicPlayer();
    private static UserInputGUI IO = new UserInputGUI();

    public static void main (String[]args){

        int choice = Integer.parseInt(IO.getUserInput("Choose your Battle\n1. For Making new album. \n" +
                "2. For making a new subalbum. \n3. Choosing a new song. \n4.Move a song to an other album"));

        choices(choice);


    }
    private static void choices(int choice){

        switch(choice) {
            case (1) :{
                String albumName = IO.getUserInput("Define the Album name");

               // createAlbum(albumName);
            }
            case (2) :{
               // createSubAlbum();
            }
            case (3) :{
                //newSong();
            }
            case (4) :{
                //addSongToAlbum();
            }
        }
    }
        private void createAlbum(String albumName){
            musicPlayer.createAlbum(albumName);
        }
        private void createSubAlbum(){

        }
        private void addSongToAlbum(){

        }
        private void newSong(){

        }


}
