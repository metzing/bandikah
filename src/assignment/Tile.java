package assignment;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Tile {
    private TileMarked tileMarked;
    private TileMine tileMine;
    private TileVisibility tileVisibility;
    private JButton jButton;

    /*public Tile(TileMarked tileMarked, TileMine tileMine, TileVisibility tileVisibility, JButton jButton) {
        this.tileMarked = tileMarked;
        this.tileMine = tileMine;
        this.tileVisibility = tileVisibility;
        this.jButton = jButton;
    }*/

    public Tile() {
        this.tileMarked = TileMarked.NOTMARKED;
        this.tileMine = TileMine.NOTMINE;
        this.tileVisibility = TileVisibility.NOTVISIBLE;
        this.jButton = new JButton();
        this.jButton.setSize(20, 20);
    }

    public TileMarked getTileMarked() {
        return tileMarked;
    }

    public TileMine getTileMine() {
        return tileMine;
    }

    public TileVisibility getTileVisibility() {
        return tileVisibility;
    }

    public JButton getjButton() {
        return jButton;
    }

    public void setTileMarked(TileMarked tileMarked) {
        this.tileMarked = tileMarked;
    }

    public void setTileMine(TileMine tileMine) {
        this.tileMine = tileMine;
    }

    public void setTileVisibility(TileVisibility tileVisibility) {
        this.tileVisibility = tileVisibility;
    }

    public void setjButton(JButton jButton) {
        this.jButton = jButton;
    }

    public void setButtonImage() throws IOException {

        Image mineMine = ImageIO.read(getClass().getResource("akna.bmp"));
        Image mineNotVisible = ImageIO.read(getClass().getResource("akna_felfedetlen.bmp"));
        Image mineVisible = ImageIO.read(getClass().getResource("akna_felfedett.bmp"));
        Image mineMarked = ImageIO.read(getClass().getResource("akna_megjelolt.bmp"));

        switch (tileVisibility) {
            case NOTVISIBLE:
                switch (tileMarked) {
                    case NOTMARKED:
                        jButton.setIcon(new ImageIcon(mineNotVisible));
                        break;
                    case MARKED:
                        jButton.setIcon(new ImageIcon(mineMarked));
                        break;
                }
                break;

            case VSIBLE:
                switch (tileMine) {
                    case NOTMINE:
                        jButton.setIcon(new ImageIcon(mineVisible));
                        break;
                    case MINE:
                        jButton.setIcon(new ImageIcon(mineMine));
                        break;
                }
                break;
        }
    }
}
