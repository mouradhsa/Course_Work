package application;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class afficheCommandeController implements Initializable {
	
	private int numCommande;
	private static HashMap<Cocktail, Integer> totalCocktails = new HashMap<Cocktail, Integer>();
	private static HashMap<Boisson, Integer> totalBoissons = new HashMap<Boisson, Integer>();
	private static int stonks;
	private static int tmpstonks = 0;
	
	static List<Cocktail> CocktailsAlaCarte = new ArrayList<Cocktail>();

    @FXML
    private TableView<Boisson> addition;

    @FXML
    private Button validecommande;

    @FXML
    private Button btnretour;
    
    @FXML
    private Button btnretire;
    
    @FXML
    private Button btnvide;

    @FXML
    private Text prixtotal;
    
    @FXML
    private TableColumn<Boisson, String> colboissons;

    @FXML
    private TableColumn<Boisson, Integer> colqte;

    @FXML
    private TableColumn<Boisson, Integer> colprix;
    
    public static int getStonks() {
		return stonks;
	}
    
    public static void setStonks(int stonks) {
		afficheCommandeController.stonks = stonks;
	}

	public static int getTmpstonks() {
		return tmpstonks;
	}

	public static HashMap<Cocktail, Integer> getTotalCocktails() {
		return totalCocktails;
	}

	public void setTotalCocktails(HashMap<Cocktail, Integer> totalCocktails) {
		this.totalCocktails = totalCocktails;
	}

	public static HashMap<Boisson, Integer> getTotalBoissons() {
		return totalBoissons;
	}

	public void setTotalBoissons(HashMap<Boisson, Integer> totalBoissons) {
		this.totalBoissons = totalBoissons;
	}

	@FXML
    void btnRetour(ActionEvent event) throws Exception{
    	Parent root = FXMLLoader.load(getClass().getResource("menuClient.fxml"));
		Stage window = (Stage) btnretour.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
    }
    
    @FXML
    void btnRetire(ActionEvent event) {
    	ObservableList<Boisson> allProduct;
        allProduct=addition.getItems();
        if(addition.getSelectionModel().getSelectedItem()==null)
        {
            Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Veuillez choisir une boisson");
            a.setHeaderText(null);
            a.showAndWait();
        }
        else
        {
        	Boisson singleProduct=addition.getSelectionModel().getSelectedItem();
        	Alert alert= new Alert(AlertType.CONFIRMATION);
        	alert.setContentText("Retirer cette boisson ? \n"+singleProduct.getNomBoisson() + singleProduct.getQuantite());
        	alert.setHeaderText(null);
        	Optional<ButtonType> action = alert.showAndWait();
        	if (action.get()==ButtonType.OK) {
        		if (singleProduct.getQuantite() > 1) {
        			int qte = singleProduct.getQuantite() - 1;
            		singleProduct.setQuantite(qte);
            		choixBoissonController.maCommande.totalBoissons.remove(singleProduct, 1);
            		
        		}else {
        			System.out.println("enleve complet");
        			addition.getItems().remove(singleProduct);
        			choixBoissonController.maCommande.totalBoissons.remove(singleProduct);
        		}
        		prixtotal.setText(calculPrixCommande() +" €");
        	}
        }
    }
    
    @FXML
    void btnVide(ActionEvent event) throws Exception {
    	if ((totalBoissons.size() != 0)||(totalCocktails.size() != 0)){
    		Alert alert= new Alert(AlertType.CONFIRMATION);
        	alert.setContentText("Vider la commande ? \n");
        	alert.setHeaderText(null);
        	Optional<ButtonType> action = alert.showAndWait();
        	if (action.get()==ButtonType.OK) {
        		choixBoissonController.maCommande.viderCommande();
            	Parent root = FXMLLoader.load(getClass().getResource("menuClient.fxml"));
        		Stage window = (Stage) btnretour.getScene().getWindow();
        		window.setScene(new Scene(root,600,400));
        	}	
    	}else {
    		Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Commande vide !");
            a.setHeaderText(null);
            a.showAndWait();
    	}
    	
    }

    @FXML
    void valideCommande(ActionEvent event) throws Exception{
    	Alert alert= new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Valider la commande ? \n");
    	alert.setHeaderText(null);
    	Optional<ButtonType> action = alert.showAndWait();
    	if (action.get()==ButtonType.OK) {
    		//boisson
    		for (Boisson b : this.totalBoissons.keySet()) {
    			for (int i=0;i<choixBoissonController.getBoissonsAlaCarte().size();i++) {
    				if ((choixBoissonController.getBoissonsAlaCarte().get(i).getNomBoisson() == b.getNomBoisson())) {
    					Boisson tmp = choixBoissonController.getBoissonsAlaCarte().get(i);
    					int qte = tmp.getQuantite();
    					tmp.setQuantite(qte-1);
    				}
    			}
    			//cocktail
    			for (int i=0;i<choixBoissonController.getCocktailsAlaCarte().size();i++) {
    				if ((choixBoissonController.getCocktailsAlaCarte().get(i).getNomBoisson() == b.getNomBoisson())) {
    					Cocktail tmp = choixBoissonController.getCocktailsAlaCarte().get(i);
    					//maj stock contenu cocktail
    					for (Boisson contenu : tmp.getListeBoissons().keySet()) {
    						if (contenu.getQuantite()-1 < 1) {
    							Alert a= new Alert(AlertType.WARNING);
    				            a.setContentText("L'un de vos cocktail ne peut être commandé (stock) !");
    				            a.setHeaderText(null);
    				            a.showAndWait();
    						}else {
    							int qteboisson = contenu.getQuantite() - 1;
        						contenu.setQuantite(qteboisson);
        						int qte = menuCocktailController.calculQteCocktail(tmp.getListeBoissons());
            					tmp.setQuantite(qte);
    						}
    					}
    				}
    			}
    		}    		
    		tmpstonks = calculPrixCommande();
    		stonks += tmpstonks;
    		choixBoissonController.maCommande.viderCommande();
    		Parent root = FXMLLoader.load(getClass().getResource("menuClient.fxml"));
    		Stage window = (Stage) btnretour.getScene().getWindow();
    		window.setScene(new Scene(root,600,400));
    	}else {
    		Parent root = FXMLLoader.load(getClass().getResource("menuClient.fxml"));
    		Stage window = (Stage) btnretour.getScene().getWindow();
    		window.setScene(new Scene(root,600,400));
    	}
 
    }
	

	public void ajouteBoissonCommande(Boisson b, int x) {
    	
    	//si déjà en stock, augmenter qte
        if (this.totalBoissons.containsKey(b)) {
        	int qte = this.totalBoissons.get(b);
        	qte += x;
        	this.totalBoissons.put(b, qte);
        } else {
        	this.totalBoissons.put(b, x);
        }  
    }  
	
	public static void supprimeBoissonStock(Boisson b, int x) {
        int qte = b.getQuantite();
        if (qte == 0) {
        	System.out.print("Le stock est vide !");
        }else {
        	qte -= x;
        	if (qte<0) {
        		b.setQuantite(0);
        	}else {
        		b.setQuantite(qte);
        	}
        	System.out.println("Boisson retirée avec succès");
        }
    }
    
    public void ajouteCocktailCommande(Cocktail c, int x) {    	
    	//si déjà en stock, augmenter qte
        if (this.totalCocktails.containsKey(c)) {
        	int qte = this.totalCocktails.get(c);
        	qte += x;
        	this.totalCocktails.put(c, qte);
        } else {
        	this.totalCocktails.put(c, x);
        }  
    }   
    
    public static void supprimeCocktailStock(Cocktail c, int x) {
        int qte = c.getQuantite();
        if (qte == 0) {
        	System.out.print("Le stock est vide !\n");
        }else {
        	qte -= x;
        	if (qte<0) {
        		c.setQuantite(0);
        	}else {
        		c.setQuantite(qte);
        	}
        }
    }
    
    public void viderCommande() {
    	this.totalBoissons.clear();
    	this.totalCocktails.clear();
    }
    
    public int calculPrixCocktails() {
    	int prix = 0;
        for (Cocktail c : this.totalCocktails.keySet())
        	prix += c.calculPrix() * this.totalCocktails.get(c); //cocktail * qte
        return prix;
    }

    public int calculPrixBoissons() {
    	int prix = 0;
        for (Boisson b : this.totalBoissons.keySet())
        	prix += b.getPrix() * this.totalBoissons.get(b); //boisson * qte
        return prix;
    }
    
    public int calculPrixCommande() {
    	int prixA = calculPrixCocktails();
    	int prixB = calculPrixBoissons();
    	int prixTotal = prixA + prixB;
    	return prixTotal;
    }
    
    
    //envoyer contenu hashmap dans une liste
    public ObservableList<Boisson> convert(HashMap<Boisson, Integer> test){
    	ObservableList<Boisson> ticket = FXCollections.observableArrayList();
    	for (Boisson b : totalBoissons.keySet()) {
    		ticket.add(b);
    	}
    	return ticket;
    }
    
    public ObservableList<Boisson> convertC(HashMap<Cocktail, Integer> test){
    	ObservableList<Boisson> ticket = FXCollections.observableArrayList();
    	for (Cocktail c : totalCocktails.keySet()) {
    		ticket.add(c);
    	}
    	return ticket;
    }
    
    
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		afficheCommandeController maCommande = new afficheCommandeController();
		maCommande = choixBoissonController.getMaCommande();
		
		CocktailsAlaCarte = choixBoissonController.getCocktailsAlaCarte();
		
		totalBoissons = maCommande.getTotalBoissons();
		totalCocktails = maCommande.getTotalCocktails();
		
		colboissons.setCellValueFactory(new PropertyValueFactory<Boisson, String>("nomBoisson"));
		colprix.setCellValueFactory(new PropertyValueFactory<Boisson, Integer>("prix"));
		colqte.setCellValueFactory(new PropertyValueFactory<Boisson, Integer>("quantite"));
		
		ObservableList<Boisson> listAddition = FXCollections.observableArrayList();
		ObservableList<Boisson> listTmp = FXCollections.observableArrayList();
		listAddition = convert(totalBoissons);
		listTmp = convertC(totalCocktails);
		listAddition.addAll(listTmp);
		addition.setItems(listAddition);
		
		prixtotal.setText(calculPrixCommande() +" €");
	}
    

}