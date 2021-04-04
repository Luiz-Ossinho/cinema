package prova3bi.Cinema;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import prova3bi.Cinema.Domain.Entities.Chair;

public class ChairController  implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	private Chair chair;

	public void setChair(Chair chair) {
		this.chair = chair;
	}
	
	

}
