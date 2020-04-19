package com.zfree.tank;

public class Main {
    public static void main(String[] args) {
        TankFrame.INSTANCE.setVisible(true);

        for (; ;) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TankFrame.INSTANCE.repaint();
        }
    }
}
