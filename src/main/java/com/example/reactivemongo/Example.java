package com.example.reactivemongo;

import java.util.Arrays;
import java.util.List;

public class Example {

    public static void main(String args[]) {
        List<String> a = Arrays.asList("ABC20111050Z", "ABB20111050C", "ABX20221050C", "ABX202H1050C", "ABC201110F0Z", "ABc20111050Z",
                "ABC20111050z", "ABC2011100Z");
        System.out.println("Total is ::" + countNumbers(a));
    }

    private static int countNumbers(List<String> numbers) {
        int total = 0;
        for (String number : numbers) {
            if (number.length() == 10 || number.length() == 12) {
                char[] chars = number.replaceAll("[^a-zA-Z]", "").toCharArray();
                boolean repeatedChar = false;
                for (int i = 0; i < chars.length; i++) {
                    for (int j = i + 1; j < chars.length; j++) {
                        if (Character.isLowerCase(chars[i]) || chars[i] == chars[j]) {
                            repeatedChar = true;
                            break;
                        }
                    }
                }
                String year = number.substring(3, 7);
                boolean isValidYear = isNumber(year);
                if (!repeatedChar && isValidYear && Character.isLetter(number.charAt(number.length() - 1)) &&
                        Character.isUpperCase(number.charAt(number.length() - 1))) {
                    if (Integer.parseInt(year) >= 2001 && Integer.parseInt(year) <= 2019) {
                        String currency = number.substring(7, number.length() - 1);
                        if (isNumber(currency)) {
                            total = total + Integer.parseInt(currency);
                        }

                    }
                }

            }
        }
        return total;
    }

    private static boolean isNumber(String number) {
        for (Character a : number.toCharArray()) {
            if (!Character.isDigit(a)) {
                return false;
            }
        }
        return true;
    }
}
