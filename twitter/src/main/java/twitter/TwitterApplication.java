package twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TwitterApplication {

	public static void main(String[] args) {
		System.out.println("Welcome to twitter backend");
		SpringApplication.run(TwitterApplication.class, args);
	}

}
