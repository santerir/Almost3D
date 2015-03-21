/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almost3d.game;

import almost3d.graphics.Raycaster;
import almost3d.graphics.Renderer;
import almost3d.world.Map;
import almost3d.world.Player;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author santeriraisanen
 */
public class Game {

    public Map map;
    public Raycaster raycaster;
    public Controls controls;
    public Renderer renderer;
    public Player player;

    public boolean running;

    public Game() {
        this.map = new Map(this);
        this.raycaster = new Raycaster(this);
        this.controls = new Controls(this);
        this.player = new Player(this);
        this.renderer = new Renderer(this);
        this.running = false;
    }

    public void initialize() {
        this.map.load();
        this.renderer.initialize();
    }
    
    public void start() {
        this.running = true;
        try {
            this.gameLoop();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("empty-statement")
    private void gameLoop() throws InterruptedException {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 24;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        // keep looping round til the game ends
        while (running) {
            // work out how long its been since the last update, this
            // will be used to calculate how far the entities should
            // move this loop
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength;
            
            this.player.update(delta);
            this.renderer.repaint();

            try {
                Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
            } catch (InterruptedException e) {};
        }
    }
}

