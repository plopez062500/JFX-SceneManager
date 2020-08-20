package com.psl.jfx_scene_manager.scene_manager;

import com.psl.jfx_scene_manager.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;

public class SceneManager {

    private static SceneManager instance;
    private static Stage stage;
    private LinkedList<SceneInfo> scenes;
    private LinkedList<SceneInfo> sceneStack;
    private SceneInfo currentScene;

    /**
     * Default constructor just initialized some values
     */
    private SceneManager(){
        scenes = new LinkedList<>();
        sceneStack = new LinkedList<>();
    }

    /**
     * Stores the stage that the SceneManage should display scenes on.
     * This method must be called before you can retrieve the instance
     * of this class.
     * @param s
     * The Stage to display scenes on
     */
    public static void initialize(Stage s){ stage = s; }

    /**
     * Store an instance of a SceneInfo object so easily load scenes at
     * any point by their name
     *
     * @param si
     * The SceneInfo object to be stored
     * @param pushToStage
     * Whether or not to display the scene on the stage once its stored
     */
    public void cacheScene(SceneInfo si, boolean pushToStage){
        for (SceneInfo siTemp : scenes) {
            if (siTemp.getName().toUpperCase().equals(si.getName().toUpperCase())) {
                System.err.println(String.format("A scene with name (%s) has already been cached", si.getName()));
                return;
            }
        }

        scenes.add(si);

        if (pushToStage){
            makeCurrent(si);
            return;
        }
    }

    /**
     * Create a Scene object from a fxml file
     * @param fxmlPath
     * The path to the fxml file relative the the Main class
     * @return
     * A Scene object created from loading the specified FXML file
     */
    public Scene createSceneFromFXML(String fxmlPath){
        try{
            Parent p = FXMLLoader.load(Main.class.getResource(fxmlPath));
            return new Scene(p);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Push a scene to the stage without caching it. This method
     * should be called for scenes that will not be used often.
     *
     * @param si
     * A SceneInfo object containing the scene object to
     * display on the stage
     * NOTE: the name of the scene is not important in this case because
     * this scene will not be cached and loaded again later
     */
    public void showScene(SceneInfo si){
        makeCurrent(si);
    }

    /**
     * Load a scene that has been cached
     *
     * @param name
     * The name of the scene you want to be loaded
     */
    public void loadScene(String name){
        SceneInfo si = getScene(name);
        if (si != null){
            makeCurrent(si);
            return;
        }
        System.err.println(String.format("Could not find scene with name (%s)", name));
    }

    /**
     * Load the next scene in the list of scenes.
     */
    public void loadNextScene(){
        if (scenes.size() == 0){
            System.err.println("No scenes have been cached in the SceneManager");
            return;
        }
        SceneInfo si = getScene(currentScene.getName());
        if (si == null){ //Scene was displayed but not cached. Load first scene in list.
            makeCurrent(scenes.getFirst());
            return;
        }
        if (si.equals(scenes.getLast())){
            System.err.println("Currently showing last scene in list");
            return;
        }

        makeCurrent(scenes.get(scenes.indexOf(currentScene) + 1));
    }

    /**
     * Pop the current scene off of the stack and update the
     * SceneManager to reflect the new stack
     */
    public void pop(){
        if (sceneStack.size() > 1){
            sceneStack.pop();
            makeCurrent(sceneStack.getFirst(), false);
            return;
        }
        System.err.println("No previous scene on the stack");
    }

    /**
     * Private utility method to update the current scene and
     * push the new current scene to the stage. Offers the option
     * to display a scene without pushing it to the stack.
     *
     * @param si
     * The SceneInfo object to be set as current and displayed
     * @param pushToStack
     */
    private void makeCurrent(SceneInfo si, boolean pushToStack){
        if (pushToStack) { sceneStack.push(si); }
        currentScene = si;
        stage.setScene(si.getScene());
    }

    /**
     * Just calls the other makeCurrent method with pushToStack as true.
     * @param si
     * The SceneInfo object to be set as current and displayed
     */
    private void makeCurrent(SceneInfo si){
        makeCurrent(si, true);
    }

    /**
     * Private utility method to get a SceneInfo object from
     * the scenes list based on a given name
     *
     * @param name
     * The name of the scene to search for
     * @return
     * A SceneInfo object found with the specified name
     */
    private SceneInfo getScene(String name){
        for (SceneInfo si : scenes){
            if (si.getName().toUpperCase().equals(name.toUpperCase())){
                return si;
            }
        }
        return null;
    }

    /**
     * @return
     * A SceneInfo object representing the scene that is currently
     * being displayed
     */
    public SceneInfo getCurrentScene(){ return currentScene; }

    /**
     * @return
     * Whether or not state has been initialized
     */
    public static boolean isInitialized() { return stage == null; }

    /**
     * @return
     * Thread safe method that returns an instance of SceneManager.
     * NOTE: you must call the initialize method before you can get
     * an instance of this class.
     */
    public static SceneManager getInstance(){
        if (isInitialized()){
            System.err.println("Unable to get SceneManager instance because it has not been initialized");
            return null;
        }
        if (instance == null){
            synchronized (SceneManager.class){
                if (instance == null){
                    instance = new SceneManager();
                }
            }
        }
        return instance;
    }
}
