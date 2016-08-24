import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ScoreBoard {
    private void writeFile()
    {
        String currentTime = null;
        String timeTxt = "" + currentTime;
        try
        {
            // create a file if it does not exist
            File myFile = new File( ".", "scores.txt" );
            PrintWriter output = new PrintWriter( new FileWriter( myFile ) );

            // write the highest score to the file
            output.write( timeTxt );
            output.close();
        }
        catch( IOException ex )
        {
            ex.printStackTrace();
        }
    }

    /**
     * Reads the highest score from the file
     * @return boolean
     */
    private boolean readFile()
    {
        String text;
        Integer iText = new Integer(0);

        try
        {
            File myFile = new File( ".", "scores.txt" );

            int bestScore;
            // check if the file exist
            if( !myFile.exists() )
            {
                bestScore = -1;
                return false;
            }

            // if it exists read the highest score from it
            BufferedReader in = new BufferedReader( new FileReader( myFile ) );
            text = in.readLine();
            bestScore = iText.parseInt( text );
            in.close();
        }
        catch( IOException ex )
        {
            ex.printStackTrace();
        }

        return true;
    }
}
