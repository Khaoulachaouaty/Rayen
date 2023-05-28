package Controller;

import application.Connexion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Classes.Vehicule;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifierController {

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

    private Vehicule vehicule;
    
    @FXML
    private Parent fxml;

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;

        marque_v.setText(vehicule.getMarque());
        modele_v.setText(vehicule.getModele());
        categorie_v.setText(vehicule.getCategorie());
        couleur_v.setText(vehicule.getCouleur());
        prix_v.setText(String.valueOf(vehicule.getPrix()));
        mat_v.setText(vehicule.getMatricule());

        if (vehicule.getStatut().equals("Disponible")) {
            disp_v.setSelected(true);
            non_disp_v.setSelected(false);
        } else {
            disp_v.setSelected(false);
            non_disp_v.setSelected(true);
        }
    }

    @FXML
    private void modifierVehicule() {
        // Récupérer les valeurs modifiées des champs de texte 
        String marque = marque_v.getText();
        String modele = modele_v.getText();
        String categorie = categorie_v.getText();
        String couleur = couleur_v.getText();
        double prix = Double.parseDouble(prix_v.getText());
        String matricule = mat_v.getText();
        String statut = disp_v.isSelected() ? "Disponible" : "Non disponible";

        // Mettre à jour les informations du véhicule
        vehicule.setMarque(marque);
        vehicule.setModele(modele);
        vehicule.setCategorie(categorie);
        vehicule.setCouleur(couleur);
        vehicule.setPrix(prix);
        vehicule.setMatricule(matricule);
        vehicule.setStatut(statut);

        // Mettre à jour les valeurs dans la base de données
        try {
            Connection conn = Connexion.getCn();
            String query = "UPDATE vehicule SET marque = ?, modele = ?, categorie = ?, couleur = ?, prix = ?, statut = ? WHERE matricule = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, marque);
            statement.setString(2, modele);
            statement.setString(3, categorie);
            statement.setString(4, couleur);
            statement.setDouble(5, prix);
            statement.setString(6, statut);
            statement.setString(7, matricule);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Fermer la fenêtre de modification
        marque_v.getScene().getWindow().hide();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/vehicule.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
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
