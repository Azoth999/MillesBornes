package jeu;


import java.util.Objects;

import cartes.Attaque;
import cartes.Carte;
import cartes.DebutLimite;

public class Coup {
	private Joueur joueur;
	private Carte carte;
	
	public Coup(Joueur joueur, Carte carte) {
		this.joueur=joueur;
		this.carte=carte;
	}
	
	public Joueur getJoueur() {
		return this.joueur;
	}
	
	public Carte getCarte() {
		return this.carte;
	}
	
	public boolean estValide(Joueur j) {
		if (carte instanceof Attaque || carte instanceof DebutLimite) {
			return !j.equals(joueur);
		}
		return j.equals(joueur);
	}
	
	public boolean jouer(Joueur j) {
	}

	@Override
	public boolean equals(Object c) {
		if (c instanceof Coup) {
			return c.hashCode() == this.hashCode();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(joueur,carte);
	}
}
