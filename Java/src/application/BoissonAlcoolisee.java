package application;

public class BoissonAlcoolisee extends Boisson {
	private int degreAlcool;
   
    public BoissonAlcoolisee(String nom, int contenance, int prix, int quantite, int degreAlcool) {
    	super(nom, contenance, prix, quantite);
    	this.degreAlcool = degreAlcool;
    }

	public int getDegreAlcool() {
		return degreAlcool;
	}


}
