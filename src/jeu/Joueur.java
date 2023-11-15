package jeu;

import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;

import cartes.*;

public class Joueur {
	private String nom;
	private int km;
	private MainAsList main;	
	private List<Borne> borneList;
	private List<Botte> botteList;
	private Stack<Limite> limitPile;
	private Stack<Bataille> bataillePile;
	private Jeu jeu;
	
	public Joueur(String nom, List<Carte> pileLimitesVitesse, List<Carte> pileBataille, List<Carte> collectionBornes,
			List<Carte> ensembleBottes) {
		this.nom = nom;
		this.km = 0;
		this.main =  new MainAsList();
		this.borneList = new ArrayList<>();
		this.botteList = new ArrayList<>();
		this.limitPile = new Stack<>();
		this.bataillePile = new Stack<>();
	}
	
	public Jeu getJeu() {
		return jeu;
	}
	
	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}
	
	public String toString() {
		return this.nom;
	}
	
	public List<Borne> getBorneList(){
		return this.borneList;
	}
	
	public List<Botte> getBotteList(){
		return this.botteList;
	}
	
	public Stack<Limite> getLimitePile(){
		return this.limitPile;
	}
	
	public Stack<Bataille> getBataillePile(){
		return this.bataillePile;
	}
	
	
	public boolean equals(Joueur joueur) {
		return this.toString().equals(joueur.toString());				
	}
	
	public MainAsList getMain() {
		return this.main;
	}
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public Carte prendreCarte(List<Carte> sabot) {
		if (!sabot.isEmpty()) {
			Carte carte = sabot.remove(0);
			donner(carte);
			return carte;
		} else {
			return null;
		}
	}
	
	public int getKm() {
		int kmBorne = 0;
		for(Borne b:borneList) {
			kmBorne += b.getKm();
		}
		return kmBorne;
	}
	
	public void ajouterKM(int kilom) {
        km += kilom;
    }
	
	public void jouerBorne(Borne borne) {
        ajouterKM(borne.getKm());
    }
	
	public int getlimite() {
		if(limitPile.isEmpty()) {
			return 200;
		}
		Carte sommetPile = limitPile.get(limitPile.size() - 1);
		if(sommetPile instanceof FinLimite) {
			return 200;
		}
		if (botteList.stream().anyMatch(carte -> carte instanceof Botte && ((Botte) carte).getType() == Probleme.Type.FEU)) {
	        return 200;
	    }
		return 50;
	}
	

	public boolean containTypeBotte() {
		Bataille bataille = bataillePile.peek();
		for(Botte b:botteList) {
			if(b.getType().equals(bataille.getType())){
				return true;
			}
		}
		return false;
	}
	
	public boolean estBloque() {
		boolean prioritaire = false;
		for(Botte b:botteList) {
			if (b.toString().equals("Véhicule prioritaire")) {
				prioritaire = true;
			}
		}
		return !((bataillePile.isEmpty() && prioritaire) ||
				(bataillePile.peek().toString().equals("Feu vert")) ||
				(bataillePile.peek() instanceof Parade && prioritaire) ||
				(bataillePile.peek().toString().equals("Feu rouge") && prioritaire) ||
				(containTypeBotte() && prioritaire));
	}
	
	
	public Set<Coup> coupsPossibles(List<Joueur> participants){
		Set<Coup> coupsPossibles = new HashSet<>();
		
		for(Joueur cible : participants) {
			for(Carte carte : this.getMain().getCarteEnMain()) {
				Coup coup = new Coup(cible,carte);
				if(coup.estValide(this)) {
					coupsPossibles.add(coup);
				}
			}
		}
		return coupsPossibles;
	}
	
	public Set<Coup> coupsParDefault(List<Joueur> participants) {
	    Set<Coup> coups = new HashSet<>();
	    
	    for(Carte carte : main.getCarteEnMain()) {
	    	coups.add(new Coup(null,carte));
	    }
	    return coups;
	}

}
