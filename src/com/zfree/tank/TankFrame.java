package com.zfree.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    public static final TankFrame INSTANCE = new TankFrame();
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 800;
    Image offScreenImage = null;
    private Player myTank;
    private List<Tank> enemies;
    private List<Bullet> bullets;
    private List<Explode> explodes;

    private TankFrame() {
        this.setTitle("com.zfree.tank.Tank War");
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);

        this.addKeyListener(new TankKeyListener());

        initGameObjects();
    }

    private void initGameObjects() {
        myTank = new Player(800, 600, Dir.U, Group.Good);

        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        explodes = new ArrayList<>();

        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        for (int i = 0; i < tankCount; i++) {
            enemies.add(new Tank(100 + 30 * i, 200, Dir.D, Group.Bad));
        }
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("enemies:" + enemies.size(), 10, 50);
        g.drawString("bullets:" + bullets.size(), 10, 70);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isLive()) {
                enemies.remove(i);
            } else {
                enemies.get(i).paint(g);
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                bullets.get(i).collidesWithTank(enemies.get(j));
            }
            if (!bullets.get(i).isLive()) {
                bullets.remove(i);
            } else {
                bullets.get(i).paint(g);
            }
        }

        for (int i = 0; i < explodes.size(); i++) {
            if (!explodes.get(i).isLive()) {
                explodes.remove(i);
            } else {
                explodes.get(i).paint(g);
            }
        }
    }

    public void add(Bullet bullet) {
        this.bullets.add(bullet);
    }

    public void add(Explode explode) { this.explodes.add(explode); }

    private class TankKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }
}
