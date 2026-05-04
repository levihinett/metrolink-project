public class Edge
{
    String to; // where does it go? immediate connection
    int time; // how long will it take
    String line; // what colour line

    public Edge(String to, int time, String line)
    {
        this.to = to;
        this.time = time;
        this.line = line; // we can define an edge with this object
    }
}