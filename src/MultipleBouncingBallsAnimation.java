import java.awt.Color;
import java.util.Random;
/**
 * MultipleBouncingBallsAnimation class creates an animation of multiple balls bouncing inside a window.
 * It creates an array of balls, each with random position, velocity and color.
 * its gets its sizes from the commandLine.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * Creates an array of balls with the specified sizes and colors within the limits.
     *
     * @param sizes array of sizes
     * @param xMin  minimum value of the x-coordinate for the ballArr
     * @param xMax  maximum value of the x-coordinate for the ballArr
     * @param yMin  minimum value of the y-coordinate for the ballArr
     * @param yMax  maximum value of the y-coordinate for the ballArr
     * @return the ballArr
     */
    public Ball[] createBallArr(int[] sizes, int xMin, int xMax, int yMin, int yMax) {
        Ball[] ballArr = new Ball[sizes.length];
        Random rand = new Random();
        double xCenter, yCenter;
        int speed, angel, redElementRandom, blueElementRandom, greenElementRadom;
        Velocity velocity;
        Point center;

        for (int i = 0; i < ballArr.length; i++) {
            // generate random x and y coordinates within the given range.
            xCenter = rand.nextDouble() * ((xMax - sizes[i]) - (xMin + sizes[i])) + (xMin + sizes[i]);
            yCenter = rand.nextDouble() * ((yMax - sizes[i]) - (yMin + sizes[i])) + (yMin + sizes[i]);
            // generate random angle and color
            angel = rand.nextInt(Globals.MAX_ANGEL) + 1;
            redElementRandom = rand.nextInt(Globals.COLOR_BOUND);
            blueElementRandom = rand.nextInt(Globals.COLOR_BOUND);
            greenElementRadom = rand.nextInt(Globals.COLOR_BOUND);
            center = new Point(xCenter, yCenter);
            ballArr[i] = new Ball(center, sizes[i], new Color(redElementRandom, blueElementRandom, greenElementRadom));
            speed = ballArr[i].getSpeed(sizes[i]);
            velocity = Velocity.fromAngleAndSpeed(angel, speed);
            // create ball object
            ballArr[i].setVelocity(velocity);
        }
        return ballArr;
    }

    /**
     * The main method that gets the input from the user and draws the animation.
     *
     * @param args The command line arguments, that represent the sizes of the ballArr
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation artGenerator = new MultipleBouncingBallsAnimation();
        InputHandler inputHandler = new InputHandler();
        int[] validSizes;
        Random rand = new Random();
        if (args.length == 0) {
            System.out.println("Error: No input was received. Instead a random value was generated");
            validSizes = new int[1];
            validSizes[0] = rand.nextInt(Globals.DEFAULT_SIZE_VALUE) + 1;
        } else {
            validSizes = inputHandler.getIntArrSizes(args, 0, args.length,
                    Globals.SMALL_WINDOW_START, Globals.SMALL_WINDOW_END,
                    Globals.SMALL_WINDOW_START, Globals.SMALL_WINDOW_END);
            // check if after error handling the arrays are empty
            if (validSizes.length == 0) {
                System.out.println("Error: No input was received. Instead a random value was generated");
                validSizes = new int[1];
                validSizes[0] = rand.nextInt(Globals.DEFAULT_SIZE_VALUE) + 1;
            }
        }
        Ball[] ballArr = artGenerator.createBallArr(validSizes, Globals.SMALL_WINDOW_START,
                Globals.SMALL_WINDOW_END, Globals.SMALL_WINDOW_START, Globals.SMALL_WINDOW_END);
        Frame[] frame = new Frame[Globals.SINGLE_FRAME];
        frame[0] = new Frame(Globals.SMALL_WINDOW_START, Globals.SMALL_WINDOW_END,
                Globals.SMALL_WINDOW_START, Globals.SMALL_WINDOW_END, ballArr);
        Screen screen = new Screen(Globals.SMALL_WINDOW_END, Globals.SMALL_WINDOW_END,
                "MultipleBouncingBallsAnimation", frame);
        screen.displayFrames();
    }
}
