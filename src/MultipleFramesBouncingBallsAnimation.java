import java.awt.Color;
import java.util.Random;
/**
 * The MultipleFramesBouncingBallsAnimation class creates two frames,
 * fills them with gray and yellow color frames,
 * and adds bouncing balls of different sizes and colors within them.
 * The sizes are received as input from commandLine.
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * The main method that gets the input from the user and draws the animation.
     *
     * @param args The command line arguments, that represent the sizes of the ballArr
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation frameGenerator = new MultipleBouncingBallsAnimation();
        InputHandler inputHandler = new InputHandler();
        Random rand = new Random();
        int[] grayFrameSize, yellowFrameSize;
        Screen screen;
        Frame[] frames = new Frame[Globals.FRAMES_AMOUNT];
        if (args.length == 0) {
            System.out.println("Error: No input was received. Instead a random value was generated");
            grayFrameSize = new int[1];
            grayFrameSize[0] = rand.nextInt(Globals.DEFAULT_SIZE_VALUE) + 1;
            yellowFrameSize = new int[1];
            yellowFrameSize[0] = rand.nextInt(Globals.DEFAULT_SIZE_VALUE) + 1;
        } else {
            grayFrameSize = inputHandler.getIntArrSizes(args, 0, args.length / 2,
                    Globals.GREY_BALL_MINIMUM, Globals.GREY_BALL_MAXIMUM,
                    Globals.GREY_BALL_MINIMUM, Globals.GREY_BALL_MAXIMUM);
            yellowFrameSize = inputHandler.getIntArrSizes(args, args.length / 2, args.length,
                    Globals.YELLOW_BALL_MINIMUM, Globals.YELLOW_BALL_MAXIMUM,
                    Globals.YELLOW_BALL_MINIMUM, Globals.YELLOW_BALL_MAXIMUM);
        }
        // check if after error handling the arrays are empty
        if (grayFrameSize.length == 0 && yellowFrameSize.length == 0) {
            System.out.println("Error: No valid input was received. Instead a random value was generated");
            grayFrameSize = new int[1];
            grayFrameSize[0] = rand.nextInt(Globals.DEFAULT_SIZE_VALUE) + 1;
            yellowFrameSize = new int[1];
            yellowFrameSize[0] = rand.nextInt(Globals.DEFAULT_SIZE_VALUE) + 1;
        }
        Ball[] grayBallArr = frameGenerator.createBallArr(grayFrameSize,
                Globals.GREY_BALL_MINIMUM, Globals.GREY_BALL_MAXIMUM,
                Globals.GREY_BALL_MINIMUM, Globals.GREY_BALL_MAXIMUM);
        Ball[] yellowBallArr = frameGenerator.createBallArr(yellowFrameSize,
                Globals.YELLOW_BALL_MINIMUM, Globals.YELLOW_BALL_MAXIMUM,
                Globals.YELLOW_BALL_MINIMUM, Globals.YELLOW_BALL_MAXIMUM);
        frames[0] = new Frame(Globals.GREY_BALL_MINIMUM, Globals.GREY_BALL_MAXIMUM,
                Globals.GREY_BALL_MINIMUM, Globals.GREY_BALL_MAXIMUM, grayBallArr, Color.gray);
        frames[1] = new Frame(Globals.YELLOW_BALL_MINIMUM, Globals.YELLOW_BALL_MAXIMUM,
                Globals.YELLOW_BALL_MINIMUM, Globals.YELLOW_BALL_MAXIMUM, yellowBallArr, Color.yellow);
        screen = new Screen(Globals.BIG_WINDOW_WIDTH, Globals.BIG_WINDOW_HEIGHT,
                "MultipleFramesBouncingBallsAnimation", frames);
        screen.displayFrames();
    }
}
