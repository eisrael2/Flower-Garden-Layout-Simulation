import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

//Initializing object Entities: Garden 
public class Garden implements GardenEntity {
	
	Rectangle rectangle;
	GardenEntity parent;
	ArrayList<GardenEntity> childList = new ArrayList<GardenEntity>();
	
	//Create a Garden object
	public Garden(Point2D position, Color color) {	
		rectangle = new Rectangle();
		rectangle.setLayoutX(position.getX());
		rectangle.setLayoutY(position.getY());
		rectangle.setWidth(500);
		rectangle.setHeight(700);
		rectangle.setArcWidth(20);
		rectangle.setArcHeight(20);
		rectangle.setStroke(color);
		rectangle.setFill(color);		
	}
	
	public Shape getShape() {
		return rectangle;
	}

	@Override
	public GardenEntity getContainer(Point2D point) {

		return null;
	}
	
	//Check garden type that was clicked and return it
	@Override
	public GardenEntity getClickedEntity(Point2D point) {
		if (this.isClicked(point)) {
			for(GardenEntity child : childList) {
				GardenEntity kid = child.getClickedEntity(point);
				if (kid != null) {
					return kid;
				}
			}
			return this;
		}
		else {
			return null;
		}
	}
	
	//Get the rectangle coordinates, height and width and set point to it
	@Override
	public boolean isClicked(Point2D point) {
		double x = rectangle.getLayoutX();
		double y = rectangle.getLayoutY();
		double width = rectangle.getWidth();
		double height = rectangle.getHeight();
		if (point.getX() > x && point.getX() < x+width && point.getY() > y && point.getY() < y+height) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void move(double dx, double dy) {
		
	}

	@Override
	public GardenEntity duplicate() {
		return null;
	}

	@Override
	public void addChild(GardenEntity child) {
		childList.add(child);
		child.setParent(this);
	}

	@Override
	public void addParent(GardenEntity Parent) {
	}

	@Override
	public void removeChild(GardenEntity entity) {
		childList.remove(entity);
	}

	@Override
	public GardenEntity getParent() {
		return parent;	
	}

	@Override
	public void setParent(GardenEntity entity) {
		parent = entity;
	}

	@Override
	public Node returnNode() {
		return rectangle;
	}
}
