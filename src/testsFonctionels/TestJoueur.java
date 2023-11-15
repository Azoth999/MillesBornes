package testsFonctionels;



import jeu.Joueur;

import java.util.ArrayList;
import java.util.List;

import cartes.Attaque;
import cartes.JeuDeCartes;
import cartes.Probleme.Type;
import cartes.Botte;
import cartes.DebutLimite;
import cartes.FinLimite;
import cartes.Parade;
import cartes.Borne;

public class TestJoueur {
	public static void main(String[] args) {
		Joueur joueur1 = new Joueur("Joueur 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		
		//Test get du Joueur
		System.out.println(joueur1.toString());
		System.out.println(joueur1.getBorneList());
		System.out.println(joueur1.getBotteList());
		System.out.println(joueur1.getLimitePile());
		
		//Test toString equals joueurs
		Joueur joueur2 = new Joueur("Joueur 2", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		Joueur joueur3 = new Joueur("Joueur 1", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		
		System.out.println("\nDeux joueurs dont les noms sont différents ne sont pas equals");
		System.out.println(joueur2.toString());
		System.out.println(joueur1.toString());
		System.out.println(joueur2.equals(joueur1));
		
		System.out.println("\nDeux joueurs de même nom sont equals");
		System.out.println(joueur3.toString());
		System.out.println(joueur1.toString());
		System.out.println(joueur3.equals(joueur1) + "\n");
		
		//Test prendreCarte
		JeuDeCartes jeuDeCartes = new JeuDeCartes();
		System.out.println(jeuDeCartes.getListeCartes());
		System.out.println(joueur1.prendreCarte(jeuDeCartes.getListeCartes()));
		System.out.println(joueur2.prendreCarte(jeuDeCartes.getListeCartes()));
		System.out.println(joueur3.prendreCarte(jeuDeCartes.getListeCartes()));
		System.out.println(joueur1.prendreCarte(jeuDeCartes.getListeCartes()));
		System.out.println(joueur2.prendreCarte(jeuDeCartes.getListeCartes()));
		System.out.println(joueur3.prendreCarte(jeuDeCartes.getListeCartes()));
		System.out.println(joueur1.prendreCarte(jeuDeCartes.getListeCartes()));
		System.out.println(joueur2.prendreCarte(jeuDeCartes.getListeCartes()));
		System.out.println(joueur3.prendreCarte(jeuDeCartes.getListeCartes()));
		
		
		//Test km
		Borne borne1 = new Borne(50,1);
		Borne borne2 = new Borne(100,1);
		Borne borne3 = new Borne(200,1);
		
		joueur1.jouerBorne(borne1);
		joueur1.jouerBorne(borne2);
		joueur1.jouerBorne(borne3);
		System.out.println(joueur1.getKm());
		
		Botte prioritaire = new Botte(Type.FEU,1);
		//défault
		System.out.println(joueur1.getlimite());
		//sommet = FinLimite
		Joueur joueurfinlimite = new Joueur("Joueur 4",List.of(new FinLimite(1)), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		System.out.println(joueurfinlimite.getlimite());
		//Proritaire
		Joueur joueurPrio = new Joueur("Joueur 4",List.of(new DebutLimite(1)), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		joueurPrio.getBotteList().add(prioritaire);
		System.out.println(joueurPrio.getlimite());
		//pile non vide
		Joueur joueurLimite = new Joueur("Joueur 4", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		joueurLimite.getLimitePile().add(new DebutLimite(1));
		System.out.println(joueurLimite.getlimite());
				
		//Test estBloque
		Joueur joueur4 = new Joueur("Joueur 4", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		Attaque feuRouge = new Attaque(Type.FEU,1);
		Botte prioritaire2 = new Botte(Type.FEU,1);
		Attaque accident = new Attaque(Type.ACCIDENT,1);
		Botte asDuVolant = new Botte(Type.ACCIDENT,1);
		Attaque panne = new Attaque(Type.ESSENCE,1);
		Botte citerne = new Botte(Type.ESSENCE,1);
		Parade feuVert = new Parade(Type.FEU,1);
		
		
		System.out.println("\nVérification est bloqué");
		joueur4.getBataillePile().add(feuRouge);
		System.out.println(joueur4.estBloque());
		joueur4.getBotteList().add(prioritaire2);
		System.out.println(joueur4.estBloque());
		joueur4.getBataillePile().add(accident);
		System.out.println(joueur4.estBloque());
		joueur4.getBotteList().add(asDuVolant);
		System.out.println(joueur4.estBloque());
		joueur4.getBataillePile().add(panne);
		System.out.println(joueur4.estBloque());
		joueur4.getBotteList().add(citerne);
		System.out.println(joueur4.estBloque());
		
		
		//Suppression des bottes
		joueur4.getBotteList().remove(prioritaire);
		joueur4.getBotteList().remove(asDuVolant);
		joueur4.getBotteList().remove(citerne);
		System.out.println(joueur4.estBloque());
		joueur4.getBataillePile().add(feuVert);
		System.out.println(joueur4.estBloque());
		
		
		//Tests TP4
		System.out.println("\nTP4");
		Joueur joueur5 = new Joueur("Joueur 5", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		System.out.println("appliquer "+ feuVert + ":" + feuVert.appliquer(joueur5));
		System.out.println("appliquer "+ feuVert + ":" + feuVert.appliquer(joueur5));
		System.out.println("appliquer "+ feuRouge + ":" + feuRouge.appliquer(joueur5));
		Borne borne100 = new Borne(100,1);
		System.out.println("appliquer "+ borne100 + ":" + borne100.appliquer(joueur5));
		//Prioritaire
		System.out.println("appliquer "+ prioritaire + ":" + prioritaire.appliquer(joueur5));
		System.out.println("appliquer "+ borne100 + ":" + borne100.appliquer(joueur5));
		System.out.println("appliquer "+ borne100 + ":" + borne100.appliquer(joueur5));
		//Debut Limite
		DebutLimite limite = new DebutLimite(1);
		System.out.println("appliquer "+ limite + ":" + limite.appliquer(joueur5));
		//Panne Essence
		System.out.println("appliquer "+ panne + ":" + panne.appliquer(joueur5));
		System.out.println("appliquer "+ borne100 + ":" + borne100.appliquer(joueur5));
		//Réparation
		Parade reparation = new Parade(Type.ACCIDENT,1);
		System.out.println("appliquer "+ reparation + ":" + reparation.appliquer(joueur5));
		//Essence
		Parade essence = new Parade(Type.ESSENCE,1);
		System.out.println("appliquer "+ essence + ":" + essence.appliquer(joueur5));

	}

}
