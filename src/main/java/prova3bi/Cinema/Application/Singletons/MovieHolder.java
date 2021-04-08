package prova3bi.Cinema.Application.Singletons;

import javafx.collections.ObservableList;
import prova3bi.Cinema.Domain.Entities.Movie;

public final class MovieHolder {
	private static MovieHolder holder = new MovieHolder();
	private ObservableList<Movie> obsList;
	private Movie movie;
	public ObservableList<Movie> getObsList() {
		return obsList;
	}
	public void setObsList(ObservableList<Movie> obsList) {
		this.obsList = obsList;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public static MovieHolder getInstance() {
		return holder;
	}
	public void ResetMovie() {
		this.movie = null;
	}
	public void ResetObsList() {
		this.obsList = null;
	}
	public void Reset() {
		ResetMovie();
		ResetObsList();
	}
	public boolean IsEmpty() {
		return this.movie==null;
	}
}
