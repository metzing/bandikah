package assignment;

import assignment.GameMenu;
import assignment.GameTable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

class GameMenuTest {
    private GameMenu testMenu;
    private GameTable testTable;
    private GameEndEvents testGameEndEvents;

    @Before
    public void setUp() {
        testMenu = new GameMenu();
        testGameEndEvents = new GameEndEvents() {
            @Override
            public void gameEnded() {

            }

            @Override
            public void gameWon(int winTime) {

            }

            @Override
            public void gameLost() {

            }
        };
        testTable = new GameTable(10, 10, 10, testGameEndEvents);
    }

    @Test
    public void testConstructor() throws Exception {
        assertNotNull(testMenu);
    }

    @Test
    public void testStartNewGame() throws Exception {
        testMenu.startNewGame(10, 10, 10);
        assertEquals(10, testMenu.getGameTable().getNumberOfMines());
    }

    @Test
    public void testGetGameTable() throws Exception {
        assertEquals(testTable.getNumberOfMines(),testMenu.getGameTable().getNumberOfMines());
    }

}