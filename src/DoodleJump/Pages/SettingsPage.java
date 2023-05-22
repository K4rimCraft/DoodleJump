package DoodleJump.Pages;

import javax.swing.plaf.metal.MetalBorders.Flush3DBorder;

import DoodleJump.Main;
import javafx.scene.Cursor;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class SettingsPage extends Pane {

    private Stage PrimaryStage;
    private Pane buttons = new Pane();

    private ImageView Background3_iv = new ImageView(Images.SettingsPage);
    // private ImageView Char1_iv = new ImageView(Images.Char1);
    // private ImageView Char2_iv = new ImageView(Images.Char2);
    // private ImageView Char3_iv = new ImageView(Images.Char3);
    // private ImageView Char4_iv = new ImageView(Images.Char4);
    // private ImageView Char5_iv = new ImageView(Images.Char5);
    private ImageView X_iv = new ImageView(Images.X);
    private ImageView Voff_iv = new ImageView(Images.Voff3);
    private ImageView Von_iv = new ImageView(Images.Von);
    private ImageView Save_iv = new ImageView(Images.Save);
    private ImageView Saved_iv = new ImageView(Images.Empty);

    private ImageView Res_iv = new ImageView();

    private ImageView zom_iv = new ImageView();
    private ImageView nor_iv = new ImageView();
    private ImageView ice_iv = new ImageView();

    private ImageView inputMode = new ImageView();

    private ImageView fly_iv = new ImageView(Images.Fly);
    private ImageView nojump_iv = new ImageView(Images.noJump);
    private ImageView immune_iv = new ImageView(Images.Immune);

    

    boolean click = false;
    boolean click2 = false;

    public SettingsPage(Stage stage) {
        this.PrimaryStage = stage;
    }

    public void start() {
        // int xy = 115;
        if (Main.numRes == 2)
            Res_iv.setImage(Images.ResCustom);
        if (Main.numRes == 3)
            Res_iv.setImage(Images.ResHD);
        if (Main.numRes == 1)
            Res_iv.setImage(Images.ResFullHD);

        if (Main.numChar == 1) {
            nor_iv.setImage(Images.nor2);
            zom_iv.setImage(Images.zom1);
            ice_iv.setImage(Images.ice1);
        } else if (Main.numChar == 2) {
            nor_iv.setImage(Images.nor1);
            zom_iv.setImage(Images.zom2);
            ice_iv.setImage(Images.ice1);
        } else if (Main.numChar == 3) {
            nor_iv.setImage(Images.nor1);
            zom_iv.setImage(Images.zom1);
            ice_iv.setImage(Images.ice2);
        }

        if (FileIO.Read("AudioState.txt").equals("ON")) {
            Von_iv.setImage(Images.Von);
        } else {
            Von_iv.setImage(Images.Voff);
        }

        if (Main.numInput == 1) {
            inputMode.setImage(Images.Keyboard);
        } else {
            inputMode.setImage(Images.Arduino);
        }

        if (Main.fly == false)
            fly_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
        else
            fly_iv.setEffect(new ColorAdjust(0, 0, 0, 0));

        if (Main.nojump == false)
            nojump_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
        else
            nojump_iv.setEffect(new ColorAdjust(0, 0, 0, 0));

        if (Main.immune == false)
            immune_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
        else
            immune_iv.setEffect(new ColorAdjust(0, 0, 0, 0));

        Res_iv.setX(1060);
        Res_iv.setY(170);
        Res_iv.setFitWidth(300);
        Res_iv.setFitHeight(80);
        Res_iv.setOnMouseEntered(e -> {
            Res_iv.setCursor(Cursor.HAND);
            Res_iv.setFitWidth(300 + 20);
            Res_iv.setFitHeight(80 + 8);
            Res_iv.setX(1060 - 10);
            Res_iv.setY(170 - 4);
        });
        Res_iv.setOnMouseExited(e -> {
            Res_iv.setFitWidth(300);
            Res_iv.setFitHeight(80);
            Res_iv.setX(1060);
            Res_iv.setY(170);
        });
        Res_iv.setOnMouseClicked(e -> {
            Main.numRes++;
            if (Main.numRes == 2) {
                Main.SelectedResolution = Main.ResolutionCustom;
                Main.SelectedOffset = Main.OffsetCustom;
                Main.Factor = 2.5;
                Res_iv.setImage(Images.ResCustom);
            }
            if (Main.numRes == 3) {
                Main.SelectedResolution = Main.ResolutionHD;
                Main.SelectedOffset = Main.OffsetHD;
                Main.Factor = 2;
                Res_iv.setImage(Images.ResHD);

            }
            if (Main.numRes == 4) {
                Main.SelectedResolution = Main.ResolutionFullHD;
                Main.SelectedOffset = Main.OffsetFullHD;
                Main.Factor = 3;
                Main.numRes = 1;
                Res_iv.setImage(Images.ResFullHD);
            }

        });

        nor_iv.setX(920);
        nor_iv.setY(310);
        nor_iv.setFitWidth(100);
        nor_iv.setFitHeight(100);
        nor_iv.setOnMouseEntered(e -> {
            nor_iv.setCursor(Cursor.HAND);
            nor_iv.setFitWidth(100 + 10);
            nor_iv.setFitHeight(100 + 10);
            nor_iv.setX(920 - 5);
            nor_iv.setY(310 - 5);
        });
        nor_iv.setOnMouseExited(e -> {
            nor_iv.setFitWidth(100);
            nor_iv.setFitHeight(100);
            nor_iv.setX(920);
            nor_iv.setY(310);
        });
        nor_iv.setOnMouseClicked(e -> {
            Main.numChar = 1;
            Main.imagChar = Images.doodleTiles;
            Main.imagBG = Images.BGDoodle;
            nor_iv.setImage(Images.nor2);
            zom_iv.setImage(Images.zom1);
            ice_iv.setImage(Images.ice1);

        });

        zom_iv.setX(1070);
        zom_iv.setY(310);
        zom_iv.setFitWidth(100);
        zom_iv.setFitHeight(100);
        zom_iv.setOnMouseEntered(e -> {
            zom_iv.setCursor(Cursor.HAND);
            zom_iv.setFitWidth(100 + 10);
            zom_iv.setFitHeight(100 + 10);
            zom_iv.setX(1070 - 5);
            zom_iv.setY(310 - 5);
        });
        zom_iv.setOnMouseExited(e -> {
            zom_iv.setFitWidth(100);
            zom_iv.setFitHeight(100);
            zom_iv.setX(1070);
            zom_iv.setY(310);
        });
        zom_iv.setOnMouseClicked(e -> {
            Main.numChar = 2;
            Main.imagChar = Images.zombieTiles;
            Main.imagBG = Images.BGZombie;
            nor_iv.setImage(Images.nor1);
            zom_iv.setImage(Images.zom2);
            ice_iv.setImage(Images.ice1);

        });

        ice_iv.setX(1220);
        ice_iv.setY(310);
        ice_iv.setFitWidth(100);
        ice_iv.setFitHeight(100);
        ice_iv.setOnMouseEntered(e -> {
            ice_iv.setCursor(Cursor.HAND);
            ice_iv.setFitWidth(100 + 10);
            ice_iv.setFitHeight(100 + 10);
            ice_iv.setX(1220 - 5);
            ice_iv.setY(310 - 5);
        });
        ice_iv.setOnMouseExited(e -> {
            ice_iv.setFitWidth(100);
            ice_iv.setFitHeight(100);
            ice_iv.setX(1220);
            ice_iv.setY(310);
        });
        ice_iv.setOnMouseClicked(e -> {
            Main.numChar = 3;
            Main.imagChar = Images.snowTiles;
            Main.imagBG = Images.BGSnow;
            nor_iv.setImage(Images.nor1);
            zom_iv.setImage(Images.zom1);
            ice_iv.setImage(Images.ice2);
        });

        inputMode.setX(1085);
        inputMode.setY(600);
        inputMode.setFitWidth(280);
        inputMode.setFitHeight(80);
        inputMode.setOnMouseEntered(e -> {
            inputMode.setCursor(Cursor.HAND);
            inputMode.setFitWidth(280 + 20);
            inputMode.setFitHeight(80 + 8);
            inputMode.setX(1085 - 10);
            inputMode.setY(600 - 4);
        });
        inputMode.setOnMouseExited(e -> {
            inputMode.setFitWidth(280);
            inputMode.setFitHeight(80);
            inputMode.setX(1085);
            inputMode.setY(600);
        });
        inputMode.setOnMouseClicked(e -> {
            if (Main.numInput == 1) {
                inputMode.setImage(Images.Arduino);
                Main.numInput = 2;
            } else {
                inputMode.setImage(Images.Keyboard);
                Main.numInput = 1;
            }
        });

        fly_iv.setX(600);
        fly_iv.setY(830);
        fly_iv.setFitWidth(200);
        fly_iv.setFitHeight(80);
        fly_iv.setOnMouseEntered(e -> {
            fly_iv.setCursor(Cursor.HAND);
            fly_iv.setFitWidth(200 + 20);
            fly_iv.setFitHeight(80 + 8);
            fly_iv.setX(600 - 10);
            fly_iv.setY(830 - 4);
        });
        fly_iv.setOnMouseExited(e -> {
            fly_iv.setFitWidth(200);
            fly_iv.setFitHeight(80);
            fly_iv.setX(600);
            fly_iv.setY(830);
        });
        fly_iv.setOnMouseClicked(e -> {
            if (Main.fly == true) {
                Main.fly = false;
                fly_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
            } else {
                Main.fly = true;
                fly_iv.setEffect(new ColorAdjust(0, 0, 0, 0));
            }

        });

        nojump_iv.setX(860);
        nojump_iv.setY(830);
        nojump_iv.setFitWidth(200);
        nojump_iv.setFitHeight(80);
        nojump_iv.setOnMouseEntered(e -> {
            nojump_iv.setCursor(Cursor.HAND);
            nojump_iv.setFitWidth(200 + 20);
            nojump_iv.setFitHeight(80 + 8);
            nojump_iv.setX(860 - 10);
            nojump_iv.setY(830 - 4);
        });
        nojump_iv.setOnMouseExited(e -> {
            nojump_iv.setFitWidth(200);
            nojump_iv.setFitHeight(80);
            nojump_iv.setX(860);
            nojump_iv.setY(830);
        });
        nojump_iv.setOnMouseClicked(e -> {
            if (Main.nojump == true) {
                Main.nojump = false;
                nojump_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
            } else {
                Main.nojump = true;
                nojump_iv.setEffect(new ColorAdjust(0, 0, 0, 0));
            }
        });

        immune_iv.setX(1120);
        immune_iv.setY(830);
        immune_iv.setFitWidth(200);
        immune_iv.setFitHeight(80);
        immune_iv.setOnMouseEntered(e -> {
            immune_iv.setCursor(Cursor.HAND);
            immune_iv.setFitWidth(200 + 20);
            immune_iv.setFitHeight(80 + 8);
            immune_iv.setX(1120 - 10);
            immune_iv.setY(830 - 4);
        });
        immune_iv.setOnMouseExited(e -> {
            immune_iv.setFitWidth(200);
            immune_iv.setFitHeight(80);
            immune_iv.setX(1120);
            immune_iv.setY(830);
        });
        immune_iv.setOnMouseClicked(e -> {
            if (Main.immune == true) {
                Main.immune = false;
                immune_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
            } else {
                Main.immune = true;
                immune_iv.setEffect(new ColorAdjust(0, 0, 0, 0));
            }
        });

        X_iv.setX(1380);
        X_iv.setY(80);
        X_iv.setFitWidth(40);
        X_iv.setFitHeight(40);
        X_iv.setOnMouseEntered(e -> {
            X_iv.setImage(Images.X2);
            X_iv.setCursor(Cursor.HAND);
        });
        X_iv.setOnMouseExited(e -> {
            X_iv.setImage(Images.X);
        });
        X_iv.setOnMouseClicked(e -> {
            PrimaryStage.setScene(new MainPage(PrimaryStage).Create());
        });

        Von_iv.setFitHeight(100);
        Von_iv.setFitWidth(100);
        Von_iv.setX(1050);
        Von_iv.setY(450);
        Von_iv.setOnMouseEntered(e -> {
            Von_iv.setCursor(Cursor.HAND);
            Von_iv.setFitHeight(110);
            Von_iv.setFitWidth(110);
            Von_iv.setX(1050 - 5);
            Von_iv.setY(450 - 5);

        });
        Von_iv.setOnMouseExited(e -> {
            Von_iv.setFitHeight(100);
            Von_iv.setFitWidth(100);
            Von_iv.setX(1050);
            Von_iv.setY(450);
        });
        Von_iv.setOnMouseClicked(e -> {
            // click2 = true;
            // Voff_iv.setImage(Images.Voff3);
            if (Audio.Mute == true) {
                Von_iv.setImage(Images.Von);
                Audio.Mute = false;
                FileIO.Write("ON", "AudioState.txt");
            } else {
                Von_iv.setImage(Images.Voff);
                Audio.Mute = true;
                FileIO.Write("OFF", "AudioState.txt");
            }

        });

        // buttons.getChildren().addAll(Char1_iv, Char2_iv, Char3_iv, Char4_iv,
        // Char5_iv, Voff_iv, Von_iv, Save_iv,
        // Saved_iv);
        this.getChildren().addAll(Background3_iv, X_iv, Von_iv, zom_iv, ice_iv, nor_iv, Res_iv, fly_iv, nojump_iv,
                immune_iv, inputMode, buttons);

    }

    public Scene Create() {
        this.setLayoutX(Main.SelectedOffset.getX());
        this.setLayoutY(Main.SelectedOffset.getY());
        this.setScaleX(Main.Factor / 3);
        this.setScaleY(Main.Factor / 3);
        start();
        return new Scene(this, Main.SelectedResolution.getX(), Main.SelectedResolution.getY());
    }
}
