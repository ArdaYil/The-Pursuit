package main;

import java.awt.*;

public class GameMenu {
    private boolean menuOpen = false;
    private KeyboardInput keyboard;
    private boolean escPressed;

    public GameMenu(KeyboardInput keyboard) {
        this.keyboard = keyboard;
        this.escPressed = keyboard.escPressed;
    }

    public void keyClickAction() {
        if (escPressed == false && this.keyboard.escPressed == true) {
            this.menuOpen = !this.menuOpen;
        }

        if (this.menuOpen == true) {

        }

        else {

        }

        escPressed = this.keyboard.escPressed;
    }

    public void draw(Graphics2D g2) {
        this.keyClickAction();

        if (menuOpen == false) return;

        g2.setColor(Color.gray);
        g2.fillRect(0, 0, 300, Game.screenHeight);
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
        g2.drawString("Game Menu", 20, 60);
    }
}
