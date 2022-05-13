package cats.cloud.dataingestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.data.spanner.repository.config.EnableSpannerRepositories;

@SpringBootApplication
@EnableSpannerRepositories
public class DataIngestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataIngestionApplication.class, args);
	}

}
