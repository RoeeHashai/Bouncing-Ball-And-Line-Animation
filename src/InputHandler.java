import java.util.Random;
/**
 * A class for handling input operations and data validation.
 */
public class InputHandler {
    /**
     * Checks if a coordinate value is within the specified range.
     *
     * @param coordinate the coordinate value to check
     * @param start      the starting value of the range
     * @param end        the ending value of the range
     * @return true if the coordinate value is within the range, false otherwise
     */
    public boolean coordinateNotInRange(double coordinate, double start, double end) {
        return coordinate < start || coordinate > end;
    }
    /**
     * Checks if a velocity component value is within the range of 0 and 130.
     *
     * @param velocityDxOrDy the velocity component value to check
     * @return true if the velocity component value is within the range, false otherwise
     */
    public boolean velocityInRange(double velocityDxOrDy) {
        return velocityDxOrDy >= 0 && velocityDxOrDy <= 130;
    }
    /**
     * Returns an array of valid integer sizes from command line arguments.
     *
     * @param args       the array of command line arguments
     * @param startIndex the starting index of the arguments to read
     * @param endIndex   the ending index of the arguments to read
     * @param xMin       the minimum x coordinate
     * @param xMax       the maximum x coordinate
     * @param yMin       the minimum y coordinate
     * @param yMax       the maximum y coordinate
     * @return an array of valid integer sizes
     */
    public int[] getIntArrSizes(String[] args, int startIndex, int endIndex, int xMin, int xMax, int yMin, int yMax) {
        boolean[] validIndexes = new boolean[endIndex - startIndex];
        int count = 0, intTry;
        double doubleTry;
        for (int i = startIndex; i < endIndex; i++) {
            try {
                intTry = Integer.parseInt(args[i]);
                if (intTry > 0 && intTry <= (0.5) * Math.abs(xMin - xMax) && intTry <= (0.5) * Math.abs(yMin - yMax)) {
                    validIndexes[i - startIndex] = true;
                    count++;
                } else {
                    System.out.println(args[i] + " is out of valid sizes range");
                }
            } catch (NumberFormatException notIntegerError) {
                try {
                    doubleTry = Double.parseDouble(args[i]);
                    args[i] = String.valueOf((int) doubleTry);
                    validIndexes[i - startIndex] = true;
                    count++;
                    System.out.println(doubleTry + " is a double num that been converted to " + (int) doubleTry);
                } catch (NumberFormatException notNumericError) {
                    if (args[i].length() == 1) {
                        System.out.println(args[i] + " is a char, please enter an int");
                    } else if (args[i].length() > 1) {
                        System.out.println(args[i] + " is a string, please enter an int");
                    }
                }
            }
        }
        int[] validInputs = new int[count];
        int j = 0;
        for (int i = startIndex; i < endIndex; i++) {
            if (validIndexes[i - startIndex]) {
                validInputs[j] = Integer.parseInt(args[i]);
                j++;
            }
        }
        return validInputs;
    }

    /**
     * Returns an array of valid double sizes from command line arguments.
     *
     * @param args the array of command line arguments
     * @param xMin the minimum x coordinate
     * @param xMax the maximum x coordinate
     * @param yMin the minimum y coordinate
     * @param yMax the maximum y coordinate
     * @return an array of valid double sizes
     */
    public double[] getDoubleArrSizes(String[] args, int xMin, int xMax, int yMin, int yMax) {
        double[] doubleValidInput = new double[Globals.VALID_LENGTH];
        Random rand = new Random();

        if (args.length < Globals.VALID_LENGTH) {
            int diff = Globals.VALID_LENGTH - args.length;
            String[] tempArgs = new String[Globals.VALID_LENGTH];
            System.arraycopy(args, 0, tempArgs, 0, args.length);
            System.out.println("Missing " + diff + " arguments, Instead random values were generated");
            for (int i = Globals.VALID_LENGTH - 1; i >= Globals.VALID_LENGTH - diff; i--) {
                tempArgs[i] = String.valueOf(rand.nextDouble() * (0.2) * Globals.MAX_DX_DY + 1);
            }
            args = tempArgs;
        } else if (args.length > Globals.VALID_LENGTH) {
            System.out.println("Too many arguments, reduced size to valid arguments amount");
            String[] tempArgs = new String[Globals.VALID_LENGTH];
            System.arraycopy(args, 0, tempArgs, 0, Globals.VALID_LENGTH);
            args = tempArgs;
        }
        for (int i = 0; i < args.length; i++) {
            try {
                Double.parseDouble(args[i]);
            } catch (NumberFormatException notNumericError) {
                if (args[i].length() == 1) {
                    System.out.println(args[i] + " is a char not a double. Instead a random value was generated");
                } else if (args[i].length() > 1) {
                    System.out.println(args[i] + " is a string not a double. Instead a random value was generated");
                }
                args[i] = String.valueOf(rand.nextDouble() * (0.2) * Globals.MAX_DX_DY + 1);
            }
        }
        if (coordinateNotInRange(Double.parseDouble(args[0]), xMin, xMax)) {
            System.out.println(args[0] + " is out of valid screen size. Instead a random value was generated");
            args[0] = String.valueOf(rand.nextDouble() * ((xMax - Globals.BOUNCING_BALL_ANIMATION_RADIUS)
                    - (xMin + Globals.BOUNCING_BALL_ANIMATION_RADIUS))
                    + (xMin + Globals.BOUNCING_BALL_ANIMATION_RADIUS));
        }
        if (coordinateNotInRange(Double.parseDouble(args[1]), yMin, yMax)) {
            System.out.println(args[1] + " is out of valid screen size. Instead a random value was generated");
            args[1] = String.valueOf(rand.nextDouble() * ((xMax - Globals.BOUNCING_BALL_ANIMATION_RADIUS)
                    - (xMin + Globals.BOUNCING_BALL_ANIMATION_RADIUS))
                    + (xMin + Globals.BOUNCING_BALL_ANIMATION_RADIUS));
        }
        if (!velocityInRange(Double.parseDouble(args[2]))) {
            System.out.println(args[2] + " is out of valid velocity speed. Instead a random value was generated");
            args[2] = String.valueOf(rand.nextDouble() * (0.2) * Globals.MAX_DX_DY + 1);
        }
        if (!velocityInRange(Double.parseDouble(args[3]))) {
            System.out.println(args[3] + " is out of valid velocity speed. Instead a random value was generated");
            args[3] = String.valueOf(rand.nextDouble() * (0.2) * Globals.MAX_DX_DY + 1);
        }
        for (int i = 0; i < doubleValidInput.length; i++) {
            doubleValidInput[i] = Double.parseDouble(args[i]);
        }
        return doubleValidInput;
    }


}

