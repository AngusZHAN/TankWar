package com.zfree.tank.chainofresponsibility;

import com.zfree.tank.AbstractGameObject;

import java.io.Serializable;

public interface Collider extends Serializable {
    public boolean collide(AbstractGameObject go1, AbstractGameObject go2);
}
