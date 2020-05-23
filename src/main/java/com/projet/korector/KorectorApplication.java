package com.projet.korector;

import com.projet.korector.config.AppProperties;
import com.projet.korector.jenkins.Jenkins;
import org.apache.log4j.LogManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.logging.Logger;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class KorectorApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(KorectorApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(KorectorApplication.class);
	}
}
