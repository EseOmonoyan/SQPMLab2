package com.ontariotechu.sofe3980U;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BinaryController {

    /** GET "/" — show the calculator form */
    @GetMapping("/")
    public String getCalculator(
            @RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
            Model model) {
        model.addAttribute("operand1", operand1);
        boolean operand1Focused = operand1.isEmpty();
        model.addAttribute("operand1Focused", operand1Focused);
        return "calculator";
    }

    /** POST "/" — process the calculation and show result or error */
    @PostMapping("/")
    public String getResult(
            @RequestParam(name = "operand1") String operand1,
            @RequestParam(name = "operator") String operator,
            @RequestParam(name = "operand2") String operand2,
            Model model) {

        Binary b1 = new Binary(operand1);
        Binary b2 = new Binary(operand2);
        Binary result;

        switch (operator) {
            case "+":
                result = Binary.add(b1, b2);
                break;
            case "*":
                result = Binary.multiply(b1, b2);
                break;
            case "&":
                result = Binary.and(b1, b2);
                break;
            case "|":
                result = Binary.or(b1, b2);
                break;
            default:
                model.addAttribute("operator", operator);
                return "error";
        }

        model.addAttribute("operand1", b1.getValue());
        model.addAttribute("operator", operator);
        model.addAttribute("operand2", b2.getValue());
        model.addAttribute("result",   result.getValue());
        return "result";
    }
}
