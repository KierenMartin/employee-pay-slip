
package employeepayslip;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A parent class for classes that support the ability to write themselves as JSONObjects,
 */
public class JSONable {
    /**
     * 
     * @return A String representing this object as JSON.
     */
    public String toJson() {
        // Gson with pretty indentation setting
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this, this.getClass());
    }
}
