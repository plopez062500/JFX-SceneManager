package com.psl.jfx_scene_manager.examples;

import com.psl.jfx_scene_manager.scene_manager.SceneInfo;
import com.psl.jfx_scene_manager.scene_manager.SceneManager;

public class FXMLLoadedScene {

    public FXMLLoadedScene(){
        SceneManager sm = SceneManager.getInstance();
        SceneInfo si = new SceneInfo("fxml loaded scene", sm.createSceneFromFXML("scenes/sample/sample.fxml"));
        sm.cacheScene(si, true);
    }
}
