package com.SoftwareDevelopment.TrComp.converters;

import com.SoftwareDevelopment.TrComp.models.Teacher;
import org.springframework.core.convert.converter.Converter;

public class StrToDriverConverter implements Converter<String, Teacher> {

    @Override
    public Teacher convert(String s) {
        if (s == "" || s == null) return null;

        Teacher p = new Teacher();
        //p.setId(Integer.parseInt(s));

        return p;
    }
}
