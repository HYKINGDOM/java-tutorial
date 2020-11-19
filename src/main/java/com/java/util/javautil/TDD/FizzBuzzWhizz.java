package com.java.util.javautil.TDD;

public class FizzBuzzWhizz {

    public String loudSay(int num) {
        String loudSay = "";
        String valueOf = String.valueOf(num);
        if (num == 0) {
            return valueOf;
        }
        if (num % 3 == 0 || valueOf.contains("3")) {
            loudSay += "Fizz";
        }
        if (num % 5 == 0) {
            loudSay += "Buzz";
        }
        if (num % 7 == 0) {
            loudSay += "Whizz";
        }
        return loudSay;
    }
}
