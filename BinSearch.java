import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
* This program calculates amount of mean median and mode.
*
* @author  Alex De Meo
* @version 1.0
* @since   2023/02/08
*/

public final class BinSearch {
    /**
    * This is a private constructor used to satisfy the
    * style checker.
    *
    * @exception IllegalStateException Utility Class
     */
    private BinSearch() {
        throw new IllegalStateException("Utility Class");
    }
    /**
    * This is the main method.
    *
    * @param args Unused
    * @throws Exception if something goes wrong
    */

    public static void main(String[] args) throws Exception {
        // Initializing variables
        String line;
        final String err = "Error";
        try {
            // new file object
            final File input = new File("input.txt");

            // Creating the writer
            final FileWriter writer = new FileWriter("output.txt");

            try {
                // Creating the scanner.
                final Scanner scanner = new Scanner(input);

                // ArrayList to hold the lines
                final ArrayList<String> lines = new ArrayList<>();
                final ArrayList<String> numberSearch = new ArrayList<>();
                // creating counter
                int counter = 0;
                // Getting the input from the first file
                while (scanner.hasNextLine()) {
                    if (counter % 2 == 0) {
                        // getting next line and putting it in the interim list
                        line = scanner.nextLine();
                        
                        lines.add(line);
                    } else {
                        line = scanner.nextLine();
                        numberSearch.add(line);
                    }
                    
                    counter++;
                }

                // taking the data and manipulating it with a function
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).length() == 0) {
                        // writing to file
                        writer.write("ERROR: Empty line in one of the files\n");
                    } else {
                        
                        String[] numStr = lines.get(i).split(" ");
                        int[] numbers = new int[numStr.length - 1];
                        
                        for (int j = 0; j < numStr.length - 1; j++) {
                            int num = Integer.parseInt(numStr[j]);
                            numbers[j] = num;
                        }
                        Arrays.sort(numbers);
                        final int index = recBinSearch(
                                numbers, Integer.parseInt(numberSearch.get(i)));
                        if (index == -1) {
                            writer.write("ERROR: " + numberSearch.get(i)
                            + " does not exist in the line\n");
                        } else {
                            writer.write("The first time we came across "
                            + numberSearch.get(i) + " is at index " + index
                            + "\n");
                        }
                    }
                }
            } catch (IOException error) {
                System.out.println(err + error.getMessage());
            }
            // closes the writer
            writer.close();
        } catch (IOException error) {
            System.out.println(err + error.getMessage());
        }
    }
    /**
    * This is the method reverseRecs the string.
    *
    * @param numbers This is the array
    * @param num this is the number to search for
    * @param start this is the beginning of the array
    * @param end This is the length of the array
    * @return the powered value
    **/

    public static int recBinSearch(int[] numbers, int num,
        int start, int end) {
        // error checking here
        if (start > end) {

            return -1;
        }
        System.out.println(start + " " + end);
        // setting the midpoint
        final int midpoint = (start + (end))/ 2;
        
        // comparing the midpoint to different parts of the array.
        if (numbers[midpoint] == num) {
            System.out.println(numbers[midpoint]);
            return midpoint;
        } else if (num < numbers[midpoint]) {
            return recBinSearch(numbers, num, start, midpoint - 1);
        } else {
            return recBinSearch(numbers, num, midpoint + 1, end);
        }
    }

    public static int recBinSearch(int[] numbers, int num) {
        return recBinSearch(numbers, num, 0, numbers.length - 1);
    }
}
