package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SectionListController implements Initializable {

	@FXML
	private HBox backgroundScene;

	@FXML
	private HBox recentlyMovieContainer;

	List<Movie> recentlyMovie;

	private List<Movie> getRecentlyMovie() {
		List<Movie> lm = new ArrayList<>();

		Movie movie = new Movie();
		movie.setImageMovie("vingImage.jpg");
		movie.setAbout("Vingadores Ultimato");
		lm.add(movie);

		movie = new Movie();
		movie.setImageMovie("jokerImage.png");
		movie.setAbout("Joker the movie");
		lm.add(movie);

		movie = new Movie();
		movie.setImageMovie("vingImage.jpg");
		movie.setAbout("Vingadores Ultimato");
		lm.add(movie);

		movie = new Movie();
		movie.setImageMovie("jokerImage.png");
		movie.setAbout("Joker the movie");
		lm.add(movie);

		movie = new Movie();
		movie.setImageMovie("vingImage.jpg");
		movie.setAbout("Vingadores Ultimato");
		lm.add(movie);

		return lm;

	}
	
	@FXML 
	private void switchGoBack(MouseEvent event) throws IOException{
		App.setRoot("InitialPage");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		recentlyMovie = new ArrayList<>(getRecentlyMovie());
		try {
			for (Movie movie : recentlyMovie) {
				FXMLLoader fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("movie.fxml"));

				VBox vBox = fxml.load();
				MovieController movieController = fxml.getController();
				movieController.setData(movie);

				recentlyMovieContainer.getChildren().add(vBox);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}