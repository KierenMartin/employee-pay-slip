
package employeepayslip;

import org.json.JSONObject;

/**
 * The Employee class holds the data for a single employee's pay information.
 */
public class Employee implements JSONable {
    
    private String firstName;
    private String lastName;
    private int annualSalary;
    /** Note: 1 = January, 12 = December */
    private int paymentMonth;
    private float superRate;
    
    public Employee(String _firstName, String _lastName, int _annualSalary, int _paymentMonth, float _superRate){
        firstName = _firstName;
        lastName = _lastName;
        annualSalary = _annualSalary;
        paymentMonth = _paymentMonth;
        superRate = _superRate;
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("firstName", firstName);
        obj.put("lastName", lastName);
        obj.put("annualSalary", annualSalary);
        obj.put("paymentMonth", paymentMonth);
        obj.put("superRate", superRate);

        return obj;
    }

    @Override
    public void fromJson(JSONObject sourceObject) {
        firstName = sourceObject.getString("firstName");
        lastName = sourceObject.getString("lastName");
        annualSalary = Integer.parseInt(sourceObject.getString("annualSalary"));
        paymentMonth = Integer.parseInt(sourceObject.getString("paymentMonth"));
        superRate = Float.parseFloat(sourceObject.getString("superRate"));
    }
    
}
