package proto;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private Menu menu;

    public Window() {
        menu = new Menu();
    }

    public void gameWindow() {
        setTitle("Menü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setContentPane(menu);
        menu.startMenu(this);
        setVisible(true);
    }




    public void gameOver(boolean b) {
        JPanel gameOverPanel = new JPanel();
        gameOverPanel.setLayout(null);
        if(b) {
            JLabel wonLabel = new JLabel("YOU WON!");
            wonLabel.setBounds(0, 0, 800, 600);
            wonLabel.setForeground(Color.RED);
            wonLabel.setFont(new Font("Serif", Font.PLAIN, 125));
            wonLabel.setHorizontalAlignment(SwingConstants.CENTER);
            wonLabel.setVerticalAlignment(SwingConstants.CENTER);
            gameOverPanel.add(wonLabel);
        }
        else {
            JLabel lostLabel = new JLabel("YOU LOST!");
            lostLabel.setBounds(0, 0, 800, 600);
            lostLabel.setForeground(Color.RED);
            lostLabel.setOpaque(true);
            lostLabel.setBackground(Color.BLACK);
            lostLabel.setFont(new Font("Serif", Font.PLAIN, 125));
            lostLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lostLabel.setVerticalAlignment(SwingConstants.CENTER);
            gameOverPanel.add(lostLabel);
        }
        setContentPane(gameOverPanel);
        revalidate();
        repaint();
    }
}
