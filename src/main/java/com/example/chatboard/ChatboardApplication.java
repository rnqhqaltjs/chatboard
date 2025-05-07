package com.example.chatboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChatboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatboardApplication.class, args);
	}

}
