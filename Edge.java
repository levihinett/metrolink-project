public class Edge
{
    String to; // where does it go? immediate connection
    double time; // how long will it take, double because decimals can't be read as int
    String line; // what colour line

    public Edge(String to, double time, String line)
    {
        this.to = to;
        this.time = time;
        this.line = line; // we can define an edge with this object
    }
}