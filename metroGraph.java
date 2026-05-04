import java.util.*;

public class metroGraph
{
    private HashMap<String, ArrayList<Edge>> graph; // station name and places you can go from there

    public metroGraph()
    {
        graph = new HashMap<>(); // create new map
    }

    public void addConnection(String from, String to, int time, String line)
    {
        graph.putIfAbsent(from, new ArrayList<>()); // if it isn't in the graph, make it
        graph.putIfAbsent(to, new ArrayList<>()); 

        graph.get(from).add(new Edge(to, time, line)); // connect to another station
        graph.get(to).add(new Edge(from, time, line)); // backward connection as tram can move back and forth
    }

    public ArrayList<Edge> getConnections(String station)
    {
        return graph.getOrDefault(station, new ArrayList<>());
    }
}