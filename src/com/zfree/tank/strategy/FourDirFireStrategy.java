package com.zfree.tank.strategy;

import com.zfree.tank.*;

public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(Player my) {
        int bx = my.getX() + ResourceMgr.badTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int by = my.getY() + ResourceMgr.badTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        Dir[] dirs = Dir.values();
        for (Dir d: dirs) {
            TankFrame.INSTANCE.getGameModel().add(new Bullet(bx, by, d, my.getGroup()));
        }
    }
}

