package com.zfree.tank.chainofresponsibility;

import com.zfree.tank.AbstractGameObject;

public interface Collider {
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2);
}
