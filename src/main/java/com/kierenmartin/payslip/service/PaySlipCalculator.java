
package com.kierenmartin.payslip.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.kierenmartin.payslip.CONSTANTS;
import com.kierenmartin.payslip.model.PaySlip;
import com.kierenmartin.payslip.model.TaxBrackets;

public class PaySlipCalculator {
    
    private PaySlip paySlip;
    private TaxBrackets taxBrackets;

    // Calculate Date
    // Calendar assumes the year is the current year. Month is -1 since Calendar starts at 0... we read January as 1.
    private Calendar calendar;
    private String monthName;

    public PaySlipCalculator(PaySlip _paySlip, TaxBrackets _taxBrackets){
        paySlip = _paySlip;
        taxBrackets = _taxBrackets;

        calendar = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), paySlip.getEmployee().getPaymentMonth() - 1, 1);
        monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
    }

    private String calculateFromDate(){
        return "01 " + monthName;
    }

    private String calculateToDate() {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + " " + monthName;
    }

    private BigDecimal calculateGrossIncome() {
        return BigDecimal.valueOf(paySlip.getEmployee().getAnnualSalary()).divide(new BigDecimal(12), CONSTANTS.DECIMALPRECISION, RoundingMode.HALF_UP);
    }

    /**
     * Calculates taxable income, based off of the income value of the employee in this object's attached payslip object.
     * @return The taxable income (incomeTax).
     */
    private BigDecimal calculateTaxableIncome(){
        BracketCalculator calc = new BracketCalculator(taxBrackets);
        return calc.calculateTaxableIncome(new BigDecimal(paySlip.getEmployee().getAnnualSalary()));
    }

    private BigDecimal calculateNetIncome(BigDecimal unroundGrossIncome, BigDecimal unroundIncomeTax) {
        return unroundGrossIncome.subtract(unroundIncomeTax);
    }

    private BigDecimal calculateSuperannuation(BigDecimal unroundGrossIncome) {
        return unroundGrossIncome.multiply(BigDecimal.valueOf(paySlip.getEmployee().getSuperRate()));
    }

    public void updatePaySlipData(){
        BigDecimal unrounded_grossIncome = calculateGrossIncome();
        BigDecimal unrounded_incomeTax = calculateTaxableIncome();
        BigDecimal unrounded_netIncome = calculateNetIncome(unrounded_grossIncome, unrounded_incomeTax);
        BigDecimal unrounded_superannuation = calculateSuperannuation(unrounded_grossIncome);
        
        // Save rounded values as integers.
        int rounded_grossIncome = unrounded_grossIncome.setScale(0, RoundingMode.HALF_UP).intValue();
        int rounded_incomeTax = unrounded_incomeTax.setScale(0, RoundingMode.HALF_UP).intValue();
        int rounded_netIncome = unrounded_netIncome.setScale(0, RoundingMode.HALF_UP).intValue();
        int rounded_superannuation = unrounded_superannuation.setScale(0, RoundingMode.HALF_UP).intValue();

        paySlip.setFromDate(calculateFromDate());
        paySlip.setToDate(calculateToDate());
        paySlip.setGrossIncome(rounded_grossIncome);
        paySlip.setIncomeTax(rounded_incomeTax);
        paySlip.setNetIncome(rounded_netIncome);
        paySlip.setSuperannuation(rounded_superannuation);
    }

}
