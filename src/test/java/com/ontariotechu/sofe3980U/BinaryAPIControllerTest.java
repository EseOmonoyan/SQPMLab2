package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // ---------------------------------------------------------------
    // Addition — string endpoint
    // ---------------------------------------------------------------

    /** TC-A-01: 111 + 1010 = 10001 (string) */
    @Test
    public void testAddString() throws Exception {
        mockMvc.perform(get("/add")
                .param("operand1", "111")
                .param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("10001"));
    }

    /** TC-A-02: 0 + 0 = 0 (string, boundary) */
    @Test
    public void testAddZeroString() throws Exception {
        mockMvc.perform(get("/add")
                .param("operand1", "0")
                .param("operand2", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    /** TC-A-03: 1111 + 1 = 10000 (carry propagation, string) */
    @Test
    public void testAddCarryString() throws Exception {
        mockMvc.perform(get("/add")
                .param("operand1", "1111")
                .param("operand2", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("10000"));
    }

    // ---------------------------------------------------------------
    // Addition — JSON endpoint
    // ---------------------------------------------------------------

    /** TC-A-04: 111 + 1010 = 10001 (JSON) */
    @Test
    public void testAddJson() throws Exception {
        mockMvc.perform(get("/add_json")
                .param("operand1", "111")
                .param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("10001"))
                .andExpect(jsonPath("$.operator").value("+"));
    }

    // ---------------------------------------------------------------
    // Multiplication — string endpoint
    // ---------------------------------------------------------------

    /** TC-A-05: 101 * 11 = 1111 (5*3=15) */
    @Test
    public void testMultiplyString() throws Exception {
        mockMvc.perform(get("/multiply")
                .param("operand1", "101")
                .param("operand2", "11"))
                .andExpect(status().isOk())
                .andExpect(content().string("1111"));
    }

    /** TC-A-06: 1010 * 0 = 0 (multiply by zero) */
    @Test
    public void testMultiplyByZeroString() throws Exception {
        mockMvc.perform(get("/multiply")
                .param("operand1", "1010")
                .param("operand2", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    /** TC-A-07: 1 * 1 = 1 (minimal multiply) */
    @Test
    public void testMultiplyOneByOne() throws Exception {
        mockMvc.perform(get("/multiply")
                .param("operand1", "1")
                .param("operand2", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    // ---------------------------------------------------------------
    // Multiplication — JSON endpoint
    // ---------------------------------------------------------------

    /** TC-A-08: 110 * 10 = 1100 (6*2=12) JSON */
    @Test
    public void testMultiplyJson() throws Exception {
        mockMvc.perform(get("/multiply_json")
                .param("operand1", "110")
                .param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("1100"))
                .andExpect(jsonPath("$.operator").value("*"));
    }

    // ---------------------------------------------------------------
    // Bitwise AND — string endpoint
    // ---------------------------------------------------------------

    /** TC-A-09: 1100 & 1010 = 1000 */
    @Test
    public void testAndString() throws Exception {
        mockMvc.perform(get("/and")
                .param("operand1", "1100")
                .param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("1000"));
    }

    /** TC-A-10: 1111 & 0 = 0 */
    @Test
    public void testAndWithZero() throws Exception {
        mockMvc.perform(get("/and")
                .param("operand1", "1111")
                .param("operand2", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    /** TC-A-11: 1111 & 1111 = 1111 (same operands) */
    @Test
    public void testAndSameOperands() throws Exception {
        mockMvc.perform(get("/and")
                .param("operand1", "1111")
                .param("operand2", "1111"))
                .andExpect(status().isOk())
                .andExpect(content().string("1111"));
    }

    // ---------------------------------------------------------------
    // Bitwise AND — JSON endpoint
    // ---------------------------------------------------------------

    /** TC-A-12: 1100 & 1010 = 1000 (JSON) */
    @Test
    public void testAndJson() throws Exception {
        mockMvc.perform(get("/and_json")
                .param("operand1", "1100")
                .param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("1000"))
                .andExpect(jsonPath("$.operator").value("&"));
    }

    // ---------------------------------------------------------------
    // Bitwise OR — string endpoint
    // ---------------------------------------------------------------

    /** TC-A-13: 1100 | 1010 = 1110 */
    @Test
    public void testOrString() throws Exception {
        mockMvc.perform(get("/or")
                .param("operand1", "1100")
                .param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("1110"));
    }

    /** TC-A-14: 0 | 1111 = 1111 */
    @Test
    public void testOrWithZero() throws Exception {
        mockMvc.perform(get("/or")
                .param("operand1", "0")
                .param("operand2", "1111"))
                .andExpect(status().isOk())
                .andExpect(content().string("1111"));
    }

    /** TC-A-15: 0 | 0 = 0 (all zeros) */
    @Test
    public void testOrAllZeros() throws Exception {
        mockMvc.perform(get("/or")
                .param("operand1", "0")
                .param("operand2", "0"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    // ---------------------------------------------------------------
    // Bitwise OR — JSON endpoint
    // ---------------------------------------------------------------

    /** TC-A-16: 1100 | 1010 = 1110 (JSON) */
    @Test
    public void testOrJson() throws Exception {
        mockMvc.perform(get("/or_json")
                .param("operand1", "1100")
                .param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("1110"))
                .andExpect(jsonPath("$.operator").value("|"));
    }
}
