import java.util.Random;

final class Functions {
    private static final Random rand = new Random();




    public static Random getRand() {
        return rand;
    }

    public static int distanceSquared(Point p1, Point p2) {
        int deltaX = p1.getX() - p2.getX();
        int deltaY = p1.getY() - p2.getY();

        return deltaX * deltaX + deltaY * deltaY;
    }

    public static int clamp(int value, int low, int high) {
        return Math.min(high, Math.max(value, low));
    }

}
