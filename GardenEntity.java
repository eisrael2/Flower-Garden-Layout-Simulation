import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

//Garden Entity Interface
public interface GardenEntity {
	public GardenEntity getContainer(Point2D point); 
	void move(double dx, double dy); //Enable move with x and y points
	Shape getShape(); //Enable a shape type
	GardenEntity getClickedEntity(Point2D point); //
	boolean isClicked(Point2D point); //Enable a click entity of an object
	GardenEntity duplicate(); //Enable a copy of an entity object
	void addChild(GardenEntity copyStuff); //Add an object on top of another object
	void addParent(GardenEntity Parent); //Add an object on top of another object
	void removeChild(GardenEntity entity);  ///Remove an object on top of another object
	GardenEntity getParent();  //Select a parent
	void setParent(GardenEntity entity); //Set parent to entity
	Node returnNode();
}
