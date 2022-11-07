package io.github.monthalcantara.cadastroclientesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class CadastroClientesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroClientesApiApplication.class, args);
	}

}
