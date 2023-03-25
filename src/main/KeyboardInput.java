package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
    Denna fil är en klass för tangent inmatning och är en singleton. Endast en instans av denna klass instansieras
    som sedan avsänds globalt av hela applikationen. Danna klass implementerar KeyListener gränssnitten vilket har default
    implementering som anropar de implementerade metoderna vid tangent inmatning.
*/


public class KeyboardInput implements KeyListener {
    public boolean upPressed, downPressed, rightPressed, leftPressed, controlKeyPressed = false;
    public boolean sprintPressed = false;
    public boolean escPressed = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }

        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        if (code == KeyEvent.VK_SHIFT) {
            this.sprintPressed = true;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            this.escPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        if (code == KeyEvent.VK_SHIFT) {
            this.sprintPressed = false;
        }

        if (code == KeyEvent.VK_ESCAPE) {
            this.escPressed = false;
        }
    }

    public boolean controlKeyPressed() {
        return upPressed == true || leftPressed == true || downPressed == true || rightPressed == true;
    }
}
