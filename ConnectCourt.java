import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConnectCourt extends JPanel {
    public int BOARD_WIDTH = 800;
    public int BOARD_HEIGHT = 600;
    public int BOX_WIDTH = 100;
    public int BOX_HEIGHT = 100;
    public int[][] board;
    private JLabel status;
    public boolean playing = true;
    public boolean p1 = true;
    public ConnectBoard cb;
    public int pos_x;
    public int pos_y;
    public int updatedY;
    public boolean winnerP1;
    public boolean winnerP2;
    public int winCountP1;
    public int winCountP2;
    public int round;

    public ConnectCourt(final JLabel status) {        
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cb = new ConnectBoard();
        cb.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        this.board = new int[BOARD_WIDTH / BOX_WIDTH][BOARD_HEIGHT / BOX_HEIGHT];
        repaint();
        this.status = status;

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pos_x = e.getX();
                pos_y = e.getY();   

                if (playing) {
                    if (pos_x >= 0 && pos_x < BOARD_WIDTH && pos_y >= 0 && pos_y < BOARD_HEIGHT) {
                        updatedY = updateY(pos_x / BOX_WIDTH, pos_y / BOX_HEIGHT);
                        if (board[pos_x / BOX_WIDTH][updatedY] == 0) {
                            if (p1) {
                                board[pos_x / BOX_WIDTH][updatedY] = 1;
                                p1 = false;
                            } else {
                                board[pos_x / BOX_WIDTH][updatedY] = 2; 
                                p1 = true;
                            }
                            repaint();
                        } else if (board[pos_x / BOX_WIDTH][updatedY] == 1 ||
                                board[pos_x / BOX_WIDTH][updatedY] == 2) {// lose a turn if selects preoccupied spot
                            if (p1) {
                                p1 = false;
                            } else {
                                p1 = true;
                            }
                        }
                    }
                    if (endWin()) {
                        round++;
                        if (p1) {
                            status.setText("Player 2 won!");
                            winnerP1 = false;
                            winnerP2 = true;
                            winCountP2++;
                        } else {
                            status.setText("Player 1 won!");
                            winnerP1 = true;
                            winnerP2 = false;
                            winCountP1++;
                        }
                    }
                    if (endTie()) {
                        round++;
                        status.setText("It's a tie! How about another round?");
                    }
                }
            }

        });
    }

    public int updateY(int x, int y) { // input params: index x and y of board[][]
        int update = 1;
        while(board[x][BOARD_HEIGHT/BOX_HEIGHT- update] != 0) {
            update++;
        }
        return (BOARD_HEIGHT/BOX_HEIGHT- update);
    }

    class ConnectBoard extends JPanel {

        public void paintComponent(Graphics g, int[][] board) {
            super.paintComponent(g);   
            g.setColor(Color.BLACK);
            for (int i = 0; i < BOARD_WIDTH / BOX_WIDTH; i++) {
                for (int j = 0; j < BOARD_HEIGHT / BOX_HEIGHT; j++) {
                    g.drawRect(BOX_WIDTH * i, BOX_HEIGHT * j, BOX_WIDTH, BOX_HEIGHT);
                    // fill in boxes with appropriate piece
                    if (board[i][j] == 1) {
                        g.setColor(Color.RED);
                        g.fillOval(i * BOX_WIDTH + 5, j * BOX_HEIGHT + 5, 90, 90);
                    } else if (board[i][j] == 2) {
                        g.setColor(Color.BLUE);
                        g.fillOval(i * BOX_WIDTH + 5, j * BOX_HEIGHT + 5, 90, 90);
                    } else if (board[i][j] == 0) {
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillOval(i * BOX_WIDTH + 5, j * BOX_HEIGHT + 5, 90, 90);
                        g.drawRect(BOX_WIDTH * i, BOX_HEIGHT * j, BOX_WIDTH, BOX_HEIGHT);
                    }
                }
            }
        }
    }

    public void jUnitTesting(int x, int y, int val) {
        board[x][y] = val;
    }

    public void reset() {
        for (int i = 0; i < BOARD_WIDTH / BOX_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT / BOX_HEIGHT ; j++) {
                board[i][j] = 0;
            }
        }
        status.setText("Choose a spot to place your piece");
        playing = true;
        repaint();
    }

    // check if all spots have been filled; if true, then tie
    public boolean endTie() {
        for (int i = 0; i < BOARD_WIDTH / BOX_WIDTH; i++) {
            for (int j = 0; j < BOARD_HEIGHT / BOX_HEIGHT ; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        playing = false;
        winnerP1 = false;
        winnerP2 = false;
        return true;
    }

    // check if four of same color in a row, column, or diagonally
    public boolean endWin() {
        int count = 0;
        int color = board[pos_x / BOX_WIDTH][updatedY]; // check if P1 or P2 made move

        // check for four horizontally
        for (int i = 0; i < BOARD_WIDTH / BOX_WIDTH; i++) {
            if (board[i][updatedY] == color) {
                count++;
                if (count == 4) {
                    playing = false;
                    return true;
                }
            } else {
                count = 0;
            }
        }

        // check for four vertically
        for (int j = 0; j < BOARD_HEIGHT / BOX_HEIGHT; j++) {
            if (board[pos_x / BOX_WIDTH][j] == color) {
                count++;
                if (count == 4) {
                    playing = false;
                    return true;
                }
            } else {
                count = 0;
            }
        }

        //check for four diagonally
        if ((pos_x / BOX_WIDTH > 1 && pos_x / BOX_WIDTH < 6) && (updatedY < 4 && updatedY > 1)) {
            if (board[pos_x / BOX_WIDTH + 1][updatedY + 1] == color &&
                    board[pos_x / BOX_WIDTH - 1][updatedY - 1] == color) {
                if (board[pos_x / BOX_WIDTH + 2][updatedY + 2] == color || 
                        board[pos_x / BOX_WIDTH - 1][updatedY - 2] == color) {
                    playing = false;
                    return true;
                }
            } else if (board[pos_x / BOX_WIDTH + 1][updatedY - 1] == color &&
                    board[pos_x / BOX_WIDTH - 1][updatedY + 1] == color) {
                if (board[pos_x / BOX_WIDTH + 2][updatedY - 2] == color ||
                        board[pos_x / BOX_WIDTH - 2][updatedY + 2] == color) {
                    playing = false;
                    return true;
                }
            } 
        }
        if ((pos_x / BOX_WIDTH >= 3) && (updatedY < 3)) {
            if (board[pos_x / BOX_WIDTH - 1][updatedY + 1] == color) {
                if (board[pos_x / BOX_WIDTH - 2][updatedY + 2] == color &&
                        board[pos_x / BOX_WIDTH - 3][updatedY + 3] == color) {
                    playing = false;
                    return true;
                }
            }
        }

        if (pos_x / BOX_WIDTH <= 2 && updatedY >= 3)
            if (board[pos_x / BOX_WIDTH + 1][updatedY - 1] == color) {
                if (board[pos_x / BOX_WIDTH + 2][updatedY - 2] == color &&
                        board[pos_x / BOX_WIDTH + 3][updatedY - 3] == color) {
                    playing = false;
                    return true;
                }   
            }



        for (int c = 1; c < 4; c++) {
            if (pos_x / BOX_WIDTH <= 4 && updatedY <= 2) {
                if (board[pos_x / BOX_WIDTH + 1][updatedY + 1] == color) {
                    if (board[pos_x / BOX_WIDTH + c][updatedY + c] == color) {
                        count++;
                        if (count == 3) {
                            playing = false;
                            return true;
                        }
                    } else {
                        count = 0;
                    }
                }
            }
        }

        for (int d = 1; d < 4; d++) {
            if (pos_x / BOX_WIDTH >= 3 && updatedY <= 3 && updatedY >= 1) {
                if (board[pos_x / BOX_WIDTH - 1][updatedY - 1] == color) {
                    if (board[pos_x / BOX_WIDTH - d][updatedY - d] == color) {
                        count++;
                        if (count == 3) {
                            playing = false;
                            return true;
                        }
                    } else {
                        count = 0;
                    }   
                }
            } 
        }
        return false;
    }


    @Override
    public void paintComponent(Graphics g) {
        cb.paintComponent(g, board);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}
