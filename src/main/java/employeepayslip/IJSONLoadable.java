
package employeepayslip;

import org.json.JSONObject;

/**
 * An interface which defines an object as supporting the ability to load relevant data from
 * a JSON string.
 */
public interface IJSONLoadable {
    /**
     * Loads data from the JSONObject given.
     */
    public void fromJson(JSONObject sourceObject);
}
