
package com.kierenmartin.payslip;

import org.json.JSONObject;

/**
 * The Employee class holds the data for a single employee's pay information.
 * This data can be loaded from a JSONObject if needed.
 */
public class Employee extends JSONable implements IJSONLoadable {
    
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
    public Employee(JSONObject json){
        fromJson(json);
    }

    // Getters
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getAnnualSalary(){
        return annualSalary;
    }
    public int getPaymentMonth(){
        return paymentMonth;
    }
    public float getSuperRate(){
        return superRate;
    }

    @Override
    public void fromJson(JSONObject sourceObject) {
        firstName = sourceObject.getString("firstName");
        lastName = sourceObject.getString("lastName");
        annualSalary = sourceObject.getInt("annualSalary");
        paymentMonth = sourceObject.getInt("paymentMonth");
        superRate = sourceObject.getFloat("superRate");
    }
    
}
