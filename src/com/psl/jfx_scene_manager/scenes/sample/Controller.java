package com.psl.jfx_scene_manager.scenes.sample;

import com.psl.jfx_scene_manager.scene_manager.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private SceneManager sm;

    public void goBackClicked(ActionEvent actionEvent) {
        sm.pop();
    }

    public void nextClicked(ActionEvent actionEvent) {
        sm.loadNextScene();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sm = SceneManager.getInstance();
    }


}
