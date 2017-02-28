import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

//Initializing object Entities: Flower Bed
public class FlowerBed implements GardenEntity {
	Rectangle rectangle;
	Color color;
	GardenEntity parent;
	ArrayList<GardenEntity> childList = new ArrayList<GardenEntity>();
	Point2D position;
	
	//Create a Flower Bed object
	public FlowerBed(Point2D position, Color color) {	
		this.position = position;
		this.color = color;
		rectangle = new Rectangle();
		rectangle.setLayoutX(position.getX());
		rectangle.setLayoutY(position.getY());
		rectangle.setWidth(150);
		rectangle.setHeight(200);
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
	
	//Check flower bed type that was clicked and return it
	@Override
	public GardenEntity getClickedEntity(Point2D point) {
		if (this.isClicked(point)) {
			for(int i = 0; i < childList.size(); i++) {
				GardenEntity child = childList.get(i).getClickedEntity(point);
				if (child != null) {
					return child;
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
	
	//Move the rectangle in the grid to a designated coordinates
	@Override
	public void move(double dx, double dy) {
		rectangle.setLayoutX(rectangle.getLayoutX() + dx);
		rectangle.setLayoutY(rectangle.getLayoutY() + dy);
		for(GardenEntity child : childList){
			child.move(dx, dy);
		}
	}

	@Override
	public GardenEntity duplicate() {
		return new FlowerBed(position, color);

	}

	@Override
	public void addChild(GardenEntity child) {
		childList.add(child);
		child.setParent(this);
	}

	@Override
	public void addParent(GardenEntity parent) {
		this.parent = parent;
		
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