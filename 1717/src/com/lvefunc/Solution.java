package com.lvefunc;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    private static final String AB = "ab";
    private static final String BA = "ba";

    public int maximumGain(String s, int x, int y) {
        String hiPattern = x > y ? AB : BA;
        String loPattern = x > y ? BA : AB;

        String hiResult = removePattern(s, hiPattern);
        String loResult = removePattern(hiResult, loPattern);

        return
                ((s.length() - hiResult.length()) / 2) * Math.max(x, y) +
                ((hiResult.length() - loResult.length()) / 2) * Math.min(x, y);
    }

    private String removePattern(String string, String pattern) {
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (!stack.isEmpty() && stack.peek() == pattern.charAt(0) && c == pattern.charAt(1))
                stack.pop();
            else
                stack.push(c);
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty())
            sb.append(stack.pop());

        return sb.reverse().toString();
    }
}
