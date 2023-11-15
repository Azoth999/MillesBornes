package jeu;

public class Jeu {

	private Joueur[] listeJoueurs;
	private Sabot sabot;
	
public Jeu() {
	// TODO Auto-generated constructor stub
}

public Joueur[] getListeJoueurs() {
	return listeJoueurs;
}

public Sabot getSabot() {
	return sabot;
}

public void inscrire(Joueur joueur) {
	joueur.setJeu(this);
}
	
}
