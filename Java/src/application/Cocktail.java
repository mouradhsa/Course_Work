package application;

import java.util.*;
import java.math.*;

public class Cocktail extends Boisson {
	private String nomCocktail;
	private HashMap<Boisson, Integer> listeBoissons;
    
    public Cocktail(String nom, int contenance, int prix, int quantite, HashMap<Boisson, Integer> listeBoissons) {
    	super(nom, contenance, prix, quantite);
    	this.listeBoissons = listeBoissons;
    }

    
  //Calcule le prix total du cocktail
    
    public int calculPrix() {
        int prix = 0;
        for (Boisson b : this.listeBoissons.keySet())
        	prix += b.getPrix();
        prix *= 1.1; //prix majoré de 10 %
        prix = Math.round(prix*100) / 100;
        return prix;
    }
   
    //Calcule le degré d'alcool total du cocktail
    
    public int calculDegreAlcool() {
        int degreAlcool = 0;
        
        for (Boisson b : this.listeBoissons.keySet()) {
        	//si alcoolisee
        	if (b.getClass().getSimpleName().equals("BoissonAlcoolisee")) {
        		BoissonAlcoolisee ba = (BoissonAlcoolisee) b;
        		degreAlcool += ba.getDegreAlcool() * this.listeBoissons.get(ba);
        	}
        }
        
        return degreAlcool;
    }
    

  //Calcule le degré de sucre total du cocktail
    
    public int calculDegreSucre() {
        int degreSucre = 0;
        
        for (Boisson b : this.listeBoissons.keySet()) {
        	//si non alcoolisee
        	if (b.getClass().getSimpleName().equals("BoissonNonAlcoolisee")) {
        		BoissonNonAlcoolisee sucre = (BoissonNonAlcoolisee) b;
        		degreSucre += sucre.getDegreSucre() * this.listeBoissons.get(sucre);
        	}
        }
        return degreSucre;
    }


    
    

	@Override
	public String toString() {
		return "Cocktail [nomCocktail=" + nomCocktail + ", listeBoissons=" + listeBoissons + "]";
	}


	

	public String getNomCocktail() {
		return nomCocktail;
	}
	
	public void setNomCocktail(String nomCocktail) {
		this.nomCocktail = nomCocktail;
	}

	public HashMap<Boisson, Integer> getListeBoissons() {
		return listeBoissons;
	}

	public void setListeBoissons(HashMap<Boisson, Integer> listeBoissons) {
		this.listeBoissons = listeBoissons;
	}

 
}
