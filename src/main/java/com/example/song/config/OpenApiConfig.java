package com.example.song.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(
                new Info()
                        .title("SomTunes API")
                        .description("API REST for the SomTunes application")
                        .version("v0.0.1")
                        .license(new License().name("").url("https://github.com/NuruMero/SomTunes"))
        );
    }
}
