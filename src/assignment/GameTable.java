package assignment;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameTable extends JFrame implements BombCallback {
    private List<List<Tile>> table;
    private int tableHeight;
    private int tableWidth;
    private int numberOfMines;
    private JPanel jPanel;


    /**
     * Creates Table, sets attributes, calls initializeTable
     *
     * @param tableHeight   height of table
     * @param tableWidth    width of table
     * @param numberOfMines number of mines
     */
    public GameTable(int tableHeight, int tableWidth, int numberOfMines) throws Exception {
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
        this.numberOfMines = numberOfMines;
        this.table = new ArrayList<>();

        this.setTitle("MineSweeper");
        setSize(this.tableWidth * 20, this.tableHeight * 20);
        this.jPanel = new JPanel();

        jPanel.setLayout(new GridLayout());
        jPanel.setVisible(true);
        add(jPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);




        //Fills table with blank tiles, with correct images
        for (int i = 0; i < tableHeight; i++) {
            table.add(new ArrayList<>());
            for (int j = 0; j < tableWidth; j++) {
                table.get(i).add(new Tile(this));
                table.get(i).get(j).updateImage();
                jPanel.add(table.get(i).get(j));
            }
        }

        //Creates random mines
        int minesLeft = numberOfMines;
        Random random = new Random();
        while (minesLeft != 0) {
            int randI = random.nextInt(tableHeight);
            int randJ = random.nextInt(tableWidth);

            Tile item = table.get(randI).get(randJ);

            if (!item.getTileMine()) {
                item.setTileMine(true);
                item.updateImage();
                minesLeft--;
            }
        }
    }

    public int getTableHeight() {
        return tableHeight;
    }

    public int getTableWidth() {
        return tableWidth;
    }

    public int getNumberOfMines() {
        return numberOfMines;
    }

    @Override
    public void onBombOpened() {
        //TODO end game
    }
}
