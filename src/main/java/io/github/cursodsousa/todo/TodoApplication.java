package io.github.cursodsousa.todo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication // Define como Aplicação Spring Boot
@RestController// Define App Rest
public class TodoApplication {


	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);

	}

}
