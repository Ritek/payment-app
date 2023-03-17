package com.wojciech.rithaler.prommtchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
public class PrommtChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrommtChallengeApplication.class, args);
	}

	@Bean
	Clock clock() {
		return Clock.systemDefaultZone();
	}

}
