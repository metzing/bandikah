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

    public GameTable(int tableHeight, int tableWidth, int numberOfMines) {
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
        this.numberOfMines = numberOfMines;
        this.table = new ArrayList<>();

        this.setTitle("MineSweeper");
        setSize(this.tableWidth * ImageManager.getImageSize(), this.tableHeight * ImageManager.getImageSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(tableWidth, tableHeight));
        jPanel.setVisible(true);
        add(jPanel, BorderLayout.CENTER);

        //Add tiles
        for (int i = 0; i < tableHeight; i++) {
            table.add(new ArrayList<>());
            for (int j = 0; j < tableWidth; j++) {
                Tile temp = new Tile(this);
                table.get(i).add(temp);
                temp.updateImage();
                jPanel.add(temp);
            }
        }

        //Creates random mines
        int minesLeft = numberOfMines;
        Random random = new Random();
        while (minesLeft != 0) {
            int randI = random.nextInt(tableHeight);
            int randJ = random.nextInt(tableWidth);

            Tile item = table.get(randI).get(randJ);

            if (!item.isHasMine()) {
                item.setHasMine(true);
                item.updateImage();
                minesLeft--;
            }
        }
    }

    @Override
    public void onBombOpened() {
        //TODO end game
    }
}
