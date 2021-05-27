package com.SoftwareDevelopment.TrComp.converters;

import com.SoftwareDevelopment.TrComp.models.Driver;
import com.SoftwareDevelopment.TrComp.models.Privilege;
import org.springframework.core.convert.converter.Converter;

public class StrToDriverConverter implements Converter<String, Driver> {

    @Override
    public Driver convert(String s) {
        if (s == "" || s == null) return null;

        Driver p = new Driver();
        p.setId(Integer.parseInt(s));

        return p;
    }
}
