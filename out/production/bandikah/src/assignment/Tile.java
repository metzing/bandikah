package assignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JButton {
    private TileState state;
    private boolean hasMine;

    private int surroundingMinesCount;

    public Tile(GameClickEvents gameClickEvents,int x, int y) {
        setSize(ImageManager.getImageSize(), ImageManager.getImageSize());
        setMargin(new Insets(0, 0, 0, 0));
        setBorder(null);

        state = TileState.CLOSED;
        hasMine = false;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (state) {
                    case OPEN:
                        break;
                    case CLOSED:
                        if (SwingUtilities.isRightMouseButton(e)) {
                            state = TileState.MARKED;
                            gameClickEvents.onMark(y,x);
                        } else if (SwingUtilities.isLeftMouseButton(e)) {
                            //state = TileState.OPEN;
                            gameClickEvents.onTileOpened(x,y);
                            if (hasMine) {
                                gameClickEvents.onBombOpened();
                            }
                        }
                        break;
                    case MARKED:
                        if(SwingUtilities.isRightMouseButton(e)){
                            state = TileState.CLOSED;
                            gameClickEvents.onUnMark(y,x);
                        }
                        break;
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

    public TileState getState() {
        return state;
    }

    public void setState(TileState state) {
        this.state = state;
        this.updateImage();
    }

    public int getSurroundingMinesCount() {
        return surroundingMinesCount;
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
