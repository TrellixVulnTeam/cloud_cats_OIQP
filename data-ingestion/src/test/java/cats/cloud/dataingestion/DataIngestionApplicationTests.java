package cats.cloud.dataingestion;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cats.cloud.dataingestion.service.DataIngestionService;

@SpringBootTest
class DataIngestionApplicationTests {

	@Autowired
	DataIngestionService service;
	
	@Test
	void testCTDataLoad() {
		service.saveCTData();
	}
	
	@Test
	void testNYCDataLoad() {
		service.saveNYCData();
	}

}
