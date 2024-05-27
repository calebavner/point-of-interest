package app;

import app.model.PointOfInterest;
import app.repos.PointOfInterestRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiPoiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPoiApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(PointOfInterestRepository repository) {
		return args -> {
			repository.save(new PointOfInterest("Lanchonete", 27L, 12L));
			repository.save(new PointOfInterest("Posto", 31L, 18L));
			repository.save(new PointOfInterest("Joalheria", 15L, 12L));
			repository.save(new PointOfInterest("Floricultura", 19L, 21L));
			repository.save(new PointOfInterest("Pub", 12L, 8L));
			repository.save(new PointOfInterest("Supermercado", 23L, 6L));
			repository.save(new PointOfInterest("Churrascaria", 28L, 2L));
		};
	}
}
