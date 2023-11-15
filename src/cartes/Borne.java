package cartes;

import jeu.Joueur;

public class Borne extends Carte {
	private int km;

	public Borne(int km,int nbr) {
		super(nbr);
		this.setKm(km);
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}
	
	@Override
	public boolean equals(Object carte) {
		if(super.equals(carte) && carte instanceof Borne) {
			return this.km == ((Borne) carte).km;
		}
		return false;
	}
	
	//Rajout de la méthode toString()
	public String toString() {
		return "Borne " + this.km + " km";
	}

}
