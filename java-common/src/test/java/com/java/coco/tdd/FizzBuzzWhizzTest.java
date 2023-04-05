package com.java.coco.tdd;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzBuzzWhizzTest {

    @Test
    public void when_multiple_3_then_return_fizz() {
        FizzBuzzWhizz fizzBuzzWhizz = new FizzBuzzWhizz();
        int num = 3;
        String loudSay = fizzBuzzWhizz.loudSay(num);
        assertEquals("Fizz", loudSay);
    }

    @Test
    public void when_multiple_5_then_return_buzz() {
        FizzBuzzWhizz fizzBuzzWhizz = new FizzBuzzWhizz();
        int num = 5;
        String loudSay = fizzBuzzWhizz.loudSay(num);
        assertEquals("Buzz", loudSay);
    }

    @Test
    public void when_multiple_7_then_return_whizz() {
        FizzBuzzWhizz fizzBuzzWhizz = new FizzBuzzWhizz();
        int num = 7;
        String loudSay = fizzBuzzWhizz.loudSay(num);
        assertEquals("Whizz", loudSay);
    }

    @Test
    public void when_multiple_3_and_5_then_return_fizzbuzz() {
        FizzBuzzWhizz fizzBuzzWhizz = new FizzBuzzWhizz();
        int num = 15;
        String loudSay = fizzBuzzWhizz.loudSay(num);
        assertEquals("FizzBuzz", loudSay);
    }

    @Test
    public void when_multiple_3_and_7_then_return_fizzWhizz() {
        FizzBuzzWhizz fizzBuzzWhizz = new FizzBuzzWhizz();
        int num = 21;
        String loudSay = fizzBuzzWhizz.loudSay(num);
        assertEquals("FizzWhizz", loudSay);
    }

    @Test
    public void when_multiple_5_and_7_then_return_buzzWhizz() {
        FizzBuzzWhizz fizzBuzzWhizz = new FizzBuzzWhizz();
        int num = 70;
        String loudSay = fizzBuzzWhizz.loudSay(num);
        assertEquals("BuzzWhizz", loudSay);
    }

    @Test
    public void when_multiple_3_and_5_and_7_then_return_buzzWhizz() {
        FizzBuzzWhizz fizzBuzzWhizz = new FizzBuzzWhizz();
        int num = 105;
        String loudSay = fizzBuzzWhizz.loudSay(num);
        assertEquals("FizzBuzzWhizz", loudSay);
    }

    @Test
    public void when_contain_3_then_return_fizz() {
        FizzBuzzWhizz fizzBuzzWhizz = new FizzBuzzWhizz();
        int num = 13;
        String loudSay = fizzBuzzWhizz.loudSay(num);
        assertEquals("Fizz", loudSay);
    }

    @Test
    public void when_contain_3_also_multiple_3_and_5_and_7_then_return_buzzWhizz() {
        FizzBuzzWhizz fizzBuzzWhizz = new FizzBuzzWhizz();
        int num = 315;
        String loudSay = fizzBuzzWhizz.loudSay(num);
        assertEquals("FizzBuzzWhizz", loudSay);
    }


    @Test
    public void when_0_then_return_0() {
        FizzBuzzWhizz fizzBuzzWhizz = new FizzBuzzWhizz();
        int num = 0;
        String loudSay = fizzBuzzWhizz.loudSay(num);
        assertEquals("0", loudSay);
    }


}
