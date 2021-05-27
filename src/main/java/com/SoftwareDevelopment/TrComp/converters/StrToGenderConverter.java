package com.SoftwareDevelopment.TrComp.converters;

import com.SoftwareDevelopment.TrComp.models.Gender;
import org.springframework.core.convert.converter.Converter;

public class StrToGenderConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String s) {
        if (s == "" || s == null) return null;

        Gender g = new Gender();
        g.setName(s);

        return g;
    }
}
