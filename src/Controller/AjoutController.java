package Controller;

import application.Connexion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AjoutController {
    @FXML
    private TextField marque_v;
    @FXML
    private TextField modele_v;
    @FXML
    private TextField categorie_v;
    @FXML
    private TextField couleur_v;
    @FXML
    private TextField prix_v;
    @FXML
    private TextField mat_v;
    @FXML
    private RadioButton disp_v;
    @FXML
    private RadioButton non_disp_v;
    @FXML
    private Parent fxml;
    
    @FXML
    public void ajouterVehicule() {
        String marque = marque_v.getText();
        String modele = modele_v.getText();
        String categorie = categorie_v.getText();
        String couleur = couleur_v.getText();
        double prix = Double.parseDouble(prix_v.getText());
        String matricule = mat_v.getText();
        String statut = disp_v.isSelected() ? "Disponible" : "Non disponible";

        // Insérer les données du véhicule dans la base de données
        try {
            Connection conn = Connexion.getCn();
            String query = "INSERT INTO vehicule (marque, modele, categorie, couleur, prix, matricule, statut) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, marque);
            statement.setString(2, modele);
            statement.setString(3, categorie);
            statement.setString(4, couleur);
            statement.setDouble(5, prix);
            statement.setString(6, matricule);
            statement.setString(7, statut);
            
            //retourne le nombre de lignes affectées par la requête.
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Le véhicule a été ajouté avec succès !");
                rediger_vers_Vehicule();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void rediger_vers_Vehicule() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/vehicule.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) mat_v.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void acceuil() {
    	Stage stage= new Stage();
        try{
        	fxml = FXMLLoader.load(getClass().getResource("/interfaces/acceuil.fxml"));
        	Scene scene= new Scene(fxml);
        	stage.setScene(scene);
        	stage.setTitle("Acceuil");
        	stage.show();
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
