package com.zfree.tank.chainofresponsibility;

import com.zfree.tank.AbstractGameObject;
import com.zfree.tank.Bullet;
import com.zfree.tank.Tank;

import java.awt.*;

public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Tank) {
            Bullet b = (Bullet) go2;
            Tank t = (Tank) go1;

            b.collidesWithTank(t);
        } else {
            collide(go2, go1);
        }
        return true;
    }
}
