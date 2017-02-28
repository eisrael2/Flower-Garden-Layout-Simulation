import java.util.ArrayList;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Circle;

//Initializing object Entities: Flower
public class Flower implements GardenEntity {
	
	Circle circle;
	Color color;
	GardenEntity parent;
	ArrayList<GardenEntity> childList = new ArrayList<GardenEntity>();
	
	public Flower(Point2D position, Color color) {
		this.color = color;
		circle = new Circle(20, color);
		circle.setCenterX(position.getX());
		circle.setCenterY(position.getY());	
	}
	
	public Shape getShape() {
		return circle;
	}

	@Override
	public GardenEntity getContainer(Point2D point) {
		return null;
	}
	//Check flower type that was clicked and return it
	@Override
	public GardenEntity getClickedEntity(Point2D point) {
		if (this.isClicked(point)) {
		return this;
		}
		else {
			return null;
		}
	}
	//Get the circle coordinates, radius and set result to it
	@Override
	public boolean isClicked(Point2D point) {
		double x = circle.getCenterX();
		double y = circle.getCenterY();
		double radius = circle.getRadius();
		double result = Math.sqrt(Math.pow(x-point.getX(), 2) + Math.pow(y-point.getY(), 2));
		return radius > result;
	}

	@Override
	public void move(double dx, double dy) {
		circle.setCenterX(circle.getCenterX() + dx);
		circle.setCenterY(circle.getCenterY() + dy);
	}

	@Override
	public GardenEntity duplicate() {
		Point2D point = new Point2D(circle.getCenterX(), circle.getCenterY());
		Flower flower = new Flower(point, color);
		return flower;
	}

	@Override
	public void addChild(GardenEntity child) {
		childList.add(child);
	}

	@Override
	public void addParent(GardenEntity parent) {
		this.parent = parent;	
	}

	@Override
	public void removeChild(GardenEntity entity) {
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
		return circle;
	}
}
