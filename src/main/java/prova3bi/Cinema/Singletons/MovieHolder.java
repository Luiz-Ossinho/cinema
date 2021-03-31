package prova3bi.Cinema.Singletons;

import prova3bi.Cinema.Domain.Entidades.Filme;

public final class MovieHolder {
	private Filme movie;
	private static MovieHolder holder = new MovieHolder();
	public Filme getMovie() {
		return movie;
	}
	public void setMovie(Filme movie) {
		this.movie = movie;
	}
	public static MovieHolder getInstance() {
		return holder;
	}

}
