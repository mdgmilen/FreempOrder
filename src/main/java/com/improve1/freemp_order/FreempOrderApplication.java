package com.improve1.freemp_order;

import com.improve1.freemp_order.domains.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FreempOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreempOrderApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(FreempOrderApplication.class);

//	public static void main(String[] args) {
//		SpringApplication.run(ConsumingRestApplication.class, args);
//	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Greeting greeting = restTemplate.getForObject(
					"http://localhost:8080/api/greeting?name=Milen", Greeting.class);
			log.info(greeting.toString());
		};
	}
}
