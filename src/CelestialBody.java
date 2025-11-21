import java.awt.Color;


public class CelestialBody {

    public int x, y;       // Current position (pixels)
    public int velocityX, velocityY;     // Velocity (pixels per frame)
    public int size;       // Diameter (pixels)
    public Color color;

    /**
     * @param x The x-coordinate of the celestial body
     * @param y The y-coordinate of the celestial body
     * @param velocityX The x-velocity of the celestial body
     * @param velocityY The y-velocity of the celestial body
     * @param size The size (diameter) of the celestial body
     * @param color The color of the celestial body
     */
    public CelestialBody(int x, int y, int velocityX, int velocityY, int size, Color color) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.size = size;
        this.color = color;
    }
}