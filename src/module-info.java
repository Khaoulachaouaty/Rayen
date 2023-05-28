module Voiture {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires javafx.base;
	requires java.sql;
	exports Controller;
	
	
	opens Classes to javafx.base;
	opens Controller to javafx.fxml;

	opens application to javafx.graphics, javafx.fxml;
}
