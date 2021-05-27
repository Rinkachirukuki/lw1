package com.SoftwareDevelopment.TrComp.converters;

import com.SoftwareDevelopment.TrComp.models.Gender;
import com.SoftwareDevelopment.TrComp.models.Privilege;
import org.springframework.core.convert.converter.Converter;

public class StrToPrivilegeConverter implements Converter<String, Privilege> {

    @Override
    public Privilege convert(String s) {
        if (s == "" || s == null) return null;

        Privilege p = new Privilege();
        p.setName(s);

        return p;
    }
}
