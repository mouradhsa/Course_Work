package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class menuSauvegardeController implements Initializable{
	
	

    @FXML
    private Button btnsave;

    @FXML
    private Button btnload;

    @FXML
    private Button btnretour;

    @FXML
    void btnLoad(ActionEvent event) {
    	try {
        	FileInputStream fis = new FileInputStream("data.dat");
            ObjectInputStream in = new ObjectInputStream(fis);
            for (int i=0; i<BoissonsAlaCarte.size();i++) {
            	Boisson b = (Boisson)in.readObject();
            	int qte = b.getQuantite();
            	BoissonsAlaCarte.get(i).setQuantite(qte);
            }
            for (int i=0; i<CocktailsAlaCarte.size();i++) {
            	Cocktail c = (Cocktail)in.readObject();
            	int qte = c.getQuantite();
            	CocktailsAlaCarte.get(i).setQuantite(qte);
            }
            int ca = (int)in.readObject();
            afficheCommandeController.setStonks(ca);
            in.close();
            Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Données chargées !");
            a.setHeaderText(null);
            a.showAndWait();
        }catch(Exception e) {
        	Alert a= new Alert(AlertType.WARNING);
            a.setContentText("impossible de charger les données");
            a.setHeaderText(null);
            a.showAndWait();
        	System.out.println("impossible de charger les données");
        	}
    }

    @FXML
    void btnRetour(ActionEvent event) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("Bar.fxml"));
		Stage window = (Stage) btnretour.getScene().getWindow();
		window.setScene(new Scene(root,600,400));
    }

    @FXML
    void btnSave(ActionEvent event) {
    	try {
        	FileOutputStream fos = new FileOutputStream("data.dat");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            for (int i=0; i<BoissonsAlaCarte.size();i++) {
            	out.writeObject(BoissonsAlaCarte.get(i));//mettre liste boissons
            }
            for (int i=0; i<CocktailsAlaCarte.size();i++) {
            	out.writeObject(CocktailsAlaCarte.get(i));//mettre liste cocktails
            }
            out.writeObject(ChiffreAffaires);
            out.close();
            Alert a= new Alert(AlertType.WARNING);
            a.setContentText("Données sauvegardées !");
            a.setHeaderText(null);
            a.showAndWait();
        }catch(Exception e) {
        	Alert a= new Alert(AlertType.WARNING);
            a.setContentText("impossible de sauvegarder les données");
            a.setHeaderText(null);
            a.showAndWait();
        	System.out.println("impossible de sauvegarder les données");
        	}
        }
    
    
 
    List<Boisson> BoissonsAlaCarte = new ArrayList<Boisson>();	
	List<Cocktail> CocktailsAlaCarte = new ArrayList<Cocktail>();	
	int ChiffreAffaires;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		BoissonsAlaCarte = choixBoissonController.getBoissonsAlaCarte();
		CocktailsAlaCarte = choixBoissonController.getCocktailsAlaCarte();
		ChiffreAffaires = afficheCommandeController.getStonks();
	}
    

}
