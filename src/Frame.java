import java.awt.Color;
/**
 * Represents a frame with bounds and an array of balls.
 */
public class Frame {
    private int leftBound;
    private int rightBound;
    private int upperBound;
    private int lowerBound;
    private Ball[] ballArr;
    private Color color;

    /**
     * Constructs a frame with the specified bounds, array of balls, and color.
     *
     * @param leftBound  the left bound of the frame
     * @param rightBound the right bound of the frame
     * @param upperBound the upper bound of the frame
     * @param lowerBound the lower bound of the frame
     * @param ballArr    the array of balls contained within the frame
     * @param color      the color of the frame
     */
    public Frame(int leftBound, int rightBound, int upperBound, int lowerBound, Ball[] ballArr, Color color) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.upperBound = upperBound;
        this.lowerBound = lowerBound;
        this.ballArr = ballArr;
        this.color = color;
    }

    /**
     * Constructs a frame with the specified bounds and array of balls, using the default color.
     *
     * @param leftBound  the left bound of the frame
     * @param rightBound the right bound of the frame
     * @param upperBound the upper bound of the frame
     * @param lowerBound the lower bound of the frame
     * @param ballArr    the array of balls contained within the frame
     */
    public Frame(int leftBound, int rightBound, int upperBound, int lowerBound, Ball[] ballArr) {
        this(leftBound, rightBound, upperBound, lowerBound, ballArr, Color.white);
    }

    /**
     * Returns the left bound of the frame.
     *
     * @return the left bound of the frame
     */
    public int getLeftBound() {
        return leftBound;
    }

    /**
     * Returns the right bound of the frame.
     *
     * @return the right bound of the frame
     */
    public int getRightBound() {
        return rightBound;
    }

    /**
     * Returns the upper bound of the frame.
     *
     * @return the upper bound of the frame
     */
    public int getUpperBound() {
        return upperBound;
    }

    /**
     * Returns the lower bound of the frame.
     *
     * @return the lower bound of the frame
     */
    public int getLowerBound() {
        return lowerBound;
    }

    /**
     * Returns the array of balls contained within the frame.
     *
     * @return the array of balls contained within the frame
     */
    public Ball[] getBallArr() {
        return ballArr;
    }

    /**
     * Returns the color of the frame.
     *
     * @return the color of the frame
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the width of the frame.
     *
     * @return the width of the frame
     */
    public int getWidth() {
        return rightBound - leftBound;
    }

    /**
     * Returns the height of the frame.
     *
     * @return the height of the frame
     */
    public int getHeight() {
        return lowerBound - upperBound;
    }
}
