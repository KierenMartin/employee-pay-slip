
package com.kierenmartin.payslip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kierenmartin.payslip.model.TaxBrackets;
import com.kierenmartin.payslip.service.JSONProcessor;

/**
 * Use the EmployeePaySlip program by feeding in a json string as the first argument.
 * An example you can run from your own computer (windows) is something like
 * curl localhost:8080 -X POST -d {json-goes-here} -H "Content-type text/plain"
 * Of course, with valid JSON.
 */
@RestController // < Ready for use by Spring MVC to handle web requests.
public class EmployeePaySlip {
    
    @Autowired
	private TaxBrackets taxBrackets;

    // Used for making sure tests are working, but is mostly just me testing that this works.
    // If somehow the ping test fails, something is TERRIBLY wrong.
	@GetMapping(value = "/ping")
	public String ping() {
        return "Pong!";
    }

	@PostMapping(value = "/", consumes = "text/plain")
	public String getPlaintextPost(@RequestBody String string) {
        // Use JSONProcessor object
        System.out.println("Processing string: " + string);
        JSONProcessor processor = new JSONProcessor(taxBrackets);
        return processor.process(string); // Return result of processing...
    }

}
