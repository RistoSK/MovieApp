package fi.movie.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Movie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private String director;
	private Long year;
	private Long duration;
	private double rating;
	

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "catid")
    private Category category;
	
	 public Movie()
	 {	 
	 }
	 
	public Movie(String title, String director , long year , long duration , double rating, Category category)
	{
		super();
		this.title = title;
		this.director = director;
		this.year = year;
		this.duration = duration;
		this.rating = rating;
		this.category = category;
	}

	public Category getCategory()
	{
		return category;
	}

	public void setCategory(Category category)
	{
		this.category = category;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDirector()
	{
		return director;
	}

	public void setDirector(String director)
	{
		this.director = director;
	}

	public Long getYear()
	{
		return year;
	}

	public void setYear(Long year)
	{
		this.year = year;
	}

	public Long getDuration()
	{
		return duration;
	}

	public void setDuration(Long duration)
	{
		this.duration = duration;
	}

	public double getRating()
	{
		return rating;
	}

	public void setRating(double rating)
	{
		this.rating = rating;
	}

	@Override
	public String toString() {
		if (this.category != null)
			return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", year=" + year + ", duration=" + duration + ", rating=" + rating + " category =" + this.getCategory() + "]";		
		else
			return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", year=" + year + ", duration=" + duration + ", rating=" + rating + "]";
	}
}