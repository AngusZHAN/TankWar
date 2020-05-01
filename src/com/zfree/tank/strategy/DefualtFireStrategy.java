package com.zfree.tank.strategy;

import com.zfree.tank.Bullet;
import com.zfree.tank.Player;
import com.zfree.tank.ResourceMgr;
import com.zfree.tank.TankFrame;

public class DefualtFireStrategy implements FireStrategy {
    @Override
    public void fire(Player my) {
        int bx = my.getX() + ResourceMgr.badTankU.getWidth() / 2 - ResourceMgr.bulletU.getWidth() / 2;
        int by = my.getY() + ResourceMgr.badTankU.getHeight() / 2 - ResourceMgr.bulletU.getHeight() / 2;
        TankFrame.INSTANCE.getGameModel().add(new Bullet(bx, by, my.getDir(), my.getGroup()));
    }
}
