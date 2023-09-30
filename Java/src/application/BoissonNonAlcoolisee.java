package application;

public class BoissonNonAlcoolisee extends Boisson {
	private int degreSucre;
	   
    public BoissonNonAlcoolisee(String nom, int contenance, int prix, int quantite, int degreSucre) {
    	super(nom, contenance, prix, quantite);
    	this.degreSucre = degreSucre;
    }

	public int getDegreSucre() {
		return degreSucre;
	}

}