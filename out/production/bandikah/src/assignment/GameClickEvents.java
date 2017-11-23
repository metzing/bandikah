package assignment;

public interface GameClickEvents {
    void onBombOpened();
    void onTileOpened(int x, int y);
    void onMark(int y, int x);
    void onUnMark(int y, int x);
}
