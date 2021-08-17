
package com.kierenmartin.payslip.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.kierenmartin.payslip.CONSTANTS;
import com.kierenmartin.payslip.service.PaySlipCalculator;

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

    // Getters
    public Employee getEmployee(){
        return employee;
    }

    // Setters
    public void setEmployee(Employee _employee){
        employee = _employee;
    }
    public void setFromDate(String _fromDate){
        fromDate = _fromDate;
    }
    public void setToDate(String _toDate){
        toDate = _toDate;
    }
    public void setGrossIncome(int _grossIncome){
        grossIncome = _grossIncome;
    }
    public void setIncomeTax(int _incomeTax){
        incomeTax = _incomeTax;
    }
    public void setSuperannuation(int _superannuation){
        superannuation = _superannuation;
    }
    public void setNetIncome(int _netIncome){
        netIncome = _netIncome;
    }

}
