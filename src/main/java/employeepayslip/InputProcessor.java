
package main.java.employeepayslip;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.HashMap;

/**
 * This class accepts input in a csv format for the employee pay slip application.
 * Its main function, process(), returns a csv-formatted string with the results!
 * From there, you can use the resulting string for whatever you please, or keep running
 * process() for every line in your input file to get more data.
 */
public class InputProcessor {
    
    // Map of Month to integer value based on Calendar values.
    private Map<String, Integer> monthMap = new HashMap<String, Integer>();

    public InputProcessor(){
        // This feels like a silly, unnecessary step, but I couldn't seem to find a way around it.
        monthMap.put("JANUARY", Calendar.JANUARY);
        monthMap.put("FEBRUARY", Calendar.FEBRUARY);
        monthMap.put("MARCH", Calendar.MARCH);
        monthMap.put("APRIL", Calendar.APRIL);
        monthMap.put("MAY", Calendar.MAY);
        monthMap.put("JUNE", Calendar.JUNE);
        monthMap.put("JULY", Calendar.JULY);
        monthMap.put("AUGUST", Calendar.AUGUST);
        monthMap.put("SEPTEMBER", Calendar.SEPTEMBER);
        monthMap.put("OCTOBER", Calendar.OCTOBER);
        monthMap.put("NOVEMBER", Calendar.NOVEMBER);
        monthMap.put("DECEMBER", Calendar.DECEMBER);
    }
    
    /**
     * Processes the given input string and attempts to calculate the correct pay slip.
     * @param input A csv-formatted string with pay slip input details.
     * Input should be in the following format: first name, last name, annual salary, super rate (%), payment start date.
     * @return A csv-formatted string with processed pay slip details, or an error message starting with [ERROR] if the action fails.
     * Output (if not an error) is presented in the following format: name, pay period, gross income, income tax, net income, super
     */
    public String process(String input){
        // Trim whitespace, split input.
        input = input.trim();
        String[] splitInput = input.split(",");

        if(splitInput.length >= 5){
            String firstName = splitInput[0];
            String lastName = splitInput[1];
            String paymentDate = splitInput[4];
            
            // We need to get the month out of the payment date string. Split by whitespace...
            String[] splitPaymentDate = paymentDate.split(" ");

            try{
                int annualSalary = Integer.valueOf(splitInput[2]); // < Can throw NumberFormatException

                // Trim off the % at the end of the super rate. It is assumed the format is respected and the % symbol is present.
                float superRate = Integer.valueOf(splitInput[3].substring(0, splitInput[3].length() - 1)) / 100f;

                // The first value will be a number, the second the month.
                if(splitPaymentDate.length >= 2 && splitPaymentDate[1].length() >= 2){
                    try{
                        int detectedMonth = monthMap.get(splitPaymentDate[1].toUpperCase()); // < can throw NullPointerException

                        // This is used later to make sure we have the correct amount of days in the given month, this year.
                        // Leap years, February... all that fun stuff.
                        Calendar calendar = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), detectedMonth, 1);
                        
                        // Calculations (not rounded)
                        float grossIncome = annualSalary / 12;
                        float incomeTax = getTaxableIncome(annualSalary);
                        float netIncome = grossIncome - incomeTax;
                        float superAmount = grossIncome * superRate;

                        // We should have everything we need now. It's time to construct the output string.
                        // It IS possible to just do this in one big multi-liner, but I don't like that.
                        String result = firstName + " " + lastName; // Name
                        result += ",";
                        result += "01 " + splitPaymentDate[1] + " - " + 
                            calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + " " + splitPaymentDate[1]; // Pay Period
                        result += ",";
                        result += Math.round(grossIncome); // Gross Income
                        result += ",";
                        result += Math.round(incomeTax); // Income Tax
                        result += ",";
                        result += Math.round(netIncome); // Net Income
                        result += ",";
                        result += Math.round(superAmount); // Super

                        return result;
                    } catch(NullPointerException e){
                        return "[ERROR] The given month " + splitPaymentDate[1] + " is not recognised.";
                    }
                }
                return "[ERROR] Payment date is malformed. Please make sure to follow the desired format, such as '01 March - 31 March'";
            } catch(NumberFormatException e){
                return "[ERROR] Malformed input. Please check the format of your input file.";
            }
        }
        return "[ERROR] Input processor expected 5 values, got " + splitInput.length;
    }

    /**
     * With the help of the TaxData class, this function does the calculations required to determine
     * how much of the given income value is taxable.
     * @param income The income earned before super, which will be taxed.
     * @return The amount of tax, as a floating point value.
     */
    private float getTaxableIncome(float income){
        TaxData taxObject = new TaxData();
        if(income <= 18200f){
            // No tax!
            taxObject.taxFlat = 0;
            taxObject.taxStart = 0;
            taxObject.taxPercentage = 0f;
        }else
        if(income <= 37000f){
            taxObject.taxFlat = 0;
            taxObject.taxStart = 18200;
            taxObject.taxPercentage = 0.19f; // 19c
        }else
        if(income <= 87000f){
            taxObject.taxFlat = 3572;
            taxObject.taxStart = 37000;
            taxObject.taxPercentage = 0.325f; // 19c
        }else
        if(income <= 180000f){
            taxObject.taxFlat = 19822;
            taxObject.taxStart = 87000;
            taxObject.taxPercentage = 0.37f; // 19c
        }else{
            taxObject.taxFlat = 54232;
            taxObject.taxStart = 180000;
            taxObject.taxPercentage = 0.45f; // 19c
        }

        return taxObject.calculateTaxTotal(income);
    }

}

/**
 * A class for tax-related information.
 * Change its flat, start and percentage values, and it will tell you what
 * the total amount of tax owed is when running its calculateTaxTotal function.
 */
class TaxData {
    public int taxFlat; // A flat amount of tax added to the result.
    public int taxStart; // The value beyond which tax is applicable by percentage.
    public float taxPercentage; // % for each $1 over taxStart

    public float calculateTaxTotal(float income){
        return (((income - taxStart) * taxPercentage) + taxFlat) / 12; // Per month, not per year.
    }
}
