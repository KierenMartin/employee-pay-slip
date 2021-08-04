
package employeepayslip;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class will handle processing and retrieving information from the JSON given
 * as input to the program, 
 */
public class JSONProcessor {

    public String process(String input){
        JSONArray inArray = new JSONArray(input);
        List<Object> outArray = new ArrayList<Object>();

        // Get all JSON objects from the array
        // Use [json object here].getDATATYPE(String key) to retrieve values.
        for(int i = 0; i < inArray.length(); i++){
            /*
                So, what do we need to do here?
                - For each object in the array:
                    - Create a new Employee object and load data into it.
                    - Create a new PaySlip object and load the employee object into it.
                    - Output the PaySlip object as a JSON string.
            */
            // Get this object. It'll be an employee's data.
            JSONObject jObj = inArray.getJSONObject(i);

            // Create employee and pay slip, store in output array.
            Employee newEmployee = new Employee(jObj);
            PaySlip newPaySlip = new PaySlip(newEmployee);
            outArray.add(newPaySlip);
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(outArray, outArray.getClass());
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