
package com.kierenmartin.payslip;

import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc // Can instead use @WebMvcTest([optional] ControllerName.class) to only initialise the web layer.
public class ProcessorTests {

	@Autowired
	private MockMvc mock;

	private String exampleJson = "[{\"firstName\":\"David\",\"lastName\":\"Rudd\",\"annualSalary\":60050,\"paymentMonth\":1,\"superRate\":0.09}]";

	/**
	 * Sanity check. Ping should always return Pong!
	 * @throws Exception
	 */
	@Test
	public void pingTest() throws Exception {
		mock.perform(get("/ping"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Pong!")));
	}

	/**
	 * Test that the output field 'employee' properly exists.
	 * @throws Exception
	 */
	@Test
	public void jsonFinalExistenceTest() throws Exception {
		mock.perform(
			post("/")
			.content(exampleJson)
			.contentType(MediaType.TEXT_PLAIN)
			.accept(MediaType.TEXT_PLAIN))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].employee").exists());
	}

	/**
	 * Test that the name of the employee in the output string is preserved and that
	 * the format is correct.
	 * @throws Exception
	 */
	@Test
	public void jsonFinalEmployeeNameTest() throws Exception {
		mock.perform(
			post("/")
			.content(exampleJson)
			.contentType(MediaType.TEXT_PLAIN)
			.accept(MediaType.TEXT_PLAIN))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].employee.firstName").value("David"));
	}

	/**
	 * Tests that output is working properly for grossIncome.
	 * @throws Exception
	 */
	@Test
	public void jsonFinalGrossIncomeTest() throws Exception {
		mock.perform(
			post("/")
			.content(exampleJson)
			.contentType(MediaType.TEXT_PLAIN)
			.accept(MediaType.TEXT_PLAIN))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].grossIncome").value(5004));
	}

	/**
	 * Tests that output is working properly for incomeTax.
	 * @throws Exception
	 */
	@Test
	public void jsonFinalIncomeTaxTest() throws Exception {
		mock.perform(
			post("/")
			.content(exampleJson)
			.contentType(MediaType.TEXT_PLAIN)
			.accept(MediaType.TEXT_PLAIN))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].incomeTax").value(922));
	}

	/**
	 * Tests that output is working properly for superannuation.
	 * @throws Exception
	 */
	@Test
	public void jsonFinalSuperannuationTest() throws Exception {
		mock.perform(
			post("/")
			.content(exampleJson)
			.contentType(MediaType.TEXT_PLAIN)
			.accept(MediaType.TEXT_PLAIN))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].superannuation").value(450));
	}

	/**
	 * Tests that output is working properly for netIncome.
	 * @throws Exception
	 */
	@Test
	public void jsonFinalNetIncomeTest() throws Exception {
		mock.perform(
			post("/")
			.content(exampleJson)
			.contentType(MediaType.TEXT_PLAIN)
			.accept(MediaType.TEXT_PLAIN))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].netIncome").value(4082));
	}

}
