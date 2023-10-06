import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * AbstractArtDrawing class generates abstract art by displaying lines with random start and end points.
 * It marks their midpoints in blue, and marks their intersection points between lines in red.
 */
public class AbstractArtDrawing {
    /**
     * Generates a random line with start and end points.
     *
     * @return the generated random line
     */

    public Line generateRandomLine() {
        Random rand = new Random();
        int xStart = rand.nextInt(Globals.ABSTRACT_ART_WIDTH) + 1; // get integer in range 1-ABSTRACT_ART_WIDTH
        int yStart = rand.nextInt(Globals.ABSTRACT_ART_HEIGHT) + 1; // get integer in range 1-ABSTRACT_ART_HEIGHT
        Point start = new Point(xStart, yStart);
        int xEnd = rand.nextInt(Globals.ABSTRACT_ART_WIDTH) + 1; // get integer in range 1-ABSTRACT_ART_WIDTH
        int yEnd = rand.nextInt(Globals.ABSTRACT_ART_HEIGHT) + 1; // get integer in range 1-ABSTRACT_ART_HEIGHT
        Point end = new Point(xEnd, yEnd);
        return new Line(start, end);
    }

    /**
     * Draws a single line on the DrawSurface.
     *
     * @param line        the line to be drawn
     * @param drawSurface the DrawSurface object to draw on
     */
    public void drawLine(Line line, DrawSurface drawSurface) {
        drawSurface.drawLine((int) line.getStart().getX(), (int) line.getStart().getY(),
                (int) line.getEnd().getX(), (int) line.getEnd().getY());
        drawSurface.setColor(Color.BLACK);
    }

    /**
     * Draws an array of lines on the given DrawSurface.
     *
     * @param lines       the array of lines to draw
     * @param drawSurface the DrawSurface to draw the lines on
     */
    public void displayLines(Line[] lines, DrawSurface drawSurface) {
        for (Line line : lines) {
            drawLine(line, drawSurface);
        }
    }

    /**
     * Generates an array of random lines with a length specified in the Globals class.
     *
     * @return the generated array of random lines
     */
    public Line[] generateRandomLines() {
        Line[] lines = new Line[Globals.NUM_RANDOM_LINES];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = generateRandomLine();
        }
        return lines;
    }

    /**
     * Marks the midpoints of all lines in the array on given DrawSurface in the color blue.
     *
     * @param lines       the array of lines to mark their midpoints
     * @param drawSurface the DrawSurface object to draw on
     */
    public void markMidpoints(Line[] lines, DrawSurface drawSurface) {
        for (Line line : lines) {
            drawSurface.setColor(Color.BLUE);
            drawSurface.fillCircle((int) line.middle().getX(), (int) line.middle().getY(), Globals.POINT_RADIUS);
        }
    }

    /**
     * Marks the intersection points between all lines in the line array on the DrawSurface in the color red.
     *
     * @param lines       the array of lines to mark their intersection points
     * @param drawSurface the DrawSurface object to draw on
     */
    public void markIntersectionPoints(Line[] lines, DrawSurface drawSurface) {
        drawSurface.setColor(Color.RED);
        Point intersection;
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines.length; j++) {
                intersection = lines[i].intersectionWith(lines[j]);
                if (i != j && intersection != null) {
                    drawSurface.fillCircle((int) intersection.getX(), (int) intersection.getY(), Globals.POINT_RADIUS);
                }
            }
        }
    }

    /**
     * The main method of the AbstractArtDrawing program. It generates random lines,
     * draws them on the screen marks their midpoints in blue,
     * and marks their intersection points between lines in red.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("AbstractArtDrawing", Globals.ABSTRACT_ART_WIDTH, Globals.ABSTRACT_ART_HEIGHT);
        DrawSurface drawSurface = gui.getDrawSurface();
        AbstractArtDrawing artGenerator = new AbstractArtDrawing();
        Line[] lines = artGenerator.generateRandomLines();
        artGenerator.displayLines(lines, drawSurface);
        artGenerator.markMidpoints(lines, drawSurface);
        artGenerator.markIntersectionPoints(lines, drawSurface);
        gui.show(drawSurface);
    }
}
