package br.com.wlmfincatti.message.adapter.entrypoint.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@RequiredArgsConstructor
@Configuration
public class OpenApi {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(Collections.singletonList(new Server()
                        .url("http://localhost:8080")
                        .description("stacklocal")))
                .info(new Info()
                            .title("Message Application")
                        .version("V1")
                        .description("Service Message"));
    }

}
