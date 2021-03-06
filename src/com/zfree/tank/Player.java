package com.zfree.tank;

import com.zfree.tank.strategy.FireStrategy;
import com.zfree.tank.strategy.FourDirFireStrategy;
import com.zfree.tank.strategy.TwoDirFireStrategy;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends AbstractGameObject {
    public static final int SPEED = 5;
    private int width, height;
    private int x, y;
    private Dir dir;
    private Group group;
    private Rectangle rect;
    private boolean bL, bR, bU, bD;
    private boolean moving = false;
    private boolean live = true;
    private FireStrategy strategy = null;

    public Player(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.width = ResourceMgr.goodTankU.getWidth();
        this.height = ResourceMgr.goodTankU.getHeight();

        this.rect = new Rectangle(x, y, width, height);
        //init fire strategy from config file
        this.initFireStrategy();
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
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
                g.drawImage(ResourceMgr.goodTankL, x, y, null);
                break;
            case R:
                g.drawImage(ResourceMgr.goodTankR, x, y, null);
                break;
            case U:
                g.drawImage(ResourceMgr.goodTankU, x, y, null);
                break;
            case D:
                g.drawImage(ResourceMgr.goodTankD, x, y, null);
                break;
        }
        move();

        rect.x = x;
        rect.y = y;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }
        setMainDir();
    }

    private void setMainDir() {
        //当按键没有被按下时停止，按键被按下时移动
        if (!bL && !bR && !bU && !bD) {
            moving = false;
        } else {
            moving = true;
            if (bL && !bR && !bU && !bD) {
                dir = Dir.L;
            }
            if (!bL && bR && !bU && !bD) {
                dir = Dir.R;
            }
            if (!bL && !bR && bU && !bD) {
                dir = Dir.U;
            }
            if (!bL && !bR && !bU && bD) {
                dir = Dir.D;
            }
        }

    }

    private void move() {
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
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
            case KeyEvent.VK_CONTROL:
                fire();
                break;

        }
        setMainDir();
    }

    private void initFireStrategy() {
        String className = PropertyMgr.get("tankFireStrategy");
        try {
            Class clazz = Class.forName("com.zfree.tank.strategy." + className);
            strategy = (FireStrategy) (clazz.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fire() {
        strategy.fire(this);
    }

    public void die() {
        this.setLive(false);
    }

    public Rectangle getRect() {
        return rect;
    }
}
