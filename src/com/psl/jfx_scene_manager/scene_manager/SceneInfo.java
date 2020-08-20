package com.psl.jfx_scene_manager.scene_manager;

import javafx.scene.Scene;

public class SceneInfo {

    private String name;
    private Scene scene;

    public SceneInfo(String name, Scene scene){
        this.name = name;
        this.scene = scene;
    }

    public String getName() { return name; }
    public Scene getScene() { return scene; }
}
