import java.util.*; // utilities, specifically for scanner
import java.io.*; // io, for bufferedreader, filereader, ioexception and input stream for later maybe

public class csvReader
{
    // csv reader methods
    private List<List<String>> csv; // belongs to object, csvReader.csv
    private List<String> parseLine(String line) // parse line function, splits line with , used in csvreader
    {
        return Arrays.asList(line.split(","));
    }

    // metroGraph methods
    private metroGraph graph; // new graph, to store stations for connections

    // constructor, gets data to use in everything else and opens the csv
    public csvReader(String filePath)  // give filepath string to use with filereader
    {
        graph = new metroGraph(); 
        String currentLineColour = "";

        csv = new ArrayList<List<String>>(); // array list for the csv, creates storage we can reference as field
        try (BufferedReader br = new BufferedReader(new FileReader(filePath)))  
        {
            // open the file and then auto closes it, changed to any file in case they test dif files, string filepath because not defined

            String line; // becomes read line in loops

            while ((line = br.readLine()) != null) // read line and stop when no lines left
            {
                List<String> row = parseLine(line);

                // make sure row has enough columns otherwise colour
                if (row.size() >= 2 && row.get(1).trim().isEmpty())
                {   // if second line is empty, it's a colour
                    currentLineColour = row.get(0).trim();
                }
                else if (row.size() >= 3 && !row.get(2).trim().isEmpty()) // make sure it also has time
                {
                    String from = row.get(0).trim();
                    String to = row.get(1).trim();
                    int time = Integer.parseInt(row.get(2).trim());

                    graph.addConnection(from, to, time, currentLineColour);
                }

                csv.add(row); // use parse function method to split
            }
        } catch (IOException csvError)
        {
            System.out.println("Error reading CSV file.");
            csvError.printStackTrace();
        }

    }

    // has to be called after csvReader is created in main, otherwise csv fails to exist and it tweaks out
    public boolean isValidStation(String station) // is the station valid? user input + all stations
    {
        for (List<String> row : csv) // go through station 1-by-1
        {
           if (row.size() >= 3) // no small or big rows to fuck shit up, ignores the colours for now
           {
            String from = row.get(0).trim(); // start station
            String to = row.get(1).trim(); // end station
            String time = row.get(2).trim(); // time, trim on all rows gets rid of spacing

            if (!time.isEmpty()) // ignore invalid rows
            {
                if (from.equalsIgnoreCase(station) || to.equalsIgnoreCase(station)) // capitals are irrelevant so long as case matches
                {
                    return true;
                }
            }
           }
        }
        return false; // otherwise, return false
    }



    // user input method
    public String[] metroQuery() // return string multiple so that we can have both scanned outputs, pull straight from file
    {
        Scanner metroInput = new Scanner(System.in); // new scanner for input
        String metroStart; // declare so scope doesn't fuck shit up and delete it
        do
        {
            System.out.println("Enter starting destination: "); // print new line
            metroStart = metroInput.nextLine(); // already declared, writes input to start

            if (!isValidStation(metroStart)) // if it isn't
            {
                System.out.println("First destination isn't valid: " + metroStart); // give the message if it isn't right, return to while loop
            }
        } while (!isValidStation(metroStart)); // loop until it's right
        System.out.println("First destination is valid: " + metroStart); // okay, it's now right, next steps

        String metroEnd; // no fucking shit up scope!
        do
        {
            System.out.println("Enter ending destination: "); // print new line
            metroEnd = metroInput.nextLine(); // same as start

            if (!isValidStation(metroEnd))
            {
                System.out.println("Last destination isn't valid: " + metroEnd);
            }
        } while (!isValidStation(metroEnd)); // loop until it's right again
        System.out.println("Last destination is valid: " + metroEnd);

        return new String[]{metroStart, metroEnd}; // returns string with start and end, can use later in main
    }

    // getters
    // metrograph getter
    public metroGraph getGraph()
    { // called further up, java compiles all at once
        return graph;
    }

}