package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JButton {
    private TileState state;
    private boolean hasMine;

    public Tile(BombCallback bombCallback) {
        setSize(20, 20);

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

    public boolean isHasMine() {
        return hasMine;
    }

    public void setHasMine(boolean hasMine) {
        this.hasMine = hasMine;
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
            default:
                image = ImageManager.getClosed();
        }
        setIcon(new ImageIcon(image));
    }
}
