package DoodleJump.Pages;


import javafx.scene.Cursor;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class SettingsPage extends Pane {

    private Stage stage;
    private Scene scene;
    private Pane buttons = new Pane();

    private ImageView Background3_iv = new ImageView(Images.Background3);
    private ImageView Char1_iv = new ImageView(Images.Char1);
    private ImageView Char2_iv = new ImageView(Images.Char2);
    private ImageView Char3_iv = new ImageView(Images.Char3);
    private ImageView Char4_iv = new ImageView(Images.Char4);
    private ImageView Char5_iv = new ImageView(Images.Char5);
    private ImageView X_iv = new ImageView(Images.X);
    private ImageView Voff_iv = new ImageView(Images.Voff3);
    private ImageView Von_iv = new ImageView(Images.Von);
    private ImageView Save_iv = new ImageView(Images.Save);
    private ImageView Saved_iv = new ImageView(Images.Empty);

    boolean click = false;
    boolean click2 = false;

    public SettingsPage(Stage stage) {
        this.stage = stage;
    }

    public void start() {
        int xy = 115;

        Char1_iv.setFitHeight(xy);
        Char1_iv.setFitWidth(xy);
        Char1_iv.setX(600);
        Char1_iv.setY(330);
        Char1_iv.setOnMouseEntered(e -> {
            Char1_iv.setCursor(Cursor.HAND);
            if (click == false) {
                Char1_iv.setImage(Images.Char1_2);
                Char1_iv.setFitHeight(xy + 10);
                Char1_iv.setFitWidth(xy + 10);
            }
        });
        Char1_iv.setOnMouseExited(e -> {
            if (click == false) {
                Char1_iv.setImage(Images.Char1);
                Char1_iv.setFitHeight(xy);
                Char1_iv.setFitWidth(xy);
            }
        });
        Char1_iv.setOnMouseClicked(e -> {
            click = true;
            Char2_iv.setImage(Images.Char);
            Char3_iv.setImage(Images.Char);
            Char4_iv.setImage(Images.Char);
            Char5_iv.setImage(Images.Char);
            Char1_iv.setImage(Images.Char1_3);
            FileIO.Write("Character/Character_Left_1.png", "Character_Left.txt");
            FileIO.Write("Character/Character_Right_1.png", "Character_Right.txt");
            FileIO.Write("Character/Character_Plus_Right_1.png", "Character_Plus_Right.txt");
            FileIO.Write("Character/Character_Plus_Left_1.png", "Character_Plus_Left.txt");
        });

////////////////////////////////////////////////////////////////////////////////
        Char2_iv.setFitHeight(xy);
        Char2_iv.setFitWidth(xy);
        Char2_iv.setX(750);
        Char2_iv.setY(330);
        Char2_iv.setOnMouseEntered(e -> {
            Char2_iv.setCursor(Cursor.HAND);
            if (click == false) {
                Char2_iv.setImage(Images.Char2_2);
                Char2_iv.setFitHeight(xy + 10);
                Char2_iv.setFitWidth(xy + 10);
            }
        });
        Char2_iv.setOnMouseExited(e -> {
            if (click == false) {
                Char2_iv.setImage(Images.Char2);
                Char2_iv.setFitHeight(xy);
                Char2_iv.setFitWidth(xy);
            }
        });
        Char2_iv.setOnMouseClicked(e -> {
            click = true;
            Char1_iv.setImage(Images.Char);
            Char3_iv.setImage(Images.Char);
            Char4_iv.setImage(Images.Char);
            Char5_iv.setImage(Images.Char);
            Char2_iv.setImage(Images.Char2_3);
            FileIO.Write("Character/Character_Left_2.png", "Character_Left.txt");
            FileIO.Write("Character/Character_Right_2.png", "Character_Right.txt");
            FileIO.Write("Character/Character_Plus_Right_2.png", "Character_Plus_Right.txt");
            FileIO.Write("Character/Character_Plus_Left_2.png", "Character_Plus_Left.txt");
        });

////////////////////////////////////////////////////////////////////////////////
        Char3_iv.setFitHeight(xy);
        Char3_iv.setFitWidth(xy);
        Char3_iv.setX(900);
        Char3_iv.setY(330);
        Char3_iv.setOnMouseEntered(e -> {
            Char3_iv.setCursor(Cursor.HAND);
            if (click == false) {
                Char3_iv.setImage(Images.Char3_2);
                Char3_iv.setFitHeight(xy + 10);
                Char3_iv.setFitWidth(xy + 10);
            }
        });
        Char3_iv.setOnMouseExited(e -> {
            if (click == false) {
                Char3_iv.setImage(Images.Char3);
                Char3_iv.setFitHeight(xy);
                Char3_iv.setFitWidth(xy);
            }
        });
        Char3_iv.setOnMouseClicked(e -> {
            click = true;
            Char1_iv.setImage(Images.Char);
            Char2_iv.setImage(Images.Char);
            Char4_iv.setImage(Images.Char);
            Char5_iv.setImage(Images.Char);
            Char3_iv.setImage(Images.Char3_3);
            FileIO.Write("Character/Character_Left_3.png", "Character_Left.txt");
            FileIO.Write("Character/Character_Right_3.png", "Character_Right.txt");
            FileIO.Write("Character/Character_Plus_Right_3.png", "Character_Plus_Right.txt");
            FileIO.Write("Character/Character_Plus_Left_3.png", "Character_Plus_Left.txt");
        });

////////////////////////////////////////////////////////////////////////////////
        Char4_iv.setFitHeight(xy);
        Char4_iv.setFitWidth(xy);
        Char4_iv.setX(1050);
        Char4_iv.setY(330);
        Char4_iv.setOnMouseEntered(e -> {
            Char4_iv.setCursor(Cursor.HAND);
            if (click == false) {
                Char4_iv.setImage(Images.Char4_2);
                Char4_iv.setFitHeight(xy + 10);
                Char4_iv.setFitWidth(xy + 10);
            }
        });
        Char4_iv.setOnMouseExited(e -> {
            if (click == false) {
                Char4_iv.setImage(Images.Char4);
                Char4_iv.setFitHeight(xy);
                Char4_iv.setFitWidth(xy);
            }
        });
        Char4_iv.setOnMouseClicked(e -> {
            click = true;
            Char1_iv.setImage(Images.Char);
            Char2_iv.setImage(Images.Char);
            Char3_iv.setImage(Images.Char);
            Char5_iv.setImage(Images.Char);
            Char4_iv.setImage(Images.Char4_3);
            FileIO.Write("Character/Character_Right_4.png", "Character_Right.txt");
            FileIO.Write("Character/Character_Plus_Right_4.png", "Character_Plus_Right.txt");
            FileIO.Write("Character/Character_Left_4.png", "Character_Left.txt");
            FileIO.Write("Character/Character_Plus_Left_4.png", "Character_Plus_Left.txt");
        });

////////////////////////////////////////////////////////////////////////////////
        Char5_iv.setFitHeight(xy);
        Char5_iv.setFitWidth(xy);
        Char5_iv.setX(1200);
        Char5_iv.setY(330);
        Char5_iv.setOnMouseEntered(e -> {
            Char5_iv.setCursor(Cursor.HAND);
            if (click == false) {
                Char5_iv.setImage(Images.Char5_2);
                Char5_iv.setFitHeight(xy + 10);
                Char5_iv.setFitWidth(xy + 10);
            }
        });
        Char5_iv.setOnMouseExited(e -> {
            if (click == false) {
                Char5_iv.setImage(Images.Char5);
                Char5_iv.setFitHeight(xy);
                Char5_iv.setFitWidth(xy);
            }
        });
        Char5_iv.setOnMouseClicked(e -> {
            click = true;
            Char1_iv.setImage(Images.Char);
            Char2_iv.setImage(Images.Char);
            Char3_iv.setImage(Images.Char);
            Char4_iv.setImage(Images.Char);
            Char5_iv.setImage(Images.Char5_3);
            FileIO.Write("Character/Character_Right_5.png", "Character_Right.txt");
            FileIO.Write("Character/Character_Left_5.png", "Character_Left.txt");
            FileIO.Write("Character/Character_Plus_Right_5.png", "Character_Plus_Right.txt");
            FileIO.Write("Character/Character_Plus_Left_5.png", "Character_Plus_Left.txt");
        });
////////////////////////////////////////////////////////////////////////////////
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
            MainPage mainPage = new MainPage(stage);
            mainPage.start();
            stage.setScene(mainPage.play());
        });

////////////////////////////////////////////////////////////////////////////////
        Voff_iv.setFitHeight(100);
        Voff_iv.setFitWidth(100);
        Voff_iv.setX(800);
        Voff_iv.setY(630);
        Voff_iv.setOnMouseEntered(e -> {
            Voff_iv.setCursor(Cursor.HAND);
            if (click2 == false) {
                Voff_iv.setImage(Images.Voff2);
                Voff_iv.setFitHeight(110);
                Voff_iv.setFitWidth(110);
            }
        });
        Voff_iv.setOnMouseExited(e -> {
            if (click2 == false) {
                Voff_iv.setImage(Images.Voff);
                Voff_iv.setFitHeight(100);
                Voff_iv.setFitWidth(100);
            }
        });
        Voff_iv.setOnMouseClicked(e -> {
            click2 = true;
            Von_iv.setImage(Images.Von3);
            Voff_iv.setImage(Images.Voff);
            FileIO.Write("OFF", "AudioState.txt");
        });

////////////////////////////////////////////////////////////////////////////////
        Von_iv.setFitHeight(100);
        Von_iv.setFitWidth(100);
        Von_iv.setX(1000);
        Von_iv.setY(630);
        Von_iv.setOnMouseEntered(e -> {
            Von_iv.setCursor(Cursor.HAND);
            if (click2 == false) {
                Von_iv.setImage(Images.Von2);
                Von_iv.setFitHeight(110);
                Von_iv.setFitWidth(110);
            }
        });
        Von_iv.setOnMouseExited(e -> {
            if (click2 == false) {
                Von_iv.setImage(Images.Von);
                Von_iv.setFitHeight(100);
                Von_iv.setFitWidth(100);
            }
        });
        Von_iv.setOnMouseClicked(e -> {
            click2 = true;
            Voff_iv.setImage(Images.Voff3);
            Von_iv.setImage(Images.Von);
            FileIO.Write("ON", "AudioState.txt");
        });

////////////////////////////////////////////////////////////////////////////////
        Save_iv.setX(780);
        Save_iv.setY(790);
        Save_iv.setFitWidth(365.63);
        Save_iv.setFitHeight(138.29);
        Save_iv.setOnMouseEntered(e -> {
            Save_iv.setImage(Images.Save2);
            Save_iv.setCursor(Cursor.HAND);
            Save_iv.setFitWidth(365.63 + 20);
            Save_iv.setFitHeight(138.29 + 7.56);
        });
        Save_iv.setOnMouseExited(e -> {
            Save_iv.setImage(Images.Save);
            Save_iv.setFitWidth(365.63);
            Save_iv.setFitHeight(138.29);
        });
        Save_iv.setOnMouseClicked(e -> {
            if (click == true && click2 == true) {
                Saved_iv.setImage(Images.Saved);
            }
        });

////////////////////////////////////////////////////////////////////////////////
        Saved_iv.setX(550);
        Saved_iv.setY(900);
        Saved_iv.setFitWidth(233.5);
        Saved_iv.setFitHeight(36.7);

////////////////////////////////////////////////////////////////////////////////
        this.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                click = false;
                click2 = false;
                Char1_iv.setImage(Images.Char1);
                Char2_iv.setImage(Images.Char2);
                Char3_iv.setImage(Images.Char3);
                Char4_iv.setImage(Images.Char4);
                Char5_iv.setImage(Images.Char5);
                Voff_iv.setImage(Images.Voff);
                Von_iv.setImage(Images.Von);
                Saved_iv.setImage(Images.Empty);
            }
        });

        buttons.getChildren().addAll(Char1_iv, Char2_iv, Char3_iv, Char4_iv, Char5_iv, Voff_iv, Von_iv, Save_iv, Saved_iv);
        this.getChildren().addAll(Background3_iv, X_iv, buttons);

    }

    public Scene setting() {
        scene = new Scene(this);
        return scene;
    }
}
