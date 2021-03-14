module prova3bi.Cinema {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.base;

    opens prova3bi.Cinema to javafx.fxml;
    exports prova3bi.Cinema;
    exports prova3bi.Cinema.Data;
}