
package com.kierenmartin.payslip;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores a list of TaxBracket objects.
 * Other objects can construct this object and use its main function to return the correct
 * TaxBracket object for the previously-given annual salary value!
 * If you're just looking for the taxableIncome value and don't need other TaxBracket data, you can use
 *  myTaxBracketsObject.getBracket().getTaxableIncome();
 */
public class TaxBrackets {
    
    private BigDecimal storedSalary;
    List<TaxBracket> brackets = new ArrayList<TaxBracket>();

    public TaxBrackets(BigDecimal annualSalary){
        storedSalary = annualSalary;

        // If you want to add new tax brackets into the program, you've come to the right place.
        // Format is:               annualSalary, bracket start,     bracket end,           flat tax,          percentage tax
        brackets.add(new TaxBracket(annualSalary, new BigDecimal(0), new BigDecimal(18200), new BigDecimal(0), new BigDecimal(0)));
        brackets.add(new TaxBracket(annualSalary, new BigDecimal(18200), new BigDecimal(37000), new BigDecimal(0), new BigDecimal(0.19)));
        brackets.add(new TaxBracket(annualSalary, new BigDecimal(37000), new BigDecimal(87000), new BigDecimal(3572), new BigDecimal(0.325)));
        brackets.add(new TaxBracket(annualSalary, new BigDecimal(87000), new BigDecimal(180000), new BigDecimal(19822), new BigDecimal(0.37)));
        brackets.add(new TaxBracket(annualSalary, new BigDecimal(180000), new BigDecimal(Integer.MAX_VALUE), new BigDecimal(54232), new BigDecimal(0.45)));
    }

    /**
     * After creating the TaxBrackets object with the desired salary, you can use this function to get the associated TaxBracket object.
     * @return A TaxBracket object matching the desired tax bracket.
     */
    public TaxBracket getBracket(){
        for(int i = 0; i < brackets.size(); i++){
            if(storedSalary.compareTo(brackets.get(i).getBracketEnd()) < 1){
                return brackets.get(i);
            }
        }
        // Fallback to returning the last element.
        return brackets.get(brackets.size() - 1);
    }

}
