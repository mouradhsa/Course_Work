package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class menuClientController{

    @FXML
    private Button btnchoix;

    @FXML
    private Button btncompo;

    @FXML
    private Button btnvoircomm;

    @FXML
    private Button btnretour;
    
    static List<Boisson> BoissonsAlaCarte = new ArrayList<Boisson>();	//liste boissons dispos
	static List<Cocktail> CocktailsAlaCarte = new ArrayList<Cocktail>();	//liste cocktails dispos
	
	public static List<Boisson> getBoissonsAlaCarte() {
		return BoissonsAlaCarte;
	}

	public static void setBoissonsAlaCarte(List<Boisson> boissonsAlaCarte) {
		BoissonsAlaCarte = boissonsAlaCarte;
	}

    public static List<Cocktail> getCocktailsAlaCarte() {
		return CocktailsAlaCarte;
	}

	public static void setCocktailsAlaCarte(List<Cocktail> cocktailsAlaCarte) {
		CocktailsAlaCarte = cocktailsAlaCarte;
	}

	@FXML
    void btnChoix(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("choixBoisson.fxml"));
		Stage window = (Stage) btnretour.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
		
    }

    @FXML
    void btnCompo(ActionEvent event) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("menuCocktail.fxml"));
		Stage window = (Stage) btnretour.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
    }

    @FXML
    void btnRetour(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("Bar.fxml"));
		Stage window = (Stage) btnretour.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
    }

    @FXML
    void btnvoirCommande(ActionEvent event) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("afficheCommande.fxml"));
		Stage window = (Stage) btnvoircomm.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
    }


}
