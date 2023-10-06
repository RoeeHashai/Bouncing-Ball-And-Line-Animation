/**
 * The Globals class contains a collection of constant values used throughout the program.
 */
public class Globals {
    // A small threshold used for comparing double values
    public static final double THRESHOLD = 0.00001;
    // The start and end positions of the small window
    public static final int SMALL_WINDOW_START = 0;
    public static final int SMALL_WINDOW_END = 200;

    // The width and height of the abstract art program
    public static final int ABSTRACT_ART_WIDTH = 400;
    public static final int ABSTRACT_ART_HEIGHT = 300;

    // The number of random lines to draw on the abstract art program
    public static final int NUM_RANDOM_LINES = 10;

    // The point radius  on the abstract art program
    public static final int POINT_RADIUS = 3;

    // The bouncing ball animation radius
    public static final int BOUNCING_BALL_ANIMATION_RADIUS = 30;

    // The time in milliseconds to wait between each iteration of the bouncing ball animation loop.
    public static final int SLEEP_TIMER = 40;

    // The size of the maximum ball
    public static final int SIZE_BIG_BALL = 50;

    // The size of maximum speed(dx,dy) of a 30 size radius ball
    public static final double MAX_DX_DY = 130.0;

    // The size of minimum speed of a ball
    public static final int MIN_SPEED = 10;

    // The size of maximum angel
    public static final int MAX_ANGEL = 360;

    // Windows sizes
    public static final int BIG_WINDOW_WIDTH = 800;
    public static final int BIG_WINDOW_HEIGHT = 600;
    public static final int GREY_BALL_MINIMUM = 50;
    public static final int GREY_BALL_MAXIMUM = 500;

    public static final int YELLOW_BALL_MINIMUM = 450;
    public static final int YELLOW_BALL_MAXIMUM = 600;

    // valid length of args of bouncing ball animation
    public static final int VALID_LENGTH = 4;

    // default radius value used if no input was received from terminal
    public static final int DEFAULT_SIZE_VALUE = 50;

    public static final int COLOR_BOUND = 256;

    public static final int FRAMES_AMOUNT = 2;

    public static final int SINGLE_FRAME = 1;

}
