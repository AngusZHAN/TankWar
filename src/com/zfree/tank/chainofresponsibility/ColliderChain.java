package com.zfree.tank.chainofresponsibility;

import com.zfree.tank.AbstractGameObject;
import com.zfree.tank.PropertyMgr;

import java.util.ArrayList;
import java.util.List;

public class ColliderChain implements Collider{
    private List<Collider> colliders;

    public ColliderChain() {
        initCollides();
    }

    private void initCollides() {
        colliders = new ArrayList<>();
        String[] colliderNames = PropertyMgr.get("colliders").split(",");
        try {
            for (String name : colliderNames) {
                Class clazz = Class.forName("com.zfree.tank.chainofresponsibility." + name);
                Collider c = (Collider) (clazz.getConstructor().newInstance());
                colliders.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void collide(AbstractGameObject go1, AbstractGameObject go2) {
        for (Collider collider : colliders) {
            collider.collide(go1, go2);
        }
    }
}
