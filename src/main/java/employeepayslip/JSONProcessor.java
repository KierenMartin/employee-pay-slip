
package employeepayslip;

import org.json.JSONArray;

/**
 * This class will handle processing and retrieving information from the JSON given
 * as input to the program, 
 */
public class JSONProcessor {

    public String process(String input){
        JSONArray jArray = new JSONArray(input);

        // TODO
        // - REMOVE DEBUG MESSAGES
        // - Implement JSON processing
        // - Implement processing of tax/income values, etc.
        // - Implement JSON output
        System.out.println("[JSONProcessor] JSON String inputted: \n" + input);
        System.out.println("[JSONProcessor] JSONArray Object is " + jArray);
        System.out.println("[JSONProcessor] Unloading JSONArray contents...");

        // Get all JSON objects from the array
        // Use [json object here].getString(String key) to retrieve values.

        for(int i = 0; i < jArray.length(); i++){
            System.out.println("Object " + i + ": " + jArray.getJSONObject(i));
        }

        return "...nothing left to do!";
    }

}

/*
Some code snippets that I found helpful:

-- https://stackoverflow.com/questions/2591098/how-to-parse-json-in-java
import org.json.*;

String jsonString = ... ; //assign your JSON String here
JSONObject obj = new JSONObject(jsonString);
String pageName = obj.getJSONObject("pageInfo").getString("pageName");

JSONArray arr = obj.getJSONArray("posts"); // notice that `"posts": [...]`
for (int i = 0; i < arr.length(); i++)
{
    String post_id = arr.getJSONObject(i).getString("post_id");
    ......
}

-- http://theoryapp.com/parse-json-in-java/

Thanks stack overflow, I'll take it from here~!
*/