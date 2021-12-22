package com.SoftwareDevelopment.TrComp.validators;

import java.util.regex.Pattern;

public class PhoneNumberValidator {
    public static boolean isValid(String phoneNumber) {
        return Pattern.compile("^(?!\\s*$)[0-9\\s]{11}$")
                .matcher(phoneNumber)
                .matches();
    }
}
