package com.monitise.alkin.core;

import java.math.BigInteger;

public class IbanUtil {

    public static boolean isIbanValid(String iban) {
        //iban is 27 characters long
        if (iban == null || iban.length() != 27) return false;

        //The first characters are uppercase letters. They are translated into numbers
        char first = iban.charAt(0);
        char second = iban.charAt(1);

        if (!Character.isAlphabetic(first) && !Character.isUpperCase(first) ||
                !Character.isAlphabetic(second) && !Character.isUpperCase(second)) {
            return false;
        }

        String firstNum = (first - 'A' + 10) + "";
        String secondNum = (second - 'A' + 10) + "";

        //The first four characters are moved to the end of the number.
        String newIban = iban.substring(4).concat(firstNum).concat(secondNum).concat(iban.substring(2, 4));

        //Check if all characters are numbers
        if (!newIban.matches("^[0-9]*$")){
            return false;
        }

        //If the modulo 97 is 1, then the initial account number is
        // correct ΙΒΑΝ format; else this is not an IBAN account number.
        BigInteger ibanNum = new BigInteger(newIban);
        BigInteger result = ibanNum.mod(new BigInteger("97"));

        return result.intValue() == 1;
    }

}
