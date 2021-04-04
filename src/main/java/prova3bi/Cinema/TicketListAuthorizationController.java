package prova3bi.Cinema;

import java.io.IOException;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import prova3bi.Cinema.Domain.Entities.Ticket;

public class TicketListAuthorizationController {

	@FXML
	private HBox sectionContainer;

	@FXML
	private TableView<Ticket> tableTicket;

	@FXML
	private TableColumn<Ticket, ?> columnSeat;

	@FXML
	private TableColumn<Ticket, ?> columnStatus;

	@FXML
	private TableColumn<Ticket, ?> columnSection;

	@FXML
	private TableColumn<Ticket, Ticket> columnChangeStatus;

	@FXML
	void switchGoBack(MouseEvent event) throws IOException {
		App.setRoot("Dashboard");
	}

	private void initializeNodes() {
		columnSeat.setCellValueFactory(new PropertyValueFactory<>("id"));
		columnStatus.setCellValueFactory(new PropertyValueFactory<>("name"));
	/*	columnSection.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Appointment, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(TableColumn.CellDataFeatures<Appointment, String> param) {
						return new SimpleStringProperty(param.getValue().getPacienteid().getNome());
					}
				});*/

		//appointmentTable.setItems(sortedData);
	}

	private void initChangeStatusButtons() {
		columnChangeStatus.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		columnChangeStatus.setCellFactory(param -> new TableCell<Ticket, Ticket>() {
			private final Button button = new Button("quitar ticket");

			@Override
			protected void updateItem(Ticket obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
			}
		});
	}

}