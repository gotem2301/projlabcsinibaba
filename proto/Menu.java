package proto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Menu extends JPanel {
    private int playerNumber;
    private String filePath;
    private JFrame frame;

    public void startMenu(JFrame frame) {
        playerNumber = 1;
        setLayout(null);
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Save");
        newGameButton.setBounds(150,100, 400, 100);
        newGameButton.setFont(new Font("Serif", Font.PLAIN, 50));
        loadGameButton.setBounds(650, 100, 400, 100);
        loadGameButton.setFont(new Font("Serif", Font.PLAIN, 50));
        add(newGameButton);
        add(loadGameButton);


        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();

                JLabel playerNumberLabel = new JLabel("A játékosok száma:");
                JButton minusButton = new JButton("-");
                JButton plusButton = new JButton("+");
                JButton playButton = new JButton("Play");
                JButton backButton = new JButton("Vissza");
                JTextField numberField = new JTextField(Integer.toString(playerNumber));

                playerNumberLabel.setBounds(0, 0, 1200, 200);
                playerNumberLabel.setFont(new Font("Serif", Font.PLAIN, 50));
                playerNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
                playerNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
                add(playerNumberLabel);

                minusButton.setBounds(400,150, 100, 100);
                minusButton.setFont(new Font("Serif", Font.PLAIN, 50));
                plusButton.setBounds(700, 150, 100, 100);
                plusButton.setFont(new Font("Serif", Font.PLAIN, 50));
                playButton.setBounds(400, 400, 400, 100);
                playButton.setFont(new Font("Serif", Font.PLAIN, 50));
                backButton.setBounds(50, 675, 100, 50);
                backButton.setFont(new Font("Serif", Font.PLAIN, 20));
                add(minusButton);
                add(plusButton);
                add(playButton);
                add(backButton);

                numberField.setBounds(525,150, 150, 100);
                numberField.setFont(new Font("Serif", Font.PLAIN, 50));
                numberField.setHorizontalAlignment(SwingConstants.CENTER);
                numberField.setEditable(false);
                add(numberField);

                minusButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(playerNumber > 1) {
                            playerNumber--;
                            numberField.setText(Integer.toString(playerNumber));
                        }
                    }
                });

                plusButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        playerNumber++;
                        numberField.setText(Integer.toString(playerNumber));
                    }
                });

                playButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        JFrame jFrame = new JFrame("Játék");
                        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        jFrame.setSize(new Dimension(800, 600));
                        jFrame.setLocationRelativeTo(null);

                        Controller controller = new Controller();
                        View view = new View();

                        jFrame.add(view);
                        jFrame.setVisible(true);

                        controller.setView(view);
                        view.setController(controller);
                        controller.newGame();
                        controller.drawEverything();
                    }
                });

                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        removeAll();
                        startMenu(frame);
                        revalidate();
                        repaint();
                    }
                });

                revalidate();
                repaint();
            }
        });


        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();

                JLabel filePathLabel = new JLabel("A fájl elérési útja:");
                JButton searchButton = new JButton("Tallózás");
                JButton playButton = new JButton("Play");
                JButton backButton = new JButton("Vissza");
                JTextField fileField = new JTextField(filePath);

                filePathLabel.setBounds(0, 0, 1200, 200);
                filePathLabel.setFont(new Font("Serif", Font.PLAIN, 50));
                filePathLabel.setHorizontalAlignment(SwingConstants.CENTER);
                filePathLabel.setVerticalAlignment(SwingConstants.CENTER);
                add(filePathLabel);

                searchButton.setBounds(850, 150, 250, 100);
                searchButton.setFont(new Font("Serif", Font.PLAIN, 50));
                playButton.setBounds(400, 400, 400, 100);
                playButton.setFont(new Font("Serif", Font.PLAIN, 50));
                backButton.setBounds(50, 675, 100, 50);
                backButton.setFont(new Font("Serif", Font.PLAIN, 20));
                add(searchButton);
                add(playButton);
                add(backButton);

                fileField.setBounds(100,150, 725, 100);
                fileField.setFont(new Font("Serif", Font.PLAIN, 50));
                fileField.setHorizontalAlignment(SwingConstants.CENTER);
                fileField.setEditable(false);
                add(fileField);

                searchButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        int result = fileChooser.showOpenDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            fileField.setText(selectedFile.getAbsolutePath());
                            fileField.setCaretPosition(0);
                        }
                    }
                });

                playButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(fileField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Nincs kiválasztva fájl!", "Hiba", JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            //Controller.loadGame(filePath);
                        }
                    }
                });

                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        removeAll();
                        startMenu(frame);
                        revalidate();
                        repaint();
                    }
                });

                revalidate();
                repaint();
            }
        });
    }
}
