
package main.java.employeepayslip;

import java.io.*;
import java.util.Scanner;

/**
 * The main class.
 */
class PaySlip {
    public static void main(String[] args) {
        // Prepare processor
        InputProcessor processor = new InputProcessor();

        if(args.length > 0){
            // Attempt reading from file
            try{
                File inputFile = new File(args[0]);
                Scanner scanner = new Scanner(inputFile);

                // For each line, get the processor to produce output to the console.
                // This could alternatively be delivered to a file for more practical applications...
                // but this isn't really a practical application!
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    System.out.println(processor.process(line)); // Print result to console.
                }
                
                scanner.close();
            } catch (FileNotFoundException e){
                System.out.println("File not found.");
                e.printStackTrace();
            }
        }else{
            System.out.println("[ERROR] Launch parameters were not specified. An input file path needs to be given.");
        }

    }
}
