package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class menuCocktailController  implements Initializable{
	
	List<Boisson> stockboissons = new ArrayList<Boisson>();
	HashMap<Boisson, Integer> newCocktail = new HashMap<Boisson, Integer>();

    @FXML
    private TableView<Boisson> carteboissons;

    @FXML
    private TableColumn<Boisson, String> colnom;

    @FXML
    private TableColumn<Boisson, Integer> colprix;
    
    @FXML
    private TableColumn<BoissonNonAlcoolisee, Integer> colsucre;
    
    @FXML
    private TableColumn<BoissonAlcoolisee, Integer> colalcool;

    @FXML
    private TableView<Boisson> tmpcocktail;

    @FXML
    private TableColumn<Boisson, String> tmpnom;

    @FXML
    private TableColumn<Boisson, Integer> tmpprix;

    @FXML
    private TextField tfnomcocktail;
    
    @FXML
    private Text txtprix;
    
    @FXML
    private Text txtsucre;
    
    @FXML
    private Text txtalcool;

    @FXML
    private Button btnajout;

    @FXML
    private Button btnretrait;

    @FXML
    private Button btncompo;

    @FXML
    private Button btnretour;

    @FXML
    void btnAjout(ActionEvent event) {
    	ObservableList<Boisson> allProduct;
    	allProduct=carteboissons.getItems();
    	if(carteboissons.getSelectionModel().getSelectedItem()==null)
        {
            Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Veuillez choisir une boisson");
            a.setHeaderText(null);
            a.showAndWait();
        }else {
        	Boisson singleProduct=carteboissons.getSelectionModel().getSelectedItem();
        	if (tmpcocktail.getItems().contains(singleProduct)) {
        		Alert a= new Alert(AlertType.WARNING);
                a.setContentText("Boisson déjà ajoutée !");
                a.setHeaderText(null);
                a.showAndWait();
        	}else {
        		tmpcocktail.getItems().add(singleProduct);
        		newCocktail.put(singleProduct, 1);
        		txtprix.setText(calculPrix() +" €");
        		txtsucre.setText(calculDegreSucre() +" %");
        		txtalcool.setText(calculDegreAlcool() +" %");
        	}
            }
    }
    
    
    @FXML
    void btnCompo(ActionEvent event) {
    	int nb = newCocktail.size();
    	if (emptyTextField(tfnomcocktail)) {
    		Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Veuillez renseigner un nom pour votre cocktail");
            a.setHeaderText(null);
            a.showAndWait();
    	} else if (nb < 2) {
    		Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Il faut au minimum 2 boissons pour composer un cocktail");
            a.setHeaderText(null);
            a.showAndWait();
    	}else {	
    		boolean contient = false;
    		Cocktail valideCocktail = new Cocktail(tfnomcocktail.getText(), 50, calculPrix(), calculQteCocktail(newCocktail), newCocktail);
    		valideCocktail.setNomCocktail(tfnomcocktail.getText());
    		valideCocktail.calculDegreAlcool();
    		
    		for (int i=0;i<CocktailsAlaCarte.size();i++) {
    			if (CocktailsAlaCarte.get(i).getListeBoissons() == valideCocktail.getListeBoissons()) {
    			contient = true;	
    			System.out.println("recette deja existante");
    			}
    		}
    		if (contient == true) {
    			int qte = valideCocktail.getQuantite();
    			int newqte = qte + 1; 
    			valideCocktail.setQuantite(newqte);
    			Alert a= new Alert(AlertType.WARNING);
                a.setContentText("Quantité du cocktail augmentée !");
                a.setHeaderText(null);
                a.showAndWait();
    		}else {
    			ajouteCocktailStock(valideCocktail, calculQteCocktail(newCocktail));
       		 	Alert a= new Alert(AlertType.WARNING);
                a.setContentText("Cocktail composé avec succès !");
                a.setContentText("Cocktail composé avec succès !");
                a.setHeaderText(null);
                a.showAndWait();
    		}	
    	}
    }
    
    public static void ajouteCocktailStock(Cocktail c, int x) {	
		if (CocktailsAlaCarte.contains(c)) {
			int qte = c.getQuantite();
			qte += x;
			c.setQuantite(qte);
		}else {
			choixBoissonController.CocktailsAlaCarte.add(c);
			c.setQuantite(x);
		}	
    }
    
    
    public static int calculQteCocktail(HashMap<Boisson, Integer> recette) {
    	int qte = 500;
    	for (Boisson b : recette.keySet()) {
        	if (b.getQuantite()<qte) {
        		qte=b.getQuantite();
        	}
        }
    	return qte;
    }
    
    
    
    @FXML
    void btnRetour(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("menuClient.fxml"));
		Stage window = (Stage) btnretour.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
    }

    @FXML
    void btnRetrait(ActionEvent event) {
    	ObservableList<Boisson> allProduct;
        allProduct=tmpcocktail.getItems();
        if(tmpcocktail.getSelectionModel().getSelectedItem()==null)
        {
            Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Veuillez choisir une boisson");
            a.setHeaderText(null);
            a.showAndWait();
        }else {
        	Boisson singleProduct=tmpcocktail.getSelectionModel().getSelectedItem();
        	Alert alert= new Alert(AlertType.CONFIRMATION);
        	alert.setContentText("Retirer cette boisson ? \n"+singleProduct.getNomBoisson());
        	alert.setHeaderText(null);
        	Optional<ButtonType> action = alert.showAndWait();
        	if (action.get()==ButtonType.OK) {
        		tmpcocktail.getItems().remove(singleProduct);
        		newCocktail.remove(singleProduct);
        	}
        	txtprix.setText(calculPrix() +" €");
        }
    }
 
    //verif si textfield vide
    public boolean emptyTextField (TextField tf) {
        if (tf.getText().isEmpty())
            return true;
        else
            return false;
    }
    
    //Calcule le prix total du cocktail
    
    public int calculPrix() {
        int prix = 0;
        for (Boisson b : this.newCocktail.keySet())
        	prix += b.getPrix();
        prix *= 1.1; //prix majoré de 10 %
        prix = Math.round(prix*100) / 100;
        return prix;
    }
    
    
    //Calcule le degré d'alcool total du cocktail
    
    public int calculDegreAlcool() {
        int degreAlcool = 0;
        
        for (Boisson b : this.newCocktail.keySet()) {
        	//si alcoolisee
        	if (b.getClass().getSimpleName().equals("BoissonAlcoolisee")) {
        		BoissonAlcoolisee ba = (BoissonAlcoolisee) b;
        		degreAlcool += ba.getDegreAlcool() * this.newCocktail.get(ba);
        	}
        }
        
        return degreAlcool;
    }
    

    //Calcule le degré de sucre total du cocktail
    
    public int calculDegreSucre() {
        int degreSucre = 0;
        
        for (Boisson b : this.newCocktail.keySet()) {
        	//si non alcoolisee
        	if (b.getClass().getSimpleName().equals("BoissonNonAlcoolisee")) {
        		BoissonNonAlcoolisee sucre = (BoissonNonAlcoolisee) b;
        		degreSucre += sucre.getDegreSucre() * this.newCocktail.get(sucre);
        	}
        }
        return degreSucre;
    }
    
    
  //mettre stockboissons dans listboissons
    public ObservableList<Boisson> convert(){
    	ObservableList<Boisson> liste = FXCollections.observableArrayList();
    	for (int i=0;i<stockboissons.size();i++) {
    		liste.add(stockboissons.get(i));
    	}
    	return liste;
    }
   

    static List<Cocktail> CocktailsAlaCarte = new ArrayList<Cocktail>();
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		CocktailsAlaCarte = choixBoissonController.getCocktailsAlaCarte();
		System.out.println(CocktailsAlaCarte);
		
		txtprix.setText(calculPrix() +" €");
		
		stockboissons = choixBoissonController.getBoissonsAlaCarte();
		ObservableList<Boisson> dispoboissons = FXCollections.observableArrayList();
		dispoboissons = convert();
		
		colnom.setCellValueFactory(new PropertyValueFactory<Boisson, String>("nomBoisson"));
		colprix.setCellValueFactory(new PropertyValueFactory<Boisson, Integer>("prix"));
		colsucre.setCellValueFactory(new PropertyValueFactory<BoissonNonAlcoolisee, Integer>("degreSucre"));
		colalcool.setCellValueFactory(new PropertyValueFactory<BoissonAlcoolisee, Integer>("degreAlcool"));
		
		tmpnom.setCellValueFactory(new PropertyValueFactory<>("nomBoisson"));
		tmpprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		
		carteboissons.setItems(dispoboissons);
		
	}

}
