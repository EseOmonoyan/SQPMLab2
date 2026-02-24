package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BinaryAPIController {

    // --- Addition ---
    @GetMapping("/add")
    public String add(
            @RequestParam(name = "operand1") String operand1,
            @RequestParam(name = "operand2") String operand2) {
        Binary b1 = new Binary(operand1);
        Binary b2 = new Binary(operand2);
        return Binary.add(b1, b2).getValue();
    }

    @GetMapping("/add_json")
    public BinaryAPIResult addJson(
            @RequestParam(name = "operand1") String operand1,
            @RequestParam(name = "operand2") String operand2) {
        Binary b1 = new Binary(operand1);
        Binary b2 = new Binary(operand2);
        String result = Binary.add(b1, b2).getValue();
        return new BinaryAPIResult(operand1, operand2, "+", result);
    }

    // --- Multiplication ---
    @GetMapping("/multiply")
    public String multiply(
            @RequestParam(name = "operand1") String operand1,
            @RequestParam(name = "operand2") String operand2) {
        Binary b1 = new Binary(operand1);
        Binary b2 = new Binary(operand2);
        return Binary.multiply(b1, b2).getValue();
    }

    @GetMapping("/multiply_json")
    public BinaryAPIResult multiplyJson(
            @RequestParam(name = "operand1") String operand1,
            @RequestParam(name = "operand2") String operand2) {
        Binary b1 = new Binary(operand1);
        Binary b2 = new Binary(operand2);
        String result = Binary.multiply(b1, b2).getValue();
        return new BinaryAPIResult(operand1, operand2, "*", result);
    }

    // --- Bitwise AND ---
    @GetMapping("/and")
    public String and(
            @RequestParam(name = "operand1") String operand1,
            @RequestParam(name = "operand2") String operand2) {
        Binary b1 = new Binary(operand1);
        Binary b2 = new Binary(operand2);
        return Binary.and(b1, b2).getValue();
    }

    @GetMapping("/and_json")
    public BinaryAPIResult andJson(
            @RequestParam(name = "operand1") String operand1,
            @RequestParam(name = "operand2") String operand2) {
        Binary b1 = new Binary(operand1);
        Binary b2 = new Binary(operand2);
        String result = Binary.and(b1, b2).getValue();
        return new BinaryAPIResult(operand1, operand2, "&", result);
    }

    // --- Bitwise OR ---
    @GetMapping("/or")
    public String or(
            @RequestParam(name = "operand1") String operand1,
            @RequestParam(name = "operand2") String operand2) {
        Binary b1 = new Binary(operand1);
        Binary b2 = new Binary(operand2);
        return Binary.or(b1, b2).getValue();
    }

    @GetMapping("/or_json")
    public BinaryAPIResult orJson(
            @RequestParam(name = "operand1") String operand1,
            @RequestParam(name = "operand2") String operand2) {
        Binary b1 = new Binary(operand1);
        Binary b2 = new Binary(operand2);
        String result = Binary.or(b1, b2).getValue();
        return new BinaryAPIResult(operand1, operand2, "|", result);
    }
}
