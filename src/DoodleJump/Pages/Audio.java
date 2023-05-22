package DoodleJump.Pages;

import java.io.File;

import DoodleJump.Main;
import javafx.scene.media.AudioClip;

public class Audio {

    private AudioClip Clip;
    public static Boolean Mute = false;

    public Audio(String path) {
        Clip = new AudioClip(new File(Main.PathToResources + path).toURI().toString());
    }

    public void play() {
        if (Mute == false) {
            Clip.play(0.2);
        }

    }

    public void stop() {
        Clip.stop();
    }
}
