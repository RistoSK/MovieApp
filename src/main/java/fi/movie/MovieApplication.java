package fi.movie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.movie.domain.Category;
import fi.movie.domain.CategoryRepository;
import fi.movie.domain.Movie;
import fi.movie.domain.MovieRepository;
import fi.movie.domain.User;
import fi.movie.domain.UserRepository;


@SpringBootApplication
public class MovieApplication {
	private static final Logger log = LoggerFactory.getLogger(MovieApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner movieDemo(CategoryRepository cRepository, MovieRepository mRepository, UserRepository uRepository) {
		return (args) -> {
			log.info("saving movies");
			
			//Creating our default categories
			cRepository.save(new Category("Action"));
			cRepository.save(new Category("Adventure"));
			cRepository.save(new Category("Animation"));
			cRepository.save(new Category("Biography"));
			cRepository.save(new Category("Comedy"));
			cRepository.save(new Category("Crime"));
			cRepository.save(new Category("Family"));
			cRepository.save(new Category("Drama"));
			
			// Creating our default movies
			mRepository.save(new Movie("Godfather" , "Francis Ford Coppola" , 1972 , 175 , 9.2, cRepository.findByCatname("Action").get(0)));
			mRepository.save(new Movie("The Lord of the Rings - Return of the King" , "Peter Jackson" , 2003 , 201 , 8.9, cRepository.findByCatname("Adventure").get(0)));
			mRepository.save(new Movie("Fight Club" , "David Fincher" , 1999 , 139 , 8.8, cRepository.findByCatname("Drama").get(0)));
			mRepository.save(new Movie("Spirited Away" , "Hayao Miyazaki" , 2001 , 125 , 8.6, cRepository.findByCatname("Animation").get(0)));
			
			// Creating default authentications: admin/admin and user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);
			
			log.info("fetching all movies");
			for (Movie movie : mRepository.findAll()) {
				log.info(movie.toString());
			}
		};
	}
}
