package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class menuBarmanController implements Initializable{

    @FXML
    private Button btngestion;

    @FXML
    private Button btnretour;
    
    @FXML
    private Text txtca;


    @FXML
    void btnGestion(ActionEvent event) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("stockBar.fxml"));
		Stage window = (Stage) btnretour.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
    }

    @FXML
    void btnRetour(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("Bar.fxml"));
		Stage window = (Stage) btnretour.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int stonks = afficheCommandeController.getStonks();
		txtca.setText(stonks +" €");
		
	}
    
}
