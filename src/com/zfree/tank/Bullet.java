package com.zfree.tank;

import java.awt.*;

public class Bullet extends AbstractGameObject {
    private static final int SPEED = 7;
    private static final int W = ResourceMgr.bulletU.getWidth();
    private static final int H = ResourceMgr.bulletU.getHeight();
    private int x, y;
    private Dir dir;
    private Group group;
    private Rectangle rect;
    private boolean live = true;

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.rect = new Rectangle(x, y, W, H);
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

    public void paint(Graphics g) {
        switch (dir) {
            case L:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
        move();

        rect.x = x;
        rect.y = y;
    }

    private void move() {
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
        boundsCheck();
    }

    public void die() {
        this.setLive(false);
    }

    public Rectangle getRect() {
        return rect;
    }

    private void boundsCheck() {
        if (x < 0 || y < 30 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            this.live = false;
        }
    }
}
