package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
//import java.util.Comparator;

public class Sorting {
    public static File inputFile;
    public static File outputFile;
    public static ArrayList<Double> inputArray; 
    public static int numberOfLoops;

    public static void main(String [] args) throws IOException {
        inputFile = new File(args[0]);
        outputFile = new File(args[1]);
        numberOfLoops = Integer.parseInt(args[2]);
        ArrayList<Double> outputArray = createInputArray();
        Object [] finalOutput = new Object[inputArray.size()];
        long startTime = System.currentTimeMillis();
        for(int loop = 0; loop < numberOfLoops; loop++){
            finalOutput = sortList(outputArray);            
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime-startTime) + " milliseconds");
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        DecimalFormat formatter = new DecimalFormat("0.00000000");
        
        for (int i = 0; i < finalOutput.length; i++) {
            writer.write(formatter.format(finalOutput[i]));
            writer.newLine();
        }
        //System.out.println(Arrays.toString(finalOutput));
        writer.close();
    }

    public static ArrayList<Double> createInputArray() {
        inputArray = new ArrayList<Double>(50000);
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            String[] temp;
            while (line != null) {
                temp = line.split(" ");
                for (int i = 0; i < temp.length; i++) {
                    inputArray.add(Double.parseDouble(temp[i]));
                }
                line = reader.readLine();                
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file you specified does not exist.");
        } catch (IOException e) {
            System.out.println("The file you specified does not fit the required format.");
        }
        
        ArrayList<Double> dest = new ArrayList<Double>(inputArray.size());
        for (double i = 0; i < inputArray.size(); i++) {  
            dest.add(i);  
        }  
        return dest;
    }

    public static <T> Object[] sortList(ArrayList<Double> outputArrayList) {
        Collections.copy(outputArrayList, inputArray);
        //Collections.sort(outputArray);
        Object [] outputArray = outputArrayList.toArray();
        TimSort.sort(outputArray, null);        
        return outputArray;
    }
}
