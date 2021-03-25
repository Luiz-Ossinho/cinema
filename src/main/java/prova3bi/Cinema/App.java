package prova3bi.Cinema;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prova3bi.Cinema.Data.DBContext;
import prova3bi.Cinema.Data.Repositories.LoginRepository;
import prova3bi.Cinema.Domain.Entidades.Login;
import prova3bi.Cinema.Domain.Entidades.NivelPermissao;
import prova3bi.Cinema.Domain.Interfaces.Repositories.ILoginRepository;

/**
 * JavaFX App
 */
public class App extends Application {

	private static Scene scene;

	@Override
	public void start(Stage stage) throws IOException {
		scene = new Scene(loadFXML("InitialPage"), 1200, 675);
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
    	ILoginRepository loginRepo = new LoginRepository(new DBContext());
    	loginRepo.Add(new Login("Usuario1", "SenhaDigitda123", NivelPermissao.Admin));
    	var login = loginRepo.Get("Usuario1");
    	System.out.println(Login.Hash("123"));
        launch();
    }

}