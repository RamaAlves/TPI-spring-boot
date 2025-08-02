package com.informatorio.info_market;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
				title = "Info Market Documentación",
				description ="Documentacion para el TPI de springboot del Informatorio",
				version = "v1",
				contact= @Contact(
						name = "Ramiro",
						email="ramiroalves21@gmail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0.html"
				)
		)
)
public class InfoMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfoMarketApplication.class, args);
	}

}
