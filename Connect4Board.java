//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//
//
///**
// * 
// * This class holds the 
// * @author erinlee
// *
// */
//@SuppressWarnings("serial")
//public class Connect4Board extends JPanel{
//    public final int BOX_WIDTH = 10;
//    public final int BOX_HEIGHT = 10;
//    public final int BOARD_ROW = 10;
//    public final int BOARD_COL = 8;
//    public final int[][] board;
//    private int pos_x;
//    private int pos_y;
//    public boolean player1 = true;
//    public boolean player2 = false;
//    public boolean playing = true;
//    private JLabel status;
//    public Piece piece;
//    public BoardCell box;
//    
//    public Connect4Board(JLabel status) {
//        board = new int[BOARD_ROW][BOARD_COL];
//        setBackground(Color.WHITE);
//        setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
////                if (!playing) {
////                    return;
////                }
//                pos_x = e.getX();
//                pos_y = e.getY();
//                if (board[pos_x / 100][pos_y / 100] == 0) {
//                    if (player1) {
//                        board[pos_x / 100][pos_y / 100] = 1;
//                    }
//                    next(pos_x, pos_y);
//                }
//            }
//        });
//        this.status = status;
//    }
//        
//        public void drawPiece(Graphics g) {
//            int rowHere = pos_x / 100;
//            int colHere = pos_y / 100;
//            if (player1) {
//                g.setColor(Color.RED);
//                g.fillOval(0, 0, 90, 90);
//            }
//            else {
//                g.setColor(Color.BLUE);
//                g.fillOval(rowHere, colHere, 90, 90);
//            }
//        }
//       
//        public void changeTurn() {
//            if (player1) {
//                player1 = false;
//                player2 = true;
//            } else {
//                player1 = true;
//                player2 = false;
//            }
//        }
//        
//    void next(int pos_x, int pos_y) {
//        int move;
//        if (playing) {
//            if (player1) {
//                move = 1;
//            } else {
//                move = 2;
//            }
//            BoardCell newbox = new BoardCell(move, pos_x, pos_y);
//            repaint();
//        }
//    }
//    
//    @Override
//    public void paintComponent(Graphics g) {
//        piece.draw(g);
//        box.draw(g);
//    }
//}
//
