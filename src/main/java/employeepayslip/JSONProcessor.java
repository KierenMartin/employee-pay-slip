
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
                So, what do we do here?
                - For each object in the array:
                    - Get the JSONObject at index i.
                    - Create a new Employee object and load data into it from that object.
                    - Create a new PaySlip object and load the employee object into it.
                    - Load the pay slip object into an array, which will be outputted later as JSON using GSON.
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
