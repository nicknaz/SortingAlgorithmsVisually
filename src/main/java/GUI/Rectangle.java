package GUI;

import java.awt.geom.Rectangle2D;

public class Rectangle extends Rectangle2D {

    private double x;
    private double y;
    private double weight;
    private double height;

    public Rectangle() {
    }

    public Rectangle(double x, double y, double weight, double height) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.height = height;
    }

    @Override
    public void setRect(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.height = height;
    }

    @Override
    public int outcode(double x, double y) {
        return 0;
    }

    @Override
    public Rectangle2D createIntersection(Rectangle2D r) {
        return null;
    }

    @Override
    public Rectangle2D createUnion(Rectangle2D r) {
        return null;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return weight;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
