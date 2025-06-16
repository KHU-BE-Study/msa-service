package khube.common_module.global.config;

import java.util.List;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.servers(List.of(
				new Server().url("http://localhost:8080").description("로컬 서버")
			))
			.info(new Info()
				.title("msa-service API")
				.description("khube API 명세서입니다.")
				.version("v1.0.0")
			);
	}
}