import java.awt.*; // for swing gui later
import javax.swing.*; // for swing


// this file is for main
public class Main
{
    public static void main(String[] args)
    {
        csvReader reader = new csvReader("Metrolink_times_linecolour(in).csv"); // metrolink file now attached for testing

        String[] userInput = reader.metroQuery(); // get user inputs

        String metroStart = userInput[0]; // start location
        String metroEnd = userInput[1]; // end


    }
}
