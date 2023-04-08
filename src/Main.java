

import javax.swing.JFrame;

import main.Game;

public class Main {
    public static void main(String[] args) {
        // Skapa fönster för spelet
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("The Pursuit");

        Game game = new Game(); // Skapa spel objekt (singelton) som styr hela spelet
        window.add(game);

        window.pack(); // Preppa fönstret
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        game.startGame(); // Starta speltråden
    }
}
