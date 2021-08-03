
package employeepayslip;

import java.io.*;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The main class.
 * Use the EmployeePaySlip program by feeding in a json file as the first argument.
 * If the file is valid, it will read each line, producing an output for each line read.
 * NOTE: USING A FILE IS CURRENTLY TEMPORARY. THE PROGRAM WILL SUPPORT JSON STRING INPUT INSTEAD LATER.
 */
class EmployeePaySlip {
    public static void main(String[] args) {
        
        // Prepare JSON processor
        JSONProcessor processor = new JSONProcessor();

        if(args.length > 0){
            // Attempt reading from file
            try{
                String jsonString = readFileAsString(args[0]);
                System.out.println(processor.process(jsonString)); // Print result to console.
            } catch (IOException e){
                System.out.println("[ERROR] Failed to read file. Is the file path correct?");
                e.printStackTrace();
            }
        }else{
            System.out.println("[ERROR] Launch parameters were not specified. An input file path must be given.");
        }

    }
    // From https://devqa.io/java-read-json-file-as-string/
    // Temporary. Used to read from files. Used for testing before deployment.
    private static String readFileAsString(String file) throws IOException
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
