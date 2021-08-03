
package employeepayslip;

import org.json.JSONObject;

/**
 * The PaySlip class deals with holding output information.
 * This includes an employee object, along with date, grossIncome, incomeTax, superannuation and netIncome
 */
public class PaySlip implements JSONable {

    private Employee employee;
    private String fromDate;
    private String toDate;
    private int grossIncome;
    private int superannuation;
    private int netIncome;

    public PaySlip(Employee _employee, String _fromDate, String _toDate, int _grossIncome, int _superannuation, int _netIncome){
        employee = _employee;
        fromDate = _fromDate;
        toDate = _toDate;
        grossIncome = _grossIncome;
        superannuation = _superannuation;
        netIncome = _netIncome;
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("employee", employee.toJson());
        obj.put("fromDate", fromDate);
        obj.put("toDate", toDate);
        obj.put("grossIncome", grossIncome);
        obj.put("superannuation", superannuation);
        obj.put("netIncome", netIncome);

        return obj;
    }

    @Override
    public void fromJson(JSONObject sourceObject) {
        employee.fromJson(sourceObject.getJSONObject("employee")); // Convert the employee using its own fromJson method.
        fromDate = sourceObject.getString("fromDate");
        toDate = sourceObject.getString("toDate");
        grossIncome = Integer.parseInt(sourceObject.getString("grossIncome"));
        superannuation = Integer.parseInt(sourceObject.getString("superannuation"));
        netIncome = Integer.parseInt(sourceObject.getString("netIncome"));
    }
    
}
