package main;

import java.awt.*;

/*
    Denna fil är en klass för spelmenyn. Den har variabler so anger ifall knappen för spelmenyn trycks ner, om spel
    menyn är öpppen och metoder för att hander spel meny operationer som att rita spelmenyn.
*/


public class GameMenu {
    private boolean menuOpen = false;
    private KeyboardInput keyboard;
    private boolean escPressed;
    private Game game;

    public GameMenu(KeyboardInput keyboard, Game game) {
        this.game = game;
        this.keyboard = keyboard;
        this.escPressed = keyboard.escPressed;
    }

    public void keyClickAction() {
        if (escPressed == false && this.keyboard.escPressed == true) {
            this.menuOpen = !this.menuOpen;
        }

        if (this.menuOpen == true) {
            // Code for open menu
        }

        else {
            // Code for closed menu
        }

        this.game.gamePaused = this.menuOpen;

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
