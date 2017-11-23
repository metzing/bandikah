package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMenu extends JFrame implements GameEndEvents {
    private JPanel scorePanel;
    private GameTable gameTable;

    private TimeManager easyTimes;
    private TimeManager mediumTimes;
    private TimeManager hardTimes;

    private JLabel easy1;
    private JLabel easy2;
    private JLabel easy3;
    private JLabel medium1;
    private JLabel medium2;
    private JLabel medium3;
    private JLabel hard1;
    private JLabel hard2;
    private JLabel hard3;

    public GameMenu() {
        setTitle("MineSweeper");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 1));

        JButton easy = new JButton("Easy");
        easy.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    startNewGame(8, 8, 10);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JButton medium = new JButton("Medium");
        medium.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    startNewGame(16, 16, 40);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JButton hard = new JButton("Hard");
        hard.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    startNewGame(30, 16, 99);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        JButton recursionTest = new JButton("RecursionTest");
        recursionTest.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    startNewGame(10, 10, 6);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        menuPanel.add(easy);
        menuPanel.add(medium);
        menuPanel.add(hard);
        menuPanel.add(recursionTest);

        easyTimes = new TimeManager("easyTimes");
        mediumTimes = new TimeManager("mediumTimes");
        hardTimes = new TimeManager("hardTimes");

        scorePanel = new JPanel();
        scorePanel.setLayout(new GridLayout(3,4));

        easy1 = new JLabel();
        easy2 = new JLabel();
        easy3 = new JLabel();
        medium1 = new JLabel();
        medium2 = new JLabel();
        medium3 = new JLabel();
        hard1 = new JLabel();
        hard2 = new JLabel();
        hard3 = new JLabel();

        scorePanel.add(new JLabel("Easy:"));
        scorePanel.add(easy1);
        scorePanel.add(easy2);
        scorePanel.add(easy3);
        scorePanel.add(new JLabel("Medium:"));
        scorePanel.add(medium1);
        scorePanel.add(medium2);
        scorePanel.add(medium3);
        scorePanel.add(new JLabel("Hard:"));
        scorePanel.add(hard1);
        scorePanel.add(hard2);
        scorePanel.add(hard3);

        setScoreLabels();

        add(scorePanel,BorderLayout.SOUTH);
        add(menuPanel, BorderLayout.CENTER);

        pack();
    }

    public void setScoreLabels(){
        easy1.setText(Integer.toString(easyTimes.get(0)));
        easy2.setText(Integer.toString(easyTimes.get(1)));
        easy3.setText(Integer.toString(easyTimes.get(2)));
        medium1.setText(Integer.toString(mediumTimes.get(0)));
        medium2.setText(Integer.toString(mediumTimes.get(1)));
        medium3.setText(Integer.toString(mediumTimes.get(2)));
        hard1.setText(Integer.toString(hardTimes.get(0)));
        hard2.setText(Integer.toString(hardTimes.get(1)));
        hard3.setText(Integer.toString(hardTimes.get(2)));
        scorePanel.updateUI();
    }

    public void startNewGame(int height, int width, int numberOfMines) {
        gameTable = new GameTable(height, width, numberOfMines, this);
        this.setVisible(false);
        gameTable.setVisible(true);
    }

    @Override
    public void gameEnded() {
        gameTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    GameMenu.this.setVisible(true);
                    gameTable.setVisible(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        gameTable.revealAll();
    }

    @Override
    public void gameWon(int winTime) {
        gameTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    GameMenu.this.setVisible(true);
                    gameTable.setVisible(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        gameTable.revealAll();
        switch (gameTable.getNumberOfMines()){
            case 10:
                easyTimes.addTime(winTime);
                easyTimes.saveTime("easyTimes");
                break;
            case 40:
                mediumTimes.addTime(winTime);
                mediumTimes.saveTime("mediumTimes");
                break;
            case 99:
                hardTimes.addTime(winTime);
                hardTimes.saveTime("hardTimes");
                break;
        }
        setScoreLabels();
    }

    @Override
    public void gameLost() {
        gameTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    GameMenu.this.setVisible(true);
                    gameTable.setVisible(false);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        gameTable.revealAll();
    }
}
