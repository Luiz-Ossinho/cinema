module prova3bi.Cinema {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires com.github.librepdf.openpdf;
	requires java.desktop;
	requires com.google.zxing;
	requires com.google.zxing.javase;
	requires javafx.graphics;

    opens prova3bi.Cinema.Application to javafx.fxml;
    exports prova3bi.Cinema.Application;
    exports prova3bi.Cinema.Data;
}