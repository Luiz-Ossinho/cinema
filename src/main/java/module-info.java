module prova3bi.Cinema {
    requires javafx.controls;
    requires javafx.fxml;

    opens prova3bi.Cinema to javafx.fxml;
    exports prova3bi.Cinema;
}