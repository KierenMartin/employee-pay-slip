
package com.kierenmartin.payslip.model;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Stores a list of TaxBracket objects.
 * Data is derived from the application.yml file, so if you want to
 * add or remove tax brackets, look there!
 */

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("tax-bracket-list")
public class TaxBrackets {
    
    private List<TaxBracket> taxBrackets;

    // Getters
    public List<TaxBracket> getTaxBrackets(){
        return taxBrackets;
    }

    // Setters
    public void setTaxBrackets(List<TaxBracket> taxBrackets){
        this.taxBrackets = taxBrackets;
    }
}
