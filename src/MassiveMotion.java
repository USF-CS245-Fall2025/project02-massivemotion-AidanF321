import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;
    protected int x1, x2, y1, y2;
    protected static int windowSizeX;
    protected static int windowSizeY;

    protected double genX;// probabilty that a new celestial body will be generated
    protected double genY;

    protected int bodySize;
    protected double bodyMass;
    protected int bodyVelocity;

    protected int starPositionX;
    protected int starPositionY;
    protected int starSize;
    protected double starMass;
    protected int starVelocityX;
    protected int starVelocityY;

    protected String listType;

    protected List<CelestialBody> bodies;
    protected Random rand;

    public MassiveMotion(String propfile) {
        // Use Properties to load an input file
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(propfile)) {
            // try to load the file into property
            prop.load(fis);
        } catch (IOException e) {
            // handle case where file cannot be read
            System.out.println("Trouble reading the file");
        }

        // pass values from config file to variables
        int timerDelay = Integer.parseInt(prop.getProperty("timerDelay"));

        windowSizeX = Integer.parseInt(prop.getProperty("windowSizeX"));
        windowSizeY = Integer.parseInt(prop.getProperty("windowSizeY"));

        genX = Double.parseDouble(prop.getProperty("genX"));
        genY = Double.parseDouble(prop.getProperty("genY"));
        bodySize = Integer.parseInt(prop.getProperty("bodySize"));
        // bodyMass = Double.parseDouble(prop.getProperty("bodyMass")); not needed
        bodyVelocity = Integer.parseInt(prop.getProperty("bodyVelocity"));

        starPositionX = Integer.parseInt(prop.getProperty("starPositionX"));
        starPositionY = Integer.parseInt(prop.getProperty("starPositionY"));
        starSize = Integer.parseInt(prop.getProperty("starSize"));
        // starMass = Double.parseDouble(prop.getProperty("star_mass"));
        starVelocityX = Integer.parseInt(prop.getProperty("starVelocityX"));
        starVelocityY = Integer.parseInt(prop.getProperty("starVelocityY"));

        tm = new Timer(timerDelay, this);

        // initialize Random instance
        this.rand = new Random();

        // Store the list type
        this.listType = prop.getProperty("list");

        if (this.listType.equals("arraylist")) {
            this.bodies = new ArrayList<>();
        } else if (this.listType.equals("single")) {
            this.bodies = new LinkedList<>();
        } else if (this.listType.equals("double")) {
            this.bodies = new DoublyLinkedList<>();
        } else if (this.listType.equals("dummyhead")) {
            this.bodies = new DummyHeadLinkedList<>(); 
        } else {
            // Default to ArrayList if the property is unknown
            System.err.println("Unknown list type. Defaulting to ArrayList.");
            this.bodies = new ArrayList<>();
        }
        
        // The spec says the first object is the star
        CelestialBody star = new CelestialBody(
            starPositionX,
            starPositionY,
            starVelocityX,
            starVelocityY,
            starSize,
            Color.RED 
        );
        
        this.bodies.add(star); //add star as first in list
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Leave this

        // This loop draws both the star and all the comets
        for (int i = 0; i < bodies.size(); i++) {
            CelestialBody body = bodies.get(i);
            
            g.setColor(body.color);
            g.fillOval(body.x, body.y, body.size, body.size);
        }

        // Recommend you leave the next line as is
        tm.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //generate comet from top or bottom
        if (rand.nextDouble() < this.genX) {//chance of new comet spawn
            // Pick a random X position
            int x = rand.nextInt(windowSizeX);
            int y = 0; // Default to top edge

            // 50% chance to come from the bottom edge 
            if (rand.nextBoolean()) {
                y = windowSizeY;// size because bottom
            }

            this.bodies.add(createRandomComet(x, y));
        }

        // Try to generate a comet from the left or right edge 
        if (rand.nextDouble() < this.genY) {
            // Pick a random Y position
            int y = rand.nextInt(windowSizeY);
            int x = 0; // Default to left edge

            // 50% chance to come from the right edge instead 
            if (rand.nextBoolean()) {
                x = windowSizeX;
            }

            this.bodies.add(createRandomComet(x, y));
        }

        // move and remove CELESTIAL BODIES

        // We must iterate BACKWARDS to safely remove items from the list due to any shifting 
        for (int i = this.bodies.size() - 1; i >= 0; i--) {
            CelestialBody body = this.bodies.get(i);

            // Update the body's position based on its velocity
            body.x += body.velocityX;
            body.y += body.velocityY;

            // Check if the body has moved "beyond the canvas" 
            boolean isOffScreen = body.x < 0 - body.size || // Too far left
                    body.x > windowSizeX || // Too far right
                    body.y < 0 - body.size || // Too far up
                    body.y > windowSizeY; // Too far down

            if (isOffScreen) {
                this.bodies.remove(i);
            }
        }

        // Keep this at the end:
        repaint();
    }

    private CelestialBody createRandomComet(int x, int y) {
        int velocityX = 0;
        // Velocity must be random between -body_velocity and +body_velocity,
        while (velocityX == 0) {
            int range = this.bodyVelocity * 2 + 1;
            velocityX = rand.nextInt(range) - this.bodyVelocity;
        }

        int velocityY = 0;
        while (velocityY == 0) {
            int range = this.bodyVelocity * 2 + 1;
            velocityY = rand.nextInt(range) - this.bodyVelocity;
        }

        return new CelestialBody(x, y, velocityX, velocityY, (int) this.bodySize, Color.BLACK);
    }

    public static void main(String[] args) {
        
        // 1. Check for the command-line argument
        if (args.length < 1) {
            System.err.println("Error: Missing properties file name.");
            System.err.println("Usage: java MassiveMotion <filename.txt>");
            System.exit(1); // Exit the program with an error
        }
        
        System.out.println("Massive Motion starting...");
        
        // 2. Create the MassiveMotion object using the argument
        // This will load all the properties, including windowSizeX/Y
        MassiveMotion mm = new MassiveMotion(args[0]);

        // 3. Create the window frame
        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        
        // 4. Set the size USING THE VARIABLES from the properties file
        // This works because you correctly made windowSizeX/Y static
        jf.setSize(windowSizeX, windowSizeY);
        
        // 5. Add the panel to the frame and show it
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
