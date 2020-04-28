package com.zfree.tank;

import java.awt.*;
import java.util.Random;

public class Tank extends AbstractGameObject {
    public static final int SPEED = 5;
    private int x, y;
    private int oldX, oldY;
    private int width, height;
    private Dir dir;
    private Group group;
    private Rectangle rect;
    private boolean bL, bR, bU, bD;
    private boolean moving = true;
    private boolean live = true;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;

        oldX = x;
        oldY = y;

        this.width = ResourceMgr.badTankU.getWidth();
        this.height = ResourceMgr.badTankU.getHeight();

        this.rect = new Rectangle(x, y, width, height);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paint(Graphics g) {
        if (!this.isLive()) {
            return;
        }
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.badTankL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.badTankR, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.badTankU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();

        rect.x = x;
        rect.y = y;
    }

    private void move() {
        oldX = x;
        oldY = y;

        if (!moving) {
            return;
        }
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }
        randomDir();

        if (r.nextInt(100) > 90) {
            fire();
        }

        boundsCheck();
    }

    private Random r = new Random();

    private void randomDir() {
        if (r.nextInt(100) > 90) {
            this.dir = Dir.randomDir();
        }
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x + width > TankFrame.GAME_WIDTH || y + height > TankFrame.GAME_HEIGHT) {
            this.back();
        }
    }

    private void back() {
        x = oldX;
        y = oldY;
    }

    private void fire() {
        int bx = x + ResourceMgr.badTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int by = y + ResourceMgr.badTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.add(new Bullet(bx, by, dir, group));
    }

    public void die() {
        this.setLive(false);
        TankFrame.INSTANCE.add(new Explode(x, y));
    }

    public Rectangle getRect() {
        return rect;
    }
}
