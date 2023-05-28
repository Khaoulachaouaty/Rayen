package Controller;

import Classes.Vehicule;
import application.Connexion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class VehiculeController {
    @FXML
    private TableView<Vehicule> table_vehicule;
    @FXML
    private TableColumn<Vehicule, String> marque_v;
    @FXML
    private TableColumn<Vehicule, String> modele_v;
    @FXML
    private TableColumn<Vehicule, String> categorie_v;
    @FXML
    private TableColumn<Vehicule, String> couleur_v;
    @FXML
    private TableColumn<Vehicule, Double> prix_v;
    @FXML
    private TableColumn<Vehicule, String> mat_v;
    @FXML
    private TableColumn<Vehicule, String> statut_v;
    @FXML
    private Parent fxml;
    
    @FXML
    private Button accueilButton;


    @FXML
    public void initialize() {
        // Configure les colonnes de la table avec les propriétés des objets Vehicule
    	marque_v.setCellValueFactory(new PropertyValueFactory<>("marque"));
    	modele_v.setCellValueFactory(new PropertyValueFactory<>("modele"));
    	categorie_v.setCellValueFactory(new PropertyValueFactory<>("categorie"));
    	couleur_v.setCellValueFactory(new PropertyValueFactory<>("couleur"));
    	prix_v.setCellValueFactory(new PropertyValueFactory<>("prix"));
    	mat_v.setCellValueFactory(new PropertyValueFactory<>("matricule"));
    	statut_v.setCellValueFactory(new PropertyValueFactory<>("statut"));

        // Charger les données des véhicules à partir de la base de données
        try  {
        	Connection conn = Connexion.getCn();
            String query = "SELECT * FROM vehicule";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String marque = resultSet.getString("marque");
                String modele = resultSet.getString("modele");
                String categorie = resultSet.getString("categorie");
                String couleur = resultSet.getString("couleur");
                double prix = resultSet.getDouble("prix");
                String matricule = resultSet.getString("matricule");
                String statut = resultSet.getString("statut");

                Vehicule vehicule = new Vehicule(marque, modele, categorie, couleur, prix, statut, matricule);
                table_vehicule.getItems().add(vehicule);
                //getItems() permet d'obtenir la liste des éléments de la table
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void supprimer() {
    	//récupère l'élément sélectionné dans la table des véhicules
        Vehicule selectedVehicule = table_vehicule.getSelectionModel().getSelectedItem();
        if (selectedVehicule != null) {
            // Supprimer la ligne de la base de données
            suppVehiculeDeDatabase(selectedVehicule);
            
            // Supprimer la ligne du TableView
            table_vehicule.getItems().remove(selectedVehicule);
        }
    }
    
    private void suppVehiculeDeDatabase(Vehicule vehicule) {
        try {
            Connection conn = Connexion.getCn();
            String query = "DELETE FROM vehicule WHERE matricule = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, vehicule.getMatricule());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ajouter() {
    	Stage acceuil= new Stage();
        try{
        	fxml = FXMLLoader.load(getClass().getResource("/interfaces/ajout.fxml"));
        	Scene scene= new Scene(fxml);
        	acceuil.setScene(scene);
        	acceuil.setTitle("Ajouter véhicule");
        	acceuil.show();
        }catch (IOException e)
        {
        	e.printStackTrace();
        }
    }
    
    
    @FXML
    void modifier() {
        Vehicule selectedVehicule = table_vehicule.getSelectionModel().getSelectedItem();
        if (selectedVehicule != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/modifier.fxml"));
                Parent modifierFXML = loader.load();
                //récupère le contrôleur associé au fichier FXML
                ModifierController modifierController = loader.getController();

                // Passer la ligne sélectionnée à ModifierController
                modifierController.setVehicule(selectedVehicule);

                Stage stage = new Stage();
                Scene scene = new Scene(modifierFXML);
                stage.setScene(scene);
                stage.setTitle("Modifier véhicule");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void client() {
    	Stage stage= new Stage();
        try{
        	fxml = FXMLLoader.load(getClass().getResource("/interfaces/client.fxml"));
        	Scene scene= new Scene(fxml);
        	stage.setScene(scene);
        	stage.setTitle("Gestion des clients");
        	stage.show();
        }catch (IOException e)
        {
        	e.printStackTrace();
        }
    }
	
	@FXML
    void accueil() {
    	Stage stage= new Stage();
        try{
        	fxml = FXMLLoader.load(getClass().getResource("/interfaces/acceuil.fxml"));
        	Scene scene= new Scene(fxml);
        	stage.setScene(scene);
        	stage.setTitle("Gestion des véhicules");
        	stage.show();
        }catch (IOException e)
        {
        	e.printStackTrace();
        }
    }

}
