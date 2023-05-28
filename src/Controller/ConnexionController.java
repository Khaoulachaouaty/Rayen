package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Classes.Admin;
import application.Connexion;


public class ConnexionController implements Initializable {
    @FXML
    private TextField mail;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox check;

    @FXML
    private TextField nom;

    @FXML
    private TextField phone;

    @FXML
    private TextField prenom;
    
    @FXML
    private Button connecter;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }

    @FXML
    private void handleConnect(ActionEvent event) {
        String email = mail.getText();
        String adminPassword = password.getText();

        try {
            Connection conn = Connexion.getCn();
            if (conn == null) {
                System.out.println("Erreur de connexion à la base de données. Veuillez vérifier les paramètres de connexion.");
                return;
            }

            String query = "SELECT * FROM admin WHERE mail = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, adminPassword);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                System.out.println("Connexion réussie !");
                loadAccueilScene();
            } else {
                System.out.println("Échec de la connexion. Veuillez vérifier vos informations.");
            }

            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSignUp(ActionEvent event) {
        String nom_admin = nom.getText();
        String prenom_admin = prenom.getText();
        String password_admin = password.getText();
        String mail_admin = mail.getText();
        String phone_admin = phone.getText();
        // Vérifier si tous les champs sont remplis
        if (mail_admin.isEmpty() || password_admin.isEmpty() || nom_admin.isEmpty() || phone_admin.isEmpty()
                || prenom_admin.isEmpty()) {
            System.out.println("Veuillez remplir tous les champs.");
            return;
        }

        // Vérifier si le bouton de case à cocher est coché
        if (!check.isSelected()) {
            System.out.println("Veuillez confirmer les informations.");
            return;
        }

        try (Connection conn = Connexion.getCn()) {
            if (conn == null) {
                System.out.println("Erreur de connexion à la base de données. Veuillez vérifier les paramètres de connexion.");
                return;
            }

            // Vérifier si le mail d'admin existe déjà dans la base de données
            String checkQuery = "SELECT * FROM admin WHERE mail = ?";
            try (PreparedStatement checkStatement = conn.prepareStatement(checkQuery)) {
                checkStatement.setString(1, mail_admin);
                try (ResultSet checkResult = checkStatement.executeQuery()) {
                    if (checkResult.next()) {
                        System.out.println("L'adresse d'admin existe déjà. Veuillez en choisir un autre.");
                        return;
                    }
                }
            }

            // Créer un objet Admin
            Admin admin = new Admin(nom_admin, prenom_admin, password_admin, mail_admin, phone_admin);

            // Insérer les informations de l'utilisateur dans la base de données
            String query = "INSERT INTO admin (nom, prenom, mail, password, phone) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setString(1, admin.getNom());
                statement.setString(2, admin.getPrenom());
                statement.setString(3, admin.getEmail());
                statement.setString(4, admin.getPassword());
                statement.setString(5, admin.getPhone());
                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("Inscription réussie !");
                    loadAccueilScene();
                } else {
                    System.out.println("Échec de l'inscription. Veuillez réessayer.");
                    openInscription();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void openInscription() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/interfaces/Inscription.fxml"));
            Parent inscriptionFXML = fxmlLoader.load();
            Stage inscriptionStage = new Stage();
            Scene inscriptionScene = new Scene(inscriptionFXML);

            inscriptionStage.setScene(inscriptionScene);
            inscriptionStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAccueilScene() {
        try {
            Parent accueilFXML = FXMLLoader.load(getClass().getResource("/interfaces/acceuil.fxml"));
            Scene accueilScene = new Scene(accueilFXML);
            Stage primaryStage = (Stage) connecter.getScene().getWindow();
            primaryStage.setScene(accueilScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
