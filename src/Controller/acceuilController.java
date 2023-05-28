package Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class acceuilController {
	
    @FXML
    private Parent fxml;
	
	@FXML
    void vehicule() {
    	Stage acceuil= new Stage();
        try{
        	fxml = FXMLLoader.load(getClass().getResource("/interfaces/vehicule.fxml"));
        	Scene scene= new Scene(fxml);
        	acceuil.setScene(scene);
        	acceuil.setTitle("Gestion des Véhicules");
        	acceuil.show();
        }catch (IOException e)
        {
        	e.printStackTrace();
        }
    }
	
	@FXML
    void client() {
    	Stage acceuil= new Stage();
        try{
        	fxml = FXMLLoader.load(getClass().getResource("/interfaces/client.fxml"));
        	Scene scene= new Scene(fxml);
        	acceuil.setScene(scene);
        	acceuil.setTitle("Gestion des clients");
        	acceuil.show();
        }catch (IOException e)
        {
        	e.printStackTrace();
        }
    }
}
