package cartes;

import cartes.Probleme.Type;
import jeu.Joueur;

public class FinLimite extends Limite {

	public FinLimite(int nbr) {
		super(nbr);
	}
	
	//Rajout des méthodes toString()
	public String toString() {
		return "Fin limite";
	}
}
