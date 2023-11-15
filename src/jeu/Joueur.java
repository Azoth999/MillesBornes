package jeu;

import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;

import cartes.Bataille;
import cartes.Borne;
import cartes.Botte;
import cartes.Carte;
import cartes.FinLimite;
import cartes.Limite;
import cartes.Parade;

public class Joueur {
	private String nom;
	private int km;
	private MainAsList main;	
	private List<Borne> borneList;
	private List<Botte> botteList;
	private Stack<Limite> limitPile;
	private Stack<Bataille> bataillePile;
	private Jeu jeu;
	
	public Joueur(String nom) {
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
	
	public void donner(Carte carte) {
		main.prendre(carte);
	}
	
	public MainAsList getMain() {
		return this.main;
	}
	
	//Bizzare qu'on nous demande de faire sur une List<Carte> et pas sabot
	public Carte prendreCarte(List<Carte> sabot) {
		if (sabot.isEmpty()) {
			return null;
		}
		Carte carte = sabot.remove(0);
		if(carte instanceof Borne) {
			borneList.add((Borne) carte);
		}
		donner(carte);
		return carte;
	}
	
	public int getKm() {
		return km;
	}
	
	public void ajouterBorne(int km) {
		this.km += km;
	}
	
	public boolean verifKm() {
		int kmBorne = 0;
		for(Borne b:borneList) {
			kmBorne += b.getKm();
		}
		return this.getKm() == kmBorne;
	}
	
	public int getLimite() {
		if( limitPile.isEmpty() || limitPile.peek() instanceof FinLimite) {
			return 200;
		}
		//Il doit y avoir mieux
		for(Botte b:botteList) {
			if (b.toString().equals("Véhicule prioritaire")) {
				return 200;
			}
		}
		return 50;
	}
	

	//Je sais pas si c'est bien opti
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
		//Pas d'opti
		return !((bataillePile.isEmpty() && prioritaire) ||
				(bataillePile.peek().toString().equals("Feu vert")) ||
				(bataillePile.peek() instanceof Parade && prioritaire) ||
				(bataillePile.peek().toString().equals("Feu rouge") && prioritaire) ||
				(containTypeBotte() && prioritaire));
	}
	
	
	//J'ai modifié pour que la classe prenne en parametre une classe MainAsList et non Main
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
