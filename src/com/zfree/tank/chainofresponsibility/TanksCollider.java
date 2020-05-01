package com.zfree.tank.chainofresponsibility;

import com.zfree.tank.AbstractGameObject;
import com.zfree.tank.Tank;
import com.zfree.tank.Wall;

public class TanksCollider implements Collider {
    @Override
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2) {
        if (go1 != go2 && go1 instanceof Tank && go2 instanceof Tank) {
            Tank t1 = (Tank) go1;
            Tank t2 = (Tank) go2;
            if (t1.isLive() && t2.isLive()) {
                if (t1.getRect().intersects(t2.getRect())) {
                    t1.back();
                }
            }
        }
        return true;
    }
}
