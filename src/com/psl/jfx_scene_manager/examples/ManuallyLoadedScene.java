package com.psl.jfx_scene_manager.examples;

import com.psl.jfx_scene_manager.scene_manager.SceneInfo;
import com.psl.jfx_scene_manager.scene_manager.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ManuallyLoadedScene {


    public ManuallyLoadedScene(){
        load();
    }

    private void load(){
        SceneManager sm = SceneManager.getInstance();

        VBox vBox = new VBox();
        vBox.setPrefSize(600, 400);
        vBox.setAlignment(Pos.CENTER);

        Label l1 = new Label("Manually loaded scene");

        Button backButton = new Button();
        backButton.setText("Go Back");
        backButton.setOnMouseClicked(mouseEvent -> {
            sm.pop();
        });

        Button nextButton = new Button();
        nextButton.setText("Next");
        nextButton.setOnMouseClicked(mouseEvent -> {
            sm.loadNextScene();
        });

        vBox.getChildren().addAll(l1, backButton, nextButton);
        sm.cacheScene(new SceneInfo("manually loaded scene", new Scene(vBox)), true);
    }

}
