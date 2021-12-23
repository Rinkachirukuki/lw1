package com.SoftwareDevelopment.TrComp.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
public class StudentStatusValidator {

    private static List<String> statusListCheck = new ArrayList<String>() {
        {
            add("Ill");
            add("Late");
            add("On time");
            add("For reason");
        }
    };

    public static boolean isValid(String status) {
        return statusListCheck.contains(status);
    }
}
