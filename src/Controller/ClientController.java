package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Classes.Client;
import application.Connexion;


public class ClientController implements Initializable {

    @FXML
    private TableView<Client> table_client;
    
    @FXML
    private TableColumn<Client, String> nom_client;
    
    @FXML
    private TableColumn<Client, String> adr_client;
    
    @FXML
    private TableColumn<Client, String> mail_client;
    
    @FXML
    private TableColumn<Client, String> cin_client;
    
    @FXML
    private TableColumn<Client, String> tel_client;
    
    @FXML
    private TableColumn<Client, String> mat_voiture;
    
    @FXML
    private Parent fxml;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configuration des cellules pour les colonnes
        nom_client.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        adr_client.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        mail_client.setCellValueFactory(new PropertyValueFactory<>("mail"));
        cin_client.setCellValueFactory(new PropertyValueFactory<>("numCin"));
        tel_client.setCellValueFactory(new PropertyValueFactory<>("numeroTelephone"));
        mat_voiture.setCellValueFactory(new PropertyValueFactory<>("matriculeVoitureLouee"));

        try {
            // Connexion à la base de données
            Connection conn = Connexion.getCn();

            String query = "SELECT * FROM client";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            // Ajout des clients à la table
            while (resultSet.next()) {
                String nomComplet = resultSet.getString("nom");
                String adresse = resultSet.getString("adresse");
                String email = resultSet.getString("mail");
                String numCin = resultSet.getString("cin");
                String telephone = resultSet.getString("tel");
                String matriculeVoiture = resultSet.getString("matricule");

                Client client = new Client(nomComplet, adresse, telephone, numCin, matriculeVoiture, email);
                table_client.getItems().add(client);
            }

            // Fermeture des ressources
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    public void supprimer() {
        Client selectedClient = table_client.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            // Supprimer la ligne de la base de données
            suppClientDeDatabase(selectedClient);
            
            // Supprimer la ligne du TableView
            table_client.getItems().remove(selectedClient);
        }
    }
    
    private void suppClientDeDatabase(Client client) {
        try {
            Connection conn = Connexion.getCn();
            String query = "DELETE FROM client WHERE cin = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, client.getNumCin());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @FXML
    void ajouter() {
    	Stage acceuil= new Stage();
        try{
        	fxml = FXMLLoader.load(getClass().getResource("/interfaces/ajoutClient.fxml"));
        	Scene scene= new Scene(fxml);
        	acceuil.setScene(scene);
        	acceuil.setTitle("Ajouter client");
        	acceuil.show();
        }catch (IOException e)
        {
        	e.printStackTrace();
        }
    }
    

    
    @FXML
    void modifier() {
        Client selectedClient = table_client.getSelectionModel().getSelectedItem();
        if (selectedClient != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/modifierClient.fxml"));
                Parent modifierFXML = loader.load();
                ModifierClientController modifierClientController = loader.getController();

                // Passer la ligne sélectionnée à ModifierClientController
                modifierClientController.setClient(selectedClient);

                Stage modifierStage = new Stage();
                Scene scene = new Scene(modifierFXML);
                modifierStage.setScene(scene);
                modifierStage.setTitle("Modifier client");
                modifierStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    @FXML
    void acceuil() {
    	Stage acceuil= new Stage();
        try{
        	fxml = FXMLLoader.load(getClass().getResource("/interfaces/acceuil.fxml"));
        	Scene scene= new Scene(fxml);
        	acceuil.setScene(scene);
        	acceuil.setTitle("Accueil");
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
