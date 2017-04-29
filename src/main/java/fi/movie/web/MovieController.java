package fi.movie.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.movie.domain.CategoryRepository;
import fi.movie.domain.Movie;
import fi.movie.domain.MovieRepository;

import java.util.List;

@Controller
public class MovieController {
	@Autowired
	private MovieRepository mRepository;
	
	@Autowired
	private CategoryRepository cRepository;
	
	@RequestMapping(value="/login")
   	public String login() {
   		return "login";
   	}
	
	@RequestMapping(value="/movielist")
    public String movieList(Model model) {
		model.addAttribute("movie", mRepository.findAll());
       return "movielist";
	}
	
	// RESTful service to get all movies
	@RequestMapping(value="/movieJson", method = RequestMethod.GET)
	public @ResponseBody List<Movie> movieListRest() {
	return (List<Movie>) mRepository.findAll();
	}
	
	// RESTful service to get movies by id
    @RequestMapping(value="/movie/{id}", method = RequestMethod.GET)
    public @ResponseBody Movie findStudentRest(@PathVariable("id") Long id) {	
    	return mRepository.findOne(id);
    } 
    
    // Add button only accessible to admin
    @PreAuthorize("hasAuthority('ADMIN')")
   	@RequestMapping(value = "/add")
    public String addMovie(Model model) {
		model.addAttribute("movie", new Movie());
		model.addAttribute("categories", cRepository.findAll());
       return "addmovie";
   	}
   	
    // Save a Movie
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Movie movie){
        mRepository.save(movie);
        return "redirect:movielist";
    }  
    
    //Delete a Movie
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long movieId, Model model) {
    	mRepository.delete(movieId);
        return "redirect:../movielist";
    }       
}