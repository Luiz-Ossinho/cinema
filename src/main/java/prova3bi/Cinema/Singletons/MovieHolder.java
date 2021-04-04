package prova3bi.Cinema.Singletons;

import javafx.collections.ObservableList;
import prova3bi.Cinema.Domain.Entidades.Filme;

public final class MovieHolder {
	private static MovieHolder holder = new MovieHolder();
	private ObservableList<Filme> obsList;
	private Filme movie;
	public ObservableList<Filme> getObsList() {
		return obsList;
	}
	public void setObsList(ObservableList<Filme> obsList) {
		this.obsList = obsList;
	}
	public Filme getMovie() {
		return movie;
	}
	public void setMovie(Filme movie) {
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
