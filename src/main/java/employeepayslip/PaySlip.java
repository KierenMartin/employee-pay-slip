
package main.java.employeepayslip;

import java.io.*;
import java.util.Scanner;

/**
 * The main class.
 */
class PaySlip {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Debug print all args
        for(int i = 0; i < args.length; i++){
            System.out.println(args[i]);
        }

        if(args.length > 0){
            // Attempt reading from file
            System.out.println("[INFO] Args exist");
            try{
                File inputFile = new File(args[0]);
                Scanner scanner = new Scanner(inputFile);

                // Print contents of file to console.
                System.out.println("[INFO] Scanning for file content...");
                while(scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    System.out.println(line);
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
