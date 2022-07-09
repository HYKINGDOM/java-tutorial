package com.java.util.javautil.UTMock;

import java.util.List;

/**
 * @author HY
 */
public class UTMock {


    public String convertToString(List<Integer> number) {

        sumNumber(number);

        StringBuilder stringBuilder = new StringBuilder();

        for (Integer integer : number) {
            for (char character : integer.toString().toCharArray()) {
                if (2 != character) {
                    stringBuilder.append(character);
                }
            }
        }


        return stringBuilder.toString();
    }


    public void sumNumber(List<Integer> number) {

        number.add(1111);
        number.add(2222);
    }

}
