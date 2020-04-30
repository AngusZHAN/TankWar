package com.zfree.tank.chainofresponsibility;

import com.zfree.tank.AbstractGameObject;
import com.zfree.tank.Tank;
import com.zfree.tank.Wall;

public class TankWallCollider implements Collider {
    @Override
    public void collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Wall) {
            Tank t = (Tank) go1;
            Wall w = (Wall) go2;
            if (t.isLive()) {
                if (t.getRect().intersects(w.getRect())) {
                    t.back();
                }
            }
        } else if ((go2 instanceof Tank && go1 instanceof Wall)) {
            collide(go2, go1);
        }
    }
}
