package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BinaryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // ---------------------------------------------------------------
    // GET "/" — calculator page
    // ---------------------------------------------------------------

    /** TC-W-01: GET "/" with no params shows empty calculator */
    @Test
    public void testGetCalculatorEmpty() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("operand1", ""));
    }

    /** TC-W-02: GET "/?operand1=101" pre-fills operand1 */
    @Test
    public void testGetCalculatorWithOperand1() throws Exception {
        mockMvc.perform(get("/").param("operand1", "101"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("operand1", "101"));
    }

    // ---------------------------------------------------------------
    // POST "/" — addition
    // ---------------------------------------------------------------

    /** TC-W-03: 111 + 1010 = 10001 */
    @Test
    public void testAddition() throws Exception {
        mockMvc.perform(post("/")
                .param("operand1", "111")
                .param("operator", "+")
                .param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "10001"));
    }

    /** TC-W-04: 0 + 0 = 0 (boundary case) */
    @Test
    public void testAdditionZero() throws Exception {
        mockMvc.perform(post("/")
                .param("operand1", "0")
                .param("operator", "+")
                .param("operand2", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"));
    }

    // ---------------------------------------------------------------
    // POST "/" — multiplication
    // ---------------------------------------------------------------

    /** TC-W-05: 101 * 11 = 1111 (5 * 3 = 15) */
    @Test
    public void testMultiplication() throws Exception {
        mockMvc.perform(post("/")
                .param("operand1", "101")
                .param("operator", "*")
                .param("operand2", "11"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1111"));
    }

    /** TC-W-06: 1010 * 0 = 0 (multiply by zero) */
    @Test
    public void testMultiplicationByZero() throws Exception {
        mockMvc.perform(post("/")
                .param("operand1", "1010")
                .param("operator", "*")
                .param("operand2", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"));
    }

    // ---------------------------------------------------------------
    // POST "/" — bitwise AND
    // ---------------------------------------------------------------

    /** TC-W-07: 1100 & 1010 = 1000 */
    @Test
    public void testBitwiseAnd() throws Exception {
        mockMvc.perform(post("/")
                .param("operand1", "1100")
                .param("operator", "&")
                .param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1000"));
    }

    /** TC-W-08: 1111 & 0000 = 0 */
    @Test
    public void testBitwiseAndWithZero() throws Exception {
        mockMvc.perform(post("/")
                .param("operand1", "1111")
                .param("operator", "&")
                .param("operand2", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "0"));
    }

    // ---------------------------------------------------------------
    // POST "/" — bitwise OR
    // ---------------------------------------------------------------

    /** TC-W-09: 1100 | 1010 = 1110 */
    @Test
    public void testBitwiseOr() throws Exception {
        mockMvc.perform(post("/")
                .param("operand1", "1100")
                .param("operator", "|")
                .param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1110"));
    }

    /** TC-W-10: 0 | 1111 = 1111 */
    @Test
    public void testBitwiseOrWithZero() throws Exception {
        mockMvc.perform(post("/")
                .param("operand1", "0")
                .param("operator", "|")
                .param("operand2", "1111"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1111"));
    }

    // ---------------------------------------------------------------
    // POST "/" — invalid operator → error page
    // ---------------------------------------------------------------

    /** TC-W-11: Invalid operator shows error view */
    @Test
    public void testInvalidOperator() throws Exception {
        mockMvc.perform(post("/")
                .param("operand1", "101")
                .param("operator", "%")
                .param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }
}
