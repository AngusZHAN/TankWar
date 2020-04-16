import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x, y;
    private Dir dir;
    private boolean bL, bR, bU, bD;
    private boolean moving = false;

    public static final int SPEED = 5;

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }


    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
        move();
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = true;
                break;
            case KeyEvent.VK_RIGHT:
                bR = true;
                break;
            case KeyEvent.VK_UP:
                bU = true;
                break;
            case KeyEvent.VK_DOWN:
                bD = true;
                break;
        }
        setMainDir();
    }

    private void setMainDir() {
        //当按键没有被按下时停止，按键被按下时移动
        if (!bL && !bR && !bU && !bD) {
            moving = false;
        } else {
            moving = true;
            if (bL && !bR && !bU && !bD) {
                dir = Dir.L;
            } else if (!bL && bR && !bU && !bD) {
                dir = Dir.R;
            } else if (!bL && !bR && bU && !bD) {
                dir = Dir.U;
            } else if (!bL && !bR && !bU && bD) {
                dir = Dir.D;
            }
        }

    }

    private void move() {
        if (!moving) {
            return;
        }
        switch (dir) {
            case L:
                x -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case D:
                y += SPEED;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                bL = false;
                break;
            case KeyEvent.VK_RIGHT:
                bR = false;
                break;
            case KeyEvent.VK_UP:
                bU = false;
                break;
            case KeyEvent.VK_DOWN:
                bD = false;
                break;
        }
        setMainDir();
    }
}
