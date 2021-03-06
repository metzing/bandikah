package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class GameTable extends JFrame implements GameClickEvents {
    //Data structure
    private ArrayList<ArrayList<Tile>> table;
    private int tableHeight;
    private int tableWidth;
    private int numberOfMines;
    private int minesLeft;
    private int timePassed;
    private int unopenedTiles;

    private JPanel uiPanel;
    private JTextField timeText;
    private Timer timer;
    private JTextField minesLeftIndicator;
    private JLabel endMessage;

    private GameEndEvents gameEndEvents;

    public GameTable(int tableHeight, int tableWidth, int numberOfMines, GameEndEvents gameEndEvents) {
        this.gameEndEvents = gameEndEvents;

        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
        this.numberOfMines = numberOfMines;
        this.minesLeft = numberOfMines;
        this.timePassed = 0;
        this.table = new ArrayList<>();
        this.unopenedTiles = tableHeight*tableWidth;

        this.setTitle("MineSweeper");
        setSize(this.tableWidth * ImageManager.getImageSize(), this.tableHeight * ImageManager.getImageSize() + 70);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Game Panel setup
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(tableWidth, tableHeight));
        gamePanel.setVisible(true);
        add(gamePanel, BorderLayout.SOUTH);

        //UI panel setup
        this.uiPanel = new JPanel();
        this.uiPanel.setSize(this.tableWidth * ImageManager.getImageSize(), 70);
        this.uiPanel.setLayout(new FlowLayout());
        JButton stopButton = new JButton("Stop");
        stopButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    endMessage.setText("YOU LOST!");
                    timer.stop();
                    gameEndEvents.gameEnded();
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
        this.uiPanel.add(new JLabel("Mines Left:"));

        this.minesLeftIndicator = new JTextField();
        minesLeftIndicator.setText(Integer.toString(minesLeft));
        minesLeftIndicator.setEditable(false);
        this.uiPanel.add(minesLeftIndicator);
        this.uiPanel.add(stopButton);


        //timeLabel setup
        this.uiPanel.add(new JLabel("Time:"));
        this.timeText = new JTextField();
        this.timeText.setEditable(false);
        this.uiPanel.add(timeText);
        this.timer = new Timer(1000, e -> {
            timePassed++;
            timeText.setText(Integer.toString(timePassed));
            uiPanel.updateUI();
            pack();
        });
        endMessage = new JLabel("");
        this.uiPanel.add(endMessage);

        add(this.uiPanel, BorderLayout.NORTH);


        //Add tiles
        for (int i = 0; i < tableHeight; i++) {
            table.add(new ArrayList<>());
            for (int j = 0; j < tableWidth; j++) {
                Tile temp = new Tile(this, j, i);
                table.get(i).add(temp);
                temp.updateImage();
                gamePanel.add(temp);
            }
        }
        gamePanel.updateUI();
        pack();

        //Creates random mines
        int minesLeft = this.numberOfMines;
        Random random = new Random();
        while (minesLeft != 0) {
            int randI = random.nextInt(tableHeight);
            int randJ = random.nextInt(tableWidth);

            Tile item = table.get(randI).get(randJ);

            if (!item.hasMine()) {
                item.setHasMine(true);
                item.updateImage();
                minesLeft--;
            }
        }

        //debug helpers
        for (int i = 0; i < tableHeight; i++) {
            for (int j = 0; j < tableWidth; j++) {
                if (table.get(i).get(j).hasMine()) {
                    System.out.print("X");
                } else {
                    System.out.print('T');
                }
            }
            System.out.print('\n');
        }

        System.out.print('\n');

        //Count surrounding mines
        for (int i = 0; i < tableHeight; i++) {
            for (int j = 0; j < tableWidth; j++) {
                Tile tile = table.get(i).get(j);
                if (!tile.hasMine()) {
                    int mineCounter = 0;
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            try {
                                Tile temp = table.get(i + k).get(j + l);
                                if (temp.hasMine()) mineCounter++;
                            } catch (IndexOutOfBoundsException ex) {
                                //Do nothing lol
                            }
                        }
                    }
                    tile.setSurroundingMinesCount(mineCounter);
                }
                if (tile.hasMine()) {
                    System.out.print("X");
                } else {
                    System.out.print(tile.getSurroundingMinesCount());
                }
            }
            System.out.print('\n');
        }
        System.out.print('\n');
        //Starts Timer
        this.timer.start();
    }

    public void checkWinCondition() {
        if (unopenedTiles == numberOfMines) {
            timer.stop();
            endMessage.setText("YOU WON!");
            gameEndEvents.gameWon(timePassed);
        }
    }

    public void revealAll() {
        for (int i = 0; i < tableHeight; i++) {
            for (int j = 0; j < tableWidth; j++) {
                table.get(i).get(j).setState(TileState.OPEN);
            }
        }
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    @Override
    public void onBombOpened() {
        timer.stop();
        endMessage.setText("YOU LOST!");
        gameEndEvents.gameLost();
    }

    @Override
    public void onTileOpened(int x, int y) {
        if (x < 0 || x >= tableWidth || y < 0 || y >= tableHeight) {
            return;
        }

        Tile item = table.get(y).get(x);

        if (item.getState() != TileState.CLOSED) {
            return;
        }

        item.setState(TileState.OPEN);
        unopenedTiles--;
        checkWinCondition();

        if (item.getSurroundingMinesCount() == 0) {
            onTileOpened(x - 1, y - 1);
            onTileOpened(x - 1, y);
            onTileOpened(x - 1, y + 1);
            onTileOpened(x, y - 1);
            onTileOpened(x, y + 1);
            onTileOpened(x + 1, y - 1);
            onTileOpened(x + 1, y);
            onTileOpened(x + 1, y + 1);
        }
    }

    @Override
    public void onMark(int y, int x) {
        minesLeftIndicator.setText(Integer.toString(--minesLeft));
        checkWinCondition();
        uiPanel.updateUI();
    }

    @Override
    public void onUnMark(int y, int x) {
        minesLeftIndicator.setText(Integer.toString(++minesLeft));
        uiPanel.updateUI();
    }
}
