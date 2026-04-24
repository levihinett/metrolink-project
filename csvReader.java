import java.util.*; // utilities, specifically for scanner
import java.io.*; // io, for bufferedreader, filereader, ioexception and input stream for later maybe

public class csvReader
{
    private List<List<String>> csv; // belongs to object, csvReader.csv

    public csvReader() // constructor, reads the csv
    {
        String currentLineColour = ""; // what colour is the line? will use later

        csv = new ArrayList<List<String>>(); // array list for the csv, creates storage we can reference as field
        try (BufferedReader br = new BufferedReader(new FileReader("Metrolink_times_linecolour(in).csv"))) // open the file and then auto closes it
        {
            String line = ""; // becomes read line in loops

            while ((line = br.readLine()) != null) // read line and stop when no lines left
            {
                csv.add(parseLine(line)); // for each line, convert into list per comma and add to main list
            }
        }

    }



    // user input method
    public static String[] metroQuery() // return string multiple so that we can have both scanned outputs
    {
        Scanner metroInput = new Scanner(System.in); // new scanner for input
        System.out.println("Enter starting destination: "); // print new line
        String metroStart = metroInput.nextLine();
        System.out.println("First destination is: " + metroStart);

        System.out.println("Enter ending destination: "); // print new line
        String metroEnd = metroInput.nextLine();
        System.out.println("Last destination is: " + metroEnd);

        return new String[]{metroStart, metroEnd}; // returns string with start and end, can use later in main
    }

}