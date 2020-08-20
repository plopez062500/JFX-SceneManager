# JFX SceneManager
This project is meant to provide an easy and effective way to manage and switch between scenes in javafx. 

## Working with the SceneManager
In the entry of your application you must initialize the SceneManager and pass it the stage you want your scenes to be displayed on.
```java
@Override
public void start(Stage primaryStage) throws Exception{
	primaryStage.setTitle("JFX SceneManager");
	//Manage other stage properties
	
	//Initialize the SceneManger
	SceneManager.initialize(primaryStage);

	//Show the Scene
	primaryStage.show();
}
```

Now anywhere in your applciation can access the scene manager like so.
```java
SceneManger sm = SceneManger.getInstance();
//do stuff with the SceneManager instance
```

## Managing Scenes
The SceneManager handles scenes through SceneInfo objects. This is a class that stores a scene and a name to be associated with it. There are two ways the SceneManger handles scenes.

You can initialize a scene and have the SceneManger store it so that it is readily avaiable anywhere in your application. This also allows you to refer to a scene by the name you associated with it.
```java
SceneManager sm = SceneManager.getInstance();

VBox vBox1 = new VBox();
vBox1.getChildren().add(new Label("Scene 1"));
SceneInfo scene1 = new SceneInfo("Scene 1", new Scene(vBox1));

VBox vBox2 = new VBox();
vBox2.getChildren().add(new Label("Scene 2"));
SceneInfo scene2 = new SceneInfo("Scene 2", new Scene(vBox2));

//The second argument is whether or not you want to have the scene pushed to the stage
sm.cacheScene(scene1, true);
sm.cacheScene(scene2, false);

//Later if you wanted to load scene 2 to the stage
sm.loadScene("scene 2");
```

Alternativley if you wanted to push a scene to the stage that you will not need to load very often or only once.
```java
SceneManager sm = SceneManager.getInstance();
VBox vBox = new VBox(new Label("This scene is not cached"));

SceneInfo si = new SceneInfo("Name doesnt matter", new Scene(vBox));

sm.showScene(si);
```
There are a couple other methods for ease of navigation. loadNextScene() and pop(). loadNextScene will load the next scene in the list of cached scenes. Keeping this in mind you can be clever about the order in which you load scenes into the SceneManager. Each time a scene is pushed to the stage it is also pushed onto the scene stack. The pop method allows you to load the previously loaded seen with a single call.
```java
SceneManager sm = SceneManager.getInstance();

VBox vBox1 = new VBox();
vBox1.getChildren().add(new Label("Scene 1"));
SceneInfo scene1 = new SceneInfo("Scene 1", new Scene(vBox1));

VBox vBox2 = new VBox();
vBox2.getChildren().add(new Label("Scene 2"));
SceneInfo scene2 = new SceneInfo("Scene 2", new Scene(vBox2));

sm.cacheScene(scene1, true);
sm.cacheScene(scene2, false);
//Currently scene 1 is being displayed

sm.loadNextScene();
//Now Scene 2 is being displayed

sm.pop();
//Scene 2 is popped off of the stack revealing Scene 1 again
```

The SceneManager also has a method to create Scene objects given a URL path to a .fxml file.
```java
SceneManager sm = SceneManager.getInstance();

//Load a scene from a .fxml file
Scene s = sm.createSceneFromFXML(Main.class.getResource("scenes/sample/sample.fxml");
SceneInfo si = new SceneInfo("fxml loaded scene", s);

//cache the loaded scene in the SceneManager
sm.cacheScene(si, true);
```

The repo is set up as an intelliJ project ready to go with examples of the code above. In the jar directory is the library to inlcude in your own projects. 