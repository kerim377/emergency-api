package com.bluesoft.emergency;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {EmergencyApplication.class})
@TestPropertySource(locations = "classpath:application-integration-test.properties")
class EmergencyApplicationIntegrationTests {

	@Test
	void contextLoads() {
	}

}
