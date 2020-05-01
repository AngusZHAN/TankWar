package com.zfree.tank;

import com.zfree.tank.chainofresponsibility.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {
    List<AbstractGameObject> objects;
    ColliderChain chain = new ColliderChain();
    private Player myTank;

    public GameModel() {
        initGameObjects();
    }

    public Player getMyTank() {
        return myTank;
    }

    private void initGameObjects() {
        myTank = new Player(800, 600, Dir.U, Group.Good);
        objects = new ArrayList<>();

        int tankCount = Integer.parseInt(PropertyMgr.get("initTankCount"));
        for (int i = 0; i < tankCount; i++) {
            this.add(new Tank(100 + 70 * i, 300, Dir.D, Group.Bad));
        }

        this.add(new Wall(300, 200, 100, 10));
    }

    public void add(AbstractGameObject go) {
        objects.add(go);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("objects:" + objects.size(), 10, 50);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < objects.size(); i++) {
            if (!objects.get(i).isLive()) {
                objects.remove(i);
                break;
            }
            AbstractGameObject go1 = objects.get(i);
            for (int j = 0; j < objects.size(); j++) {
                AbstractGameObject go2 = objects.get(j);
                chain.collide(go1, go2);
            }
            objects.get(i).paint(g);
        }
    }
}
