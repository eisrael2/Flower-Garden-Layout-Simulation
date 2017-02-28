# Flower-Graden-Game-Layout

Third Assignment In Object Oriented Software Design

Requirements:

1. Start by setting up your initial environment. Note that this includes the “tool kit” on the left hand side including the items you can select (flowers, vegetables, flower beds, trees, birds etc), the garden (green rectangle) on the right hand side, and the red buttons. All the other right and side items will be placed into the garden by dragging. This must be a JavaFX application – and by now you should know how to get that started and to display something onto the screen. Suggestions: Think about what classes you will include in your design – Flower (probably vegetables can be treated like flowers with different colors), Tree, Animal(?), Flower bed. As a first step create a JavaFX application that lays out the framework.

What goes into a Flower class (similar for each of the node classes). Position, Color, and Shape. It needs to contain a Circle shape – and provide a getter method to return that shape upon demand. Note: It is this shape that you’ll add to the Scene Graph: e.g. pane.getChildren().add(redCircle); The red circle inside the Flower instance references the SAME red circle that is on the scene graph. Therefore you can manipulate it within its Flower object and that will cause it to move on the GUI.

Example of working with a circle: circle = new Circle();
circle.setCenterX( x coord);
circle.setCenterY( y coord);
circle.setRadius(Try different sizes here);
circle.setFill(choose a color);
circle.setStroke(Color.BLACK);
circle.setStrokeWidth(1);

2. Composite Pattern: Arrange your classes into a hierarchy using the composite pattern. You’ll need to define an interface and then have composite classes (flower bed, garden, tree?) and node classes (flower, animal). Suggestions: Think about common operations that you might like to perform on all classes in the hierarch. You can also add these later. Two starting ideas might be
a. void move(): Because we would like to be able to move items together. If flowers are in a flower bed and I use the mouse handler to move the flower bed – then all its contents
should move with it.
b. Boolean getClickedEntity(). Because we are placing the event handler on the scene – we need functionality to figure out which entity we are actually clicking on.

3. Mouse Event Handlers: We are going to place the event handler onto the scene (instead of onto individual shapes). This is a *slight* modification to the code in the Lab exercise. First decide which events you want to handle. I give examples for dragged:
scene.setOnMouseDragged(mouseHandler);
Then you have to create the mouseHandler. It goes into your main JavaFX class. Make sure you understand what each part of the code does.

In particular notice that:
- clickPoint captures the coordinates of the mouse click.
- garden.getClickedEntity() starts with the highest node in the graph of composite items (i.e. garden) and asks it to return the clicked item. In typical Composite fashion – this gets delegated throughout all the objects until a clicked element is found. - eventName stores the extracted name of the event. In this case “MOUSE_DRAGGED”
- deltaX and deltaY compute the CHANGE in X and Y since the last click point (see lastPosition)
- then the move method is called – and arguments are deltaX and deltaY. (The benefit of separating out the move method is that you can use it directly from the mouseHandler and also you can use it if an item is moved as part of its container.

4. Drag Functionality: If you click and drag an item in the toolkit you must be able to place it into the garden. If you place it in the garden then you must add it as a child of the garden. For example – if you drag a
flowerbed into the garden it becomes a child of the garden. Then if you drag a red flower into the flower bed it becomes a child of the flower bed (and by default a grand child of the garden).
a. Creating a permanent object in the tool kit: If you click on a red flower in the tool kit and drag it – then it is gone from the tool kit. You need to solve this problem. One solution is create a new object on the initial keypressed and then drag that object into the garden. Make sure that if you click on an object in the garden it doesn’t create a duplicate though. So you’ll have to figure out the logic and code for this.
b. Add child to parent: When you are dragging an item and then release the mouse – if it is placed into a composite object e.g. a tree or flower bed, then you need to add it as a child (i.e. follow the composite pattern).
c. Dragging entire composite structures: If you drag a flower – only the flower moves. If you drag a flower bed – the entire flower bed including all its contents must move. This is where you use typical composite delegation behavior. Don’t allow the large green garden to move. This is fixed.

5. Buttons for save and retrieve.
Finally create three buttons for save, retrieve, and exit. We’ll explore some additional options for saving to file. However, you’ll definitely want to use the FileChooser functionality.

Example:

FileChooser fileChooser = new FileChooser();
fileChooser.setInitialDirectory(new File("C:\\soccerPlayers"));
fileChooser.setTitle("Open Resource File");
fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
File selectedFile = fileChooser.showOpenDialog(teamBuilder.primaryStage);
image = new Image("File:" + selectedFile.toString(),80,100,false,false);
You will have to figure out what format you want to save your file in. You can choose any format, but we’ll be looking at JSON and serialization in class on Thursday 11th
Turn in Source code and jar file.

Grades will be assigned according to:
1. Initial UML design. Do this first. (5 points)
2. Journal – explaining your design decisions (5 points)
3. Functioning elegant code (40 points). We will specifically look for:
a. Mouse event handlers working effectively.
b. Composite pattern correctly implemented.
c. File IO working. Save and Retrieve.
d. Exit button should close the app. (Will go over in class on Thursday).
