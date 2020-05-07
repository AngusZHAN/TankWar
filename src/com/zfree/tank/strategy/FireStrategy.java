package com.zfree.tank.strategy;

import com.zfree.tank.Player;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    public void fire(Player my);
}
