package almost3d.game;

import almost3d.graphics.Raycaster;
import almost3d.graphics.Renderer;
import almost3d.world.Map;
import almost3d.world.Player;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class handles timing of render and update events.
 * <p>
 * The constructor and initialize method handle creation of all needed game
 * components. After that, the game loop is started, which updates game objects
 * and renders frames into the game window in precisely timed intervals. The
 * game loops attempts to keep the framerate at a constant 60 FPS by varying the
 * time between frames in relation to processing time.
 *
 *
 * @author santeriraisanen
 */
public class Game {

    public Map map;
    public Raycaster raycaster;
    public Controls controls;
    public Renderer renderer;
    public Player player;
    public StartGUI startgui;

    public boolean running;
    public boolean initialized;


    public Game() {
        this.map = new Map(this);
        this.raycaster = new Raycaster(this);
        this.controls = new Controls(this);
        this.player = new Player(this);
        this.renderer = new Renderer(this);
        this.running = false;
        this.initialized = false;
    }
        
    /**
     * Initializes map and game window
     */
    public void initialize(int mapId) {
        if (this.initialized) {
            return;
        }
        this.map.load(mapId);
        this.renderer.initialize();
        this.initialized = true;
    }
    
    /**
     * Initialize game with custom map.
     * 
     * @param map map in integer-matrix form
     */
    public void initialize(int[][] map) {
        if (this.initialized) {
            return;
        }
        this.map.load(map);
        this.renderer.initialize();
        this.initialized = true;
    }



    /**
     * Starts the game loop, which will run until the program is closed
     */
    public void start() {
        if (!this.initialized) {
            return;
        }
        this.running = true;
        try {
            this.gameLoop();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private void gameLoop() throws InterruptedException {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        long averageDeltaTime = 0;
        int frames = 0;

        while (running) {
            frames = (frames + 1) % 30;
            if (frames == 29) {
                System.out.println("FPS: " + 29 * (1000000000 / (float) averageDeltaTime));
                averageDeltaTime = 0;
            }

            long now = System.nanoTime();
            long deltaTime = now - lastLoopTime;
            lastLoopTime = now;
            averageDeltaTime += deltaTime;

            this.map.update(deltaTime);
            this.player.update(deltaTime);
            this.renderer.repaint();

            try {
                Thread.sleep(Math.max(0, (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000));
            } catch (InterruptedException e) {
            }
        }
    }

    
}
