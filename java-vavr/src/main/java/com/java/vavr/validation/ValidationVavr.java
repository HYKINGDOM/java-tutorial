package com.java.vavr.validation;

import io.vavr.Function2;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import lombok.Data;

public class ValidationVavr {

    public static void main(String[] args) {
        ValidationVavr validationVavr = new ValidationVavr();
        Validation<Seq<String>, Person> personValidation = validationVavr.validatePerson("eqweqw", 12);

        boolean valid = personValidation.isValid();
        Seq<String> error = personValidation.getError();
        Person per = validationVavr.validatePerson("eqweqw", 12).get();
    }

    String NAME_ERR = "Invalid characters in name: ";
    String AGE_ERR = "Age must be at least 0";

    public Validation<Seq<String>, Person> validatePerson(String name, int age) {
        Function2<String, Integer, Person> function2 = (a, b) -> new Person();
        return Validation.combine(validateName(name), validateAge(age)).ap(function2);
    }

    private Validation<String, String> validateName(String name) {
        String invalidChars = name.replaceAll("[a-zA-Z ]", "");
        return invalidChars.isEmpty() ? Validation.valid(name) : Validation.invalid(NAME_ERR + invalidChars);
    }

    private Validation<String, Integer> validateAge(int age) {
        return age < 0 ? Validation.invalid(AGE_ERR) : Validation.valid(age);
    }


    @Data
    public static class Person {
        private String name;
        private int age;
    }
}
