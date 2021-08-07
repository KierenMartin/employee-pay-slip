
package com.kierenmartin.payslip;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Use the EmployeePaySlip program by feeding in a json string as the first argument.
 * An example you can run from your own computer (windows) is something like
 * curl localhost:8080 -X POST -d {json-goes-here} -H "Content-type text/plain"
 * Of course, with valid JSON.
 */
@RestController // < Ready for use by Spring MVC to handle web requests.
public class EmployeePaySlip {
    
	@PostMapping(value = "/", consumes = "text/plain")
	public String getPlaintextPost(@RequestBody String string) {
        // Use JSONProcessor object
        System.out.println("Processing string: " + string);
        JSONProcessor processor = new JSONProcessor();
        return processor.process(string); // Return result of processing...
    }

}
