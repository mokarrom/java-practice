import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

//https://www.geeksforgeeks.org/maximum-difference-between-two-elements/

class Result {

    /*
     * Complete the 'maxDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int maxDifference(List<Integer> arr) {
    // Write your code here

        int numOfItems = arr.size();
        
        if (numOfItems < 2) {
            return -1;
        }
        
        int maxDifference = -1;
        Integer[] items = arr.toArray(new Integer[numOfItems]);
        int maxRightItem = items[numOfItems - 1];  

        for (int i = numOfItems - 2; i >= 0; i--) { 
            if (items[i] > maxRightItem) {
                maxRightItem = items[i]; 
            }             
            else { 
                int diff = maxRightItem - items[i]; 
                if (diff > maxDifference) { 
                    maxDifference = diff; 
                } 
            } 
        } 
        
        if (maxDifference < 1) {
            maxDifference = -1;
        }
        
        return maxDifference; 
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, arrCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.maxDifference(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
