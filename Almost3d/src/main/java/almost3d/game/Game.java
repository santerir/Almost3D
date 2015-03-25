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
    public boolean paused;

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

    public void pause() {
        this.paused = !this.paused;
    }

    public void start() {
        this.running = true;
        this.paused = false;
        try {
            this.gameLoop();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("empty-statement")
    private void gameLoop() throws InterruptedException {
        long lastLoopTime = System.nanoTime();
        final int TARGET_FPS = 25;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

        while (running) {
                long now = System.nanoTime();
                long deltaTime = now - lastLoopTime;
                lastLoopTime = now;

                this.player.update(deltaTime);
                this.renderer.repaint();

                try {
                        Thread.sleep((lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000);
                } catch (InterruptedException e) {}
        }
    }
}
