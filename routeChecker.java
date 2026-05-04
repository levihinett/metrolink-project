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

        HashMap<String, String> previousLine = new HashMap<>(); // what line were we in order to get to where we are

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

                String lastLine = previousLine.get(nowStation); // actually determine what line we were on
                if (lastLine != null && !lastLine.equals(edge.line))
                {
                    laterDistance +=2; // if last line isn't blank, and equals a different line, add 2m to journey
                }

                if (laterDistance < distances.get(laterStation)) // is this faster than the last one
                {
                    distances.put(laterStation, laterDistance); // if it is, add it
                    previous.put(laterStation, nowStation); // track our last location
                    previousLine.put(laterStation, edge.line);
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
        String lastLine = null; // no line yet to be used
        for (int i = 0; i < route.size() - 1; i++) // minus 1 as we're always looking ahead 1
        {
            String from = route.get(i); // nowStation
            String to = route.get(i + 1); // laterStation
            String line = previousLine.get(to); // line used on the next stop

            // check if the line has changed
            if (lastLine != null && !lastLine.equals(line))
            { // making sure we haven't already travelled, if different needs to swap lines
                System.out.println("Change at " + from + " (takes 2m to swap lines)");
            }
            System.out.println(from + " ->" + to + " on " + line + " line");
            lastLine = line; // remember to see if it changes
        }
        System.out.println("Total time: " + distances.get(end) + " mins");
    }
}