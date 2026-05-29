package com.ssafy.history.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("EnjoyTrip History API")
                        .description("역사 관광지, 한능검 퀴즈, 역사 태그 데이터 확인용 API")
                        .version("v1"));
    }
}
