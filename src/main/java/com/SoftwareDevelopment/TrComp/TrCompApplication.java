package com.SoftwareDevelopment.TrComp;

import com.SoftwareDevelopment.TrComp.converters.StrToDriverConverter;
import com.SoftwareDevelopment.TrComp.converters.StrToGenderConverter;
import com.SoftwareDevelopment.TrComp.converters.StrToMarkConverter;
import com.SoftwareDevelopment.TrComp.converters.StrToPrivilegeConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
@EnableWebMvc
public class TrCompApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TrCompApplication.class, args);
	}
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StrToGenderConverter());
		registry.addConverter(new StrToPrivilegeConverter());
		registry.addConverter(new StrToMarkConverter());
		registry.addConverter(new StrToDriverConverter());
	}

}
