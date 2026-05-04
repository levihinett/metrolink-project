import java.awt.*; // for swing gui later
import javax.swing.*; // for swing


// this file is for main
public class Main
{
    public static void main(String[] args)
    {
        metroGraph graph = new metroGraph();

        graph.addConnection("Bury", "Radcliffe", 6, "yellow"); // b to r, 6m yellow route
        graph.addConnection("Radcliffe", "Whitefield", 3, "yellow"); // r, can go back on b, goes to w, 3m yellow

        for (Edge edge : graph.getConnections("Radcliffe"))
        {
            System.out.println(edge.to + " " + edge.time + " " + edge.line);
        }

        //csvReader reader = new csvReader("Metrolink_times_linecolour(in).csv"); // metrolink file now attached for testing

        //String[] userInput = reader.metroQuery(); // get user inputs

        //String metroStart = userInput[0]; // start location
        //String metroEnd = userInput[1]; // end


    }
}
