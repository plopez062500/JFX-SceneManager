package com.psl.jfx_scene_manager;

import com.psl.jfx_scene_manager.examples.FXMLLoadedScene;
import com.psl.jfx_scene_manager.examples.ManuallyLoadedScene;
import com.psl.jfx_scene_manager.examples.NonCachedScene;
import com.psl.jfx_scene_manager.scene_manager.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("JFX SceneManager");

        SceneManager.initialize(primaryStage);
        loadScenes();

        primaryStage.show();
    }

    private static void loadScenes(){
        new ManuallyLoadedScene();
        new FXMLLoadedScene();
        new NonCachedScene();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
