package Controller;

import application.Connexion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AjoutClientController {
	 @FXML
	    private TextField adr_client;

	    @FXML
	    private TextField cin_client;

	    @FXML
	    private TextField mail_client;

	    @FXML
	    private TextField mat_client;

	    @FXML
	    private TextField nom_client;

	    @FXML
	    private TextField tel_client;

    @FXML
    private Parent fxml;
    
    @FXML
    public void ajouterClient() {
        String nom = nom_client.getText();
        String adr = adr_client.getText();
        String mail = mail_client.getText();
        String cin = cin_client.getText();
        String tel = tel_client.getText();
        String matricule = mat_client.getText();

        // Insérer les données du véhicule dans la base de données
        try {
            Connection conn = Connexion.getCn();
            String query = "INSERT INTO client (nom, adresse, mail, cin, tel, matricule) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, adr);
            statement.setString(3, mail);
            statement.setString(4, cin);
            statement.setString(5, tel);
            statement.setString(6, matricule);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Le client a été ajouté avec succès !");
                rediger_vers_Client();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void rediger_vers_Client() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/client.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) cin_client.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void acceuil() {
    	Stage acceuil= new Stage();
        try{
        	fxml = FXMLLoader.load(getClass().getResource("/interfaces/acceuil.fxml"));
        	Scene scene= new Scene(fxml);
        	acceuil.setScene(scene);
        	acceuil.setTitle("Acceuil");
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
	
	@FXML
    void vehicule() {
    	Stage acceuil= new Stage();
        try{
        	fxml = FXMLLoader.load(getClass().getResource("/interfaces/vehicule.fxml"));
        	Scene scene= new Scene(fxml);
        	acceuil.setScene(scene);
        	acceuil.setTitle("Gestion des véhicules");
        	acceuil.show();
        }catch (IOException e)
        {
        	e.printStackTrace();
        }
    }
}
