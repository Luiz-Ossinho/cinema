package prova3bi.Cinema;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import prova3bi.Cinema.Domain.Entidades.Filme;
import prova3bi.Cinema.Domain.Entidades.Sala;
import prova3bi.Cinema.Domain.Entidades.Sessao;

public class SectionAdminFormController implements Initializable {

	@FXML
    private TextField txtInitialTime;

    @FXML
    private TextField txtFinalTime;

    @FXML
    private TextField txtPrice;

    @FXML
    private ComboBox<Sala> cbClass;

    @FXML
    private ComboBox<Filme> cbMovie;

    @FXML
    private Label initialTimeLabel;

    @FXML
    private Label finalTimeLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label cbClassLabel;

    @FXML
    private Label cbMovieLabel;

    @FXML
    void onSubmit(ActionEvent event) {
    	getFormData();
    	editFormData();
    }

    @FXML
    void switchGoBack(MouseEvent event) throws IOException {
    	App.setRoot("Dashboard");
    }
    
    private void getFormData() {

    }
    
    private void editFormData() {
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
