import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import javafx.scene.shape.Circle;

//Initializing object Entities: Flower
public class Tree implements GardenEntity {
	
	Circle circle;
	Color color;
	GardenEntity parent;
	ArrayList<GardenEntity> childList = new ArrayList<GardenEntity>();
	
	public Tree(Point2D position, Color color) {	
		this.color = color;
		circle = new Circle(50, color);
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
	//Check tree type that was clicked and return it
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
		for(int i = 0; i < childList.size(); i++) {
			GardenEntity child = childList.get(i);
			child.move(dx, dy);
		}
	}

	@Override
	public GardenEntity duplicate() {
		Point2D point = new Point2D(circle.getCenterX(), circle.getCenterY());
		Tree tree = new Tree(point, color);
		return tree;
	}

	@Override
	public void addChild(GardenEntity child) {
		childList.add(child);
		child.setParent(this);
	}

	@Override
	public void removeChild(GardenEntity entity) {
		childList.remove(entity);
	}

	@Override
	public void addParent(GardenEntity parent) {
		this.parent = parent;
		
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
