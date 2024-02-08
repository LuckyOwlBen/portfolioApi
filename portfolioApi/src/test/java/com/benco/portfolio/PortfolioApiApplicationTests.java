package com.benco.portfolio;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class PortfolioApiApplicationTests {

	@Test
	void contextLoads() {
		//String[] args = ["--spring.profiles.active=dev", "--spring.output.ansi.enabled=always"];
		//PortfolioApiApplication.main(args);
		assertTrue(true);
	}

}
