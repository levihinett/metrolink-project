public class csvReader
{
    public static String metroQuery()
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