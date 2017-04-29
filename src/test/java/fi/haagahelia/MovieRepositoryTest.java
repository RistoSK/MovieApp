package fi.haagahelia;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.movie.domain.Category;
import fi.movie.domain.Movie;
import fi.movie.domain.MovieRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository repository;

    @Test
    public void findByAuthorShouldReturnMovie()
    {
        List<Movie> movies = repository.findByDirector("Francis Ford Coppola"); 
        assertThat(movies).hasSize(1);
        assertThat(movies.get(0).getTitle()).isEqualTo("Godfather");
    }
    
    @Test
    public void createNewMovie()
    {
    	Movie movie = new Movie("Star Wars", "George Lucas", 1977 , 121 , 8.7 , new Category("Fantasy"));
    	repository.save(movie);
    	assertThat(movie.getId()).isNotNull();
    }    

}