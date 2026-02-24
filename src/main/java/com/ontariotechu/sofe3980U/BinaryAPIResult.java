package com.ontariotechu.sofe3980U;

public class BinaryAPIResult {
    private String operand1;
    private String operand2;
    private String operator;
    private String result;

    public BinaryAPIResult(String operand1, String operand2, String operator, String result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
        this.result  = result;
    }

    public String getOperand1() { return operand1; }
    public String getOperand2() { return operand2; }
    public String getOperator() { return operator; }
    public String getResult()   { return result; }
}
