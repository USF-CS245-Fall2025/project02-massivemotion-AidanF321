import java.awt.Color;


public class CelestialBody {

    public int x, y;       // Current position (pixels)
    public int velocityX, velocityY;     // Velocity (pixels per frame)
    public int size;       // Diameter (pixels)
    public Color color;

    public CelestialBody(int x, int y, int velocityX, int velocityY, int size, Color color) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.size = size;
        this.color = color;
    }
}