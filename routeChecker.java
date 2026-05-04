import java.util.*;

public class routeChecker
{
    private metroGraph graph;

    public routeChecker(metroGraph graph)
    {
        this.graph = graph; // graph from csv
    }

    public void findShortestRoute(String start, String end)
    {
        HashMap<String, Double> distances = new HashMap<>(); // stores best time for each station
        HashMap<String, String> previous = new HashMap<>(); // where we came from

        for (String station : graph.getStations())
        {
            distances.put(station, Double.MAX_VALUE); // sets every connection to basically inf
        }
        distances.put(start, 0.0);

        // check smallest
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingDouble(station -> distances.get(station))); 
        // look at the double (time), mapped by distances 
        pq.add(start);

        // meat and potatoes, bad attempt at djikstra's
        while (!pq.isEmpty()) // while nodes to check
        {
            String nowStation = pq.poll(); // pop, removes and returns smallest
            double nowDistance = distances.get(nowStation);  // whats current shortest

            if (nowStation.equals(end))
            {
                break; // end if we're at end of queue
            }
            if (nowDistance == Double.MAX_VALUE)
            {
                continue; // don't freak out if not sorted 
            }

            for (Edge edge : graph.getConnections(nowStation)) // for trams along current line, where can we go?
            {
                String laterStation = edge.to; // grab the nearest node

                double laterDistance = nowDistance + edge.time; // how long to follow that node

                if (laterDistance < distances.get(laterStation)) // is this faster than the last one
                {
                    distances.put(laterStation, laterDistance); // if it is, add it
                    previous.put(laterStation, nowStation); // track our last location
                    pq.add(laterStation); // we can explore later
                }
            }

        }
        // foolproofing for checking, what if there isn't a route
        if (!previous.containsKey(end) && !start.equals(end))
        {
            System.out.println("No route found.");
            return;
        }

        // flip route for backwards
        ArrayList<String> route = new ArrayList<>();
        String now = end;
        while (now != null) 
        {
            route.add(now);
            now = previous.get(now); // stores backwards
        }
        // flip so it actually goes start to end
        Collections.reverse(route); 

        // output
        System.out.println("Shortest route:");
        for (String station : route)
        {
            System.out.println(station);
        }
        System.out.println("Total time: " + distances.get(end) + "mins");
    }
}