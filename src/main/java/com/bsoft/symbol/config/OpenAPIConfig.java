package com.bsoft.symbol.config;

import com.bsoft.symbol.model.Symbol;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ArraySchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Schema(ref = "SymbolList")
    private List<Symbol> symbolList;

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI().components(new Components()
                .addSchemas("SymbolList", new ArraySchema().type("Symbol")
                ));
    }
}
