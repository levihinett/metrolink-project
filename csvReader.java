import java.util.*; // utilities, specifically for scanner
import java.io.*; // io, for bufferedreader, filereader, ioexception and input stream for later maybe

public class csvReader
{
    private List<List<String>> csv; // belongs to object, csvReader.csv

    public csvReader() // constructor, reads the csv
    {
        String currentLineColour = ""; // what colour is the line? will use later

        csv = new ArrayList<List<String>>(); // array list for the csv, creates storage we can reference as field
        try (BufferedReader br = new BufferedReader(new FileReader("Metrolink_times_linecolour(in).csv"))) 
        {
            // open the file and then auto closes it

            String line = ""; // becomes read line in loops

            while ((line = br.readLine()) != null) // read line and stop when no lines left
            {
                csv.add(parseLine(line)); // for each line, convert into list per comma and add to main list
            }
        } catch (IOException csvError)
        {
            System.out.println("Error reading CSV file.");
            csvError.printStackTrace
        }

    }

    public static boolean isValidStation(String station, ArrayList<String> stations) // is the station valid? user input + all stations
    {
        for (String validStation : stations) // go through station 1-by-1
        {
            if (validStation.equalsIgnoreCase(station)) // ignore capitals
            {
                return true;
            }
        }
        return false; // otherwise, return false
    }



    // user input method
    public static String[] metroQuery() // return string multiple so that we can have both scanned outputs
    {
        Scanner metroInput = new Scanner(System.in); // new scanner for input
        String metroStart; // declare so scope doesn't fuck shit up and delete it
        do
        {
            System.out.println("Enter starting destination: "); // print new line
            String metroStart = metroInput.nextLine();

            if (!isValidStation(metroStart,csv)) // if it isn't
            {
                System.out.println("First destination isn't valid: " + metroStart); // give the message if it isn't right, return to while loop
            }
        } while (!isValidStation(metroStart,csv)); // loop until it's right
        System.out.println("First destination is valid: " + metroStart); // okay, it's now right, next steps

        String metroEnd; // no fucking shit up scope!
        do
        {
            System.out.println("Enter ending destination: "); // print new line
            String metroEnd = metroInput.nextLine();

            if (!isValidStation(metroEnd,csv))
            {
                System.out.println("Last destination isn't valid: " + metroEnd);
            }
        } while (!isValidStation(metroEnd,csv)); // loop until it's right again
        System.out.println("Last destination is valid: " + metroEnd);

        return new String[]{metroStart, metroEnd}; // returns string with start and end, can use later in main
    }

}