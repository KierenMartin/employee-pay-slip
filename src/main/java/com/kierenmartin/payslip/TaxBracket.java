
package com.kierenmartin.payslip;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class manages automatic calculation of taxable income.
 * Initialise it with the required information and it will give you a taxable income value
 * to retrieve later.
 */
public class TaxBracket {
    
    private BigDecimal income;
    private BigDecimal taxableIncome;

    private BigDecimal bracketStart;
    private BigDecimal bracketEnd;

    private BigDecimal taxFlat;
    private BigDecimal taxPercentage;

    /**
     * 
     * @param _income is the annual salary.
     * @param _bracketStart is the value at which this bracket begins. This is subtracted from 'income' to produce a portion that is then taxed by the taxPerecntage.
     * @param _bracketEnd is the value at which this bracket ends and the next begins. Remember that the check is (income <= bracketEnd)
     * @param _taxFlat is the flat amount of tax added on top of the percentage-based tax for this bracket.
     * @param _taxPercentage Where 1.00 is 100%, the tax percentage for this tax bracket.
     */
    public TaxBracket(BigDecimal _income, BigDecimal _bracketStart, BigDecimal _bracketEnd, BigDecimal _taxFlat, BigDecimal _taxPercentage){
        income = _income;
        bracketStart = _bracketStart;
        bracketEnd = _bracketEnd;
        taxFlat = _taxFlat;
        taxPercentage = _taxPercentage;
        calculateTaxableIncome();
    }

    // Getters
    public BigDecimal getBracketEnd(){
        return bracketEnd;
    }
    public BigDecimal getTaxableIncome(){
        return taxableIncome;
    }

    private void calculateTaxableIncome(){
        taxableIncome = income.subtract(bracketStart).multiply(taxPercentage).add(taxFlat).divide(new BigDecimal(12), CONSTANTS.DECIMALPRECISION, RoundingMode.HALF_UP); // Per month, not per year.
    }

}
