package prova3bi.Cinema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Repositories.LoginRepository;
import prova3bi.Cinema.Data.Repositories.MovieRepository;
import prova3bi.Cinema.Data.Repositories.RoomRepository;
import prova3bi.Cinema.Domain.Entidades.TrilhaSonora;
import prova3bi.Cinema.Domain.Entidades.Filme;
import prova3bi.Cinema.Domain.Entidades.Login;
import prova3bi.Cinema.Domain.Entidades.NivelPermissao;
import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Entidades.TipoSala;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ILoginRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IMovieRepository;
import prova3bi.Cinema.Domain.Interfaces.Repositories.IRoomRepository;
import prova3bi.Cinema.Services.SessionService;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("primary"), 640, 480);
		stage.setScene(scene);
		stage.show();
	}

	static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static Parent loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		// var filmeTest = new Filme(0);
		var context = new DBContext();
//		ILoginRepository loginRepo = new LoginRepository(context);
//		loginRepo.Add(new Login("Usuario8", "SenhaDigitda123", NivelPermissao.Admin));
//		var login = loginRepo.Get("Usuario8");
//		IMovieRepository filmesRepo = new MovieRepository(context);
//		var newFilme = new Filme("Pantera Negrao", "", "Sinopse braba la", Audio.Dublado);
//		int id = filmesRepo.Add(newFilme);
//		var filme = filmesRepo.Get(id);
//		IRoomRepository salasRepo = new RoomRepository(context);
//		var sala = new Sala(TipoSala.XD, 64, 123);
//		salasRepo.Add(sala);
//		var salas = salasRepo.GetAll();
//		Codigo acima Funcionando
//		var listalevementeMaior = SessionService.testeLogica(10);
//		var listaMaxima = SessionService.testeLogica(15);
		launch();
	}

}