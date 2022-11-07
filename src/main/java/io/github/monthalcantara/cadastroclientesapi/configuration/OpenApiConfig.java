package io.github.monthalcantara.cadastroclientesapi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("CRUD - Cadastro de Clientes")
                        .version("0.0.1")
                        .description("API resolução a desafio de processo seletivo Itaú")
                        .termsOfService("http://www.termsofservice.url")
                        .contact(descriptionContact())
                        .license(descriptionLicense())
                );
    }

    private Contact descriptionContact() {
        return new Contact()
                .name("Montival Junior")
                .email("montival_junior@yahoo.com.br")
                .url("monthAlcantara.github.io");
    }

    private License descriptionLicense() {
        return new License()
                .name("License")
                .url("#");
    }
}
