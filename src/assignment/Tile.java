package assignment;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JButton {
    private TileState state;
    private boolean hasMine;

    private int surroundingMinesCount;

    public Tile(BombCallback bombCallback) {
        setSize(ImageManager.getImageSize(), ImageManager.getImageSize());
        setMargin(new Insets(0, 0, 0, 0));
        setBorder(null);


        state = TileState.CLOSED;
        hasMine = false;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (state == TileState.CLOSED) {
                    if (SwingUtilities.isRightMouseButton(e)) {
                        state = TileState.MARKED;
                    } else {
                        state = TileState.OPEN;
                        //TODO add callback
                        if (hasMine) {
                            bombCallback.onBombOpened();
                        }
                    }
                }
                updateImage();
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
    }

    public boolean hasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
    }

    public void setSurroundingMinesCount(int surroundingMinesCount) {
        this.surroundingMinesCount = surroundingMinesCount;
    }

    public void updateImage() {
        Image image = null;
        switch (state) {
            case OPEN:
                if (hasMine) {
                    image = ImageManager.getMine();
                } else {
                    image = ImageManager.getPlain();
                }
                break;
            case CLOSED:
                image = ImageManager.getClosed();
                break;
            case MARKED:
                image = ImageManager.getMarked();
                break;
        }
        setIcon(new ImageIcon(image));

        if (state == TileState.OPEN && !hasMine) {
            setIcon(null);
            if (surroundingMinesCount > 0) {
                setText(Integer.toString(surroundingMinesCount));
            }
            setEnabled(false);
        }
    }
}
