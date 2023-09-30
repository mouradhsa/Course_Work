package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class stockBarController implements Initializable{
	
	@FXML
    private TableView<Boisson> stocks;

    @FXML
    private TableColumn<Boisson, String> colnom;

    @FXML
    private TableColumn<Boisson, Integer> colprix;

    @FXML
    private TableColumn<Boisson, Integer> colqte;

    @FXML
    private Button btnajout;

    @FXML
    private Button btnretirer;

    @FXML
    private Button btnretour;

    @FXML
    private TextField tfqte;

    @FXML
    void btnAjout(ActionEvent event) { 	
    	if(stocks.getSelectionModel().getSelectedItem()==null) {
            Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Veuillez choisir une boisson");
            a.setHeaderText(null);
            a.showAndWait();
        }else if (emptyTextField(tfqte)) {
    		Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Veuillez renseigner une quantité à ajouter");
            a.setHeaderText(null);
            a.showAndWait();
        }if ((stocks.getSelectionModel().getSelectedItem()!=null) && (!emptyTextField(tfqte))) {
    		int qte = Integer.parseInt(tfqte.getText());
    		Boisson singleProduct=stocks.getSelectionModel().getSelectedItem();
    		if (qte > 0) {
    			int x = singleProduct.getQuantite();
    			singleProduct.setQuantite(x + qte);
    			stocks.getItems().remove(singleProduct);
    			stocks.getItems().add(singleProduct);
    			for (int i=0; i<CocktailsAlaCarte.size();i++) {
    				int qte2 = menuCocktailController.calculQteCocktail(CocktailsAlaCarte.get(i).getListeBoissons());
    				CocktailsAlaCarte.get(i).setQuantite(qte2);
    			}
    		}else {
    			Alert a= new Alert(AlertType.WARNING);
                a.setContentText("Veuillez saisir une quantité positive");
                a.setHeaderText(null);
                a.showAndWait();
    		}
    	}
    }

    @FXML
    void btnRetirer(ActionEvent event) {
    	if(stocks.getSelectionModel().getSelectedItem()==null) {
            Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Veuillez choisir une boisson");
            a.setHeaderText(null);
            a.showAndWait();
        }else if (emptyTextField(tfqte)) {
    		Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Veuillez renseigner une quantité à retirer");
            a.setHeaderText(null);
            a.showAndWait();
        }if ((stocks.getSelectionModel().getSelectedItem()!=null) && (!emptyTextField(tfqte))) {
    		int qte = Integer.parseInt(tfqte.getText());
    		Boisson singleProduct=stocks.getSelectionModel().getSelectedItem();
    		int x = singleProduct.getQuantite();
    		if (qte > 0) {
    			if (qte<=x) {
    				singleProduct.setQuantite(x - qte);
        			stocks.getItems().remove(singleProduct);
        			stocks.getItems().add(singleProduct);
    			}else {
    				Alert a= new Alert(AlertType.WARNING);
                    a.setContentText("Vous ne pouvez pas retirer plus que la quantité présente en stock !");
                    a.setHeaderText(null);
                    a.showAndWait();
    			}
    		}else {
    			Alert a= new Alert(AlertType.WARNING);
                a.setContentText("Veuillez saisir une quantité positive");
                a.setHeaderText(null);
                a.showAndWait();
    		}
    	}
    }

    @FXML
    void btnRetour(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("menuBarman.fxml"));
		Stage window = (Stage) btnretour.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
    }

    static List<Boisson> BoissonsAlaCarte = new ArrayList<Boisson>();	
	static List<Cocktail> CocktailsAlaCarte = new ArrayList<Cocktail>();
	
	public static void stockToTab(ObservableList<Boisson> carte) {
		for (int i=0; i<BoissonsAlaCarte.size();i++) {
			carte.add(BoissonsAlaCarte.get(i));
		}
    }
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		BoissonsAlaCarte = choixBoissonController.getBoissonsAlaCarte();
		CocktailsAlaCarte = choixBoissonController.getCocktailsAlaCarte();
		
		colnom.setCellValueFactory(new PropertyValueFactory<>("nomBoisson"));
		colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		colqte.setCellValueFactory(new PropertyValueFactory<>("quantite"));	
		
		//initialisation stocks boissons
		ObservableList<Boisson> list = FXCollections.observableArrayList();
		stockToTab(list);
	   
	    stocks.setItems(list);
	}
	
	//verif si textfield vide
    public boolean emptyTextField (TextField tf) {
        if (tf.getText().isEmpty())
            return true;
        else
            return false;
    }

}
