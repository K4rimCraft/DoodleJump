package DoodleJump.Pages;

import java.io.File;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Audio {

    private  String path;
    private  Media media;
    private  MediaPlayer mediaplayer;

    public Audio(String path) {
        this.path = path;
    }
    
    public  void audioPlayer() {
        media = new Media(new File(path).toURI().toString());
       mediaplayer = new MediaPlayer(media);
       mediaplayer.setCycleCount(Timeline.INDEFINITE);
    }
    public  void audioPlayer(int replay) {
        media = new Media(new File(path).toURI().toString());
       mediaplayer = new MediaPlayer(media);
       mediaplayer.setCycleCount(replay);
    }
    public  void play(){
        mediaplayer.play();
    }
    public  void stop(){
        mediaplayer.pause();
    }
}
