package com.zfree.tank.chainofresponsibility;

import com.zfree.tank.AbstractGameObject;
import com.zfree.tank.Bullet;
import com.zfree.tank.Tank;

public class BulletTankCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Tank) {
            Bullet b = (Bullet) go1;
            Tank t = (Tank) go2;

            if (!t.isLive() || !b.isLive()) {
                return false;
            }
            if (t.getGroup() == b.getGroup()) {
                return true;
            }
            if (t.getRect().intersects(b.getRect())) {
                t.die();
                b.die();
                return false;
            }
        } else if ((go2 instanceof Bullet && go1 instanceof Tank)) {
            return collide(go2, go1);
        }
        return true;
    }
}
