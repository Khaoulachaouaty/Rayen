package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Classes.Client;
import application.Connexion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModifierClientController {
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

    private Client client;

    public void setClient(Client client) {
        this.client = client;
        if (client != null) {
            nom_client.setText(client.getNomComplet());
            adr_client.setText(client.getAdresse());
            mail_client.setText(client.getMail());
            cin_client.setText(client.getNumCin());
            tel_client.setText(client.getNumeroTelephone());
            mat_client.setText(client.getMatriculeVoitureLouee());
        }
    }

    @FXML
    private void modifierClient() {
        // Récupérer les valeurs modifiées des champs de texte
        String nom = nom_client.getText();
        String adresse = adr_client.getText();
        String mail = mail_client.getText();
        String cin = cin_client.getText();
        String telephone = tel_client.getText();
        String matricule = mat_client.getText();

        // Mettre à jour les informations du client
        client.setNomComplet(nom);
        client.setAdresse(adresse);
        client.setMail(mail);
        client.setNumCin(cin);
        client.setNumeroTelephone(telephone);
        client.setMatriculeVoitureLouee(matricule);

        // Mettre à jour les valeurs dans la base de données
        try {
            Connection conn = Connexion.getCn();
            String query = "UPDATE client SET nom = ?, adresse = ?, mail = ?, cin = ?, tel = ?, matricule = ? WHERE cin = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, adresse);
            statement.setString(3, mail);
            statement.setString(4, cin);
            statement.setString(5, telephone);
            statement.setString(6, matricule);
            statement.setString(7, cin);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Fermer la fenêtre de modification
        nom_client.getScene().getWindow().hide();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/interfaces/client.fxml"));
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
        Stage acceuil = new Stage();
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/interfaces/acceuil.fxml"));
            Scene scene = new Scene(fxml);
            acceuil.setScene(scene);
            acceuil.setTitle("Acceuil");
            acceuil.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void client() {
        Stage gestionClients = new Stage();
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/interfaces/client.fxml"));
            Scene scene = new Scene(fxml);
            gestionClients.setScene(scene);
            gestionClients.setTitle("Gestion des clients");
            gestionClients.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void vehicule() {
        Stage gestionVehicules = new Stage();
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("/interfaces/vehicule.fxml"));
            Scene scene = new Scene(fxml);
            gestionVehicules.setScene(scene);
            gestionVehicules.setTitle("Gestion des véhicules");
            gestionVehicules.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
