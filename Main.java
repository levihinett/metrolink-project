import java.awt.*; // for swing gui later
import javax.swing.*; // for swing


// this file is for main
public class Main
{
    public static void main(String[] args)
    {
        csvReader reader = new csvReader("Metrolink_times_linecolour(in).csv");

        metroGraph graph = reader.getGraph();

        System.out.println("Connections from Firswood: ");
        for (Edge edge : graph.getConnections("Firswood"))
        {
            System.out.println(edge.to + " | " + edge.time + " mins | " + edge.line);
        }
    }
}
