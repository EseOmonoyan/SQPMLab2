package com.ontariotechu.sofe3980U;

/**
 * Binary number representation and arithmetic operations.
 * Supports addition (+), multiplication (*), bitwise AND (&), and bitwise OR (|).
 */
public class Binary {
    private String number; // binary string, always valid (only '0' and '1')

    /**
     * Constructor: validates and stores the binary string.
     * Leading zeros are stripped; invalid strings default to "0".
     */
    public Binary(String number) {
        // Validate: must contain only 0s and 1s
        if (number == null || number.isEmpty() || !number.matches("[01]+")) {
            this.number = "0";
            return;
        }
        // Remove leading zeros
        int i = 0;
        while (i < number.length() - 1 && number.charAt(i) == '0') {
            i++;
        }
        this.number = number.substring(i);
    }

    /** Returns the binary string value */
    public String getValue() {
        return number;
    }

    /**
     * ADD: returns a new Binary representing (a + b).
     */
    public static Binary add(Binary num1, Binary num2) {
        String a = num1.getValue();
        String b = num2.getValue();
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;

        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if (i >= 0) { sum += a.charAt(i--) - '0'; }
            if (j >= 0) { sum += b.charAt(j--) - '0'; }
            carry = sum / 2;
            result.insert(0, sum % 2);
        }
        return new Binary(result.toString());
    }

    /**
     * MULTIPLY: returns a new Binary representing (a * b).
     * Uses repeated addition (shift-and-add method).
     */
    public static Binary multiply(Binary num1, Binary num2) {
        String a = num1.getValue();
        String b = num2.getValue();
        Binary result = new Binary("0");

        for (int i = b.length() - 1; i >= 0; i--) {
            if (b.charAt(i) == '1') {
                // Shift a left by (b.length() - 1 - i) positions
                int shiftCount = b.length() - 1 - i;
                String shifted = a + "0".repeat(shiftCount);
                result = add(result, new Binary(shifted));
            }
        }
        return result;
    }

    /**
     * BITWISE AND: returns a new Binary representing (a & b).
     */
    public static Binary and(Binary num1, Binary num2) {
        String a = num1.getValue();
        String b = num2.getValue();

        // Pad shorter string with leading zeros
        int maxLen = Math.max(a.length(), b.length());
        a = padLeft(a, maxLen);
        b = padLeft(b, maxLen);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            result.append((a.charAt(i) == '1' && b.charAt(i) == '1') ? '1' : '0');
        }
        return new Binary(result.toString());
    }

    /**
     * BITWISE OR: returns a new Binary representing (a | b).
     */
    public static Binary or(Binary num1, Binary num2) {
        String a = num1.getValue();
        String b = num2.getValue();

        int maxLen = Math.max(a.length(), b.length());
        a = padLeft(a, maxLen);
        b = padLeft(b, maxLen);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < maxLen; i++) {
            result.append((a.charAt(i) == '1' || b.charAt(i) == '1') ? '1' : '0');
        }
        return new Binary(result.toString());
    }

    /** Pads a binary string with leading zeros to the target length */
    private static String padLeft(String s, int length) {
        while (s.length() < length) {
            s = "0" + s;
        }
        return s;
    }
}
