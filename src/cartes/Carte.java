package cartes;

import jeu.Joueur;

public abstract class Carte {
	private int nombre;
	
	protected Carte(int nbr) {
		this.setNombre(nbr);
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public boolean equals(Object carte) {
		if (carte instanceof Carte) {
			return  this.getClass()==carte.getClass();
		}
		return false;
	}
	
	public boolean appliquer(Joueur j) {
		return false;
	}


	
}
