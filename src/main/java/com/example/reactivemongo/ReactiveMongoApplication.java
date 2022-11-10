package com.example.reactivemongo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class ReactiveMongoApplication {

	public static void main(String[] args) {
			new SpringApplicationBuilder(ReactiveMongoApplication.class)
					.web(WebApplicationType.REACTIVE)
					.build()
					.run(args);
		}

}
