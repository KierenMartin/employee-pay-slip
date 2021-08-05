
package employeepayslip;

public class CONSTANTS {
    private CONSTANTS(){} // No need to initialise this, ever.

    // We can change the decimal precision to get more accurate results on divisions.
    // After all, we're dealing with money, so we can't afford to lose track of it.
    // Why is this needed? Well, without it we get indefinitely-long numbers quite often in our divisions,
    // and BigDecimal will throw an error if that happens.
    public static final int DECIMALPRECISION = 16;
    
}
