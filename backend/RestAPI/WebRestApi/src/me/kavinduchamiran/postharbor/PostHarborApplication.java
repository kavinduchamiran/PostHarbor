package me.kavinduchamiran.postharbor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {  DataSourceAutoConfiguration.class })
public class PostHarborApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostHarborApplication.class, args);
	}

}
