package com.example.webapp.validators;

public class StringValidator {

    public static boolean validate(String value) {
        return !(value == null || value.isEmpty());
    }

}
