package application;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class BarController implements Initializable{
	
	
	 @FXML
	 private Button btnclient;

	 @FXML
	 private Button btnbarman;

	 @FXML
	 private Button btnsauv;
	 
	 @FXML
	 private Button btnquitter;
	 
	 @FXML
	 private AnchorPane rootpane;

	 
	 @FXML
	 void btnBarman(ActionEvent event) throws Exception{
		 Parent root = FXMLLoader.load(getClass().getResource("menuBarman.fxml"));
		 Stage window = (Stage) btnclient.getScene().getWindow();
		 window.setScene(new Scene(root,600,400));
	 }
	 
	 
	 @FXML
	 void btnClient(ActionEvent event) throws Exception {
		 Parent root = FXMLLoader.load(getClass().getResource("menuClient.fxml"));
		 Stage window = (Stage) btnclient.getScene().getWindow();
		 window.setScene(new Scene(root,600,400));
	 }
	 
	 
	 @FXML
	 void btnSauv(ActionEvent event) throws Exception{
		 Parent root = FXMLLoader.load(getClass().getResource("menuSauvegarde.fxml"));
		 Stage window = (Stage) btnclient.getScene().getWindow();
		 window.setScene(new Scene(root,600,400));
	 }
	 
	 @FXML
	 void btnQuitter(ActionEvent event) {
		 Stage stage = (Stage)btnquitter.getScene().getWindow();
		 stage.close();
	 }


	//boisson non alcoolisee = nom, contenance, prix, qte, degre sucre
	    Boisson bs1 = new BoissonNonAlcoolisee("Coca-Cola", 50, 3, 20, 40);
		Boisson bs2 = new BoissonNonAlcoolisee("Jus de tomate", 50, 2, 15, 30);
		Boisson bs3 = new BoissonNonAlcoolisee("Jus de Citron", 20, 2, 12, 30);
		//boisson non alcoolisee = nom, contenance, prix, qte, degre alcool
		Boisson ba1 = new BoissonAlcoolisee("Vodka", 10, 5, 8, 90);
		Boisson ba2 = new BoissonAlcoolisee("Gin", 20, 4, 10, 60);
		Boisson ba3 = new BoissonAlcoolisee("Rhum blanc", 20, 6, 10, 80);
		

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			//cocktail
					HashMap<Boisson, Integer> compo = new HashMap<Boisson, Integer>();
					compo.put(ba2, 1); compo.put(bs2, 1); compo.put(bs3, 1);
					int qte = menuCocktailController.calculQteCocktail(compo);
					Cocktail c1 = new Cocktail("Bloody Mary", 50, 20, qte, compo);
					if (choixBoissonController.getBoissonsAlaCarte().isEmpty()) {
						choixBoissonController.ajouteBoissonStock(bs1, 20);
						choixBoissonController.ajouteBoissonStock(bs2, 15);
						choixBoissonController.ajouteBoissonStock(bs3, 12);
						choixBoissonController.ajouteBoissonStock(ba1, 8);
						choixBoissonController.ajouteBoissonStock(ba2, 10);
						choixBoissonController.ajouteBoissonStock(ba3, 10);
						choixBoissonController.ajouteCocktailStock(c1, qte);
					}
			
		}
	 
}
