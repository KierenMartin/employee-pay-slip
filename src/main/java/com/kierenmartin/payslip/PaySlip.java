
package com.kierenmartin.payslip;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * The PaySlip class deals with holding output information.
 * This includes an employee object, along with date, grossIncome, incomeTax, superannuation and netIncome.
 * The PaySlip will auto-calculate these values if given a new Employee object using either its constructor
 * or setEmployee setter.
 */
public class PaySlip extends JSONable {
    // Your IDE will probably complain about these being 'unused'.
    // GSON will use these, so don't remove them.
    private Employee employee;
    private String fromDate = "UNDEFINED";
    private String toDate = "UNDEFINED";
    private int grossIncome = -1;
    private int incomeTax = -1;
    private int superannuation = -1;
    private int netIncome = -1;

    public PaySlip(Employee _employee){
        setEmployee(_employee);
    }

    // Setters
    public void setEmployee(Employee _employee){
        employee = _employee;
        recalculateEmployeeData();
    }

    /**
     * Calculates date, income, tax, etc. from the 'employee' variable stored in this
     * PaySlip object.
     */
    private void recalculateEmployeeData(){
        if(employee != null){
            // Calculate Date
            // Calendar assumes the year is the current year. Month is -1 since Calendar starts at 0... we read January as 1.
            Calendar calendar = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), employee.getPaymentMonth() - 1, 1);
            String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());

            fromDate = "01 " + monthName;
            toDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + " " + monthName;

            // Calculate incomes and taxes
            BigDecimal unrounded_grossIncome = BigDecimal.valueOf(employee.getAnnualSalary()).divide(new BigDecimal(12), CONSTANTS.DECIMALPRECISION, RoundingMode.HALF_UP);
            BigDecimal unrounded_incomeTax = getTaxableIncome(new BigDecimal(employee.getAnnualSalary()));
            BigDecimal unrounded_netIncome = unrounded_grossIncome.subtract(unrounded_incomeTax);
            BigDecimal unrounded_superannuation = unrounded_grossIncome.multiply(BigDecimal.valueOf(employee.getSuperRate()));
            
            // Save rounded values as integers.
            grossIncome = unrounded_grossIncome.setScale(0, RoundingMode.HALF_UP).intValue();
            incomeTax = unrounded_incomeTax.setScale(0, RoundingMode.HALF_UP).intValue();
            netIncome = unrounded_netIncome.setScale(0, RoundingMode.HALF_UP).intValue();
            superannuation = unrounded_superannuation.setScale(0, RoundingMode.HALF_UP).intValue();
        }
    }

    /**
     * Private function that calculates taxable income, based off of the income value given.
     * @param income The income of the employee
     * @return The taxable income (incomeTax).
     */
    private BigDecimal getTaxableIncome(BigDecimal income){
        TaxBrackets brackets = new TaxBrackets(income);
        return brackets.getBracket().getTaxableIncome();
    }

}
