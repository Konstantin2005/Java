package com.beautifulgit.mealplanner.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI mealPlannerOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Meal Planner API")
                        .description("API for managing recipes and meal plans")
                        .version("v1")
                        .contact(new Contact().name("Meal Planner")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project README")
                        .url("https://github.com/Konstantin2005/Java"));
    }
}
