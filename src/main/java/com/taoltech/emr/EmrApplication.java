package com.taoltech.emr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "EMR Application API", version = "1.0.0"))
@SecuritySchemes({
        @SecurityScheme(name = "BasicAuth", type = SecuritySchemeType.HTTP, scheme = "basic", in = SecuritySchemeIn.HEADER),
        @SecurityScheme(name = "TokenAuth", type = SecuritySchemeType.APIKEY, scheme = "bearer", in = SecuritySchemeIn.HEADER)
    })
public class EmrApplication {

    public static final String AUTH = "BasicAuth";
    
    public static void main(String[] args) {
        SpringApplication.run(EmrApplication.class, args);
    }

    @Bean
    ObjectMapper mapper() {
        return new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
