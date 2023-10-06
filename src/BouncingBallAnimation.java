import java.awt.Color;
/**
 * BouncingBallAnimation class creates an animation of a bouncing ball on a GUI window.
 * The ball starts at a given point from commandLine and bounces around within the bounds of the window.
 */
public class BouncingBallAnimation {
    /**
     * Moves the circle's center back into the bounds of the screen if the center is in the screen but with the
     * ball.getSize() it deviates out of bound.
     *
     * @param ball  the ball to bounce back in to bound
     * @param frame the current frane of the ball
     */
    public static void bounceInToBound(Ball ball, Frame frame) {
        if (ball.getCenter().getX() <= ball.getSize()
                || ball.getCenter().getX() + ball.getSize() >= frame.getRightBound()
                || ball.getCenter().getY() <= ball.getSize()
                || ball.getCenter().getY() + ball.getSize() >= frame.getLowerBound()) {
            ball.getCenter().setX(frame.getRightBound() / 2.0);
            ball.getCenter().setY(frame.getLowerBound() / 2.0);
        }

    }

    /**
     * The main method that gets the input from the user and draws the animation.
     *
     * @param args The command line arguments. that represent (x,y) center and (dx,dy)
     */
    public static void main(String[] args) {
        InputHandler inputHandler = new InputHandler();
        double[] inputArray = inputHandler.getDoubleArrSizes(args, Globals.SMALL_WINDOW_START,
                Globals.SMALL_WINDOW_END, Globals.SMALL_WINDOW_START, Globals.SMALL_WINDOW_END);
        Point point = new Point(inputArray[0], inputArray[1]);
        Velocity velocity = new Velocity(inputArray[2], inputArray[3]);
        Ball[] ball = new Ball[1];
        ball[0] = new Ball(point, Globals.BOUNCING_BALL_ANIMATION_RADIUS, Color.BLACK);
        ball[0].setVelocity(velocity.getDx(), velocity.getDy());
        Frame[] frame = new Frame[Globals.SINGLE_FRAME];
        frame[0] = new Frame(Globals.SMALL_WINDOW_START, Globals.SMALL_WINDOW_END,
                Globals.SMALL_WINDOW_START, Globals.SMALL_WINDOW_END, ball);
        bounceInToBound(ball[0], frame[0]);
        Screen screen = new Screen(Globals.SMALL_WINDOW_END, Globals.SMALL_WINDOW_END,
                "BouncingBallAnimation", frame);
        screen.displayFrames();

    }
}
