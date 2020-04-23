package com.zfree.tank;

import java.awt.*;

public class Explode {
    private int x, y;
    private int width, height;
    private int step = 0;
    private boolean live = true;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        this.width = ResourceMgr.explodes[0].getWidth();
        this.height = ResourceMgr.explodes[0].getHeight();
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void paint(Graphics g) {
        if (!isLive()) { return; }

        g.drawImage(ResourceMgr.explodes[step], x, y, null);
        step++;

        if (step >= ResourceMgr.explodes.length) {
            this.die();
        }

    }

    private void die() {
        this.setLive(false);
    }

}
