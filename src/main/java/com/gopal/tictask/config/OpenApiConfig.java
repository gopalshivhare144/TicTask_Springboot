package com.gopal.tictask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Adds Bearer JWT security scheme for Swagger UI.
 */

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi()
    {
        return new OpenAPI().info(new Info()
        .title("TicTaskApi")
        .description("Task Tracker Api")
                .version("1.0.0"))
                 .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
    
}
