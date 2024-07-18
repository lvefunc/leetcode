package com.lvefunc;

import java.util.Arrays;

public class Solution {
    private static final char whitespace = ' ';
    private static final char plus = '+';
    private static final char minus = '-';
    private static final char zero = '0'; // -> number characters use 48 through 57
    private static final int initialValue = -1;

    public int myAtoi(String s) {
        char[] chars = s.toCharArray();

        boolean isPositive = true, isSignSet = false;
        int startIndex = initialValue, endIndex = initialValue;

        // determine sign and indices of a number string

        boolean isLeadingWS = true;

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (c == whitespace && isLeadingWS)
                continue;
            else
                isLeadingWS = false;

            if (c == minus && !isSignSet) {
                isPositive = false;
                isSignSet = true;
                continue;
            }

            if (c == plus && !isSignSet) {
                isSignSet = true;
                continue;
            }

            if (!isSignSet)
                isSignSet = true;

            if (c < 48 || c > 57) {
                if (startIndex == initialValue)
                    return 0;
                if (endIndex == initialValue)
                    endIndex = i - 1;
            } else if (startIndex == initialValue) {
                startIndex = i;
            }

            if (i == chars.length - 1 && endIndex == initialValue)
                endIndex = i;
        }

        if (startIndex == initialValue || endIndex == initialValue)
            return 0;

        // clear leading zeros

        boolean isLeadingZero = true;

        for (int i = startIndex; i <= endIndex; i++) {
            int c = chars[i];

            if (c == zero && isLeadingZero) {
                if (startIndex != endIndex)
                    startIndex++;
            } else {
                isLeadingZero = false;
            }
        }

        // 32 bit int can have up to 10 digits in its decimal form, so if that's the case we can surely say
        // that this number is out of range of 32 bit int, so we return either max or min value asap

        if (endIndex - startIndex + 1 > 10)
            return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        // construct digits array

        char[] digits = new char[10];

        Arrays.fill(digits, zero);
        System.arraycopy(chars, startIndex, digits, 10 - (endIndex - startIndex + 1), endIndex - startIndex + 1);

        // construct the number

        int number = 0;

        try {
            for (int i = 0; i < digits.length; i++) {
                int digit = digits[i] - zero;
                int place = expBySquaring(10, digits.length - 1 - i);
                int result = Math.multiplyExact(place, digit);
                number = isPositive ? Math.addExact(number, result) : Math.subtractExact(number, result);
            }

            return number;
        } catch (ArithmeticException e) {
            return isPositive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
    }

    public int expBySquaring(int base, int exp) {
        int result = 1;

        while (exp != 0) {
            if ((exp & 1) == 1)
                result *= base;
            exp >>= 1;
            base *= base;
        }

        return result;
    }
}