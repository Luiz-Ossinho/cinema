package prova3bi.Cinema.Util;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class Constraints {

	public static void setTextFieldInteger(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*")) {
				txt.setText(oldValue);
			}
		});
	}

	public static void setTextFieldMaxLength(TextField txt, int max) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && newValue.length() > max) {
				txt.setText(oldValue);
			}
		});
	}

	public static void setTextFieldDouble(TextField txt) {
		txt.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && !newValue.matches("\\d*([\\.]\\d*)?")) {
				txt.setText(oldValue);
			}
		});
	}

	public static void dateField(final TextField textField) {
		maxField(textField, 10);

		textField.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() < 11) {
					String value = textField.getText();
					value = value.replaceAll("[^0-9]", "");
					value = value.replaceFirst("(\\d{2})(\\d)", "$1/$2");
					value = value.replaceFirst("(\\d{2})\\/(\\d{2})(\\d)", "$1/$2/$3");
					value = value.replaceFirst("", "$1/$2/$3 $4:$5");
					textField.setText(value);
					positionCaret(textField);
				}
			}
		});
	}

	private static void positionCaret(final TextField textField) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// Posiciona o cursor sempre a direita.
				textField.positionCaret(textField.getText().length());
			}
		});
	}

	private static void maxField(final TextField textField, final Integer length) {
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
				if (newValue.length() > length)
					textField.setText(oldValue);
			}
		});
	}
}