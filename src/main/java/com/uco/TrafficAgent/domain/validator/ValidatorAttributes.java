package com.uco.TrafficAgent.domain.validator;

import java.util.List;

public class ValidatorAttributes {

    private static final String PATTERN = "^(?=.*\\d)(?=.*[\\u0021-\\u002b\\u003c-\\u0040])(?=.*[A-Z])(?=.*[a-z])\\S{8,16}";
    private static final String PATTERN_NUMBER = "[0-9]*";
    private static final String PATTERN_DECIMAL = "^-?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$";

    private ValidatorAttributes() {
    }

    public static void validateRequired(String valor, String message) {
        if(valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void noEmpty(List<? extends Object> lista, String mensaje){
        if(lista == null || lista.isEmpty()){
            throw  new IllegalArgumentException(mensaje);
        }
    }

    private static boolean acceptancePatternNumber(String valor, String pattern) {
        return valor.matches(pattern);
    }

    public static void stringContainNumbers(String valor, String message) {
        if(!acceptancePatternNumber(valor, PATTERN_NUMBER))
        {
            throw new IllegalArgumentException(message);
        }
    }


    private static boolean acceptancePatternDecimal(String valor, String pattern) {
        return valor.matches(pattern);
    }

    public static void stringContainNumbersDecimal(String valor, String message) {
        if(!acceptancePatternDecimal(valor, PATTERN_DECIMAL))
        {
            throw new IllegalArgumentException(message);
        }
    }

    public static void sizePassword(String valor, String message)
    {
        if(!(valor.length()>=8 && valor.length()<=16))
        {
            throw new IllegalArgumentException(message);
        }
    }

    private static boolean acceptancePatternPassword(String data, String pattern)
    {
        return data.matches(pattern);
    }

    public static void specialCharactersPassword(String password, String message)
    {
        if(!acceptancePatternPassword(password, PATTERN))
        {
            throw new IllegalArgumentException(message);
        }
    }
}
