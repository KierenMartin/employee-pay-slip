
package employeepayslip;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * The PaySlip class deals with holding output information.
 * This includes an employee object, along with date, grossIncome, incomeTax, superannuation and netIncome
 */
public class PaySlip extends JSONable {

    // We can change the decimal precision to get more accurate results on divisions.
    // After all, we're dealing with money, so we can't afford to lose track of it.
    private final static int decimalPrecision = 16;

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
            BigDecimal unrounded_grossIncome = BigDecimal.valueOf(employee.getAnnualSalary()).divide(new BigDecimal(12), decimalPrecision, RoundingMode.HALF_UP);
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

    private BigDecimal getTaxableIncome(BigDecimal income){
        BigDecimal taxFlat;
        BigDecimal taxStart;
        BigDecimal taxPercentage;

        if(income.compareTo(new BigDecimal(18200)) < 1){
            // No tax!
            taxFlat = new BigDecimal(0);
            taxStart = new BigDecimal(0);
            taxPercentage = new BigDecimal(0);
        }else
        if(income.compareTo(new BigDecimal(37000)) < 1){
            taxFlat = new BigDecimal(0);
            taxStart = new BigDecimal(18200);
            taxPercentage = new BigDecimal(0.19); // 19c/$
        }else
        if(income.compareTo(new BigDecimal(87000)) < 1){
            taxFlat = new BigDecimal(3572);
            taxStart = new BigDecimal(37000);
            taxPercentage = new BigDecimal(0.325); // 32.5c/$
        }else
        if(income.compareTo(new BigDecimal(180000)) < 1){
            taxFlat = new BigDecimal(19822);
            taxStart = new BigDecimal(87000);
            taxPercentage = new BigDecimal(0.37); // 37c/$
        }else{
            taxFlat = new BigDecimal(54232);
            taxStart = new BigDecimal(180000);
            taxPercentage = new BigDecimal(0.45); // 45c/$
        }

        return income.subtract(taxStart).multiply(taxPercentage).add(taxFlat).divide(new BigDecimal(12), decimalPrecision, RoundingMode.HALF_UP); // Per month, not per year.
    }

}
