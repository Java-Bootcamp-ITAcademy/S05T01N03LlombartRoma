package cat.itacademy.barcelonactiva.llombartroma.toni.s05.t01.n03.S05T01N03LlombartRoma;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Flowers API", version = "2.0", description = "Flowers DB management"))
public class S05T01N03LlombartRomaApplication {
	public static void main(String[] args) {

		SpringApplication.run(S05T01N03LlombartRomaApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
