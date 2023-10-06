import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * This class represents a screen that contains multiple frames with balls.
 */
public class Screen {
    private int rightBound;
    private int lowerBound;
    private String name;
    private Frame[] frames;

    /**
     * Constructor for the Screen class.
     *
     * @param rightBound the right bound of the screen.
     * @param lowerBound the lower bound of the screen.
     * @param name       the name of the screen.
     * @param frames     an array of frames to be displayed on the screen.
     */
    Screen(int rightBound, int lowerBound, String name, Frame[] frames) {
        this.rightBound = rightBound;
        this.lowerBound = lowerBound;
        this.name = name;
        this.frames = frames;
    }

    /**
     * Returns an array of frames.
     *
     * @return the array of frames.
     */
    public Frame[] getFrames() {
        return frames;
    }

    /**
     * Displays the frames on the screen.
     */
    public void displayFrames() {
        GUI gui = new GUI(name, rightBound, lowerBound);
        Sleeper sleeper = new Sleeper();
        DrawSurface drawSurface;
        ScreenMotionManager screenMotionManager = new ScreenMotionManager(this);
        while (true) {
            drawSurface = gui.getDrawSurface();
            for (Frame frame : frames) {
                drawSurface.setColor(frame.getColor());
                drawSurface.fillRectangle(frame.getLeftBound(), frame.getUpperBound(),
                        frame.getWidth(), frame.getHeight());
                for (Ball ball : frame.getBallArr()) {
                    ball.drawOn(drawSurface);
                }
            }
            screenMotionManager.moveOneStep();
            gui.show(drawSurface);
            sleeper.sleepFor(Globals.SLEEP_TIMER);
        }
    }
}
