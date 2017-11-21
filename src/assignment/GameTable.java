package assignment;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameTable extends JFrame {
    private List<List<Tile>> table;
    private int tableHeight;
    private int tableWidth;
    private int numberOfMines;
    private JFrame jFrame;
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
        initializeTable();

        this.jFrame = new JFrame("MineSweeper");
        this.jFrame.setSize(this.tableWidth * 20, this.tableHeight * 20);
        this.jPanel = new JPanel();

        jPanel.setLayout(new GridLayout());
        jPanel.setVisible(true);
        jFrame.add(jPanel, BorderLayout.CENTER);

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.pack();
    }

    public void initializeTable() throws IOException {

        //Fills table with blank tiles, with correct images
        for (int i = 0; i < tableHeight; i++) {
            table.add(new ArrayList<>());
            for (int j = 0; j < tableWidth; j++) {
                table.get(i).add(new Tile());
                table.get(i).get(j).setButtonImage();

            }
        }

        //Creates random mines
        int minesLeft = numberOfMines;
        Random random = new Random();
        while (minesLeft != 0) {
            int randI = random.nextInt(tableHeight);
            int randJ = random.nextInt(tableWidth);
            if (table.get(randI).get(randJ).getTileMine() != TileMine.MINE) {
                table.get(randI).get(randJ).setTileMine(TileMine.MINE);
                table.get(randI).get(randJ).setButtonImage();
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

    public JFrame getjFrame() {
        return jFrame;
    }

    public JPanel getjPanel() {
        return jPanel;
    }
}
