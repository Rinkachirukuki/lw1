package com.SoftwareDevelopment.TrComp.converters;

import com.SoftwareDevelopment.TrComp.models.Mark;
import com.SoftwareDevelopment.TrComp.models.Privilege;
import org.springframework.core.convert.converter.Converter;

public class StrToMarkConverter implements Converter<String, Mark> {

    @Override
    public Mark convert(String s) {
        if (s == "" || s == null) return null;

        Mark p = new Mark();
        p.setName(s);

        return p;
    }
}
