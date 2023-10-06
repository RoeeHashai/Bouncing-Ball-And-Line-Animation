/**
 * A class that manages the motion of the ball within the screen.
 * It ensures that the ball stays within the screen limits by bouncing it back in the opposite direction
 * if it's about to go out of range in the X or Y direction.
 */
public class ScreenMotionManager {
    private Screen screen;

    ScreenMotionManager(Screen screen) {
        this.screen = screen;
    }

    /**
     * Moves the ball one step within the limits of the screen.
     * If the next move of the ball is about to go out of range in the X or Y direction,
     * it bounces back in the opposite direction.
     */
    public void moveOneStep() {
        for (Frame frame : screen.getFrames()) {
            for (Ball ball : frame.getBallArr()) {
                if (ball.getVelocity() == null) {
                    return;
                }
                Point nextStepCenter = ball.getVelocity().applyToPoint(ball.getCenter());
                boolean eventInRange = isEventInRange(nextStepCenter, ball, frame);

                if (eventInRange) {
                    ball.setCenter(nextStepCenter);
                } else {
                    handleOutOfBounds(nextStepCenter, ball, frame);
                }
            }
        }
    }

    private boolean isEventInRange(Point nextStepCenter, Ball ball, Frame frame) {
        return nextStepCenter.getX() - ball.getSize() > frame.getLeftBound()
                && nextStepCenter.getX() + ball.getSize() < frame.getRightBound()
                && nextStepCenter.getY() - ball.getSize() > frame.getUpperBound()
                && nextStepCenter.getY() + ball.getSize() < frame.getLowerBound();
    }

    private void handleOutOfBounds(Point nextStepCenter, Ball ball, Frame frame) {
        boolean eventDeviationXMin = nextStepCenter.getX() - ball.getSize() < frame.getLeftBound()
                || doubleCompare(nextStepCenter.getX() - ball.getSize(), frame.getLeftBound());
        boolean eventDeviationYMin = nextStepCenter.getY() - ball.getSize() < frame.getUpperBound()
                || doubleCompare(nextStepCenter.getY() - ball.getSize(), frame.getUpperBound());
        boolean eventDeviationXMax = nextStepCenter.getX() + ball.getSize() > frame.getRightBound()
                || doubleCompare(nextStepCenter.getX() + ball.getSize(), frame.getRightBound());
        boolean eventDeviationYMax = nextStepCenter.getY() + ball.getSize() > frame.getLowerBound()
                || doubleCompare(nextStepCenter.getY() + ball.getSize(), frame.getLowerBound());

        flipDxDy(eventDeviationXMin, eventDeviationXMax, eventDeviationYMin, eventDeviationYMax, ball);

        if (eventDeviationXMin && eventDeviationYMin) {
            handleDeviationLeftUpperCorner(ball, frame);
        } else if (eventDeviationXMin && eventDeviationYMax) {
            handleDeviationLeftLowerCorner(ball, frame);
        } else if (eventDeviationXMax && eventDeviationYMax) {
            handleDeviationRightLowerCorner(ball, frame);
        } else if (eventDeviationXMax && eventDeviationYMin) {
            handleDeviationRightUpperCorner(ball, frame);
        } else if (eventDeviationXMin) {
            handleDeviationXMin(nextStepCenter, ball, frame);
        } else if (eventDeviationXMax) {
            handleDeviationXMax(nextStepCenter, ball, frame);
        } else if (eventDeviationYMin) {
            handleDeviationYMin(nextStepCenter, ball, frame);
        } else if (eventDeviationYMax) {
            handleDeviationYMax(nextStepCenter, ball, frame);
        }
    }

    // Handle deviation when the ball hits the left upper corner of the screen
    private void handleDeviationLeftUpperCorner(Ball ball, Frame frame) {
        if (doubleCompare(ball.getCenter().getX() - ball.getSize(), frame.getLeftBound())
                && doubleCompare(ball.getCenter().getY() - ball.getSize(), frame.getUpperBound())) {
            ball.setCenter(ball.getVelocity().applyToPoint(ball.getCenter()));
        }
        ball.setCenter(new Point(frame.getLeftBound() + ball.getSize(), frame.getUpperBound() + ball.getSize()));
    }

    // Handle deviation when the ball hits the left lower corner of the screen
    private void handleDeviationLeftLowerCorner(Ball ball, Frame frame) {
        if (doubleCompare(ball.getCenter().getX() - ball.getSize(), frame.getLeftBound())
                && doubleCompare(ball.getCenter().getY() + ball.getSize(), frame.getLowerBound())) {
            ball.setCenter(ball.getVelocity().applyToPoint(ball.getCenter()));
        } else {
            ball.setCenter(new Point(frame.getLeftBound() + ball.getSize(), frame.getLowerBound() - ball.getSize()));
        }
    }

    // Handle deviation when the ball hits the right lower corner of the screen
    private void handleDeviationRightLowerCorner(Ball ball, Frame frame) {
        if (doubleCompare(ball.getCenter().getX() + ball.getSize(), frame.getRightBound())
                && doubleCompare(ball.getCenter().getY() + ball.getSize(), frame.getLowerBound())) {
            ball.setCenter(ball.getVelocity().applyToPoint(ball.getCenter()));
        } else {
            ball.setCenter(new Point(frame.getRightBound() - ball.getSize(), frame.getLowerBound() - ball.getSize()));
        }
    }

    // Handle deviation when the ball hits the right upper corner of the screen
    private void handleDeviationRightUpperCorner(Ball ball, Frame frame) {
        if (doubleCompare(ball.getCenter().getX() + ball.getSize(), frame.getRightBound())
                && doubleCompare(ball.getCenter().getY() - ball.getSize(), frame.getUpperBound())) {
            ball.setCenter(ball.getVelocity().applyToPoint(ball.getCenter()));
        } else {
            ball.setCenter(new Point(frame.getRightBound() - ball.getSize(), frame.getUpperBound() + ball.getSize()));
        }

    }

    // Handle deviation when the ball hits the left bound of the screen
    private void handleDeviationXMin(Point nextStepCenter, Ball ball, Frame frame) {
        Line centersLines = new Line(ball.getCenter(), nextStepCenter);
        Point newCenter =
                getInterPointCentersLine(centersLines, frame.getLeftBound() + ball.getSize(),
                        frame.getUpperBound(), frame.getLeftBound() + ball.getSize(), frame.getLowerBound());
        if (ball.getCenter().equals(newCenter)) {
            ball.setCenter(ball.getVelocity().applyToPoint(ball.getCenter()));
        } else {
            ball.setCenter(newCenter);
        }
    }

    // Handle deviation when the ball hits the right bound of the screen
    private void handleDeviationXMax(Point nextStepCenter, Ball ball, Frame frame) {
        Line centersLines = new Line(ball.getCenter(), nextStepCenter);
        Point newCenter = getInterPointCentersLine(centersLines, frame.getRightBound() - ball.getSize(),
                frame.getUpperBound(), frame.getRightBound() - ball.getSize(), frame.getLowerBound());
        if (ball.getCenter().equals(newCenter)) {
            ball.setCenter(ball.getVelocity().applyToPoint(ball.getCenter()));
        } else {
            ball.setCenter(newCenter);
        }
    }

    // Handle deviation when the ball hits the upper bound of the screen
    private void handleDeviationYMin(Point nextStepCenter, Ball ball, Frame frame) {
        Line centersLines = new Line(ball.getCenter(), nextStepCenter);
        Point newCenter = getInterPointCentersLine(centersLines, frame.getLeftBound(),
                frame.getUpperBound() + ball.getSize(), frame.getRightBound(),
                frame.getUpperBound() + ball.getSize());
        if (ball.getCenter().equals(newCenter)) {
            ball.setCenter(ball.getVelocity().applyToPoint(ball.getCenter()));
        } else {
            ball.setCenter(newCenter);
        }
    }

    // Handle deviation when the ball hits the lower bound of the screen
    private void handleDeviationYMax(Point nextStepCenter, Ball ball, Frame frame) {
        Line centersLines = new Line(ball.getCenter(), nextStepCenter);
        Point newCenter = getInterPointCentersLine(centersLines, frame.getLeftBound(),
                frame.getLowerBound() - ball.getSize(), frame.getRightBound(),
                frame.getLowerBound() - ball.getSize());
        if (ball.getCenter().equals(newCenter)) {
            ball.setCenter(ball.getVelocity().applyToPoint(ball.getCenter()));
        } else {
            ball.setCenter(newCenter);
        }
    }

    // Flips the x and/or y velocity of an object if it goes out of boundaries
    private void flipDxDy(boolean eventDeviationXMin, boolean eventDeviationXMax,
                          boolean eventDeviationYMin, boolean eventDeviationYMax, Ball ball) {
        if (eventDeviationXMin || eventDeviationXMax) {
            ball.getVelocity().setDx(ball.getVelocity().getDx() * (-1));
        }
        if (eventDeviationYMin || eventDeviationYMax) {
            ball.getVelocity().setDy(ball.getVelocity().getDy() * (-1));
        }
    }

    // Calculates the intersection point between two lines: centersLines and toIntersect
    private Point getInterPointCentersLine(Line centersLines, double otherLineStartX, double otherLineStartY,
                                           double otherLineEndX, double otherLineEndY) {
        Point startToInter = new Point(otherLineStartX, otherLineStartY);
        Point endToInter = new Point(otherLineEndX, otherLineEndY);
        Line toIntersect = new Line(startToInter, endToInter);
        return centersLines.intersectionWith(toIntersect);

    }

    // Compares two double values for equality within a threshold
    private boolean doubleCompare(double num1, double num2) {
        return Math.abs(num1 - num2) < Globals.THRESHOLD;
    }

}
