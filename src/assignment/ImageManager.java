package assignment;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageManager {
    private static Image mine;
    private static Image marked;
    private static Image closed;
    private static Image plain;

    public static int getImageSize(){
        return 20;
    }

    public static Image getMine() {
        if (mine == null) {
            try {
                mine = ImageIO.read(new FileInputStream("akna.bmp"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return mine;
    }

    public static Image getMarked() {
        if (marked == null) {
            try {
                marked = ImageIO.read(new FileInputStream("akna_megjelolt.bmp"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return marked;
    }

    public static Image getClosed() {
        if (closed == null) {
            try {
                closed = ImageIO.read(new FileInputStream("akna_felfedetlen.bmp"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return closed;
    }

    public static Image getPlain() {
        if (plain == null) {
            try {
                plain = ImageIO.read(new FileInputStream("akna_felfedett.bmp"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return plain;
    }
}
