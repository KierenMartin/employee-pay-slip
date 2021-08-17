
package com.kierenmartin.payslip.model;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * This class manages automatic calculation of taxable income.
 * The application.yml file together with the TaxBrackets class help
 * to fill these TaxBracket objects in with relevant information.
 */
@ConstructorBinding
public class TaxBracket {
    
    private final BigDecimal bracketStart;
    private final BigDecimal bracketEnd;
    private final BigDecimal taxFlat;
    private final BigDecimal taxPercentage;

    /**
     * @param _bracketStart is the value at which this bracket begins. This is subtracted from 'income' to produce a portion that is then taxed by the taxPerecntage.
     * @param _bracketEnd is the value at which this bracket ends and the next begins. Remember that the check is (income <= bracketEnd)
     * @param _taxFlat is the flat amount of tax added on top of the percentage-based tax for this bracket.
     * @param _taxPercentage Where 1.00 is 100%, the tax percentage for this tax bracket.
     */
    public TaxBracket(BigDecimal bracketStart, BigDecimal bracketEnd, BigDecimal taxFlat, BigDecimal taxPercentage){
        this.bracketStart = bracketStart;
        this.bracketEnd = bracketEnd;
        this.taxFlat = taxFlat;
        this.taxPercentage = taxPercentage;

        System.out.println("[TaxBracket] Created.");
    }

    // Getters
    public BigDecimal getBracketStart(){
        return bracketStart;
    }
    public BigDecimal getBracketEnd(){
        return bracketEnd;
    }
    public BigDecimal getTaxFlat(){
        return taxFlat;
    }
    public BigDecimal getTaxPercentage(){
        return taxPercentage;
    }

}
