
package employeepayslip;

import org.json.JSONObject;
/**
 * An interface class for classes that support the ability to write themselves as JSONObjects,
 * while also being able to read in a JSONObject and retrieve the data from that.
 */
public interface JSONable {
    /**
     * 
     * @return A JSONObject representing this object.
     */
    public JSONObject toJson();
    /**
     * Loads data from the JSONObject given.
     */
    public void fromJson(JSONObject sourceObject);
}
