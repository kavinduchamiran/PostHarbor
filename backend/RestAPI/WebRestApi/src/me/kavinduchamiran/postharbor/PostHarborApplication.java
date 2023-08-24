package me.kavinduchamiran.postharbor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.kavinduchamiran.postharbor.entities")
@ComponentScan({"com.kavinduchamiran.postharbor.entities", "me.kavinduchamiran.postharbor.controller"})
@EnableJpaRepositories("com.kavinduchamiran.postharbor.entities")
public class PostHarborApplication {
	public static void main(String[] args) {
		SpringApplication.run(PostHarborApplication.class, args);
	}

}
