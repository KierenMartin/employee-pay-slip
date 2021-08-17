
package com.kierenmartin.payslip.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.kierenmartin.payslip.CONSTANTS;
import com.kierenmartin.payslip.model.TaxBracket;
import com.kierenmartin.payslip.model.TaxBrackets;

public class BracketCalculator {
    
    private TaxBrackets taxBrackets;

    public BracketCalculator(TaxBrackets _taxBrackets){
        taxBrackets = _taxBrackets;
    }

    /**
     * Given a particular annual salary value, this function returns the correct tax bracket object for that salary.
     * @param inputSalary is the annual salary earned, as a BigDecimal value.
     * @return The associated tax bracket object.
     */
    public TaxBracket getBracket(BigDecimal inputSalary){
        List<TaxBracket> bracketsList = taxBrackets.getTaxBrackets();

        for(int i = 0; i < bracketsList.size(); i++){
            if(inputSalary.compareTo(bracketsList.get(i).getBracketEnd()) < 1){
                return bracketsList.get(i);
            }
        }
        // Fallback to returning the last element.
        // This will happen if we provide a salary value greater than the biggest tax bracket.
        // This is fine, since the biggest bracket applies to all values above a certain amount anyway.
        return bracketsList.get(bracketsList.size() - 1);
    }

    /**
     * Calculates and returns taxable income, given an annual salary.
     * The tax bracket is automatically looked for and found.
     * @param inputSalary is the annual salary.
     * @return The taxable income, as a BigDecimal value.
     */
    public BigDecimal calculateTaxableIncome(BigDecimal inputSalary){
        TaxBracket bracket = getBracket(inputSalary);

        return inputSalary.subtract(bracket.getBracketStart()).multiply(bracket.getTaxPercentage()).add(bracket.getTaxFlat()).divide(new BigDecimal(12), CONSTANTS.DECIMALPRECISION, RoundingMode.HALF_UP); // Per month, not per year.
    }
}
