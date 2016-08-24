import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class MyGame implements Runnable {
    private int winCountP1;
    private int winCountP2;
    private int round; 

    public void run() {
        final JFrame frame = new JFrame("CONNECT FOUR");
        frame.setLocation(500, 500);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Choose a spot to place your piece");
        status_panel.add(status);

        final ConnectCourt connect = new ConnectCourt(status);
        frame.add(connect, BorderLayout.CENTER);

        final JPanel cpan = new JPanel();
        frame.add(cpan, BorderLayout.NORTH);

        final JButton score = new JButton("Scoreboard");
        score.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                winCountP1 = connect.winCountP1;
                winCountP2 = connect.winCountP2;
                round = connect.round;
                System.out.println("wp1"+ winCountP1);
                System.out.println("wp2" + winCountP2);
                try {

                    FileWriter writer = new FileWriter("scorerecord.txt", true);
                    writer.write("Round" + round + ": " + "," +"Player1: " + winCountP1 + 
                            "," + "Player2: " + winCountP2 + "\n");
                    writer.close();      
                } catch (FileNotFoundException exc) {
                    System.out.println("File could not be found");
                } catch(IOException ex) {
                    throw new IllegalArgumentException();
                }

                try {
                    String[] line = new String[15];
                    String current = null;
                    String input = "";
                    ArrayList<String> scoreboard = new ArrayList<String>();
                    BufferedReader buff= new BufferedReader(new FileReader("scorerecord.txt"));
                    while ((current = buff.readLine()) != null) {
                        line = current.split(",");
                        input = line[0] + " " + line[1] + " " +line[2] +  "\n";
                        scoreboard.add(input);
                    }
                    String output = "";
                    for (String s: scoreboard) {
                        output += s + "\n";
                    }                  
                    JOptionPane.showMessageDialog(null, output);
                    buff.close();

                } catch (FileNotFoundException e1) {
                    System.out.println("File could not be found");
                } catch (IOException e2) {
                    System.out.println("IO Exception");
                }
            }


        });
        cpan.add(score);

        final JButton reset = new JButton("Start Over");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                connect.reset();
            }
        });

        cpan.add(reset);


        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        connect.reset();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MyGame());

    }
}



