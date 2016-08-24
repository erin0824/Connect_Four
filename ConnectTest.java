import static org.junit.Assert.*;

import javax.swing.JLabel;

import org.junit.Test;

public class ConnectTest {  

    // Tests for endWin (i.e. complex search)
    // Test for updateY method
    // Test for endTie
    // Test playing boolean

    public int BOARD_WIDTH = 800;
    public int BOARD_HEIGHT = 600;
    public int BOX_WIDTH = 100;
    public int BOX_HEIGHT = 100;

    /*Note: updateY() - method that returns y-coord after 
     * piece shifted down
     */
    @Test  
    public void testUpdateY() { 
        final JLabel status = new JLabel("Test1");
        ConnectCourt cTest = new ConnectCourt(status);
        assertEquals(5, cTest.updateY(0, 0)); 
        cTest.board[2][5] = 1;
        cTest.board[2][4] = 1;
        assertEquals(3, cTest.updateY(2, 5));
        cTest.board[1][5] = 2;
        cTest.board[2][5] = 1;
        assertEquals(4, cTest.updateY(1, 5));
    }

    /* Note: endTie() returns false if there is at least 
     * one empty box
     */
    @Test 
    public void testNoEndTie() {
        final JLabel status = new JLabel("Test2a");
        ConnectCourt cTest = new ConnectCourt(status);
        for (int i = 0; i < 7; i ++) {
            for (int j = 0; j < 6; j++) {
                cTest.board[i][j] = 1;
            }
        }
        assertFalse(cTest.endTie());
        assertTrue(cTest.playing);
    }

    @Test 
    public void testEndTie() {
        final JLabel status = new JLabel("Test3");
        ConnectCourt cTest = new ConnectCourt(status);
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 6; j++) {
                cTest.board[i][j] = 1;
            }
        }
        assertTrue(cTest.endTie());
        assertFalse(cTest.playing);
    }

    @Test
    public void testEndWinHorizontally() { // horizontal win
        final JLabel status = new JLabel("Test4");
        ConnectCourt cTest = new ConnectCourt(status);
        cTest.pos_x = 240;
        cTest.updatedY = 5;
        cTest.board[5][5] = 1;
        cTest.board[2][4] = 2;
        cTest.board[4][5] = 1;
        cTest.board[2][3] = 2;
        cTest.board[3][5] = 1;
        cTest.board[2][2] = 2;
        cTest.board[2][5] = 1;
        assertTrue(cTest.endWin());
        assertFalse(cTest.playing);
    }

    @Test
    public void testEndWinVertically() {
        final JLabel status = new JLabel("Test5");
        ConnectCourt cTest = new ConnectCourt(status);
        cTest.pos_x = 10;
        cTest.updatedY = 2;
        cTest.board[0][5] = 1;
        cTest.board[2][4] = 2;
        cTest.board[0][4] = 1;
        cTest.board[2][3] = 2;
        cTest.board[0][3] = 1;
        cTest.board[2][2] = 2;
        cTest.board[0][2] = 1;
        assertTrue(cTest.endWin());
        assertFalse(cTest.playing);
    }

    @Test 
    public void testEndWinDiagonally() {
        final JLabel status = new JLabel("Test6");
        ConnectCourt cTest = new ConnectCourt(status);
        cTest.pos_x = 352;
        cTest.updatedY = 2;
        cTest.board[0][5] = 1;
        cTest.board[1][5] = 2;
        cTest.board[1][4] = 1;
        cTest.board[2][5] = 2;
        cTest.board[2][3] = 1;
        cTest.board[3][4] = 2;
        cTest.board[3][2] = 1;
        assertTrue(cTest.endWin());
        assertFalse(cTest.playing);
    }

    @Test 
    public void testEndWinDiagonallyTwo() {
        final JLabel status = new JLabel("Test7");
        ConnectCourt cTest = new ConnectCourt(status);
        cTest.pos_x = 360;
        cTest.updatedY = 2;
        cTest.board[6][5] = 1;
        cTest.board[5][5] = 2;
        cTest.board[5][4] = 1;
        cTest.board[4][5] = 2;
        cTest.board[6][4] = 1;
        cTest.board[4][4] = 2;
        cTest.board[4][3] = 1;
        cTest.board[3][5] = 2;
        cTest.board[3][4] = 1;
        cTest.board[3][4] = 2;
        cTest.board[3][2] = 1;
        assertTrue(cTest.endWin());
        assertFalse(cTest.playing);
    }

    @Test 
    public void testEndWinDiagonallyThree() {
        final JLabel status = new JLabel("Test8");
        ConnectCourt cTest = new ConnectCourt(status);
        cTest.pos_x = 582;
        cTest.updatedY = 2;
        cTest.board[2][5] = 1;
        cTest.board[3][5] = 2;
        cTest.board[3][4] = 1;
        cTest.board[4][5] = 2;
        cTest.board[5][5] = 1;
        cTest.board[4][4] = 2;
        cTest.board[4][3] = 1;
        cTest.board[5][4] = 2;
        cTest.board[5][3] = 1;
        cTest.board[6][5] = 2;
        cTest.board[5][2] = 1;
        assertTrue(cTest.endWin());
        assertFalse(cTest.playing);
    }

    @Test
    public void testNoEndWin() {
        final JLabel status = new JLabel("Test9");
        ConnectCourt cTest = new ConnectCourt(status);
        cTest.pos_x = 10;
        cTest.updatedY = 2;
        cTest.board[0][5] = 1;
        cTest.board[1][5] = 2;
        cTest.board[0][4] = 1;
        cTest.board[1][4] = 2;
        cTest.board[0][3] = 1;
        cTest.board[0][2] = 2;
        assertFalse(cTest.endWin());
        assertTrue(cTest.playing);
    }

    @Test 
    public void testNoEndWinTwo() {
        final JLabel status = new JLabel("Test10");
        ConnectCourt cTest = new ConnectCourt(status);
        cTest.pos_x = 380;
        cTest.updatedY = 4;
        cTest.board[7][5] = 1;
        cTest.board[4][5] = 2;
        cTest.board[6][4] = 2;
        cTest.board[4][4] = 1;
        cTest.board[5][3] = 1;
        cTest.board[3][3] = 2;
        cTest.board[4][2] = 1;
        cTest.board[3][5] = 2;
        cTest.board[3][4] = 1;
        assertFalse(cTest.endWin());
        assertTrue(cTest.playing);
    }

    @Test
    public void testEndWinVerticallyTwo() {
        final JLabel status = new JLabel("Test11");
        ConnectCourt cTest = new ConnectCourt(status);
        cTest.pos_x = 500;
        cTest.updatedY = 2;
        cTest.board[5][5] = 1;
        cTest.board[0][5] = 2;
        cTest.board[5][4] = 1;
        cTest.board[0][3] = 2;
        cTest.board[5][3] = 1;
        cTest.board[0][4] = 2;
        cTest.board[5][2] = 1;
        assertTrue(cTest.endWin());
        assertFalse(cTest.playing);
    }    
}
