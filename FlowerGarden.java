import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//Initializing objects: JavaFX and Garden Entities 
public class FlowerGarden extends Application {
	final int maxFlowers = 5;
	final int maxVegtegables = 4;
	Stage primaryStage;
	Scene scene;
	AnchorPane pane;
	ArrayList<GardenEntity> entities = new ArrayList<GardenEntity>();
	Garden garden;
	Image birdImage;
	ImageView birdImageView;
	int counter = 0;
	GardenEntity itemMove;
	GardenEntity copyStuff;
	Rectangle toolkit;
	
	//Create a Java Scene: set the windows size and create objects 
	@Override
	public void start(Stage primaryStage) throws Exception {
		pane = new AnchorPane();
		scene = new Scene(pane, 800, 725);
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Welcome to the Graden's Kipper");
		primaryStage.setScene(scene);
		primaryStage.show();
		addToolKit();
		CreateGarden();
		CreateFlowerBed();
		CreateTree();
		CreateFlowers();
		CreateVegtegables();
		scene.setOnMousePressed(mouseHandler);
		scene.setOnMouseDragged(mouseHandler);
		scene.setOnMouseReleased(mouseHandler);
	}
	
	//A toolkit to store the garden entities
	public void addToolKit(){
		toolkit = new Rectangle();
		toolkit.setFill(Color.WHITE);
		toolkit.setStroke(Color.BLACK);
		toolkit.setLayoutX(7);
		toolkit.setLayoutY(7);
		toolkit.setWidth(280);
		toolkit.setHeight(705);
		pane.getChildren().add(toolkit);
		
	}
	
	//Mouse Handler: set X and Y coordinates to set objects in JavaFX
	EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

		public Point2D lastPosition;
		private GardenEntity copyStuff;
		private GardenEntity itemMove;
		@Override
		public void handle(MouseEvent mouseEvent) {
			Point2D clickPoint = new Point2D(mouseEvent.getX(), mouseEvent.getY());
			String eventName = mouseEvent.getEventType().getName();
			
			switch(eventName) {
			
			//Mouse Handler to check where is an object was dragged 
			case "MOUSE_DRAGGED":
				System.out.println("Mouse Dragged");
				if (copyStuff != null) {
					double dx = clickPoint.getX() - lastPosition.getX();
					double dy = clickPoint.getY() - lastPosition.getY();
					System.out.println(" " + dx + " " + dy);
					copyStuff.move(dx, dy);
				}
				if(itemMove != null) {
					double dx = clickPoint.getX() - lastPosition.getX();
					double dy = clickPoint.getY() - lastPosition.getY();
					itemMove.move(dx, dy);
				}
				
			break;
			
			//Mouse Handler to check where location was an object released
			case "MOUSE_RELEASED":
				GardenEntity parent = garden.getClickedEntity(clickPoint);
				if(parent != null) {
					if(copyStuff != null) {
						parent.addChild(copyStuff);
						copyStuff = null;
					}
					if(itemMove != null && parent != itemMove) {
						parent.addChild(itemMove);
						itemMove = null;
					}
					else {
						if(copyStuff != null) {
							pane.getChildren().remove(copyStuff.returnNode()); 
							copyStuff = null;
						}
						if (itemMove != null) {
							pane.getChildren().remove(itemMove.returnNode());
							copyStuff = null;
						}
					}
				}
				
			break;
			
			//Mouse Handler to check if an object was pressed
			case "MOUSE_PRESSED":
				itemMove = garden.getClickedEntity(clickPoint);
				GardenEntity clicked = getClickedEntity(clickPoint);
				if(clicked != null){
					copyStuff = clicked.duplicate();
					pane.getChildren().add(copyStuff.returnNode());
				}
				if(itemMove != null) {
					if(itemMove.getParent() != null) {
						itemMove.getParent().removeChild(itemMove);
						itemMove.setParent(null);
					}
				}
				lastPosition = clickPoint;
			break;
	        	}	
			lastPosition = clickPoint;
	        }               
	     };
	    
	    //Create a Flower object in toolkit
		public void CreateFlowers() {	
			Label flowerLabel = new Label("Flowers");
			flowerLabel.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 20));
			flowerLabel.setLayoutX(30);
			flowerLabel.setLayoutY(30);
			pane.getChildren().add(flowerLabel);
			
			int bX = 10, bY =70;
			int rowCounter = 0;
			for(int j = 0; j < maxFlowers; j++){
				if (j > 0){
					if(rowCounter++ < 4){
						bY = bY+140;
					} else {
						bY = 70;
						bX = bX + 100;
						rowCounter = 0;
					}
				}	
				
				Flower flower = new Flower(new Point2D(50+50*j,100), Color.rgb(155,5,244));
				pane.getChildren().add(flower.getShape());
				entities.add(flower);
			}
		}
		
		//Enable the option to click an object
		public GardenEntity getClickedEntity(Point2D point) {
			if (isClicked(point)) {
				for(GardenEntity child : entities) {
					GardenEntity kid = child.getClickedEntity(point);
					if (kid != null) {
						return kid;
					}
				}
			}
				return null;
		}
		
		//Check if an object was clicked in toolkit
		public boolean isClicked(Point2D point) {
			double x = toolkit.getX();
			double y = toolkit.getY();
			double width = toolkit.getWidth();
			double height = toolkit.getHeight();
			if (point.getX() > x && point.getX() < x+width && point.getY() > y && point.getY() < y+height) {
				return true;
			}
			else {
				return false;
			}
		}
		
		//Create a Vegetable object in toolkit
		public void CreateVegtegables() {	
			Label vegteablesLabel = new Label("Vegteables");
			vegteablesLabel.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 20));
			vegteablesLabel.setLayoutX(30);
			vegteablesLabel.setLayoutY(140);
			pane.getChildren().add(vegteablesLabel);
			
			int bX = 10, bY =70;
			int rowCounter = 0;
			for(int j = 0; j < maxVegtegables; j++){
				if (j > 0){
					if(rowCounter++ < 4){
						bY = bY+140;
					} else {
						bY = 70;
						bX = bX + 100;
						rowCounter = 0;
					}
				}	
				Flower vegtegables = new Flower(new Point2D(50+50*j,200), Color.rgb(200,40,10));
				pane.getChildren().add(vegtegables.getShape());
				entities.add(vegtegables);
			}
		}
		//Create a Garden
		public void CreateGarden() {
			garden = new Garden(new Point2D(300,10), Color.rgb(10,250,150));
			pane.getChildren().add(garden.getShape());
		}
		
		//Create a Flower Bed object 
		public void CreateFlowerBed() {
			
			Label flowerBedLabel = new Label("Flower Bed");
			flowerBedLabel.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 20));
			flowerBedLabel.setLayoutX(30);
			flowerBedLabel.setLayoutY(250);
			pane.getChildren().add(flowerBedLabel);
			
			FlowerBed flowerBed = new FlowerBed(new Point2D(50,300), Color.rgb(99,97,44));
			pane.getChildren().add(flowerBed.getShape());
			entities.add(flowerBed);
		}
		
		//Create a Tree object in toolkit
		public void CreateTree() {
			
			Label treeLabel = new Label("Tree");
			treeLabel.setFont(Font.font ("Times New Roman", FontWeight.BOLD, 20));
			treeLabel.setLayoutX(30);
			treeLabel.setLayoutY(530);
			pane.getChildren().add(treeLabel);
			
			Tree tree = new Tree(new Point2D(100,600), Color.rgb(10,79,69));
			pane.getChildren().add(tree.getShape());
			entities.add(tree);
		}
		
	public static void main(String[] args) {
		launch(args);
	}
}
