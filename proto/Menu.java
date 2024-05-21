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
        newGameButton.setBounds(125,50, 250, 75);
        newGameButton.setFont(new Font("Serif", Font.PLAIN, 35));
        loadGameButton.setBounds(415, 50, 250, 75);
        loadGameButton.setFont(new Font("Serif", Font.PLAIN, 35));
        add(newGameButton);
        add(loadGameButton);
        ImageIcon gifIcon = new ImageIcon("proto/Images/tenor.gif");
        JLabel gifLabel = new JLabel(gifIcon);
        gifLabel.setBounds(200,125, 400, 400);
        add(gifLabel);


        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();

                JLabel playerNumberLabel = new JLabel("A játékosok száma:");
                JButton minusButton = new JButton("-");
                JButton plusButton = new JButton("+");
                JButton playButton = new JButton("Play");
                JButton backButton = new JButton("Vissza");
                JTextField numberField = new JTextField(Integer.toString(playerNumber));

                playerNumberLabel.setBounds(0, 0, 800, 200);
                playerNumberLabel.setFont(new Font("Serif", Font.PLAIN, 35));
                playerNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
                playerNumberLabel.setVerticalAlignment(SwingConstants.CENTER);
                add(playerNumberLabel);

                minusButton.setBounds(265 ,150, 75, 75);
                minusButton.setFont(new Font("Serif", Font.PLAIN, 35));
                plusButton.setBounds(460, 150, 75, 75);
                plusButton.setFont(new Font("Serif", Font.PLAIN, 35));
                playButton.setBounds(300, 300, 200, 75);
                playButton.setFont(new Font("Serif", Font.PLAIN, 35));
                backButton.setBounds(50, 400, 75, 35);
                backButton.setFont(new Font("Serif", Font.PLAIN, 15));
                add(minusButton);
                add(plusButton);
                add(playButton);
                add(backButton);

                numberField.setBounds(350, 150, 100, 75);
                numberField.setFont(new Font("Serif", Font.PLAIN, 35));
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
                        Window jFrame = new Window();
                        jFrame.setTitle("Játék");
                        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        jFrame.setSize(new Dimension(800, 600));
                        jFrame.setLocationRelativeTo(null);

                        Controller controller = new Controller();
                        controller.setPlayerNumber(playerNumber);
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

                filePathLabel.setBounds(0, 0, 800, 200);
                filePathLabel.setFont(new Font("Serif", Font.PLAIN, 35));
                filePathLabel.setHorizontalAlignment(SwingConstants.CENTER);
                filePathLabel.setVerticalAlignment(SwingConstants.CENTER);
                add(filePathLabel);

                searchButton.setBounds(550, 150, 175, 75);
                searchButton.setFont(new Font("Serif", Font.PLAIN, 35));
                playButton.setBounds(300, 300, 200, 75);
                playButton.setFont(new Font("Serif", Font.PLAIN, 35));
                backButton.setBounds(50, 400, 75, 35);
                backButton.setFont(new Font("Serif", Font.PLAIN, 15));
                add(searchButton);
                add(playButton);
                add(backButton);

                fileField.setBounds(75,150, 450, 75);
                fileField.setFont(new Font("Serif", Font.PLAIN, 30));
                fileField.setHorizontalAlignment(SwingConstants.CENTER);
                fileField.setEditable(false);
                add(fileField);

                searchButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        int result = fileChooser.showOpenDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            fileField.setText(selectedFile.getName());
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
                            frame.dispose();
                            Window jFrame = new Window();
                            jFrame.setTitle("Játék");
                            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            jFrame.setSize(new Dimension(800, 600));
                            jFrame.setLocationRelativeTo(null);

                            Controller controller = new Controller();
                            controller.setPlayerNumber(playerNumber);
                            View view = new View();

                            jFrame.add(view);
                            jFrame.setVisible(true);

                            controller.setView(view);
                            view.setController(controller);
                            filePath = fileField.getText().substring(0, fileField.getText().length()-4);

                            /*String[] tmp = filePath.split("/");
                            String save = tmp[tmp.length - 1];*/
                            controller.loadGame(filePath);
                            controller.drawEverything();
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
