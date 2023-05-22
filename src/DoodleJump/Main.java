package DoodleJump;

import java.io.File;

import DoodleJump.GameLogic.ArduinoListener;
import DoodleJump.GameLogic.GamePage;
import DoodleJump.Pages.Audio;
import DoodleJump.Pages.DifficultyPage;
import DoodleJump.Pages.FileIO;
import DoodleJump.Pages.Images;
import DoodleJump.Pages.MainPage;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

        public final static Point2D ResolutionFullHD = new Point2D(1920, 1080);
        public final static Point2D ResolutionHD = new Point2D(1280, 720);
        public final static Point2D ResolutionCustom = new Point2D(1600, 900);

        public final static Point2D OffsetFullHD = new Point2D(0, 0);
        public final static Point2D OffsetHD = new Point2D(-213, -119);
        public final static Point2D OffsetCustom = new Point2D(-133, -75);

        public static Point2D SelectedResolution = ResolutionFullHD;
        public static Point2D SelectedOffset = OffsetFullHD;
        public static double Factor = 3;

        public static int numRes = 1;
        public static int numChar = 1;
        public static Image imagChar;
        public static Image imagBG;
        public static int numInput = 1;

        public static Boolean fly = false;
        public static Boolean nojump = false;
        public static Boolean immune = false;

        public static int numMonDifficulty = 2;
        public static int numPowDifficulty = 6;

        public static String PathToFont = "file:E:\\";
        public static String PathToResources = "C:\\Users\\kimos\\Downloads\\CSE1 2nd Term\\Programming\\VS Code Java Projects\\DoodleJump Karim Branch\\DoodleJump\\";

        @Override
        public void start(Stage stage) {

                // Media media = new Media(new File(PathToResources +
                // "Intro.mp4").toURI().toString());
                // MediaPlayer Intro = new MediaPlayer(media);
                // MediaView intro = new MediaView(Intro);
                // intro.setFitWidth(1920);
                // intro.setFitHeight(1080);

                if (FileIO.Read("AudioState.txt").equals("ON"))
                        Audio.Mute = false;
                else
                        Audio.Mute = true;

                ArduinoListener.start();
                Images images = new Images();
                imagChar = Images.doodleTiles;
                imagBG = Images.BGDoodle;
                

                // Intro.play();
                // Intro.setOnEndOfMedia(new Runnable() {
                // @Override
                // public void run() {
                // Intro.pause();
                // firstPage.start();
                // stage.setScene(firstPage.play());
                // }
                // });
                stage.setScene(new MainPage(stage).Create());
                stage.setOnCloseRequest(e -> {
                        System.exit(0);
                });
                stage.setResizable(false);
                stage.setTitle("Doddle_Jumb");
                stage.show();
        }

        public static void main(String[] args) {
                launch(args);
        }

}
