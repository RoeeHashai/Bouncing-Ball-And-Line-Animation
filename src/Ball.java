import biuoop.DrawSurface;
import java.awt.Color;
/**
 * The Ball class represents a ball in 2D plane, defined by its center point, radius, color and velocity.
 * It provides methods to access and modify the ball's properties, and methods to draw the ball on a DrawSurface
 * and move the ball within its unique limits.
 */
public class Ball {
    // The ball object attributes
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;

    /**
     * Creates a new Ball object with a center point, radius and color.
     *
     * @param center the center point of the ball
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;

    }

    /**
     * Creates a new Ball object with a center point (by x and y coordinates), radius and color.
     *
     * @param x      the x coordinate of the center point
     * @param y      the y coordinate of the center point
     * @param radius the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        this(new Point(x, y), radius, color);
    }

    /**
     * Returns the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return radius;
    }

    /**
     * Returns the x coordinate of the center.
     *
     * @return the x coordinate of the center
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Returns the y coordinate of the center.
     *
     * @return the y coordinate of the center
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Returns the velocity of the ball(velocity has the dx, and dy values).
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param velocity the velocity of the ball
     */
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * Sets the velocity of the ball. By given values of dx and dy.
     *
     * @param dx the dx value of the ball
     * @param dy the dy value of the ball
     */
    public void setVelocity(double dx, double dy) {
        velocity = new Velocity(dx, dy);
    }

    /**
     * Sets the radius of the ball.
     *
     * @param radius the radius of the ball
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Sets the color of the ball.
     *
     * @param color the color of the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the center of the ball.
     *
     * @param center the center of the ball
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Calculates the speed of a ball based on its size. The smaller the faster.
     *
     * @param radius the size of the ball
     * @return the speed of the ball
     */
    public int getSpeed(double radius) {
        int speed;
        if (radius >= Globals.SIZE_BIG_BALL) {
            speed = Globals.MIN_SPEED;
        } else {
            speed = (int) (((Globals.SIZE_BIG_BALL + Globals.MIN_SPEED) - radius) * 0.3);
        }
        return speed;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface to draw the ball on
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }
}