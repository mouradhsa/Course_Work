package application;

import java.awt.TextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class choixBoissonController implements Initializable{
	
	static List<Boisson> BoissonsAlaCarte = new ArrayList<Boisson>();	//liste boissons dispos
	static List<Cocktail> CocktailsAlaCarte = new ArrayList<Cocktail>();	//liste cocktails dispos
	
	static afficheCommandeController maCommande = new afficheCommandeController();

	@FXML
    private TableView<Boisson> cartebar;
    
    @FXML
    private TableView<Boisson> tmpcommande;

    //Carte Bar
    @FXML
    private TableColumn<Boisson, String> carteboissons;

    @FXML
    private TableColumn<Boisson, Integer> carteprix;

    @FXML
    private TableColumn<Boisson, Integer> cartestock;

    //Commande en cours
    @FXML
    private TableColumn<Boisson, String> colboissons;

    @FXML
    private TableColumn<Boisson, Integer> colprix;
    
    @FXML
    private TableColumn<Boisson, Integer> colqte;

    @FXML
    private Button btnvalide;

    @FXML
    private Button btnretour;

    @FXML
    private Button ajoutercommande;

    @FXML
    private Button retirercommande;
   
    
    
    
    public static List<Boisson> getBoissonsAlaCarte() {
		return BoissonsAlaCarte;
	}

	public static void setBoissonsAlaCarte(List<Boisson> boissonsAlaCarte) {
		BoissonsAlaCarte = boissonsAlaCarte;
	}

	public static afficheCommandeController getMaCommande() {
		return maCommande;
	}

	public void setMaCommande(afficheCommandeController maCommande) {
		this.maCommande = maCommande;
	}
	
    public static List<Cocktail> getCocktailsAlaCarte() {
		return CocktailsAlaCarte;
	}

	public static void setCocktailsAlaCarte(List<Cocktail> cocktailsAlaCarte) {
		CocktailsAlaCarte = cocktailsAlaCarte;
	}
	
	@FXML
    void ajouterCommande(ActionEvent event) {
    	if(cartebar.getSelectionModel().getSelectedItem()==null) {
            Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Veuillez choisir une boisson");
            a.setHeaderText(null);
            a.showAndWait();
    	}else {
        	Boisson singleProduct=cartebar.getSelectionModel().getSelectedItem();
        	Boisson test = new Boisson(singleProduct.getNomBoisson(),singleProduct.getContenance(),singleProduct.getPrix(),1);
        	if (singleProduct.getQuantite()<1) {
    			Alert a= new Alert(AlertType.WARNING);
                a.setContentText("Désolé, nous n'en avons plus en stock.");
                a.setHeaderText(null);
                a.showAndWait();
        	}else {
            	maCommande.ajouteBoissonCommande(test, 1);
            	tmpcommande.getItems().add(test);
            	}
        	}
    	}
    

    

    @FXML
    void btnRetour(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("menuClient.fxml"));
		Stage window = (Stage) btnretour.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
    }

    @FXML
    void btnValide(ActionEvent event) throws Exception{
    	/**
    	for (int i=0; i<tmpcommande.getItems().size();i++) { 
    		maCommande.ajouteBoissonCommande(tmpcommande.getItems().get(i), 1);
    	}*/
    	System.out.println(maCommande.getTotalBoissons());
    	Parent root = FXMLLoader.load(getClass().getResource("menuClient.fxml"));
		Stage window = (Stage) btnretour.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
    }

    @FXML
    void retirerCommande(ActionEvent event) {
    	ObservableList<Boisson> allProduct;
        allProduct=tmpcommande.getItems();
        if(tmpcommande.getSelectionModel().getSelectedItem()==null)
        {
            Alert a= new Alert(AlertType.WARNING);
            a.setContentText("choisissez une boisson");
            a.setHeaderText(null);
            a.showAndWait();
        }
        else
        {
        	Boisson singleProduct=tmpcommande.getSelectionModel().getSelectedItem();
        	Alert alert= new Alert(AlertType.CONFIRMATION);
        	alert.setContentText("Retirer cette boisson ? \n"+singleProduct.getNomBoisson());
        	alert.setHeaderText(null);
        	Optional<ButtonType> action = alert.showAndWait();
        	if (action.get()==ButtonType.OK) {
        		if (singleProduct.getQuantite() > 1) {
        			int qte = singleProduct.getQuantite() - 1;
            		singleProduct.setQuantite(qte);
        		}else {
        			tmpcommande.getItems().remove(singleProduct);
        		}
        	}
        }

    }
    
    
    
    public static void ajouteBoissonStock(Boisson b, int x) {	
		if (BoissonsAlaCarte.contains(b)) {
			int qte = b.getQuantite();
			qte += x;
			b.setQuantite(qte);
		}else {
			BoissonsAlaCarte.add(b);
			b.setQuantite(x);
		}
		System.out.println("Boisson ajoutée avec succès");
    }
    
    
    
    public static void ajouteCocktailStock(Cocktail c, int x) {	
		if (CocktailsAlaCarte.contains(c)) {
			int qte = c.getQuantite();
			qte += x;
			c.setQuantite(qte);
		}else {
			CocktailsAlaCarte.add(c);
			c.setQuantite(x);
		}
    }
    
    
    
    
    
    
    
    public static void stockToTab(ObservableList<Boisson> carte) {
		for (int i=0; i<BoissonsAlaCarte.size();i++) {
			carte.add(BoissonsAlaCarte.get(i));
		}
		for (int i=0; i<CocktailsAlaCarte.size();i++) {
			carte.add(CocktailsAlaCarte.get(i));
		}
    }
    
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		carteboissons.setCellValueFactory(new PropertyValueFactory<>("nomBoisson"));
		carteprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		cartestock.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		
		colboissons.setCellValueFactory(new PropertyValueFactory<>("nomBoisson"));
		colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		colqte.setCellValueFactory(new PropertyValueFactory<>("quantite"));	
		
		//initialisation stocks boissons
		ObservableList<Boisson> list = FXCollections.observableArrayList();
		stockToTab(list);
	   
	    cartebar.setItems(list);
	    
	}
    
	//verif si textfield vide
    public boolean emptyTextField (TextField tf) {
        if (tf.getText().isEmpty())
            return true;
        else
            return false;
    }
    

}
