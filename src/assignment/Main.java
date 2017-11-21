package assignment;

public class Main {

    public static void main(String[] args) {
        try {
            GameTable gameTable = new GameTable(10, 10, 10);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
