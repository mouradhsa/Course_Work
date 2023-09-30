package application;

import java.io.Serializable;
import java.util.*;

public class Boisson implements Serializable{
	private String nomBoisson;	//nom de la boisson
    private int contenance;	//contenance de la boisson
    private int prix;	//prix de la boisson
    private int quantite;	//qte de la boisson
    
	
	public Boisson(String nomBoisson, int contenance, int prix, int quantite) {
		this.nomBoisson = nomBoisson;
		this.contenance = contenance;
		this.prix = prix;
		this.quantite = quantite;
    }


	@Override
	public String toString() {
		return "Boisson [nomBoisson=" + nomBoisson + ", contenance=" + contenance + ", prix=" + prix + ", quantite="
				+ quantite + "]";
	}

	public String getNomBoisson() {
		return nomBoisson;
	}


	public void setNomBoisson(String nomBoisson) {
		this.nomBoisson = nomBoisson;
	}


	public int getContenance() {
		return contenance;
	}


	public void setContenance(int contenance) {
		this.contenance = contenance;
	}


	public int getPrix() {
		return prix;
	}


	public void setPrix(int prix) {
		this.prix = prix;
	}


	public int getQuantite() {
		return quantite;
	}


	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}



}
